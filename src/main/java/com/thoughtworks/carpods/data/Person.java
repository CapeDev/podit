package com.thoughtworks.carpods.data;

public class Person {

    private String firstName;
    private String lastName;
    private String homeLocation;
    private String aboutMe;
    private int id = -1;

    private Person(Builder personBuilder) {
        this.firstName = personBuilder.firstName;
        this.lastName = personBuilder.lastName;
        this.homeLocation = personBuilder.homeLocation;
        this.aboutMe = personBuilder.aboutMe;
        this.id = personBuilder.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public int getId() {
        return id;
    }


    public static class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private String homeLocation;
        private String aboutMe;

        public Builder firstName(String firstName) {
            this.firstName = (firstName == null) ? "" : firstName ;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = (lastName == null) ? "" : lastName ;
            return this;
        }

        public Builder homeLocation(String homeLocation) {
            this.homeLocation = (homeLocation == null) ? "" : homeLocation ;
            return this;
        }

        public Builder aboutMe(String aboutMe) {
            this.aboutMe = (aboutMe == null) ? "" : aboutMe ;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
