package com.jm.acai.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jm.acai.R;

import java.util.ArrayList;
import java.util.List;


public class CustomGridAdapter extends BaseAdapter {

    private Context context;

    private List<String> labelList = new ArrayList<>();

    private List<Integer> iconList = new ArrayList<>();

    public CustomGridAdapter(Context context){
        this.context = context;

        fillLabelList();
        fillIconsList();
    }

    private void fillIconsList() {
        iconList.add(R.drawable.ic_menu);
        iconList.add(R.drawable.ic_new_order);
        iconList.add(R.drawable.ic_my_orders);
    }

    private void fillLabelList() {
        labelList.add("Cardápio");
        labelList.add("Novo Pedido");
        labelList.add("Meus Pedidos");
    }


    @Override
    public int getCount() {
        return labelList.size();
    }

    @Override
    public Object getItem(int i) {
        return labelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i+1000;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout container;
        if (view == null) {
            container = new LinearLayout(context);
            container.setGravity(Gravity.CENTER);
            container.setOrientation(LinearLayout.VERTICAL);
            container.setPadding(0,16,0,0);

            ImageView gridImage = new ImageView(context);
            int screenSize = context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

            if(screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
                gridImage.setLayoutParams(new GridView.LayoutParams(225, 225));
            }else if(screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE) {
                gridImage.setLayoutParams(new GridView.LayoutParams(75, 75));
            }else {
                gridImage.setLayoutParams(new GridView.LayoutParams(125, 125));
            }
            gridImage.setPadding(0, 16, 0, 0);
            gridImage.setImageResource(iconList.get(i));

            TextView gridText = new TextView(context);
            gridText.setText(labelList.get(i));
            gridText.setTextColor(context.getResources().getColor(R.color.colorAccent));
            gridText.setPadding(0,16,0,16);
            gridText.setTypeface(null,Typeface.BOLD);
            gridText.setGravity(Gravity.CENTER);

            container.addView(gridImage);
            container.addView(gridText);
        } else {
            container = (LinearLayout) view;
        }

        return container;
    }
}
