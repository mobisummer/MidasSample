package com.ms.midassample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.midas.Midas;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public static final int CODE_FOR_LOGIN = 100;
    private TextView tvHint;
    private Button btnLogin;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tvHint = findViewById(R.id.tv_status);
        btnLogin = findViewById(R.id.btn_login);
        btnLogout = findViewById(R.id.btn_logout);

        btnLogin.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        updateLoginStatus();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_index:
                Midas.show();
                break;
            case R.id.nav_goods:
            case R.id.nav_order:
            case R.id.nav_custom_service:
            case R.id.nav_about:
            default:
                Toast.makeText(this, "only support Index now", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (SPUtils.isUserLogin()) {
                    UserManager.doLogout();
                } else {
                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), CODE_FOR_LOGIN);
                }
                break;
            case R.id.btn_logout:
                UserManager.doLogout();
                updateLoginStatus();
                break;
            default:
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateLoginStatus() {

        if (SPUtils.isUserLogin()) {
            btnLogin.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
            tvHint.setText(String.format("Welcome , %s", SPUtils.getUser()));
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
