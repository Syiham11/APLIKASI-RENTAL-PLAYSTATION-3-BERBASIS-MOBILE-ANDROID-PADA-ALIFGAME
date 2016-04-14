package com.nua.rentalalifgame;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anwar on 31/07/2015.
 */
public class JSONBuilderString {
    static InputStream is = null;
    static InputStream isSessi = null;
    static JSONObject jsonObject = null;
    static JSONObject jsonObjectSessi = null;
    static String jsonString = "";
    static String jsonStringSessi = "";
    public String usrUnique = null;
    public int ConnCode;

    DefaultHttpClient httpClient;
    HttpPost httpPost;

    public JSONBuilderString(){
    }

    public void setUsrUnique(String usrunique){
        this.usrUnique = usrunique;
    }

    public int getConnCode(){
        return ConnCode;
    }

    private void setConnCode(int code){
        this.ConnCode = code;
    }

    public JSONObject getJsonFromUrl(String url, String username, String password){
        try {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Accept","Application/json");
            httpPost.setHeader("User-Agent","NZ");

            List<NameValuePair> nameValuePairs;
            nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("email",username));
            nameValuePairs.add(new BasicNameValuePair("password",password));
            //if(this.usrUnique != null){
                //nameValuePairs.add(new BasicNameValuePair("unik-kode", this.usrUnique));
            //}
            BasicHttpParams parameters = new BasicHttpParams();
            parameters.setParameter("email",username);
            parameters.setParameter("password",password);
            //if(this.usrUnique != null){
              //  parameters.setParameter("unik-kode", this.usrUnique);
            //}
            httpPost.setParams(parameters);

            AbstractHttpEntity entity = new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8);
            entity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            entity.setContentEncoding("UTF-8");

            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            StatusLine statusLine = httpResponse.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                HttpEntity httpEntity = httpResponse.getEntity();
                isSessi = httpEntity.getContent();
                Log.e("Input Stream JSON","Inisial String JSON succes");
            }else{
                isSessi = null;
                //HttpEntity httpEntity = httpResponse.getEntity();
                //is = httpEntity.getContent();
                setConnCode(statusLine.getStatusCode());
                Log.e("Input Stream JSON","Inisial JSON ERROR, Kode koneksi -> " + statusLine.getStatusCode());
            }
        }catch (UnsupportedEncodingException e){
            Log.e("Error JSON", "UnsupportedEncodingException -> "+ e.toString());
        }catch (ClientProtocolException e){
            Log.e("Error JSON", "Client Protocol -> "+ e.toString());
        }catch (IOException e){
            Log.e("Error JSON", "IO Exception -> "+ e.toString());
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(isSessi, Charset.forName("UTF-8")));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line+"\n");
            }
            isSessi.close();
            jsonStringSessi = stringBuilder.toString();
        }catch (Exception e){
            Log.e("Buffer JSON Error","Error Converting Result -> " +e.toString());
        }

        try {
            jsonObjectSessi = new JSONObject(jsonStringSessi);
        }catch (JSONException e){
            Log.e("JSON Parser Error","Error Parse Data -> " + e.toString());
        }

        return jsonObjectSessi;
    }

    public JSONObject getJsonFromUrl(String url, ArrayList<NameValuePair> nameValuePair){
        try {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Accept","Application/json");
            httpPost.setHeader("User-Agent","NZ");

            List<NameValuePair> nameValuePairs;
            nameValuePairs = nameValuePair;

            AbstractHttpEntity entity = new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8);
            entity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            entity.setContentEncoding("UTF-8");

            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            StatusLine statusLine = httpResponse.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                Log.e("Input Stream JSON","Inisial String JSON succes");
            }else{
                is = null;
                //HttpEntity httpEntity = httpResponse.getEntity();
                //is = httpEntity.getContent();
                setConnCode(statusLine.getStatusCode());
                Log.e("Input Stream JSON","Inisial JSON ERROR, Kode koneksi -> " + statusLine.getStatusCode());
            }
        }catch (UnsupportedEncodingException e){
            Log.e("Error JSON", "UnsupportedEncodingException -> "+ e.toString());
        }catch (ClientProtocolException e){
            Log.e("Error JSON", "Client Protocol -> "+ e.toString());
        }catch (IOException e){
            Log.e("Error JSON", "IO Exception -> "+ e.toString());
        }

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
            Log.e("Buffer JSON Error","Error Converting Result -> " +e.toString());
        }

        try {
            jsonObject = new JSONObject(jsonString);
        }catch (JSONException e){
            Log.e("JSON Parser Error","Error Parse Data -> " + e.toString());
        }

        return jsonObject;
    }

    public JSONObject getJsonFromUrl(String url){
        try {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Accept","Application/json");
            httpPost.setHeader("User-Agent","NZ");

            List<NameValuePair> nameValuePairs;
            nameValuePairs = new ArrayList<NameValuePair>();
            if(this.usrUnique != null){
                nameValuePairs.add(new BasicNameValuePair("id_pengguna", this.usrUnique));
            }
            BasicHttpParams parameters = new BasicHttpParams();
            if(this.usrUnique != null){
                parameters.setParameter("id_pengguna", this.usrUnique);
            }
            httpPost.setParams(parameters);

            AbstractHttpEntity entity = new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8);
            entity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            entity.setContentEncoding("UTF-8");

            httpPost.setEntity(entity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            StatusLine statusLine = httpResponse.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                Log.e("Input Stream JSON","Inisial String JSON succes");
            }else{
                is = null;
                //HttpEntity httpEntity = httpResponse.getEntity();
                //is = httpEntity.getContent();
                setConnCode(statusLine.getStatusCode());
                Log.e("Input Stream JSON","Inisial JSON ERROR, Kode koneksi -> " + statusLine.getStatusCode());
            }
        }catch (UnsupportedEncodingException e){
            Log.e("Error JSON", "UnsupportedEncodingException -> "+ e.toString());
        }catch (ClientProtocolException e){
            Log.e("Error JSON", "Client Protocol -> "+ e.toString());
        }catch (IOException e){
            Log.e("Error JSON", "IO Exception -> "+ e.toString());
        }

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
            Log.e("Buffer JSON Error","Error Converting Result -> " +e.toString());
        }

        try {
            jsonObject = new JSONObject(jsonString);
        }catch (JSONException e){
            Log.e("JSON Parser Error","Error Parse Data -> " + e.toString());
        }

        return jsonObject;
    }
}
