package com.thoughtworks.carpods;

import android.app.Application;

public abstract class PodApplication extends Application {
    public abstract void inject(Object object);
}
