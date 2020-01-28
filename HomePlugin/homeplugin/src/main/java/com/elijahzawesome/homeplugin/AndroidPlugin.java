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

import s.Sign;

public class AndroidPlugin {

    private Context context;

    public AndroidPlugin(Context context)
    {
        this.context = context;
    }

    public void SetEnvironment() {
        // sign APK here and uninstall old env
        Sign.main(new String[]{"sign.jar", "/sdcard/test_unsigned.apk"});
    }
}
