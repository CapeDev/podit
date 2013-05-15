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

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }

    public String getPodHomeLocation() {
        return podHomeLocation;
    }

    public void setPodHomeLocation(String podHomeLocation) {
        this.podHomeLocation = podHomeLocation;
    }

    public String getPodDepartureTime() {
        return podDepartureTime;
    }

    public void setPodDepartureTime(String podDepartureTime) {
        this.podDepartureTime = podDepartureTime;
    }

    public String getPodReturnTime() {
        return podReturnTime;
    }

    public void setPodReturnTime(String podReturnTime) {
        this.podReturnTime = podReturnTime;
    }

    public String getAboutPod() {
        return aboutPod;
    }

    public void setAboutPod(String aboutPod) {
        this.aboutPod = aboutPod;
    }

    public void addMember(Person member) {
        this.members.add(member);
    }
}
