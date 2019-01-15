package ru.handh.authorization.network;


import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ru.handh.authorization.BuildConfig;

public class ApiKeyInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("APPID", BuildConfig.API_KEY)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }

}
