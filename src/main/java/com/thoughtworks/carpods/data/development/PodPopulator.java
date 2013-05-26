package com.thoughtworks.carpods.data.development;

import android.content.Context;
import com.thoughtworks.carpods.R;
import com.thoughtworks.carpods.data.CarPodsDatabase;
import com.thoughtworks.carpods.data.Pod;

public class PodPopulator implements Populator {

    private final Context context;

    public PodPopulator(Context context) {
        this.context = context;
    }

    @Override
    public void populate() {
        CarPodsDatabase database = new CarPodsDatabase(context);
        database.open();
        database.savePod(createPod("Ren Runners", "The Renaissance", 800, 1800));
        database.savePod(createPod("The kids in the Res", "Residence Inn", 745, 1800));
        database.savePod(createPod("The Original Ren", "Residence Inn", 700, 1900));
        database.close();
    }

    private Pod createPod(String name, String homeLocation, int departurnTime, int returnTime) {
        Pod.Builder newPod = new Pod.Builder();
        newPod.name(name);
        newPod.homeLocation(homeLocation);
        newPod.departureTime(departurnTime);
        newPod.returnTIme(returnTime);
        return newPod.build();
    }
}