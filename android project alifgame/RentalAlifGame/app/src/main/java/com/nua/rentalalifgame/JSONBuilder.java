package com.nua.rentalalifgame;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Anwar on 09/09/2015.
 */
public class JSONBuilder {
    public static InputStream is = null;
    public static JSONObject jsonObject = null;
    public static String jsonString = "";
    private String id_user = "";

    public JSONBuilder(){
    }

    public void setId_user(String idUser){
        this.id_user = idUser;
    }

    @SuppressLint("NewApi")
    public JSONObject getJsonFromUrl(String url, String query_post){
        try {
            //192.168.43.125
            URL urlData = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlData.openConnection();
            httpURLConnection.setRequestProperty("Accept","application/json");
            httpURLConnection.setRequestProperty("User-Agent", "NZ");
            httpURLConnection.setAllowUserInteraction(false);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Content-length", "0");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            try(OutputStream outputStream = httpURLConnection.getOutputStream()){
                outputStream.write(query_post.getBytes("UTF-8"));
            }
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();
            int kodeKoneksi = httpURLConnection.getResponseCode();
            if(kodeKoneksi == 200 || kodeKoneksi == 201) {
                is = httpURLConnection.getInputStream();
            }else {
                is = null;
            }
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            is = null;
            Log.e("Error Json Parse","URL Exception -> " + e.toString());
        } catch (IOException e) {
            //e.printStackTrace();
            is = null;
            Log.e("Error Json Parse","Input Output Exception -> " + e.toString());
        }

        if(is != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line+"\n");
                }
                is.close();
                jsonString = stringBuilder.toString();
            }catch (Exception e){
                //e.printStackTrace();
                jsonString = "";
                Log.e("Error Json Parse","Buffered Reader Error -> " + e.toString());
            }
        }else {
            jsonString = "";
        }

        if(jsonString.equals("")){
            return null;
        }else {
            try {
                jsonObject = new JSONObject(jsonString);
            } catch (JSONException e) {
                //e.printStackTrace();
                jsonObject = null;
                Log.e("Erron Build Json","JSON Object Exception -> " + e.toString());
            }
            return jsonObject;
        }
    }

    public JSONObject getJsonFromUrl(String url){
        try {
            //192.168.43.125
            URL urlData = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlData.openConnection();
            httpURLConnection.setRequestProperty("Accept","application/json");
            httpURLConnection.setRequestProperty("User-Agent","NZ");
            //httpURLConnection.setAllowUserInteraction(false);
            //httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Content-length","0");
            //httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            int kodeKoneksi = httpURLConnection.getResponseCode();
            if(kodeKoneksi == 200 || kodeKoneksi == 201) {
                is = httpURLConnection.getInputStream();
            }else {
                is = null;
            }
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            is = null;
            Log.e("Error Json Parse","URL Exception -> " + e.toString());
        } catch (IOException e) {
            //e.printStackTrace();
            is = null;
            Log.e("Error Json Parse","Input Output Exception -> " + e.toString());
        }

        if(is != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line+"\n");
                }
                is.close();
                jsonString = stringBuilder.toString();
            }catch (Exception e){
                //e.printStackTrace();
                jsonString = "";
                Log.e("Error Json Parse","Buffered Reader Error -> " + e.toString());
            }
        }else {
            jsonString = "";
        }

        if(jsonString.equals("")){
            return null;
        }else {
            try {
                jsonObject = new JSONObject(jsonString);
            } catch (JSONException e) {
                //e.printStackTrace();
                jsonObject = null;
                Log.e("Erron Build Json","JSON Object Exception -> " + e.toString());
            }
            return jsonObject;
        }
    }
}