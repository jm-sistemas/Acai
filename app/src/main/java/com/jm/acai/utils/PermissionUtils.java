package com.jm.acai.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

public class PermissionUtils {
    private Activity activity;

    public PermissionUtils(Activity activity){
        this.activity = activity;
    }

    public boolean isCallAllowed(){
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }else {
            if (Build.VERSION.SDK_INT >= 23) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1);

            } else {
                DialogUtils.showAlert(activity,"É necessário aceitar a permissão de Telefone");
            }
            return false;
        }
    } //Retorna True se o aplicativo tem permissão de Telefone

    public boolean isReadingStorageAllowed(){
        if(Build.VERSION.SDK_INT >= 23){
            if(ActivityCompat.checkSelfPermission(activity,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                return true;
            }else {
                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                return false;
            }
        }else {
            return true;
        }
    } //Retorna True se o aplicativo possui permissão de Armazenamento

    public boolean isWritingStorageAllowed(){
        if(Build.VERSION.SDK_INT >= 23){
            if(ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                return true;
            }else {
                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                return false;
            }
        }else {
            return true;
        }
    } //Retorna True se o aplicativo possui permissão de Armazenamento

    public boolean isCameraAllowed(){
        if(Build.VERSION.SDK_INT >= 23){
            if(ActivityCompat.checkSelfPermission(activity,Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                return true;
            }else {
                ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CAMERA},1);
                return false;
            }
        }else {
            return true;
        }
    } //Retorna True se o aplicativo possui permissão de Câmera

}
