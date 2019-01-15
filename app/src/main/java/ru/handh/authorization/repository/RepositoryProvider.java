package ru.handh.authorization.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public final class RepositoryProvider {

    private static WeatherRepository sWeatherRepository;

    private RepositoryProvider() {
    }

    @NonNull
    public static WeatherRepository provideRepository() {
        if (sWeatherRepository == null) {
            sWeatherRepository = new DefaultWeatherRepository();
        }
        return sWeatherRepository;
    }

    @SuppressWarnings("unused")
    public static void setRepository(@NonNull WeatherRepository weatherRepository) {
        sWeatherRepository = weatherRepository;
    }

    @MainThread
    public static void init() {
        sWeatherRepository = new DefaultWeatherRepository();
    }
}
