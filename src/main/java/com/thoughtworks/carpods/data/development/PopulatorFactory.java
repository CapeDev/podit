package com.thoughtworks.carpods.data.development;

import android.content.Context;

public class PopulatorFactory {
    public Populator createPeoplePopulator(Context context){
        return new PeoplePopulator(context);
    }
    public Populator createPodPopulator(Context context){
        return new PodPopulator(context);
    }
}
