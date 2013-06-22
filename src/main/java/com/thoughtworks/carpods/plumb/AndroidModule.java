package com.thoughtworks.carpods.plumb;

import android.app.Application;
import android.content.Context;
import com.thoughtworks.carpods.UI.people.DisplayPerson;
import com.thoughtworks.carpods.UI.people.EditPerson;
import com.thoughtworks.carpods.UI.people.PeopleList;
import com.thoughtworks.carpods.UI.pod.EditPod;
import com.thoughtworks.carpods.data.DataAccessFactory;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(
        complete =  false,
        library = true,
        injects = {
                EditPerson.class,
                DisplayPerson.class,
                PeopleList.class,
                EditPod.class
        }
)
public class AndroidModule {
    private final Application application;

    public AndroidModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton @ForApplication
    Context provideApplicationContext() {
        return application;
    }

    @Provides @ForApplication
    DataAccessFactory provideDataAccessFactory() {
        return new DataAccessFactory();
    }
}
