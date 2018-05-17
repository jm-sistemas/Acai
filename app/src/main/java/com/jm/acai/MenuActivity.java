package com.jm.acai;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jm.acai.adapters.MenuPagerAdapter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MenuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private static final int START_PAGE = 0;
    private static final int NORMAL_PAGE = 1;
    private static final int END_PAGE = 2;

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private ImageView previousImageView, nextImageView;
    String[] names = {"Açaí","Casquinha" ,"Milkshake", " nome aqui "};
    String[] imagesUrl = {"http://www.hummsorvetes.com.br/wp-content/uploads/2016/09/humm-sorvetes-acai-no-copo.jpg",
            "http://www.sergel.com.br/images/delicias/casquinha-gd.png",
            "https://www.browneyedbaker.com/wp-content/uploads/2012/01/white-russian-milkshake-1-550.jpg",
            "url img"};
    String[] prices = {"R$13,00","R$4,00","R$12,00","R$"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bindElements();
    }

    private void bindElements() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MenuPagerAdapter menuPagerAdapter = new MenuPagerAdapter(this,names,imagesUrl,prices);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(menuPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        previousImageView = findViewById(R.id.iv_previous);
        previousImageView.setOnClickListener(this);

        nextImageView = findViewById(R.id.iv_next);
        nextImageView.setOnClickListener(this);

        dotsLayout = findViewById(R.id.ll_dots);
        changePage(0);
    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[names.length];

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

    private void setPageVisibility(int type){
        switch (type){
            case START_PAGE:
                previousImageView.setVisibility(GONE);
                nextImageView.setVisibility(VISIBLE);
                break;
            case NORMAL_PAGE:
                previousImageView.setVisibility(VISIBLE);
                nextImageView.setVisibility(VISIBLE);
                break;
            case END_PAGE:
                previousImageView.setVisibility(VISIBLE);
                nextImageView.setVisibility(GONE);
                break;
        }
    }

    private void changePage(int position){
        addBottomDots(position);
        if(position == 0){
            setPageVisibility(START_PAGE);
        }else if (position > 0 && position < names.length - 1) {
            setPageVisibility(NORMAL_PAGE);
        }else if(position == names.length - 1) {
            setPageVisibility(END_PAGE);
        }
        viewPager.setCurrentItem(position);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changePage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int current = viewPager.getCurrentItem();
        switch (v.getId()) {
            case R.id.iv_previous:
                changePage(current - 1);
                break;
            case R.id.iv_next:
                changePage(current + 1);
                break;
        }
    }
}
