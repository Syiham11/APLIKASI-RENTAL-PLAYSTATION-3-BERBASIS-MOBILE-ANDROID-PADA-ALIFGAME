package com.nua.rentalalifgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Anwar on 10/09/2015.
 */
public class SplashScreen extends Activity {

    SharedPreferences sharedPreferences;
    String id_user = "id_pengguna";
    String email_user = "email_pengguna";

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.s_id_user), Context.MODE_PRIVATE);
        id_user = sharedPreferences.getString(getResources().getString(R.string.s_id_user), getResources().getString(R.string.s_id_user_default));
        sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.s_email_user), Context.MODE_PRIVATE);
        email_user = sharedPreferences.getString(getResources().getString(R.string.s_email_user), getResources().getString(R.string.s_id_user_default));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(id_user.equals("id_pengguna") || email_user.equals("email_pengguna")){
                    intent = new Intent(SplashScreen.this,LoginActivity.class);
                    intent.putExtra("logout","logout");
                    intent.putExtra("id_pengguna","id_pengguna");
                    intent.putExtra("email_pengguna","email_pengguna");
                    SplashScreen.this.startActivity(intent);
                    SplashScreen.this.finish();
                }else{
                    intent = new Intent(SplashScreen.this,MainActivity.class);
                    intent.putExtra("id_pengguna",id_user);
                    intent.putExtra("email_pengguna",email_user);
                    SplashScreen.this.startActivity(intent);
                    SplashScreen.this.finish();
                }
            }
        },5000);
    }
}
