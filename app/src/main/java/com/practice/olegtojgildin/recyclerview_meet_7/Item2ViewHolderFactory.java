package com.practice.olegtojgildin.recyclerview_meet_7;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by olegtojgildin on 13/01/2019.
 */

public class Item2ViewHolderFactory implements ViewHolderFactory {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View itemView=inflater.inflate(R.layout.list_item_2,parent,false);
        RecyclerView.ViewHolder holder=new CustomAdapter.ViewHolder2(itemView);
        return holder;
    }
}
