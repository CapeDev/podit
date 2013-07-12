package com.thoughtworks.carpods.data;

import java.util.ArrayList;
import java.util.List;

public class Pod implements Listable {

    private Long id;
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

    @Override
    public Long getId() {
        return id;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String label() {
        return podName;
    }

    @Override
    public String iconPath() {
        return "";
    }

    public static class Builder {
        private static final String CLAZZ_TAG = "Pod.Builder";
        private String name;
        private String homeLocation;
        private int departureTime;
        private int returnTime;
        private String about;
        private Long id = -1L;
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

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder member(Person newPodMember) {
            podMembers.add(newPodMember);
            return this;
        }

        public Builder members(List<Person> members) {
            podMembers = members;
            return this;
        }

        public Pod build() {
            return new Pod(this);
        }
    }
}
