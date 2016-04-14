package com.nua.rentalalifgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anwar on 13/09/2015.
 */
public class AdapterDetail extends BaseAdapter {

    ArrayList<String> list = new ArrayList<String>();
    ArrayList<KategoriHarga> kategoriHargaArrayList;

    private static final int TYPE_DESC = 0;
    private static final int TYPE_KATEGORI_HARGA = 1;

    private Context context;

    public AdapterDetail(Context context){
        this.context = context;
        kategoriHargaArrayList = new ArrayList<KategoriHarga>();
    }

    private class ViewHolder{
        TextView jenis_ps;
        TextView kelengkapan_ps;
        TextView keterangan_ps;
        TextView nama_kategori;
        TextView harga;
    }

    public void addItem(String item){
        this.list.add(item);
    }

    public void addKategori(KategoriHarga kategoriHarga){
        this.kategoriHargaArrayList.add(kategoriHarga);
    }

    @Override
    public int getItemViewType(int position){
        if (position == TYPE_DESC){
            return TYPE_DESC;
        }else {
            return TYPE_KATEGORI_HARGA;
        }
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position).toString();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(position == TYPE_DESC){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.detail_design,null);

            holder = new ViewHolder();
            holder.jenis_ps = (TextView) convertView.findViewById(R.id.detail_jenis_ps);
            holder.kelengkapan_ps = (TextView) convertView.findViewById(R.id.detail_kelengkapan_ps);
            holder.keterangan_ps = (TextView) convertView.findViewById(R.id.detail_keterangan_ps);

            convertView.setTag(holder);

            holder.jenis_ps.setText(DetailPsActivity.JENIS_PS);
            holder.kelengkapan_ps.setText(DetailPsActivity.KELENGKAPAN_PS);
            holder.keterangan_ps.setText(DetailPsActivity.KETERANGAN_PS);

            return convertView;

        }else{

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.detail_row_design,null);

            holder = new ViewHolder();
            holder.nama_kategori = (TextView) convertView.findViewById(R.id.row_nama_kategori);
            holder.harga = (TextView) convertView.findViewById(R.id.row_harga_kategori);

            convertView.setTag(holder);

            KategoriHarga kategoriHarga = this.kategoriHargaArrayList.get(position - 1);
            holder.nama_kategori.setText(kategoriHarga.getNama_kategori());
            holder.harga.setText(kategoriHarga.getHarga_kategori());

            return convertView;
        }
    }
}
