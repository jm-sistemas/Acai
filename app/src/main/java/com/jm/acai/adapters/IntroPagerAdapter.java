package com.jm.acai.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IntroPagerAdapter extends PagerAdapter {

    private Context context;
    private int[] screens;

    public IntroPagerAdapter(Context context, int[] screens) {
        this.context = context;
        this.screens = screens;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(screens[position], container, false);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return screens.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
