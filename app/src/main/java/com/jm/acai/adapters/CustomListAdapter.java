package com.jm.acai.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CustomListAdapter extends BaseAdapter {

    private Context context;

    private List<String> labelList;
    private List<String> subLabelList = new ArrayList<>();
    private List<Boolean> isPresentList = new ArrayList<>();

    private int[] labelPadding = {16, 16, 16, 16};

    public CustomListAdapter(Context context, List<String> labelList, List<String> subLabelList, List<Boolean> isPresentList){
        this.context = context;
        this.labelList = labelList;
        this.subLabelList = subLabelList;
        this.isPresentList = isPresentList;
    }

    public CustomListAdapter(Context context, List<String> labelList){
        this.context = context;
        this.labelList = labelList;
    }

    private TextView createLabel(String text, int[] padding, int textSize, int textColor){
        TextView label = new TextView(context);
        label.setPadding(padding[0],padding[1],padding[2],padding[3]);
        label.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
        if(textColor != 0)
            label.setTextColor(textColor);
        else
            label.setTypeface(Typeface.SANS_SERIF);
        label.setText(text);

        return label;
    }

    @Override
    public int getCount() {
        return labelList.size();
    }

    @Override
    public Object getItem(int i) {
        return labelList.get(i);
    }

    public List<Boolean> getIsPresentList() {
        return isPresentList;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LinearLayout container = new LinearLayout(context);

        RelativeLayout firstLine = new RelativeLayout(context);

        TextView listTextView = createLabel(labelList.get(i),labelPadding,15, Color.BLACK);
        firstLine.addView(listTextView);

        if(!isPresentList.isEmpty()) {
            CheckBox isPresentCheckBox = new CheckBox(context);
            isPresentCheckBox.setPadding(16, 16, 0, 16);
            isPresentCheckBox.setChecked(isPresentList.get(i));
            isPresentCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    isPresentList.set(i, isChecked);
                }
            });

            RelativeLayout.LayoutParams params = new
                    RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            isPresentCheckBox.setLayoutParams(params);

            firstLine.addView(isPresentCheckBox);
        }

        container.setOrientation(LinearLayout.VERTICAL);
        container.addView(firstLine);

        if(subLabelList.size() > 0) {
            TextView subListTextView = createLabel(subLabelList.get(i), labelPadding, 16, Color.BLACK);
            container.addView(subListTextView);
        }

        return container;
    }
}
