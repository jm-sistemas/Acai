package com.jm.acai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.jm.acai.adapters.CustomGridAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private final static int MENU = 0;
    private final static int MESSAGES = 1;
    private final static int NOTIFICATIONS = 2;
    private final static int GRADES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindElements();
    }

    private void bindElements() {
        TextView textView = findViewById(R.id.tv_welcome);
        String name = "Fulano";
        textView.setText("Olá, "+name+"!");
        GridView gridView = findViewById(R.id.gv_main);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(new CustomGridAdapter(this));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        switch (position){
            case MENU:
                break;
        }
        if(intent != null)
            startActivity(intent);
    }
}
