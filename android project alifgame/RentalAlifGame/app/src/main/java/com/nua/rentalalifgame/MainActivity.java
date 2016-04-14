package com.nua.rentalalifgame;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    DrawerLayout drawerLayout;
    DrawerAdapter drawerAdapter;
    RecyclerView drawerMenu;
    ArrayAdapter adapter;
    Toolbar toolbar;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public String id_pengguna, email_pengguna;
    public int position_f;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_pengguna = getIntent().getExtras().getString("id_pengguna");
        email_pengguna = getIntent().getExtras().getString("email_pengguna");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerMenu = (RecyclerView) findViewById(R.id.drawer_menu);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        String[] menu = getResources().getStringArray(R.array.menu);
        ArrayList<String> array_menu = new ArrayList<String>();
        for(int i = 0;i<menu.length;i++){
            array_menu.add(menu[i]);
        }
        //adapter = new ArrayAdapter(this,R.layout.drawer_list_item,array_menu);
        drawerAdapter = new DrawerAdapter(this,array_menu,email_pengguna);

        int offsetWidth = getActionBarHeight() + 30;
        int width = getResources().getDisplayMetrics().widthPixels - offsetWidth;
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) drawerMenu.getLayoutParams();
        params.width = width;
        drawerMenu.setLayoutParams(params);
        drawerMenu.setAdapter(drawerAdapter);
        drawerMenu.setHasFixedSize(true);
        drawerMenu.setLayoutManager(new LinearLayoutManager(this));
        drawerMenu.addOnItemTouchListener(new RecycleItemClickListener(this, new RecycleItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(position != 0) {
                    drawerLayout.closeDrawers();
                    if (position_f != position) {
                        position_f = position;
                        Bundle args = new Bundle();
                        args.putInt("Menu", position_f);
                        Fragment detail = new MainFragment();
                        detail.setArguments(args);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
                        if(position_f == 1){
                            toolbar.setTitle("Rental Alif Game");
                        }else if(position == 2){
                            toolbar.setTitle("Profil Alif");
                        }else if(position == 3){
                            toolbar.setTitle("Pengaturan Akun");
                        }else{
                            toolbar.setTitle("Rental Alif Game");
                        }
                    }
                }
            }
        }));

        position_f = 1;

        Bundle args = new Bundle();
        args.putInt("Menu", position_f);
        args.putString("id_pengguna", id_pengguna);
        args.putString("email_pengguna",email_pengguna);
        Fragment detail = new MainFragment();
        detail.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, detail).commit();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    private int getActionBarHeight() {
        int actionBarHeight = getSupportActionBar().getHeight();
        if (actionBarHeight != 0)
            return actionBarHeight;
        final TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
                actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        } else if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        return actionBarHeight;
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(drawerMenu)){
            drawerLayout.closeDrawers();
        }else {
            finish();
        }
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

        return super.onOptionsItemSelected(item);
    }

    public static class MainFragment extends Fragment implements OnMapReadyCallback{

        private int mPage;
        private String mId_pengguna, mEmail_pengguna;
        private GoogleMap googleMap;
        private MapView mapView;

        @Override
        public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            mPage = getArguments().getInt("Menu");
            mId_pengguna = getArguments().getString("id_pengguna");
            mEmail_pengguna = getArguments().getString("email_pengguna");
            View view = null;
            switch (mPage){
                default:
                case 1 :
                    view = inflater.inflate(R.layout.fragment_home, container, false);
                    MapsInitializer.initialize(getActivity());
                    switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())) {
                        case ConnectionResult.SUCCESS:
                            mapView = (MapView) view.findViewById(R.id.frame_maps);
                            mapView.onCreate(savedInstanceState);
                            mapView.getMapAsync(this);

                            /*if (mapView != null) {
                                googleMap = mapView.getMap();
                                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                                googleMap.setMyLocationEnabled(true);
                                googleMap.getUiSettings().setZoomGesturesEnabled(true);
                                googleMap.getUiSettings().setZoomControlsEnabled(true);
                            }*/
                            break;
                        case ConnectionResult.SERVICE_MISSING:
                            break;
                        case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                            break;
                        default:
                    }

                    Button buttonDaftarPs = (Button) view.findViewById(R.id.btn_pemesanan);
                    Button buttonGantiAlamat = (Button) view.findViewById(R.id.btn_ganti_alamat);
                    Button buttonDaftarPesanan = (Button) view.findViewById(R.id.btn_daftar_pesanan);
                    buttonDaftarPs.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),DaftarPsActivity.class);
                            intent.putExtra("id_pengguna",mId_pengguna);
                            intent.putExtra("email_pengguna",mEmail_pengguna);
                            startActivity(intent);
                        }
                    });
                    buttonGantiAlamat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),GantiAlamatActivity.class);
                            intent.putExtra("id_pengguna",mId_pengguna);
                            intent.putExtra("email_pengguna",mEmail_pengguna);
                            startActivity(intent);
                        }
                    });
                    buttonDaftarPesanan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),DaftarRentalActivity.class);
                            intent.putExtra("id_pengguna",mId_pengguna);
                            intent.putExtra("email_pengguna",mEmail_pengguna);
                            startActivity(intent);
                        }
                    });
                    break;
                case 2 :
                    view = inflater.inflate(R.layout.fragment_alif,container,false);
                    break;
                case 3:
                    view = inflater.inflate(R.layout.fragment_ubah_password,container,false);

                    final EditText editText = (EditText) view.findViewById(R.id.usr_ganti_password);
                    Button buttonGantiPwd = (Button) view.findViewById(R.id.button_ganti_pwd);
                    buttonGantiPwd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String pwd_baru = editText.getText().toString();
                            if(!pwd_baru.equals("")) {
                                new AsyncGantiPwd(pwd_baru,mId_pengguna,getActivity()).execute();
                            }else{
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("Perhatian")
                                        .setMessage("Maaf, anda harus mengisi password baru.")
                                        .setNegativeButton("ULANGI", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .show();
                            }
                        }
                    });
                    break;
                case 4 :
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    intent.putExtra("logout","logout");
                    startActivity(intent);
                    break;
            }
            return view;
        }

        @Override
        public void onMapReady(GoogleMap gMap){
            if(!mapView.equals(null)) {
                gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                gMap.getUiSettings().setMyLocationButtonEnabled(true);
                gMap.setMyLocationEnabled(true);
                gMap.getUiSettings().setZoomGesturesEnabled(true);
                gMap.getUiSettings().setZoomControlsEnabled(true);
                googleMap = gMap;
            }
        }

        @Override
        public void onResume(){
            if (mPage == 1 && !mapView.equals(null)) {
                switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())) {
                    case ConnectionResult.SUCCESS:
                        mapView.onResume();
                        mapView.getMapAsync(this);
                        break;
                    case ConnectionResult.SERVICE_MISSING:
                        break;
                    case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                        break;
                    default:
                }
            }
            super.onResume();
        }

        @Override
        public  void onPause(){
            if(mPage == 1 && !mapView.equals(null)) {
                switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())) {
                    case ConnectionResult.SUCCESS:
                        mapView.onPause();
                        mapView.getMapAsync(this);
                        break;
                    case ConnectionResult.SERVICE_MISSING:
                        break;
                    case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                        break;
                    default:
                }
            }
            super.onPause();
        }

        @Override
        public void onDestroy(){
            super.onDestroy();
            //mapView.onDestroy();
        }

    }


    public static class AsyncGantiPwd extends AsyncTask<String,String,String>{

        String url,pwd_baru,id_peng,pesan;
        Activity activity;
        ProgressDialog progressDialog;

        public AsyncGantiPwd(String pass_bar, String id_p,Activity activity){
            this.pwd_baru = pass_bar;
            this.id_peng = id_p;
            this.activity = activity;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog =new ProgressDialog(this.activity);
            progressDialog.setIndeterminate(false);
            progressDialog.setMessage("Proses ubah...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            this.url = "http://www.alifgame.16mb.com/mobile_post_ganti_password.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String error = "";
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            try{
                JSONBuilderString jparse = new JSONBuilderString();
                nameValuePairs.add(new BasicNameValuePair("id_pengguna",id_peng));
                nameValuePairs.add(new BasicNameValuePair("password",pwd_baru));
                JSONObject jsonObject = jparse.getJsonFromUrl(this.url,nameValuePairs);
                error = jsonObject.getString("error");
                pesan = jsonObject.getString("pesan");
                return error;
            } catch (JSONException e) {
                //e.printStackTrace();
                return error;
            }
        }

        @Override
        public void onPostExecute(String error){
            super.onPostExecute(error);
            progressDialog.dismiss();
            if(error.equals("0")){
                new AlertDialog.Builder(this.activity)
                        .setTitle("Sukses")
                        .setMessage(pesan.toString())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(activity,LoginActivity.class);
                                intent.putExtra("logout","logout");
                                activity.startActivity(intent);
                            }
                        })
                .show();
            }else {
                new AlertDialog.Builder(this.activity)
                        .setTitle("Gagal")
                        .setMessage(pesan.toString())
                        .setPositiveButton("ULANGI", new DialogInterface.OnClickListener() {
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
