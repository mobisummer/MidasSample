package com.ms.midassample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.midas.Midas;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int CODE_FOR_LOGIN = 100;
    Button btnLogin;
    Button btnLogout;
    Button btnOpenMidas;
    TextView tvHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btn_log_in);
        btnLogout = findViewById(R.id.btn_log_out);
        btnOpenMidas = findViewById(R.id.btn_open_midas);
        tvHint = findViewById(R.id.tv_hint);
        btnLogin.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnOpenMidas.setOnClickListener(this);
        updateLoginStatus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_log_in:
                if (SPUtils.isUserLogin()) {
                    UserManager.doLogout();
                } else {
                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), CODE_FOR_LOGIN);
                }
                break;
            case R.id.btn_log_out:
                UserManager.doLogout();
                updateLoginStatus();
                break;
            case R.id.btn_open_midas:
                Midas.show();
                break;
            default:
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateLoginStatus() {
        if (SPUtils.isUserLogin()) {
            btnLogin.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
            tvHint.setText(String.format("Hello,%s", SPUtils.getUser()));
        } else {
            btnLogin.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.GONE);

            tvHint.setText("Haven't Log In");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case CODE_FOR_LOGIN:
                if (resultCode == Activity.RESULT_OK) {
                    updateLoginStatus();
                } else {
                    Toast.makeText(this, "Sign in failed", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);

        }
    }
}
