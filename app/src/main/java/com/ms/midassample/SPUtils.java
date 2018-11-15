package com.ms.midassample;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SPUtils {
    private static final String SP_NAME = "demo_preferences";
    private SharedPreferences sp;
    private static SPUtils instance;
    public static final String KEY_USER = "user";

    private SPUtils() {
        sp = ContextManager.appContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static SPUtils getInstance() {
        if (instance == null) {
            synchronized (SPUtils.class) {
                if (instance == null) {
                    instance = new SPUtils();
                }
            }
        }
        return instance;
    }

    public static void setUser(String user) {
        getInstance().sp.edit().putString(KEY_USER, user).apply();
    }

    public static String getUser() {
        return getInstance().sp.getString(KEY_USER, "");
    }

    public static void clearUser() {
        getInstance().sp.edit().remove(KEY_USER).apply();
    }

    public static boolean isUserLogin() {
        return !TextUtils.isEmpty(getUser());
    }

}
