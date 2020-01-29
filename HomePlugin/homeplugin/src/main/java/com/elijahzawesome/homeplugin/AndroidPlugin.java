package com.elijahzawesome.homeplugin;

import java.io.*;
import java.util.*;
import android.os.*;
import android.content.*;
import android.net.Uri;
import android.content.pm.*;

import s.Sign;

public class AndroidPlugin {

    private Context context;
    private static String SDRootPath;

    public AndroidPlugin(Context context)
    {
        this.context = context;
        SDRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public void SetEnvironment(String EnvironmentFile, String PackageToReplace) {
        // sign APK here and uninstall old env
        Sign.main(new String[]{"sign.jar", SDRootPath + "/test_unsigned.apk"});
        // Need to eventually make it read the package name from the selected package in UnityPlayerPrefs
        UninstallAPK(PackageToReplace);
        InstallAPK(SDRootPath + "/test_unsigned.s.apk");
    }

    public String[] GetInstalledEnvironments() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = context.getPackageManager().queryIntentActivities( mainIntent, 0);
        List<String> finalList = new ArrayList<>();
        for(ResolveInfo info : pkgAppsList) {
            if(info.resolvePackageName.startsWith("com.oculus.environment")) {
                finalList.add(info.resolvePackageName);
            }
        }
        return finalList.toArray(new String[finalList.size()]);
    }

    private void UninstallAPK(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        context.startActivity(intent);
    }

    private void InstallAPK(String path) {
        Intent promptInstall = new Intent(Intent.ACTION_VIEW)
                .setDataAndType(Uri.parse("content://" + path),
                        "application/vnd.android.package-archive");
        context.startActivity(promptInstall);
    }

    private boolean IsPackageInstalled(String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            pm.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
