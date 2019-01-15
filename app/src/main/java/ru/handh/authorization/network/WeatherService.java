package ru.handh.authorization.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherService {

    @GET("weather")
    Observable<ResponseBody> getWeather(@Query("q") String city);

}
