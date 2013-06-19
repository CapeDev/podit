package com.thoughtworks.carpods.data;

import android.content.Context;

import javax.inject.Inject;

public class DatabaseFactory {
    @Inject public DatabaseFactory() { }

    public PodItDatabase instance(Context context) {
        return PodItDatabase.getInstance(context);
    }
}
