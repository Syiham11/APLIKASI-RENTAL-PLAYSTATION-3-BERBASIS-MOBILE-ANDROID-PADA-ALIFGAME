package com.nua.rentalalifgame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by g40-70 on 16/09/2015.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    public static final int TYPE_HEAD = 0;
    public static final int TYPE_ROW = 1;

    private ArrayList<String> ListArray;
    private Context context;
    String email;

    public DrawerAdapter(Context context,ArrayList<String> ArrayList, String email){
        this.context = context;
        this.ListArray = ArrayList;
        this.email = email;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEAD){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_header,parent,false);
            return new ViewHolder(view,viewType);
        }else if(viewType == TYPE_ROW){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item,parent,false);
            return new ViewHolder(view,viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String row_text = ListArray.get(position);
        if(holder.viewType == TYPE_HEAD){
            holder.textView.setText(email);
        }else if(holder.viewType == TYPE_ROW){
            holder.textView.setText(row_text);
        }
    }

    @Override
    public int getItemCount() {
        return ListArray.size();
    }

    @Override
    public int getItemViewType(int position){

        if (position == 0) {
            return TYPE_HEAD;
        }else {
            return TYPE_ROW;
        }
    }

    public static class ViewHolder  extends RecyclerView.ViewHolder {

        protected int viewType;
        TextView textView;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            this.viewType = viewType;

            if(viewType == TYPE_HEAD){
                textView = (TextView) itemView.findViewById(R.id.text_email);
            }else if(viewType == TYPE_ROW){
                textView = (TextView) itemView.findViewById(R.id.drawer_menu_item);
            }
        }
    }
}
