package com.ms.midassample;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.ms.midas.Midas;
import com.ms.midas.MidasListener;

/**
 * @author Toby
 */
public class MyApp extends Application {
    public static final String APP_ID = "APPID|DB7C68CCFF744B6C9CB8B18F7EF8339C";
    private String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        ContextManager.init(this);
    }
}
