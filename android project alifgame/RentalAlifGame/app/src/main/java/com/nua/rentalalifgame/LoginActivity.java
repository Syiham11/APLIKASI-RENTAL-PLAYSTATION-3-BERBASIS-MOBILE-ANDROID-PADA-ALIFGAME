package com.nua.rentalalifgame;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anwar on 09/09/2015.
 */
public class LoginActivity extends Activity {

    Button buttonLogin, buttonDaftar;
    EditText edit_email, edit_password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editorSP;
    ProgressBar progressBar;
    LinearLayout linearLayout;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        linearLayout = (LinearLayout) findViewById(R.id.form_login);
        progressBar = (ProgressBar) findViewById(R.id.loader_login);
        edit_email = (EditText) findViewById(R.id.usr_email);
        edit_password = (EditText) findViewById(R.id.usr_password);

        buttonLogin = (Button) findViewById(R.id.button);
        buttonDaftar = (Button) findViewById(R.id.button1);
        String logout = getIntent().getStringExtra("logout");
        if(logout.equals("logout")){
            linearLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            sharedPreferences = LoginActivity.this.getSharedPreferences(getResources().getString(R.string.s_id_user), Context.MODE_PRIVATE);
            editorSP = sharedPreferences.edit();
            editorSP.putString(getApplicationContext().getResources().getString(R.string.s_id_user), getApplicationContext().getResources().getString(R.string.s_id_user_default));
            editorSP.commit();

            sharedPreferences = LoginActivity.this.getSharedPreferences(getResources().getString(R.string.s_email_user), Context.MODE_PRIVATE);
            editorSP = sharedPreferences.edit();
            editorSP.putString(getApplicationContext().getResources().getString(R.string.s_email_user), getApplicationContext().getResources().getString(R.string.s_email_default));
            editorSP.commit();
            progressBar.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_email = edit_email.getText().toString();
                String s_pwd = edit_password.getText().toString();
                if (s_email.equals("") || s_pwd.equals("")) {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Kesalahan")
                            .setMessage("Email dan Password harus di isi !")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                } else {
                    linearLayout.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    new AsycnLogin(s_email, s_pwd).execute();
                }
            }
        });

        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,PendaftaranActivity.class);
                startActivity(intent);
            }
        });
    }

    public class AsycnLogin extends AsyncTask<String,String,ArrayList<String>>{

        String url = null;
        String s_email = null;
        String s_password = null;

        public AsycnLogin(String email, String password){
            this.url = "http://10.0.2.2/mobile_login.php";
            this.s_email = email;
            this.s_password = password;
            /*try {
                this.query_post = String.format("email=%s&password=%s",
                        URLEncoder.encode(email,"UTF-8"),
                        URLEncoder.encode(password, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                //e.printStackTrace();
                Log.e("ERROR CHARSET","UnsupportedEncoding -> " + e.toString());
            }*/
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            ArrayList<String> listItem = new ArrayList<String>();
            try{
                JSONBuilderString jsonBuilder = new JSONBuilderString();
                JSONObject jsonObject = jsonBuilder.getJsonFromUrl(this.url,this.s_email,this.s_password);
                if(jsonObject != null){
                        String id_user = String.valueOf(jsonObject.getInt("id_pengguna"));
                        String email = jsonObject.getString("email");
                        String error = String.valueOf(jsonObject.getInt("error"));
                        listItem.add(0,id_user);
                        listItem.add(1,email);
                        listItem.add(2,error);
                }else {
                    listItem.clear();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listItem;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list){
            super.onPostExecute(list);
            if(!list.isEmpty()){
                String id_usr = list.get(0).toString();
                String email = list.get(1).toString();
                String error_ac = list.get(2).toString();
                if(!error_ac.equals("1") && !email.equals("")) {
                    sharedPreferences = LoginActivity.this.getSharedPreferences(getResources().getString(R.string.s_id_user), Context.MODE_PRIVATE);
                    editorSP = sharedPreferences.edit();
                    editorSP.putString(getApplicationContext().getResources().getString(R.string.s_id_user), id_usr);
                    editorSP.commit();

                    sharedPreferences = LoginActivity.this.getSharedPreferences(getResources().getString(R.string.s_email_user), Context.MODE_PRIVATE);
                    editorSP = sharedPreferences.edit();
                    editorSP.putString(getApplicationContext().getResources().getString(R.string.s_email_user), email);
                    editorSP.commit();

                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("id_pengguna",id_usr);
                    intent.putExtra("email_pengguna",email);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }else{
                    sharedPreferences = LoginActivity.this.getSharedPreferences(getResources().getString(R.string.s_id_user), Context.MODE_PRIVATE);
                    editorSP = sharedPreferences.edit();
                    editorSP.putString(getApplicationContext().getResources().getString(R.string.s_id_user), getApplicationContext().getResources().getString(R.string.s_id_user_default));
                    editorSP.commit();

                    sharedPreferences = LoginActivity.this.getSharedPreferences(getResources().getString(R.string.s_email_user), Context.MODE_PRIVATE);
                    editorSP = sharedPreferences.edit();
                    editorSP.putString(getApplicationContext().getResources().getString(R.string.s_email_user), getApplicationContext().getResources().getString(R.string.s_email_default));
                    editorSP.commit();

                    progressBar.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);

                    edit_email.setText(this.s_email);
                    edit_password.setText(this.s_password);
                    String pesan;
                    if(email.equals("") && error_ac.equals("0")){
                        pesan = "Email anda belum terdaftar atau belum di konfirmasi.";
                    }else {
                        pesan = "Email dan password tidak sesuai.";
                    }

                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Kesalahan !")
                            .setMessage(pesan)
                            .setPositiveButton("Ulangi", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        }

    }

}
