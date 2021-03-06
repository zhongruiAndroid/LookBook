package com.zr.wanandroid.module.my.activity;

import android.accounts.AccountManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.dividerline.BaseItemDivider;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.common.manager.UserManager;
import com.zr.wanandroid.module.my.presenter.CoinRecordPresenter;

public class CoinRecordActivity extends BaseActivity<CoinRecordPresenter> {
    private RecyclerView rvCoinRecord;
    @Override
    public int getContentView() {
        return R.layout.coin_record_act;
    }

    @Override
    public void initView() {
        titleView.setAppTitle("总积分:"+ UserManager.get().getUser().getCoinBean().getCoinCount());
        rvCoinRecord = findViewById(R.id.rvCoinRecord);
        rvCoinRecord.setLayoutManager(new LinearLayoutManager(mActivity));
        BaseItemDivider baseItemDivider = new BaseItemDivider(mActivity, 2, color(R.color.c_divider));
        baseItemDivider.setSkipEndCount(2);
        rvCoinRecord.addItemDecoration(baseItemDivider);

        rvCoinRecord.setAdapter(getPresenter().initAdapter());
    }

    @Override
    public void initData() {
        showProgress();
        getData(1,false);
    }

    @Override
    protected void getData(int page, boolean isLoad) {
        getPresenter().getUserCoinRecord(1,isLoad);
    }

    @Override
    public void onNoDoubleClick(View v) {

    }
}
