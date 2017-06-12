package com.timvelo.app.domain.api;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by admin on 06/06/2017.
 */

public class ApiProvider {

    private final Context context;
    private final ApiService apiservice;
    private final String murl;

    public ApiProvider(Context context, @Nullable String url) {
        if (null == url)
            url = ApiContract.BASE_API_URL;
        this.murl = url;
        this.context = context;
        this.apiservice = create();
    }

    private ApiService create() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Timber.d(message));
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new ConnectivityInterceptor(context))
                .readTimeout(ApiContract.TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(ApiContract.TIMEOUT, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(murl)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
                .create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiservice;
    }
}
