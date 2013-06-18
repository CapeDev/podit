package com.thoughtworks.carpods.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Pod {

    private final int id;
    private String podName;
    private String podHomeLocation;
    private int podDepartureTime;
    private int podReturnTime;
    private String aboutPod;
    private List<Person> members;

    public Pod(Builder podBuilder) {
        this.podName = podBuilder.name;
        this.podHomeLocation = podBuilder.homeLocation;
        this.podDepartureTime = podBuilder.departureTime;
        this.podReturnTime = podBuilder.returnTime;
        this.aboutPod = podBuilder.about;
        this.id = podBuilder.id;
        this.members = podBuilder.podMembers;
    }

    public String getName() {
        return podName;
    }

    public String getHomeLocation() {
        return podHomeLocation;
    }

    public int getDepartureTime() {
        return podDepartureTime;
    }

    public int getReturnTime() {
        return podReturnTime;
    }

    public String getAboutPod() {
        return aboutPod;
    }

    public static class Builder {
        private static final String CLAZZ_TAG = "Pod.Builder";
        private String name;
        private String homeLocation;
        private int departureTime;
        private int returnTime;
        private String about;
        private int id = -1;
        private List<Person> podMembers = new ArrayList<Person>();

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder homeLocation(String homeLocation) {
            this.homeLocation = homeLocation;
            return this;
        }

        public Builder departureTime(int departureTime) {
            this.departureTime = departureTime;
            return this;
        }

        public Builder returnTime(int returnTime) {
            this.returnTime = returnTime;
            return this;
        }

        public Builder about(String about) {
            this.about = about;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder member(Person newPodMember) {
            podMembers.add(newPodMember);
            return this;
        }

        public Pod build() {
            return new Pod(this);
        }
    }
}
