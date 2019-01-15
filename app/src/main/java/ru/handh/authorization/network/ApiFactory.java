package ru.handh.authorization.network;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.handh.authorization.BuildConfig;

public final class ApiFactory {

    private static OkHttpClient sClient;
    private static volatile WeatherService sService;

    private ApiFactory() {
    }

    @NonNull
    public static WeatherService getWeatherService() {
        WeatherService service = sService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = sService;
                if (service == null) {
                    service = sService = createService().create(WeatherService.class);
                }
            }
        }
        return service;
    }

    public static void recreate() {
        sService = createService().create(WeatherService.class);
    }

    @NonNull
    private static Retrofit createService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_ENDPOINT)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .build();
    }

}
