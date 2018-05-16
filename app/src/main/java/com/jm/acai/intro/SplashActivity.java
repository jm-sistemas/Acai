package com.jm.acai.intro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jm.acai.utils.AutenticationUtils;
import com.jm.acai.utils.OnConnectionCompletedListener;
import com.jm.acai.utils.ServerConnection;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AutenticationUtils.checkLogin(this, "");
    }
}
