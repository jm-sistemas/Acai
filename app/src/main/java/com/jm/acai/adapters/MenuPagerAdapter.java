package com.jm.acai.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.jm.acai.R;

public class MenuPagerAdapter extends PagerAdapter{

    private Context context;
    private String[] names, imagesUrl, prices;

    public MenuPagerAdapter(Context context, String[] names, String[] imagesUrl, String[] prices) {
        this.context = context;
        this.names = names;
        this.imagesUrl = imagesUrl;
        this.prices = prices;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.menu_item, container, false);

        final ImageView imageView = itemView.findViewById(R.id.iv_menu_image);
        Glide.with(context).asBitmap().load(imagesUrl[position]).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                imageView.setImageBitmap(resource);
            }
        });

        TextView nameTextView = itemView.findViewById(R.id.tv_menu_name);
        nameTextView.setText(names[position]);

        TextView priceTextView = itemView.findViewById(R.id.tv_menu_price);
        priceTextView.setText(prices[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
