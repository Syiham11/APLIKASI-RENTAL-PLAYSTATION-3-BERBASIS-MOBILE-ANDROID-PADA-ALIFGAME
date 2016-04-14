package com.nua.rentalalifgame;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anwar on 15/09/2015.
 */
public class GantiAlamatActivity extends AppCompatActivity {

    EditText editTextAlamat;
    Button buttonProsesAlamat;
    LinearLayout linearLayout;
    ProgressBar loaderAlamat;
    TextView textViewAlamat;
    Toolbar toolbar;
    public String id_pengguna;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_alamat);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id_pengguna = getIntent().getStringExtra("id_pengguna");
        linearLayout = (LinearLayout) findViewById(R.id.linear_alamat);
        loaderAlamat = (ProgressBar) findViewById(R.id.loader_alamat);
        textViewAlamat = (TextView) findViewById(R.id.text_alamat);
        editTextAlamat = (EditText) findViewById(R.id.edit_alamat);
        buttonProsesAlamat = (Button) findViewById(R.id.btn_proses_alamat);
        buttonProsesAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String alamat_ganti = editTextAlamat.getText().toString();
                if(alamat_ganti.equals("") || alamat_ganti.equals(null)){
                    new AlertDialog.Builder(GantiAlamatActivity.this)
                            .setTitle("Perhatian")
                            .setMessage("Maaf, anda harus mengisi alamat untuk perubahan !")
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }else {
                    new AsyncAlamat(true, alamat_ganti).execute();
                }
            }
        });

        new AsyncAlamat().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public class AsyncAlamat extends AsyncTask<String,String,String>{

        String url;
        Boolean postAlamat = false;
        String alamat_ganti;

        public AsyncAlamat(){
        }

        public AsyncAlamat(Boolean post,String alamat_ganti){
            this.postAlamat = post;
            this.alamat_ganti = alamat_ganti;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            this.url = "http://10.0.2.2/mobile_alamat.php";
            linearLayout.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... params) {
            String alamat = "";
            if(!this.postAlamat) {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_pengguna",id_pengguna));
                try {
                    JSONBuilderString jparse = new JSONBuilderString();
                    JSONObject jsonObject = jparse.getJsonFromUrl(this.url,nameValuePairs);
                    if(jsonObject != null) {
                        alamat = jsonObject.getString("alamat");
                    }else {
                        alamat = "";
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("id_pengguna",id_pengguna));
                nameValuePairs.add(new BasicNameValuePair("alamat",alamat_ganti));
                try {
                    JSONBuilderString jparse = new JSONBuilderString();
                    JSONObject jsonObject = jparse.getJsonFromUrl(this.url,nameValuePairs);
                    alamat = jsonObject.getString("alamat");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return alamat;
        }

        @Override
        protected void onPostExecute(String alamat){
            super.onPostExecute(alamat);
            loaderAlamat.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            if(this.postAlamat){
                editTextAlamat.setText("");
                new AlertDialog.Builder(GantiAlamatActivity.this)
                        .setTitle("Perhatian")
                        .setMessage("Alamat anda telah berganti")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
            textViewAlamat.setText(alamat);
        }
    }
}
