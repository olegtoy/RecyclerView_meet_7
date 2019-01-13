package com.practice.olegtojgildin.recyclerview_meet_7;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by olegtojgildin on 13/01/2019.
 */

public interface ViewHolderFactory {
    /**
     * Метод создания конкретного ViewHolder
     * @param parent родительская view
     * @param inflater {@link LayoutInflater} для получения объекта  view из xml
     * @return готовый объект класса {@link RecyclerView.ViewHolder}
     */
    RecyclerView.ViewHolder createViewHolder(ViewGroup parent, LayoutInflater inflater);
}
