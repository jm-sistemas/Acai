package com.jm.acai.intro;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jm.acai.R;
import com.jm.acai.adapters.IntroPagerAdapter;
import com.jm.acai.utils.AutenticationUtils;

public class IntroActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener,ActivityCompat.OnRequestPermissionsResultCallback {

    private int[] screens;

    private Button skipButton,nextButton;

    private boolean shouldRequestPermissions;

    private ViewPager viewPager;
    private LinearLayout dotsLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        startObjects();
    }

    private void startObjects() {
        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.ll_dots);

        skipButton = findViewById(R.id.skipButton);
        skipButton.setOnClickListener(this);

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);

        if(Build.VERSION.SDK_INT >= 23) {
            shouldRequestPermissions = true;
            screens = new int[]{R.layout.slide1,R.layout.slide2, R.layout.slide4};
        }else
            screens = new int[]{R.layout.slide1,R.layout.slide2};

        IntroPagerAdapter introPagerAdapter = new IntroPagerAdapter(this,screens);
        viewPager.setAdapter(introPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        addBottomDots(0);
    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[screens.length];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(getResources().getColor(R.color.dot_active));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        addBottomDots(position);

        if (position == screens.length - 1) {
            nextButton.setText("Concluído");
            skipButton.setVisibility(View.GONE);
        } else {
            nextButton.setText("Próximo");
            skipButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.skipButton){
            if(shouldRequestPermissions)
                requestPermissions();
            else
                AutenticationUtils.finishIntro(this);

        }else if(view.getId() == R.id.nextButton) {
            int current = viewPager.getCurrentItem() + 1;
            if (current < screens.length) {
                viewPager.setCurrentItem(current);
            } else {
                if(shouldRequestPermissions)
                    requestPermissions();
                else
                    AutenticationUtils.finishIntro(this);
            }
        }
    }

    private void requestPermissions() {
        if(Build.VERSION.SDK_INT >= 23){
            String[] permissions = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CALL_PHONE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
            requestPermissions(permissions,0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutenticationUtils.finishIntro(this);
    }
}
