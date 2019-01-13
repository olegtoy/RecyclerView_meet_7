package com.practice.olegtojgildin.recyclerview_meet_7;

/**
 * Created by olegtojgildin on 11/01/2019.
 */

public enum ItemTypes implements BaseItem {
    Item1(0),Item2(1),Item3(2);
    private int mType;

    ItemTypes(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }
}
