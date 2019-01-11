package com.practice.olegtojgildin.recyclerview_meet_7;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;
    private static final int TYPE_THREE = 2;


    public CustomAdapter(List<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public int getItemViewType(int position) {

        if (workers.get(position).getType() == 0)
            return TYPE_ONE;
        else if (workers.get(position).getType() == 1)
            return TYPE_TWO;
        else if (workers.get(position).getType() == 2)
            return TYPE_THREE;
        else
            return -1;
    }

    @Override
    public void onViewMoved(int oldPosition, int newPosition) {
        Worker worker = workers.get(oldPosition);
        List<Worker> newlist = new ArrayList<>(workers);
        newlist.remove(oldPosition);
        newlist.add(newPosition, worker);
        onNewData(newlist);
    }

    @Override
    public void onViewSwiped(int position) {
        List<Worker> newlist = new ArrayList<>(workers);
        newlist.remove(position);
        onNewData(newlist);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_1, parent, false);
            return new ViewHolder1(view);
        } else if (viewType == TYPE_TWO) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_2, parent, false);
            return new ViewHolder2(view);
        } else if (viewType == TYPE_THREE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_3, parent, false);
            return new ViewHolder3(view);
        } else {
            throw new RuntimeException("The type has to be ONE or TWO");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof ViewHolder1)
            initLayout1((ViewHolder1) holder, i);
        if (holder instanceof ViewHolder2)
            initLayout2((ViewHolder2) holder, i);
        if (holder instanceof ViewHolder3)
            initLayout3((ViewHolder3) holder, i);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            if (holder instanceof ViewHolder1)
                for (String key : o.keySet()) {
                    if (key.equals("Name"))
                        ((ViewHolder1) holder).name.setText(workers.get(position).getName());
                    if (key.equals("Age"))
                        ((ViewHolder1) holder).age.setText(workers.get(position).getAge());
                    if (key.equals("position"))
                        ((ViewHolder1) holder).position.setText(workers.get(position).getPosition());
                }
            if (holder instanceof ViewHolder2)
                for (String key : o.keySet()) {
                    if (key.equals("Name"))
                        ((ViewHolder2) holder).name.setText(workers.get(position).getName());
                    if (key.equals("Age"))
                        ((ViewHolder2) holder).age.setText(workers.get(position).getAge());
                    if (key.equals("position"))
                        ((ViewHolder2) holder).position.setText(workers.get(position).getPosition());
                    if (key.equals("photo"))
                        ((ViewHolder2) holder).photo.setImageResource(workers.get(position).getPhoto());
                }
            if (holder instanceof ViewHolder3)
                for (String key : o.keySet()) {
                    if (key.equals("Name"))
                        ((ViewHolder3) holder).name.setText(workers.get(position).getName());
                    if (key.equals("Age"))
                        ((ViewHolder3) holder).age.setText(workers.get(position).getAge());
                    if (key.equals("position"))
                        ((ViewHolder3) holder).position.setText(workers.get(position).getPosition());
                    if (key.equals("photo"))
                        ((ViewHolder3) holder).photo.setImageResource(workers.get(position).getPhoto());
                }
        }
    }

    public void onNewData(List<Worker> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCall(workers, newData));
        diffResult.dispatchUpdatesTo(CustomAdapter.this);
        workers.clear();
        workers.addAll(newData);
    }

    public void initLayout1(ViewHolder1 holder, int i) {
        holder.name.setText("Name: " + workers.get(i).getName());
        holder.age.setText("Age: " + workers.get(i).getAge());
        holder.position.setText("Position: " + workers.get(i).getPosition());
    }

    public void initLayout2(ViewHolder2 holder, int i) {
        holder.name.setText("Name: " + workers.get(i).getName());
        holder.age.setText("Age: " + workers.get(i).getAge());
        holder.position.setText("Position: " + workers.get(i).getPosition());
        holder.photo.setImageResource(workers.get(i).getPhoto());
    }

    public void initLayout3(ViewHolder3 holder, int i) {
        holder.name.setText("Name: " + workers.get(i).getName());
        holder.age.setText("Age: " + workers.get(i).getAge());
        holder.position.setText("Position: " + workers.get(i).getPosition());
        holder.photo.setImageResource(workers.get(i).getPhoto());
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
