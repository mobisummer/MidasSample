package com.ms.midassample;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.midas.Midas;
import com.ms.midas.MidasListener;

/**
 * @author Toby
 * SplashActivty
 */
public class SplashActivity extends AppCompatActivity {
    public static final String APP_ID = "APPID|DB7C68CCFF744B6C9CB8B18F7EF8339C";
    private static String TAG = SplashActivity.class.getSimpleName();
    TextView tvSdkVersion;
    TextView tvHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        tvSdkVersion = findViewById(R.id.tv_sdk_version);
        tvSdkVersion.setText(String.format("SDK VERSION : %s", BuildConfig.SDK_VERSION_NAME));
        tvHint = findViewById(R.id.tv_hint);
        Midas.init(this, APP_ID, new MidasListener() {
            @Override
            public void onSucceed() {
                Toast.makeText(SplashActivity.this, "success", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onSucceed");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Midas.show();
                        SplashActivity.this.finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                }, 3000);
            }

            @Override
            public void onFailed(String s) {
                Log.e(TAG, "onFailed: " + s);
                tvHint.setText("Init Failed , due to " + s +" Please Restart App to Continue");
            }
        });

        if (!UserManager.isUserLogin()) {
            UserManager.doLogin("Mobisummer", "123456");
        }
    }
}
