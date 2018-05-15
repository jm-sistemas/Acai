package com.jm.acai.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerConnection extends AsyncTask<String,Void,String>{
    public static final String HOST_DEFAULT = "";

    private static final int CONNECTION_TIME_OUT = 3000;
    private static final int READ_TIME_OUT = 3000;

    private ProgressDialog progressDialog;
    private OnConnectionCompletedListener<Object> onConnectionCompletedListener;
    private String address;
    private WeakReference<Context> context;
    private boolean showWaitDialog = true;
    private String dialogMessage = "";


    public ServerConnection(Context context,OnConnectionCompletedListener<Object> onConnectionCompletedListener){
        this.context = new WeakReference<>(context);
        this.onConnectionCompletedListener = onConnectionCompletedListener;
        this.address = HOST_DEFAULT + "ws.php";
    }

    public ServerConnection(Context context,OnConnectionCompletedListener<Object> onConnectionCompletedListener,String address){
        this.context = new WeakReference<>(context);
        this.onConnectionCompletedListener = onConnectionCompletedListener;
        this.address = address;
    }

    public void shouldShowWaitDialog(boolean showWaitDialog){
        this.showWaitDialog = showWaitDialog;
    }

    public void setDialogMessage(String dialogMessage){
        this.dialogMessage = dialogMessage;
    }

    private String getDataWithPost(String params){
        HttpURLConnection connection = null;
        String response = null;
        try{
            URL url = new URL("http://"+address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(CONNECTION_TIME_OUT);
            connection.setReadTimeout(READ_TIME_OUT);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            writer.write(params);
            writer.close();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String input;
            StringBuilder stringBuilder = new StringBuilder();
            while ((input = reader.readLine()) != null){
                stringBuilder.append(input);
            }
            reader.close();

            response = stringBuilder.toString();
            return response.replaceAll(String.valueOf((char) 65279),""); //Removes BOM Character from String
        }catch (Exception e){
            e.printStackTrace();
            return ConnectivityUtils.CONNECTION_ERROR;
        }
        finally {
            if(connection != null) {
                try {
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    } //Se conecta a um endereço usando o método POST e retorna o resultado


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(showWaitDialog) {
            if(dialogMessage.isEmpty())
                progressDialog = DialogUtils.getLoadingDialog(context.get());
            else
                progressDialog = DialogUtils.getLoadingDialog(context.get(), dialogMessage);
        }
    }

    @Override
    protected String doInBackground(String... params) {
        if(ConnectivityUtils.isNetworkConnected(context.get())){
            if(params[0] != null)
                return getDataWithPost(params[0]);
            else
                return getDataWithPost("");
        }else {
            return ConnectivityUtils.NETWORK_ERROR;
        }
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        if(showWaitDialog)
            progressDialog.dismiss();
        onConnectionCompletedListener.onConnectionCompleted(response);
    }
}
