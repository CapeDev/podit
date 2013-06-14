package com.thoughtworks.carpods.data.development;

import android.content.Context;
import com.thoughtworks.carpods.data.Pod;
import com.thoughtworks.carpods.data.PodDataAccess;

public class PodPopulator implements Populator {

    private final Context context;

    public PodPopulator(Context context) {
        this.context = context;
    }

    @Override
    public void populate() {
        PodDataAccess database = new PodDataAccess(context);
        database.savePod(createPod("Ren Runners", "The Renaissance", 800, 1800, "We run. a lot."));
        database.savePod(createPod("The kids in the Res", "Residence Inn", 745, 1800, "We're all staying at the Residence Inn."));
        database.savePod(createPod("The Original Ren", "Residence Inn", 700, 1900, "Is there really anything better?"));
    }

    private Pod createPod(String name, String homeLocation, int departurnTime, int returnTime, String about) {
        Pod.Builder newPod = new Pod.Builder();
        newPod.name(name);
        newPod.homeLocation(homeLocation);
        newPod.departureTime(departurnTime);
        newPod.returnTime(returnTime);
        newPod.about(about);
        return newPod.build();
    }
}