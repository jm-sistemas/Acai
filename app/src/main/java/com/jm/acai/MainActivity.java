package com.jm.acai;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.jm.acai.adapters.CustomGridAdapter;
import com.jm.acai.utils.DialogUtils;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private final static int MENU = 0;
    private final static int NEW_ORDER = 1;
    private final static int MY_ORDERS = 2;
    private final static int STORES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindElements();
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView textView = findViewById(R.id.tv_welcome);
        String message = getWelcomeMessage()+"seja bem vindo!";
        textView.setText(message);
    }

    private void bindElements() {
        GridView gridView = findViewById(R.id.gv_main);
        gridView.setOnItemClickListener(this);
        gridView.setAdapter(new CustomGridAdapter(this));
    }

    private String getWelcomeMessage(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour >= 0 && hour < 12){
            return "Bom dia, ";
        }else if(hour >= 12 && hour < 18){
            return "Boa tarde, ";
        }else {
            return "Boa noite, ";
        }
    }

    @Override
    public void onBackPressed() {
        new DialogUtils(this).showExitDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        switch (position){
            case MENU:
                intent = new Intent(this,MenuActivity.class);
                break;
            case NEW_ORDER:
                intent = new Intent(this,NewOrderActivity.class);
                break;
            case MY_ORDERS:
                intent = new Intent(this,OrdersActivity.class);
                break;
            case STORES:
                intent = new Intent(this,StoresActivity.class);
        }
        if(intent != null)
            startActivity(intent);
    }
}
