package ru.handh.authorization.repository;


import io.reactivex.Observable;

public interface WeatherRepository {

    Observable<String> getWeather();

}
