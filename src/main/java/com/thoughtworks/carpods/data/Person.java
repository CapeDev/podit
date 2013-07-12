package com.thoughtworks.carpods.data;

public class Person implements Listable {

    private String firstName;
    private String lastName;
    private String homeLocation;
    private String aboutMe;
    private Long id = -1L;
    private String picture;

    private Person(Builder personBuilder) {
        this.firstName = personBuilder.firstName;
        this.lastName = personBuilder.lastName;
        this.homeLocation = personBuilder.homeLocation;
        this.aboutMe = personBuilder.aboutMe;
        this.id = personBuilder.id;
        this.picture = personBuilder.picture;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String iconPath() {
        return picture;
    }

    @Override
    public String label() {
        return String.format("%s %s", firstName, lastName);
    }

    public static class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private String homeLocation;
        private String aboutMe;
        private String picture = "";

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

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Person build() {
            return new Person(this);
        }

        public Builder picture(String path) {
            this.picture = path;
            return this;
        }
    }
}
