package ru.handh.authorization;

import android.app.Application;

import ru.handh.authorization.network.ApiFactory;
import ru.handh.authorization.repository.RepositoryProvider;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ApiFactory.recreate();
        RepositoryProvider.init();
    }
}
