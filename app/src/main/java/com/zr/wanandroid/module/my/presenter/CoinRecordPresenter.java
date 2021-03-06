package com.zr.wanandroid.module.my.presenter;

import com.github.adapter.LoadListener;
import com.github.adapter.LoadMoreAdapter;
import com.github.developtools.N;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.BaseRequestListener;
import com.zr.wanandroid.module.my.activity.CoinRecordActivity;
import com.zr.wanandroid.module.my.adapter.CoinRecordAdapter;
import com.zr.wanandroid.module.my.bean.CoinRecordBean;
import com.zr.wanandroid.module.my.model.UserModel;

import java.util.List;

public class CoinRecordPresenter extends BasePresenter<CoinRecordActivity> implements LoadMoreAdapter.OnLoadMoreListener {
    private CoinRecordAdapter adapter;
    public LoadMoreAdapter initAdapter(){
        adapter=new CoinRecordAdapter(1);
        adapter.setOnLoadMoreListener(this);
        return adapter;
    }

    public void getUserCoinRecord(int page,boolean isLoad){
        UserModel.getInstance().getUserCoinRecord(page,new BaseRequestListener<List<CoinRecordBean>,Boolean>() {
            @Override
            public void onSuccess(List<CoinRecordBean> data) {
                loadResult(isLoad);
                if(!isLoad){
                    adapter.setList(data,true);
                    getView().showContent();
                }else{
                    adapter.addList(data,true);
                }
            }
            @Override
            public void onCustomSuccess(List<CoinRecordBean> data, Boolean custom) {
                adapter.loadHasMore(custom);
            }
            @Override
            public void onError(String code, String errorMsg) {
                showToast(errorMsg);
                if(isLoad){
                    loadError(adapter);
                }else{
                    getView().showError();
                }
            }
        });
    }
    @Override
    public void loadMore(LoadListener loadListener) {
        getUserCoinRecord(pageNum,true);
    }

}
