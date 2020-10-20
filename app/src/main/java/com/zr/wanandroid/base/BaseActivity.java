package com.zr.wanandroid.base;


import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;

import com.android.basecore.act.MVPBaseActivity;
import com.android.basecore.presenter._BasePresenter;
import com.github.progress.ProgressFrameLayout;
import com.github.progress.ProgressInter;
import com.github.progress.ProgressLinearLayout;
import com.github.progress.ProgressRelativeLayout;
import com.zr.wanandroid.R;
import com.zr.wanandroid.widget.TitleView;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public abstract class BaseActivity<P extends _BasePresenter> extends MVPBaseActivity<P> implements com.github.progress.ProgressInter.ErrorOnClickListener, com.github.progress.ProgressInter.NoNetworkOnClickListener {

    private ProgressInter progressInter;
    protected PtrClassicFrameLayout pcflRefresh;
    public TitleView titleView;
    @Override
    protected final void initViewPrevious() {
        titleView =findViewById(R.id.titleView);
    }
    @Override
    public void initViewAfter() {
        View pcflRefreshView = findViewById(R.id.pcflRefresh);
        if (null != pcflRefreshView) {
            pcflRefresh = (PtrClassicFrameLayout) pcflRefreshView;
            pcflRefresh.setLastUpdateTimeRelateObject(this);
//            pcfl.disableWhenHorizontalMove(true);
            pcflRefresh.setYOffsetMultiple(3);
            pcflRefresh.setXOffsetMultiple(3);
            pcflRefresh.setScaledTouchMultiple(0.5f);
            pcflRefresh.setPtrHandler(new PtrDefaultHandler() {
                @Override
                public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                    getOtherData();
                    getData(1, false);
                }
            });
        }
        View loadView = findViewById(R.id.plLoad);
        if (null != loadView) {
            if (loadView instanceof ProgressRelativeLayout || loadView instanceof ProgressLinearLayout || loadView instanceof ProgressFrameLayout) {
                progressInter = (ProgressInter) loadView;
                progressInter.setNoNetworkOnClickListener(this);
                progressInter.setErrorOnClickListener(this);
            }
        }
    }

    protected void getOtherData() {
    }

    protected void getData(int page, final boolean isLoad) {
    }

    public void showError(){
        if (progressInter != null) {
            progressInter.showError();
        }
    }

    public void showEmpty(){
        if (progressInter != null) {
            progressInter.showEmpty();
        }

    }

    public void showProgress(){
        if (progressInter != null) {
            progressInter.showProgress();
        }

    }

    public void showContent(){
        if (progressInter != null) {
            progressInter.showContent();
        }

    }
    public void showNoNetwork(){
        if (progressInter != null) {
            progressInter.showNoNetwork();
        }
    }
    @Override
    public void errorOnClick() {

    }
    @Override
    public void noNetworkOnClick() {

    }
}
