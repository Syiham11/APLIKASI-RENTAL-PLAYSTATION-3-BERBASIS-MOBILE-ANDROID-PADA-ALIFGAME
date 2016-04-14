package com.nua.rentalalifgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anwar on 16/09/2015.
 */
public class DaftarRentalActivity extends AppCompatActivity {

    Toolbar toolbar;
    public ListView listView;
    public ArrayList<DaftarPesanan> daftarPesanans;
    public AdapterDaftarPesanan adapterDaftarPesanan;
    public String id_pengguna, email_pengguna;
    View footLoad;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_rental);

        id_pengguna = getIntent().getStringExtra("id_pengguna");
        email_pengguna = getIntent().getStringExtra("email_pengguna");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        footLoad = LayoutInflater.from(this).inflate(R.layout.loader,null);
        listView = (ListView) findViewById(R.id.daftar_rental);
        daftarPesanans = new ArrayList<DaftarPesanan>();
        adapterDaftarPesanan = new AdapterDaftarPesanan(this,R.layout.row_design_rental,daftarPesanans);
        listView.setAdapter(adapterDaftarPesanan);

        new AsyncDaftarPesanan().execute();
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

    public class AsyncDaftarPesanan extends AsyncTask<String,String,ArrayList<String>>{

        String url;

        public AsyncDaftarPesanan(){
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            this.url = "http://10.0.2.2mobile_daftar_pesanan.php";
            if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
                listView.addFooterView(footLoad,null,false);
            }else {
                listView.addFooterView(footLoad,null,false);
                listView.setAdapter(adapterDaftarPesanan);
            }
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            ArrayList<String> list = new ArrayList<String>();
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("id_pengguna",id_pengguna));
            try {
                JSONBuilderString jsonParse = new JSONBuilderString();
                JSONObject jsonObject = jsonParse.getJsonFromUrl(this.url,nameValuePairs);
                if(jsonObject != null){
                    JSONArray jsonArray = jsonObject.getJSONArray("daftar_pesanan");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id_pemesanan = object.getString("id_pemesanan");
                        String status_pesanan = object.getString("status_pesanan");
                        String jenis_ps = object.getString("jenis_ps");
                        String kategori = object.getString("nama_kategori");
                        String harga = object.getString("harga");
                        String tanggal = object.getString("tanggal");

                        DaftarPesanan daftarPesanan = new DaftarPesanan(id_pemesanan,jenis_ps,kategori,harga,tanggal,status_pesanan);
                        //daftarPesanans.add(daftarPesanan);
                        adapterDaftarPesanan.add(daftarPesanan);
                        list.add(id_pemesanan);
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list){
            super.onPostExecute(list);
            if (list.isEmpty()){
                listView.removeFooterView(footLoad);
                new AlertDialog.Builder(DaftarRentalActivity.this)
                        .setTitle("Perhatian")
                        .setMessage("Anda tidak memiliki pesanan rental.")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("PESAN SEKARANG", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(DaftarRentalActivity.this,DaftarPsActivity.class);
                                intent.putExtra("id_pengguna",id_pengguna);
                                intent.putExtra("email_pengguna",email_pengguna);
                                startActivity(intent);
                            }
                        })
                        .show();
            }else {
                adapterDaftarPesanan.notifyDataSetChanged();
                listView.removeFooterView(footLoad);
            }
        }

    }

}
