package com.practice.olegtojgildin.recyclerview_meet_7;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        MyItemTouchHelper.ItemTouchHelperAdapter {
    private List<Worker> workers;
    private final List<ViewHolderBinder> mBinder;
    private SparseArray<ViewHolderFactory> mFactoryMap;


    public CustomAdapter(List<Worker> workers) {
        this.workers = workers;
        mBinder = new ArrayList<>();
        initFactory();
        setData(workers);
    }

    public void initFactory() {
        mFactoryMap = new SparseArray<>();
        mFactoryMap.put(ItemTypes.Item1.getType(), new Item1ViewHolderFactory());
        mFactoryMap.put(ItemTypes.Item2.getType(), new Item2ViewHolderFactory());
        mFactoryMap.put(ItemTypes.Item3.getType(), new Item3ViewHolderFactory());
    }

    @Override
    public int getItemViewType(int position) {

        if (workers.get(position).getType() == 0)
            return ItemTypes.Item1.getType();
        else if (workers.get(position).getType() == 1)
            return ItemTypes.Item2.getType();
        else if (workers.get(position).getType() == 2)
            return ItemTypes.Item3.getType();
        else
            return -1;
    }

    public ViewHolderBinder generateBinder(BaseItem item) {
        if (item.getType() == ItemTypes.Item1.getType()) {
            return new ViewHolder1Binder(item, ItemTypes.Item1.getType());
        } else if (item.getType() == ItemTypes.Item2.getType()) {
            return new ViewHolder2Binder(item, ItemTypes.Item2.getType());
        } else if (item.getType() == ItemTypes.Item3.getType()) {
            return new ViewHolder3Binder(item, ItemTypes.Item3.getType());
        }
        return null;
    }

    @Override
    public void onViewMoved(int oldPosition, int newPosition) {
        Worker worker = workers.get(oldPosition);
        List<Worker> newlist = new ArrayList<>();
        newlist.addAll(workers);
        newlist.remove(oldPosition);
        newlist.add(newPosition, worker);
        setData(newlist);

    }

    @Override
    public void onViewSwiped(int position) {
        List<Worker> newlist = new ArrayList<>(workers);
        newlist.remove(position);
        setData(newlist);
    }

    private void setData(List<Worker> items) {
        mBinder.clear();
        for (BaseItem item : items) {
            mBinder.add(generateBinder(item));
        }
        onNewData(items);
    }

    public void onNewData(List<Worker> newData) {
        List<Worker> tempList = new ArrayList<>(newData);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCall(workers, newData));
        diffResult.dispatchUpdatesTo(CustomAdapter.this);
        workers.clear();
        workers.addAll(tempList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderFactory factory = mFactoryMap.get(viewType);
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return factory.createViewHolder(parent, inflater);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        ViewHolderBinder binder = mBinder.get(i);
        if (binder != null) {
            binder.bindViewHolder(holder);
        }
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView age;
        public TextView position;

        public ViewHolder1(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            age = v.findViewById(R.id.age);
            position = v.findViewById(R.id.position);
        }
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView age;
        public TextView position;
        public ImageView photo;

        public ViewHolder2(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            age = v.findViewById(R.id.age);
            position = v.findViewById(R.id.position);
            photo = v.findViewById(R.id.photo);

        }
    }

    public static class ViewHolder3 extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView age;
        public TextView position;
        public ImageView photo;

        public ViewHolder3(View v) {
            super(v);
            name = v.findViewById(R.id.name);
            age = v.findViewById(R.id.age);
            position = v.findViewById(R.id.position);
            photo = v.findViewById(R.id.photo);

        }
    }

}
