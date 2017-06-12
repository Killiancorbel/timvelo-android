package com.timvelo.app.ui.raceList;

import com.timvelo.app.TimveloApp;
import com.timvelo.app.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 07/06/2017.
 */

public class RacePresenter extends BasePresenter<RaceView> {

    void loadRaces(int page, boolean pullToRefresh) {
        Disposable disposable = TimveloApp.getApiProvider().getApiService()
                .getRaces(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        races -> {
                            getView().setData(races);
                            getView().showContent();
                        },
                        error -> {
                            getView().showError(error, pullToRefresh);
                        }
                );
        disposables.add(disposable);
    }

    void loadNextPage(int page) {
        Disposable disposable = TimveloApp.getApiProvider().getApiService()
                .getRaces(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        races -> {
                            getView().setData(races);
                        }
                );
        disposables.add(disposable);
    }
}
