package com.practice.olegtojgildin.recyclerview_meet_7;

import android.support.v7.widget.RecyclerView;

/**
 * Created by olegtojgildin on 13/01/2019.
 */

public class ViewHolder3Binder extends ViewHolderBinder {

    public final Worker worker;

    public ViewHolder3Binder(BaseItem item, int viewType){
        super(viewType);
        worker=(Worker)item;
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder) {
        CustomAdapter.ViewHolder3 myViewHolder =(CustomAdapter.ViewHolder3)holder;
        myViewHolder.position.setText("Position: " +worker.getPosition());
        myViewHolder.age.setText("Age: " +worker.getAge());
        myViewHolder.name.setText("Name: " +worker.getName());
        myViewHolder.photo.setImageResource(worker.getPhoto());
    }

    @Override
    public BaseItem getItem() {
        return worker;
    }
}
