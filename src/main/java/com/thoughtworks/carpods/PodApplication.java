package com.thoughtworks.carpods;

import android.app.Application;
import dagger.ObjectGraph;

import java.util.Arrays;
import java.util.List;

public class PodApplication extends Application {

    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = ObjectGraph.create(getModules().toArray());
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new AndroidModule(this));
    }

    public void inject(Object object) {
        graph.inject(object);
    }
}
