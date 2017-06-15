package com.timvelo.app.ui.result;

import com.timvelo.app.TimveloApp;
import com.timvelo.app.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 13/06/2017.
 */

class ResultPresenter extends BasePresenter<ResultView> {

    void loadResult(int id, boolean pullToRefresh) {
        Disposable disposable = TimveloApp.getApiProvider()
                .getApiService()
                .getResult(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        results -> {
                            getView().setData(results);
                            getView().showContent();
                        },
                        error -> {
                            getView().showError(error, pullToRefresh);
                        }
                );
        disposables.add(disposable);
    }
}
