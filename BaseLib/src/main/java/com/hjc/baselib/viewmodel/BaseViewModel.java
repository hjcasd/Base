package com.hjc.baselib.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.hjc.baselib.base.BaseActionEvent;
import com.hjc.baselib.base.IViewModelAction;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:15
 * @Description: ViewModel 基类
 */
public class BaseViewModel extends AndroidViewModel implements IViewModelAction {

    private CompositeDisposable mCompositeDisposable;
    private MutableLiveData<BaseActionEvent> actionLiveData;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        actionLiveData = new MutableLiveData<>();
    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void startLoading() {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.START_LOADING_DIALOG);
        actionLiveData.setValue(baseActionEvent);
    }

    @Override
    public void dismissLoading() {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.DISMISS_LOADING_DIALOG);
        actionLiveData.setValue(baseActionEvent);
    }

    @Override
    public void showLoading() {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.SHOW_LOADING);
        actionLiveData.setValue(baseActionEvent);
    }

    @Override
    public void showContent() {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.SHOW_CONTENT);
        actionLiveData.setValue(baseActionEvent);
    }

    @Override
    public void showEmpty() {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.SHOW_EMPTY);
        actionLiveData.setValue(baseActionEvent);
    }

    @Override
    public void showError() {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.SHOW_ERROR);
        actionLiveData.setValue(baseActionEvent);
    }

    @Override
    public MutableLiveData<BaseActionEvent> getActionLiveData() {
        return actionLiveData;
    }

}

