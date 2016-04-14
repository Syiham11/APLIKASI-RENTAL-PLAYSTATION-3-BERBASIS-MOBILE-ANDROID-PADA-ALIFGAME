package com.nua.rentalalifgame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by g40-70 on 16/09/2015.
 */
public class RecycleItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener listener;
    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }

    GestureDetector gestureDetector;

    public RecycleItemClickListener(Context context, OnItemClickListener listener){
        this.listener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if(childView != null && this.listener != null && gestureDetector.onTouchEvent(e)){
            this.listener.onItemClick(childView,rv.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
