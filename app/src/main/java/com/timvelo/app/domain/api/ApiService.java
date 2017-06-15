package com.timvelo.app.domain.api;

import com.timvelo.app.domain.models.Classement;
import com.timvelo.app.domain.models.Race;
import com.timvelo.app.domain.models.Result;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by admin on 06/06/2017.
 */

public interface ApiService {

    @GET(ApiContract.Page.RACES)
    Observable<ArrayList<Race>> getRaces(@Query(ApiContract.Params.PAGE) int page);

    @GET(ApiContract.Page.RESULTS)
    Observable<ArrayList<Result>> getResult(@Path(ApiContract.Params.ID) int id);

    @GET(ApiContract.Page.CALENDAR)
    Observable<ArrayList<Race>> getCalendar(@Query(ApiContract.Params.PAGE) int page);

    @GET(ApiContract.Page.CLASSEMENT)
    Observable<ArrayList<Classement>> getClassement();
}
