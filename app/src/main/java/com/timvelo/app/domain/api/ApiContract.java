package com.timvelo.app.domain.api;


import com.timvelo.app.BuildConfig;

public class ApiContract {

    public static final String BASE_URL = BuildConfig.BASEURL;
    public static final String BASE_API_URL = BASE_URL + "api/";
    public static final Integer TIMEOUT = 60;
    public interface Page {
        String RACES = "races";
        String RESULTS = "results/{id}";
    }
    public interface Params {
        String ID = "id";
        String PAGE = "page";
    }
}
