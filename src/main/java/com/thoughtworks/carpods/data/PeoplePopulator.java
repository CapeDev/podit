package com.thoughtworks.carpods.data;

import android.content.Context;

public class PeoplePopulator {

    Context context;

    public PeoplePopulator(Context context) {
        this.context = context;
    }

    public void populate() {
        CarPodsDatabase database = new CarPodsDatabase(context);
        database.open();
        database.savePerson(createPerson("Alice", "Jones", "Residence Inn", "Silly"));
        database.savePerson(createPerson("Bob", "Smith", "Residence Inn", "Smart"));
        database.savePerson(createPerson("Charlie", "White", "Renaissance Hotel", "Kind"));
        database.savePerson(createPerson("David", "Washington", "Renaissance Hotel", "Caring"));
        database.close();
    }

    private Person createPerson(String firstname, String lastName, String location, String about) {
        return new Person.Builder()
                                  .firstName(firstname)
                                  .lastName(lastName)
                                  .homeLocation(location)
                                  .aboutMe(about)
                                  .build();
    }
}
