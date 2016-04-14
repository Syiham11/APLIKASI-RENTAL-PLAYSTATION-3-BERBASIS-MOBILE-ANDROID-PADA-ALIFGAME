package com.nua.rentalalifgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anwar on 10/09/2015.
 */
public class AdapterDaftarPs extends ArrayAdapter<DataPs> {

    private ArrayList<DataPs> ListArrayPs;

    public AdapterDaftarPs(Context context, int resLayout, ArrayList<DataPs> ArrayListPs){
        super(context,resLayout,ArrayListPs);
        this.ListArrayPs = ArrayListPs;
        this.ListArrayPs.addAll(ArrayListPs);
    }

    public class ViewHolder{
        TextView jenis_ps;
        TextView kelengkapan_ps;
    }

    public void add(DataPs dataPs){
        this.ListArrayPs.add(dataPs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_design,null);

        holder = new ViewHolder();

        holder.jenis_ps  = (TextView) convertView.findViewById(R.id.jenis_ps);
        holder.kelengkapan_ps = (TextView) convertView.findViewById(R.id.kelengkapan_ps);

        convertView.setTag(holder);

        DataPs dataPs = this.ListArrayPs.get(position);
        holder.jenis_ps.setText(dataPs.getJenis_ps());
        holder.kelengkapan_ps.setText(dataPs.getKeterangan_ps());

        return convertView;
    }

}
