package com.zr.wanandroid.module.home.presenter;

import com.github.adapter.LoadListener;
import com.github.adapter.LoadMoreAdapter;
import com.zr.wanandroid.base.BasePresenter;
import com.zr.wanandroid.common.listener.RequestListener;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.home.bean.HomeArticleBean;
import com.zr.wanandroid.module.home.fragment.SearchResultFragment;
import com.zr.wanandroid.module.home.model.HomeModel;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPresenter extends BasePresenter<SearchResultFragment> implements LoadMoreAdapter.OnLoadMoreListener {
    private HomeAdapter adapter;
    private List<HomeArticleBean> articleList;
    private String searchText;
    public HomeAdapter initAdapter() {
        articleList=new ArrayList<>();
        adapter=new HomeAdapter(getView().getActivity());
        adapter.setList(articleList);
        adapter.setOnLoadMoreListener(this);
        return adapter;
    }
    public void getData(int page, String text, boolean isLoad) {
        searchText=text;
        HomeModel.getInstance().getSearchArticleList(page, text, new RequestListener<List<HomeArticleBean>>() {
            @Override
            public void onSuccess(List<HomeArticleBean> obj) {
                loadResult(isLoad);
                if(isLoad){
                    adapter.addList(obj,true);
                }else{
                    getView().showContent();
                    adapter.setList(obj,true);
                }
            }
            @Override
            public void onError(String code, String errorMsg) {
                showToast(errorMsg);
                loadError(null);
                if(!isLoad){
                    getView().showError();
                }
            }
        });
    }

    @Override
    public void loadMore(LoadListener loadListener) {
        getData(pageNum,searchText,true);
    }
}
