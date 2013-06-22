package com.thoughtworks.carpods.data;

import android.content.Context;

import javax.inject.Inject;

public class DataAccessFactory {

    @Inject DatabaseFactory database;

    public PeopleDataAccess people(Context context) {
        return new PeopleDataAccess(database.instance(context));
    }

    public PodDataAccess pods(Context context) {
        return new PodDataAccess(database.instance(context));
    }
}
