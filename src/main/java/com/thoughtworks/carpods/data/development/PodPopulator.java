package com.thoughtworks.carpods.data.development;

import android.content.Context;
import com.thoughtworks.carpods.data.PeopleDataAccess;
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
        populatePeople();
        PodDataAccess podDataAccess = new PodDataAccess(context);

        List<Person> renRunnersMembers = getMembersStartingWithId(1);
        podDataAccess.savePod(createPod("Ren Runners", "The Renaissance", 800, 1800, "We run. a lot.", renRunnersMembers));

        List<Person> kidsInTheResMembers = getMembersStartingWithId(4);
        podDataAccess.savePod(createPod("The kids in the Res", "Residence Inn", 745, 1800, "We're all staying at the Residence Inn.", kidsInTheResMembers));

        List<Person> originalRenMembers = getMembersStartingWithId(7);
        podDataAccess.savePod(createPod("The Original Ren", "Residence Inn", 700, 1900, "Is there really anything better?", originalRenMembers));
    }

    private List<Person> getMembersStartingWithId(int id) {
        PeopleDataAccess peopleDataAccess = new PeopleDataAccess(context);
        return Arrays.asList(
            peopleDataAccess.getPersonFromDatabaseWithId(id++),
            peopleDataAccess.getPersonFromDatabaseWithId(id++),
            peopleDataAccess.getPersonFromDatabaseWithId(id++)
        );

    }

    private void populatePeople() {
        Populator peoplePopulator = new PeoplePopulator(context);
        peoplePopulator.populate();
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