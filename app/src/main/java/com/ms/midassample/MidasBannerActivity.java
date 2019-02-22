package com.ms.midassample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.ms.midas.CreativeListener;
import com.ms.midas.MidasBanner;

public class MidasBannerActivity extends AppCompatActivity {

    private static final String TAG = MidasBannerActivity.class.getName();
    private MidasBanner mMidasBanner;

    static void start(Activity activity) {
        Intent intent = new Intent(activity, MidasBannerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midas_banner);
        mMidasBanner = findViewById(R.id.banner);
        mMidasBanner.setCreativeListener(new CreativeListener() {
            @Override
            public void onCreativeError(View view, int errorCode, String errorMsg) {
                Log.d(TAG, String.format("Load error %s, code=%s", errorMsg, errorCode));
            }

            @Override
            public void onCreativeLoaded(View view) {
                Log.d(TAG, "MidasIcon Loaded");
            }

            @Override
            public void onCreativeShowed(View view) {
                Log.d(TAG, "MidasIcon showed");
            }

            @Override
            public void onCreativeClosed(View view) {
                Log.d(TAG, "MidasIcon closed");
            }

            @Override
            public void onCreativeClicked(View view) {
                Log.d(TAG, "MidasIcon clicked");
            }
        });
        mMidasBanner.load();

    }
}
