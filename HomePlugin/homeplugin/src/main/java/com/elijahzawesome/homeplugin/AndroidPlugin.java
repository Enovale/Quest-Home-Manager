package com.elijahzawesome.homeplugin;

import java.io.*;
import android.os.Environment;
import android.os.BatteryManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;
import android.content.ComponentName;
import android.util.Log;
import android.provider.Settings;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

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
