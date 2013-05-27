package com.thoughtworks.carpods.data;

import java.util.ArrayList;
import java.util.List;

public class Pod {

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
        private String name;
        private String homeLocation;
        private int departureTime;
        private int returnTime;
        private String about;

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

        public Pod build() {
            return new Pod(this);
        }
    }
}
