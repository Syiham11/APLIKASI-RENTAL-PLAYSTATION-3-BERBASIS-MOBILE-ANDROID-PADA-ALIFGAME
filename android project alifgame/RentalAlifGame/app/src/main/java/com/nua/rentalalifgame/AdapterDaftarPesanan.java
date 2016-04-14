package com.nua.rentalalifgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by g40-70 on 16/09/2015.
 */
public class AdapterDaftarPesanan extends ArrayAdapter {

    private ArrayList<DaftarPesanan> ListArrayPs;

    public AdapterDaftarPesanan(Context context, int resLayout, ArrayList<DaftarPesanan> ArrayListPs){
        super(context,resLayout,ArrayListPs);
        this.ListArrayPs = ArrayListPs;
        this.ListArrayPs.addAll(ArrayListPs);
    }

    public class ViewHolder{
        TextView jenis_ps;
        TextView tanggal;
        TextView status;
        TextView harga;
        TextView kategori;
    }

    public void add(DaftarPesanan dataPs){
        this.ListArrayPs.add(dataPs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_design_rental,null);

        holder = new ViewHolder();

        holder.jenis_ps  = (TextView) convertView.findViewById(R.id.rental_jenis_ps);
        holder.kategori = (TextView) convertView.findViewById(R.id.rental_kategori_harga);
        holder.tanggal  = (TextView) convertView.findViewById(R.id.rental_tanggal);
        holder.harga = (TextView) convertView.findViewById(R.id.rental_harga);
        holder.status  = (TextView) convertView.findViewById(R.id.rental_status);


        convertView.setTag(holder);

        DaftarPesanan dataPs = this.ListArrayPs.get(position);
        holder.jenis_ps.setText(dataPs.getJenis_ps());
        holder.kategori.setText(dataPs.getKategori_ps());
        holder.tanggal.setText(dataPs.getTanggal());
        holder.harga.setText(dataPs.getHarga());
        holder.status.setText(dataPs.getStatus_pesanan());

        return convertView;
    }


}
