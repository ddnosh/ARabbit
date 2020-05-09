package com.androidwind.androidquick.sample.mvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidwind.androidquick.sample.R;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends BaseMVVMActivity<LoginViewModel> {

    Button mLoginButton;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void getBundleExtras(@NotNull Bundle extras) {

    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        super.initViewsAndEvents(savedInstanceState);
        mLoginButton = findViewById(R.id.login);
        mLoginButton.setOnClickListener(v -> {
            getViewModel().login().observe(this, this::loadSuccess);
        });
    }

    public void loadSuccess(Boolean aBoolean) {
        if (aBoolean != null && aBoolean) {
            Toast.makeText(this, aBoolean + "", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
