package com.thoughtworks.carpods.data.development;

import android.content.Context;
import com.thoughtworks.carpods.data.PeopleDataAccess;
import com.thoughtworks.carpods.data.Person;

public class PeoplePopulator implements Populator {

    Context context;

    public PeoplePopulator(Context context) {
        this.context = context;
    }

    public void populate() {
        PeopleDataAccess database = new PeopleDataAccess(context);
        database.savePerson(createPerson("Alice", "Jones", "Residence Inn", "Silly"));
        database.savePerson(createPerson("Bob", "Smith", "Residence Inn", "Smart"));
        database.savePerson(createPerson("Charlie", "White", "Renaissance Hotel", "Kind"));
        database.savePerson(createPerson("David", "Washington", "Renaissance Hotel", "Caring"));
        database.savePerson(createPerson("Han", "Solo", "Hilton Anatole", "Scruffy Nerf Herder"));
        database.savePerson(createPerson("Luke", "Skywalker", "Hilton Anatole", "Young Jedi"));
        database.savePerson(createPerson("Princess", "Leia", "Hilton Anatole", "Luke's Sister"));
        database.savePerson(createPerson("Darth", "Vader", "Hilton Anatole", "Luke's Father"));
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