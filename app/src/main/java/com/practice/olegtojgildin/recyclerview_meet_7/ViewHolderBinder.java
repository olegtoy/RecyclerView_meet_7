package com.practice.olegtojgildin.recyclerview_meet_7;

import android.support.v7.widget.RecyclerView;

/**
 * Created by olegtojgildin on 13/01/2019.
 */

public abstract class ViewHolderBinder {
    protected final int viewType;

    public ViewHolderBinder(int viewType){
        this.viewType=viewType;
    }

    public abstract void bindViewHolder(RecyclerView.ViewHolder holder);

    public abstract BaseItem getItem();

}
