package com.nua.rentalalifgame;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by g40-70 on 11/09/2015.
 */
public class PendaftaranActivity extends AppCompatActivity {

    public String NAMA_LENGKAP = "";
    public String EMAIL = "";
    public String PASSWORD = "";
    public String JENIS_KELAMIN = "";
    public String ALAMAT = "";
    public String TANGGAL_LAHIR = "";
    public String NO_HP = "";

    Calendar myCalendar;

    Toolbar toolbar;
    EditText nama,email,password,alamat,tanggal_lahir,no_hp;
    Spinner jenis_kelamin;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        nama = (EditText) findViewById(R.id.reg_nama);
        email = (EditText) findViewById(R.id.reg_email);
        password = (EditText) findViewById(R.id.reg_password);
        alamat = (EditText) findViewById(R.id.reg_alamat);
        tanggal_lahir = (EditText) findViewById(R.id.reg_tgl_lahir);
        no_hp = (EditText) findViewById(R.id.reg_no_hp);
        jenis_kelamin = (Spinner) findViewById(R.id.spinner_jkl);

        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateTanggal();
            }
        };

        tanggal_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PendaftaranActivity.this,
                        dateListener,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH))
                        .show();
            }
        });

        jenis_kelamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                JENIS_KELAMIN = parent.getItemAtPosition(position).toString();
                //Toast.makeText(PendaftaranActivity.this, JENIS_KELAMIN.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                JENIS_KELAMIN = parent.getItemAtPosition(0).toString();
            }
        });

        Button btn_reg = (Button) findViewById(R.id.reg_btn_daftar);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NAMA_LENGKAP = nama.getText().toString();
                EMAIL = email.getText().toString();
                PASSWORD = password.getText().toString();
                ALAMAT = alamat.getText().toString();
                NO_HP = no_hp.getText().toString();
                TANGGAL_LAHIR = tanggal_lahir.getText().toString();
                new AsyncPenadfataran().execute();
            }
        });

    }

    private void updateTanggal(){
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        tanggal_lahir.setText(sdf.format(myCalendar.getTime()));
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
        if(id == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public class AsyncPenadfataran extends AsyncTask<String, String, String>{

        String url,pesan;
        ProgressDialog progressDialog;

        public AsyncPenadfataran(){
            progressDialog = new ProgressDialog(PendaftaranActivity.this);
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            this.url = "http://www.alifgame.16mb.com/mobile_post_register.php";
            progressDialog.setMessage("Proses daftar...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String error = "";
            ArrayList<NameValuePair> post_parameter;
            post_parameter = new ArrayList<NameValuePair>();
            post_parameter.add(new BasicNameValuePair("nama",NAMA_LENGKAP));
            post_parameter.add(new BasicNameValuePair("jenis_kelamin",JENIS_KELAMIN));
            post_parameter.add(new BasicNameValuePair("alamat",ALAMAT));
            post_parameter.add(new BasicNameValuePair("email",EMAIL));
            post_parameter.add(new BasicNameValuePair("no_hp",NO_HP));
            post_parameter.add(new BasicNameValuePair("password",PASSWORD));
            post_parameter.add(new BasicNameValuePair("tanggal_lahir", TANGGAL_LAHIR));
            try {
                JSONBuilderString jparse = new JSONBuilderString();
                JSONObject jsonObject = jparse.getJsonFromUrl(this.url, post_parameter);
                error = jsonObject.getString("error");
                pesan = jsonObject.getString("pesan");
            } catch (JSONException e) {
                //e.printStackTrace();
            }
            return error;
        }

        @Override
        protected void onPostExecute(String error){
            super.onPostExecute(error);
            progressDialog.dismiss();
            new AlertDialog.Builder(PendaftaranActivity.this)
                    .setTitle("Perhatian")
                    .setMessage(pesan.toString())
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }

    }

}
