package com.thoughtworks.carpods;

import android.content.Context;
import com.thoughtworks.carpods.UI.people.DisplayPerson;
import com.thoughtworks.carpods.UI.people.EditPerson;
import com.thoughtworks.carpods.UI.people.PeopleList;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(
        library = true,
        injects = {
                EditPerson.class,
                DisplayPerson.class,
                PeopleList.class
        }
)
public class AndroidModule {
    private final PodApplication application;

    public AndroidModule(PodApplication application) {
        this.application = application;
    }

    @Provides @Singleton @ForApplication Context provideApplicationContext() {
        return application;
    }
}
