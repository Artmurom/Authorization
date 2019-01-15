package ru.handh.authorization.repository;

import org.json.JSONObject;

import java.text.DecimalFormat;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.handh.authorization.network.ApiFactory;

public class DefaultWeatherRepository implements WeatherRepository {

    @SuppressWarnings("FieldCanBeLocal")
    private final String CITY = "Moscow";

    private final String MAIN = "main";
    private final String TEMP = "temp";

    private DecimalFormat mDF = new DecimalFormat("#,#");


    @Override
    public Observable<String> getWeather() {
        return ApiFactory.getWeatherService()
                .getWeather(CITY)
                .map(result -> {
                    JSONObject jsonObject = new JSONObject(result.string());
                    return mDF.format(Double.valueOf(jsonObject.getJSONObject(MAIN).getString(TEMP)) - 273.15);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
