package com.timvelo.app;

import android.app.Application;

import com.timvelo.app.domain.api.ApiContract;
import com.timvelo.app.domain.api.ApiProvider;

import timber.log.Timber;

/**
 * Created by admin on 06/06/2017.
 */

public class TimveloApp extends Application {

    private static ApiProvider apiProvider;

    static public ApiProvider getApiProvider() { return apiProvider; }

    @Override
    public void onCreate() {
        super.onCreate();
        manageDependency();
        initTimber();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    // Do not log
                }
            });
        }
    }


    private void manageDependency() {
        if (null == apiProvider) {
            apiProvider = new ApiProvider(getApplicationContext(), ApiContract.BASE_API_URL);
        }
    }
}