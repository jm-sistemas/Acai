package com.jm.acai.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jm.acai.intro.IntroActivity;
import com.jm.acai.MainActivity;

public class AutenticationUtils{

    public static boolean isFirstAccess(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean("isFirstAccess",true);
    }

    public static void finishIntro(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putBoolean("isFirstAccess",false).apply();

        context.startActivity(new Intent(context, MainActivity.class));
        ((Activity) context).finish();
    }

    public static void checkLogin(Context context, String response){
        boolean isAccessGranted = Licenses.isLicenseValid(response);
        Intent intent = null;

        if(isFirstAccess(context)){
            intent = new Intent(context, IntroActivity.class);
            intent.putExtra("isFirstAccess",true);
        }else if(isAccessGranted) {
            intent = new Intent(context, MainActivity.class);
        }
        if(intent != null)
            context.startActivity(intent);
        ((Activity) context).finish();
    }
}
