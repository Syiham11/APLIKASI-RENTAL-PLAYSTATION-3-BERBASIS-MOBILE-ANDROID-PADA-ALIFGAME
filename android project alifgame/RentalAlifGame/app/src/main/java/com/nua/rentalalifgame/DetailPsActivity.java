package com.nua.rentalalifgame;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by g40-70 on 12/09/2015.
 */
public class DetailPsActivity extends AppCompatActivity {

    public static final String TAG_DETAIL = "detail_ps";
    public static final String TAG_ID_PS = "id_ps";
    public static final String TAG_JENIS_PS = "jenis_ps";
    public static final String TAG_KELENGKAPAN_PS = "kelengkapan_ps";
    public static final String TAG_KETERANGAN_PS = "keterangan";
    public static final String TAG_KATEGORI_HARGA = "kategori_harga";
    public static final String TAG_ID_KATEGORI = "id_kategori";
    public static final String TAG_NAMA_KATEGORI = "nama_kategori";
    public static final String TAG_HARGA = "harga";

    public String id_ps = "0";
    public String id_pengguna = "id_pengguna";
    public String email_pengguna = "email_pengguna";

    ListView listView;
    Toolbar toolbar;
    ProgressBar progressBar;
    AdapterDetail adapterDetail;

    public static String JENIS_PS = "";
    public static String KELENGKAPAN_PS = "";
    public static String KETERANGAN_PS = "";
    public ArrayList<String> id_kategori;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ps);

        id_ps = getIntent().getExtras().getString("id_ps");
        id_pengguna = getIntent().getExtras().getString("id_pengguna");
        email_pengguna = getIntent().getExtras().getString("email_pengguna");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        listView = (ListView) findViewById(R.id.daftar_detail);
        progressBar = (ProgressBar) findViewById(R.id.detail_loader);
        adapterDetail = new AdapterDetail(DetailPsActivity.this);

        new AsycnDataDetail(listView).execute();
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

    public class AsycnDataDetail extends AsyncTask<String,String,ArrayList<String>>{

        String url;
        ArrayList<KategoriHarga> kategoriHargaArrayList;

        ListView listDetail;

        public AsycnDataDetail(ListView listDetail){
            this.listDetail = listDetail;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            this.url = "http://www.alifgame.16mb.com/mobile_post_detail_ps.php";
            this.listDetail.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            kategoriHargaArrayList = new ArrayList<KategoriHarga>();
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            ArrayList<String> listItem = new ArrayList<String>();
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("id_ps",id_ps));
            try{
                JSONBuilderString jsonBuilder = new JSONBuilderString();
                JSONObject jsonObject = jsonBuilder.getJsonFromUrl(this.url,nameValuePairs);
                if(jsonObject != null){
                    JSONArray arrayDetail = jsonObject.getJSONArray(TAG_DETAIL);
                    for(int i = 0; i<arrayDetail.length();i++){
                        JSONObject object = arrayDetail.getJSONObject(i);
                        JENIS_PS = "PS " + object.getString(TAG_JENIS_PS);
                        KELENGKAPAN_PS = object.getString(TAG_KELENGKAPAN_PS);
                        KETERANGAN_PS = object.getString(TAG_KETERANGAN_PS);
                        adapterDetail.addItem("detail");
                    }
                    JSONArray arrayKategori = jsonObject.getJSONArray(TAG_KATEGORI_HARGA);
                    for (int i=0;i<arrayKategori.length();i++){
                        JSONObject object = arrayKategori.getJSONObject(i);
                        String id_kategori = object.getString(TAG_ID_KATEGORI);
                        String nama_kategori = object.getString(TAG_NAMA_KATEGORI);
                        String harga = "Rp. " + object.getString(TAG_HARGA);

                        listItem.add(id_kategori);
                        KategoriHarga kategoriHarga = new KategoriHarga(id_kategori,nama_kategori,harga);
                        kategoriHargaArrayList.add(kategoriHarga);
                        adapterDetail.addItem(id_kategori);
                        adapterDetail.addKategori(kategoriHarga);
                    }

                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            return listItem;
        }

        @Override
        protected void  onPostExecute(ArrayList<String> listItem){
            super.onPostExecute(listItem);
            id_kategori = listItem;
            progressBar.setVisibility(View.GONE);
            this.listDetail.setVisibility(View.VISIBLE);
            this.listDetail.setAdapter(adapterDetail);
            this.listDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 0) {
                        final String id_kat = id_kategori.get(position - 1);
                        new AlertDialog.Builder(DetailPsActivity.this)
                                .setTitle("Perhatian")
                                .setMessage("Anda ingin merental playstation !")
                                .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setPositiveButton("Kirim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        new AsyncPesanRental(id_kat).execute();
                                    }
                                })
                                .show();
                    }
                }
            });
        }

    }

    public class AsyncPesanRental extends AsyncTask<String,String,String>{

        ProgressDialog progressDialog;
        String id_kate,url;

        public AsyncPesanRental(String id_kat){
            id_kate = id_kat;
        }

        @Override
        protected void onPreExecute(){
            this.url = "http://www.alifgame.16mb.com/mobile_post_pemesanan.php";
            progressDialog = new ProgressDialog(DetailPsActivity.this);
            progressDialog.setMessage("Proses Mengirim");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String error = "";
            ArrayList<NameValuePair> post_parameter = new ArrayList<NameValuePair>();
            try{
                JSONBuilderString jsonBuilder = new JSONBuilderString();
                post_parameter.add(new BasicNameValuePair("id_kategori",id_kate));
                post_parameter.add(new BasicNameValuePair("id_pengguna",id_pengguna));
                post_parameter.add(new BasicNameValuePair("id_ps",id_ps));
                JSONObject jsonObject = jsonBuilder.getJsonFromUrl(this.url,post_parameter);
                error = jsonObject.getString("error");
                return error;
            } catch (JSONException e) {
                //e.printStackTrace();
                error = "1";
                return error;
            }
        }

        @Override
        protected void onPostExecute(String error){
            super.onPostExecute(error);
            progressDialog.dismiss();
            if(error.toString().equals("0")) {
                new AlertDialog.Builder(DetailPsActivity.this)
                        .setTitle("Sukses")
                        .setMessage("Pesanan telah terkitim !")
                        .setNegativeButton("TUTUP", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("LIHAT PESANAN SAYA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(DetailPsActivity.this,DaftarRentalActivity.class);
                                intent.putExtra("id_pengguna",id_pengguna);
                                intent.putExtra("email_pengguna",email_pengguna);
                                startActivity(intent);
                            }
                        })
                        .show();
            }else{
                new AlertDialog.Builder(DetailPsActivity.this)
                        .setTitle("Gagal")
                        .setMessage("Pesanan tidak dapat terkitim !")
                        .setNegativeButton("ULANGI", new DialogInterface.OnClickListener() {
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