package com.zr.wanandroid.module.question.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.developtools.DensityUtils;
import com.github.dividerline.BaseItemDivider;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseFragment;
import com.zr.wanandroid.module.home.adapter.HomeAdapter;
import com.zr.wanandroid.module.question.presenter.QuestionPresenter;

public class QuestionFragment extends BaseFragment<QuestionPresenter> {
    private RecyclerView rvQuestion;
    @Override
    public int getContentView() {
        return R.layout.question_frag;
    }

    public static QuestionFragment newInstance() {
        Bundle args = new Bundle();
        
        QuestionFragment fragment = new QuestionFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initView() {
        rvQuestion = (RecyclerView) findViewById(R.id.rvQuestion);


        rvQuestion.setLayoutManager(new LinearLayoutManager(mActivity));
        rvQuestion.addItemDecoration(new BaseItemDivider(mActivity, DensityUtils.dp2px(20)));
        HomeAdapter adapter = getPresenter().initAdapter();
        rvQuestion.setAdapter(adapter);

    }

    @Override
    public void initData() {
        showProgress();
        getData(1,false);
    }
    @Override
    protected void getData(int page, boolean isLoad) {
        getPresenter().getData(1,false);
    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
