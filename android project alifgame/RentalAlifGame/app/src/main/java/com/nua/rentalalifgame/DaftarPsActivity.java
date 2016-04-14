package com.nua.rentalalifgame;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DaftarPsActivity extends AppCompatActivity {

    public String id_pengguna, email_pengguna;

    ArrayList<DataPs> dataPsArrayList;
    AdapterDaftarPs adapterDaftarPs;
    ListView listView;
    View loaderFooter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_ps);

        id_pengguna = getIntent().getExtras().getString("id_pengguna");
        email_pengguna = getIntent().getExtras().getString("email_pengguna");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.daftar_ps);
        loaderFooter = LayoutInflater.from(this).inflate(R.layout.loader,null);

        dataPsArrayList = new ArrayList<DataPs>();
        adapterDaftarPs = new AdapterDaftarPs(this,R.layout.list_design,dataPsArrayList);
        listView.setAdapter(adapterDaftarPs);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataPs ps = (DataPs) parent.getItemAtPosition(position);
                String id_ps = ps.getId_ps();
                Intent intent = new Intent(DaftarPsActivity.this,DetailPsActivity.class);
                intent.putExtra("id_pengguna",id_pengguna);
                intent.putExtra("email_pengguna",email_pengguna);
                intent.putExtra("id_ps",id_ps);
                startActivity(intent);
            }
        });

        new AsycnData(listView,loaderFooter).execute();
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

    public class AsycnData extends AsyncTask<String,String,ArrayList<String>> {

        ListView listView;
        View loaderFooter;
        String url;

        public AsycnData(ListView listView, View foot){
            this.listView = listView;
            this.loaderFooter = foot;
        }

        @Override
        protected void onPreExecute(){
            this.url = "http://10.0.2.2/mobile_daftar_ps.php";
            if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
                this.listView.addFooterView(loaderFooter,null,false);
            }else {
                this.listView.addFooterView(loaderFooter,null,false);
                this.listView.setAdapter(adapterDaftarPs);
            }
            super.onPreExecute();
        }


        @Override
        protected ArrayList<String> doInBackground(String... params) {
            ArrayList<String> listItem = new ArrayList<String>();
            try{
                JSONBuilderString jsonBuilder = new JSONBuilderString();
                JSONObject jsonObject = jsonBuilder.getJsonFromUrl(this.url);
                if(jsonObject != null){
                    JSONArray jsonArray = jsonObject.getJSONArray("daftar_ps");
                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id_ps = object.getString("id_ps");
                        String jenis_ps = "PS " + object.getString("jenis_ps");
                        String kelengkapan_ps = object.getString("kelengkapan_ps");
                        String keterangan_ps = object.getString("keterangan");

                        DataPs ps = new DataPs(id_ps,jenis_ps,kelengkapan_ps,keterangan_ps);
                        //dataPsArrayList.add(ps);
                        adapterDaftarPs.add(ps);
                    }
                }else {
                    listItem.clear();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return listItem;
        }

        @Override
        protected void onPostExecute(ArrayList<String > list){
            super.onPostExecute(list);
            adapterDaftarPs.notifyDataSetChanged();
            this.listView.removeFooterView(this.loaderFooter);
        }
    }
}
