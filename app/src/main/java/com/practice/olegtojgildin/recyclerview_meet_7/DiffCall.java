package com.practice.olegtojgildin.recyclerview_meet_7;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by olegtojgildin on 10/01/2019.
 */

public class DiffCall extends DiffUtil.Callback {
    private List<Worker> mOldList;
    private List<Worker> mNewList;

    public DiffCall(List<Worker> oldList, List<Worker> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getId() == mNewList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).equals(mNewList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Worker newWorker = mNewList.get(newItemPosition);
        Worker oldWorket = mOldList.get(oldItemPosition);
        Bundle diff = new Bundle();
        if (!newWorker.getAge().equals(oldWorket.getAge())) {
            diff.putString("age", newWorker.getAge());
        }
        if (!(newWorker.getName().equals(oldWorket.getName()))) {
            diff.putString("Name", newWorker.getName());
        }
        if (!(newWorker.getPosition().equals(oldWorket.getPosition()))) {
            diff.putString("position", newWorker.getName());
        }
        if (!(newWorker.getPhoto().equals(oldWorket.getPhoto()))) {
            diff.putString("photo", Integer.toString(newWorker.getPhoto()));
        }
        if (diff.size() == 0) {
            return null;
        }
        return diff;
    }
}
