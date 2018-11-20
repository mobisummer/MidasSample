package com.ms.midassample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;

public final class ContextManager {
    private static Context sAppContext;

    // app scope
    private static Activity sActivity;

    public static void init(@NonNull Context context) {
        sAppContext = context.getApplicationContext();

        if (sAppContext == null) {
            sAppContext = context;
        }
    }

    /**
     * Use this as possible instead of {@link #activity()}
     */
    public static Context appContext() {
        return sAppContext;
    }

    public static Resources resources() {
        return sAppContext.getResources();
    }

    public static AssetManager assetManager() {
        return sAppContext.getAssets();
    }

    public static ContentResolver contentResolver() {
        return sAppContext.getContentResolver();
    }

    public static Object systemService(String name) {
        if (!TextUtils.isEmpty(name)) {
            return sAppContext.getSystemService(name);
        }
        return null;
    }

    public static SharedPreferences sharedPreferences(String name, int mode) {
        return sAppContext.getSharedPreferences(name, mode);
    }

    public static PackageManager packageManager() {
        return sAppContext.getPackageManager();
    }

    public static ActivityManager activityManager() {
        return (ActivityManager) sAppContext.getSystemService(Context.ACTIVITY_SERVICE);
    }

    public static ApplicationInfo appInfo() {
        return sAppContext.getApplicationInfo();
    }

    public static Configuration config() {
        return resources().getConfiguration();
    }

    public static WindowManager windowManager() {
        return (WindowManager) sAppContext.getSystemService(Context.WINDOW_SERVICE);
    }

    public static ComponentName startService(Intent intent) {
        return sAppContext.startService(intent);
    }

    // activity scope

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkSelfPermission(String name) {
        return (sAppContext.checkSelfPermission(name) == PackageManager.PERMISSION_GRANTED);
    }

    public static void update(Activity activity) {
        sActivity = activity;
    }

    public static Activity activity() {
        return sActivity;
    }

    public static Window window() {
        return activity().getWindow();
    }

    public static ViewConfiguration viewConfig() {
        return ViewConfiguration.get(sActivity);
    }
}
