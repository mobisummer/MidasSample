package com.ms.midassample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @author Toby
 * SplashActivty
 */
public class SplashActivity extends AppCompatActivity {
    TextView tvSdkVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        tvSdkVersion = findViewById(R.id.tv_sdk_version);
        tvSdkVersion.setText(String.format("SDK VERSION : %s", BuildConfig.SDK_VERSION_NAME));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 4000);
    }
}
