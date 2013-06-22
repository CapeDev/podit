package com.thoughtworks.carpods;

import com.thoughtworks.carpods.plumb.AndroidModule;
import dagger.ObjectGraph;

import java.util.Arrays;
import java.util.List;

public class CarPodApplication extends PodApplication {

    private ObjectGraph graph;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = ObjectGraph.create(getModules().toArray());
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(new AndroidModule(this));
    }

    @Override
    public void inject(Object object) {
        graph.inject(object);
    }
}
