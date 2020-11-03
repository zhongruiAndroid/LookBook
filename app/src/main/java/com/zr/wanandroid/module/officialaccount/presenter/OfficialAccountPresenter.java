package com.zr.wanandroid.module.officialaccount.presenter;

import android.view.View;

import com.github.adapter.listener.NoDoubleAdapterOnClickListener;
import com.github.developtools.N;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.bridge.ActBridge;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.module.officialaccount.adapter.OfficialAccountAdapter;
import com.zr.wanandroid.module.officialaccount.bean.OfficialAccountBean;
import com.zr.wanandroid.module.officialaccount.fragment.OfficialAccountAuthorFragment;
import com.zr.wanandroid.module.officialaccount.model.OfficialAccountModel;

import java.util.List;

public class OfficialAccountPresenter extends BasePresenter<OfficialAccountAuthorFragment> {
    private OfficialAccountAdapter adapter;

    public OfficialAccountAdapter initAdapter() {
        adapter = new OfficialAccountAdapter(getView().getActivity());
        adapter.setOnItemClickListener(new NoDoubleAdapterOnClickListener() {
            @Override
            public void onNoDoubleClick(View view, int i) {
                getView().hiddenDrawerLayout();
                ActBridge.toOfficialAccountArticleActivity(getView().getActivity(),adapter.getList());
            }
        });
        return adapter;
    }

    public void getData(int page, boolean isLoad) {
        OfficialAccountModel.getInstance().OfficialAccountAuthorList(new RequestListener<List<OfficialAccountBean>>() {
            @Override
            public void onSuccess(List<OfficialAccountBean> obj) {
                loadResult(isLoad);
                if(N.isEmpty(obj)){
                    getView().showEmpty();
                    return;
                }
                adapter.setList(obj,true);
                if(!isLoad){
                    getView().showContent();
                }
            }
            @Override
            public void onError(String code, String errorMsg) {
                showToast(errorMsg);
                if(!isLoad){
                    getView().showError();
                }
            }
        });
    }
}
