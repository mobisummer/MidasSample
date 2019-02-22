package com.ms.midassample;

import android.app.Application;
import android.widget.Toast;

import com.ms.midas.Midas;
import com.ms.midas.MidasListener;

/**
 * @author Toby
 */
public class MyApp extends Application {
    public static final String APP_ID = "MeetMyShop";
    private String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        ContextManager.init(this);
        Midas.init(this, APP_ID, new MidasListener() {
            @Override
            public void onSucceed() {
                Toast.makeText(MyApp.this, "init success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(String error) {
                Toast.makeText(MyApp.this, String.format("init failed, %s", error), Toast.LENGTH_LONG).show();
            }
        });
    }
}
