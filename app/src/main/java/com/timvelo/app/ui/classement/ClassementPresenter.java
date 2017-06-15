package com.timvelo.app.ui.classement;

import com.timvelo.app.TimveloApp;
import com.timvelo.app.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 15/06/2017.
 */

public class ClassementPresenter extends BasePresenter<ClassementView> {
    void loadClassement(boolean pullToRefresh) {
        Disposable disposable = TimveloApp.getApiProvider().getApiService()
                .getClassement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        classements -> {
                            getView().setData(classements);
                            getView().showContent();
                        },
                        error -> {
                            getView().showError(error, pullToRefresh);
                        }
                );
        disposables.add(disposable);
    }
}
