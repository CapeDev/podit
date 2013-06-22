package com.thoughtworks.carpods.data.development;

import android.content.Context;
import com.thoughtworks.carpods.data.Person;
import com.thoughtworks.carpods.data.Pod;
import com.thoughtworks.carpods.data.PodDataAccess;

import java.util.Arrays;
import java.util.List;

public class PodPopulator implements Populator {

    private final Context context;

    public PodPopulator(Context context) {
        this.context = context;
    }

    @Override
    public void populate() {
        PodDataAccess database = new PodDataAccess(context);
        List<Person> renRunnersMembers = Arrays.asList(
            createPerson("Alice", "Jones", "Residence Inn", "Silly"),
            createPerson("Bob", "Smith", "Residence Inn", "Smart"),
            createPerson("Charlie", "White", "Renaissance Hotel", "Kind")
        );
        database.savePod(createPod("Ren Runners", "The Renaissance", 800, 1800, "We run. a lot.", renRunnersMembers));
        List<Person> kidsInTheResMembers = Arrays.asList(
            createPerson("David", "Washington", "Renaissance Hotel", "Caring"),
            createPerson("Han", "Solo", "Hilton Anatole", "Scruffy Nerf Herder"),
            createPerson("Luke", "Skywalker", "Hilton Anatole", "Young Jedi")
        );
        database.savePod(createPod("The kids in the Res", "Residence Inn", 745, 1800, "We're all staying at the Residence Inn.", kidsInTheResMembers));
        List<Person> originalRenMembers = Arrays.asList(
            createPerson("Princess", "Leia", "Hilton Anatole", "Luke's Sister"),
            createPerson("Darth", "Vader", "Hilton Anatole", "Luke's Father")
        );
        database.savePod(createPod("The Original Ren", "Residence Inn", 700, 1900, "Is there really anything better?", originalRenMembers));
    }

    private Pod createPod(String name, String homeLocation, int departureTime, int returnTime, String about, List<Person> members) {
        Pod.Builder newPod = new Pod.Builder();
        newPod.name(name);
        newPod.homeLocation(homeLocation);
        newPod.departureTime(departureTime);
        newPod.returnTime(returnTime);
        newPod.about(about);

        for (Person member : members) {
            newPod.member(member);
        }

        return newPod.build();
    }

    private Person createPerson(String firstname, String lastName, String location, String about) {
        return new Person.Builder()
                .firstName(firstname)
                .lastName(lastName)
                .homeLocation(location)
                .aboutMe(about)
                .picture("")
                .build();
    }
}