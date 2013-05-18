package com.thoughtworks.carpods.data;

import java.util.ArrayList;
import java.util.List;

public class Pod {

    private String podName;
    private String podHomeLocation;
    private String podDepartureTime;
    private String podReturnTime;
    private String aboutPod;
    private List<Person> members;

    public Pod(String podName, String podHomeLocation, String podDepartureTime, String podReturnTime, String aboutPod, Person... members) {
        this.podName = podName;
        this.podHomeLocation = podHomeLocation;
        this.podDepartureTime = podDepartureTime;
        this.podReturnTime = podReturnTime;
        this.aboutPod = aboutPod;
        this.members = new ArrayList<Person>();
        for(Person member : members) {
            this.members.add(member);
        }
    }

    public String getName() {
        return podName;
    }

    public String getHomeLocation() {
        return podHomeLocation;
    }

    public String getDepartureTime() {
        return podDepartureTime;
    }

    public String getReturnTime() {
        return podReturnTime;
    }

    public String getAboutPod() {
        return aboutPod;
    }
}
