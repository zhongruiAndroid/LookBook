package com.zr.wanandroid.module.home.activity;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.android.basecore.tools.ActTools;
import com.github.developtools.ActivityUtils;
import com.github.developtools.N;
import com.github.fastshape.MyRadioButton;
import com.github.statusbar.StatusBarUtils;
import com.zr.wanandroid.R;
import com.zr.wanandroid.base.BaseActivity;
import com.zr.wanandroid.module.home.fragment.HomeFragment;
import com.zr.wanandroid.module.home.presenter.HomeActPresenter;
import com.zr.wanandroid.module.knowledgesystem.fragment.KnowledgeSystemFragment;
import com.zr.wanandroid.module.my.fragment.MyFragment;
import com.zr.wanandroid.module.navigation.fragment.NavigationFragment;
import com.zr.wanandroid.module.officialaccount.fragment.OfficialAccountFragment;
import com.zr.wanandroid.module.question.fragment.QuestionFragment;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends BaseActivity<HomeActPresenter> {
    private FrameLayout flHomeContent;

    private MyRadioButton selectView;
    private MyRadioButton rbHomeTab;
    private MyRadioButton rbKnowledgeSystemTab;
    private MyRadioButton rbNavigationTab;
    private MyRadioButton rbOfficialAccountTab;
    private MyRadioButton rbQuestionTab;
    private MyRadioButton rbMyTab;


    private HomeFragment homeFragment;
    private NavigationFragment navigationFragment;
    private OfficialAccountFragment officialAccountFragment;
    private QuestionFragment questionFragment;
    private MyFragment myFragment;
    private KnowledgeSystemFragment knowledgeSystemFragment;

    private Map<Integer, Fragment> fragmentMap = new HashMap<>();

    @Override
    public int getContentView() {
        return R.layout.main_act;
    }

    @Override
    public void initView() {
        StatusBarUtils.setStatusColor(this,color(R.color.colorAccent),false);

        flHomeContent = findViewById(R.id.flHomeContent);
        rbHomeTab = findViewById(R.id.rbHomeTab);
        rbKnowledgeSystemTab = findViewById(R.id.rbKnowledgeSystemTab);
        rbNavigationTab = findViewById(R.id.rbNavigationTab);
        rbOfficialAccountTab = findViewById(R.id.rbOfficialAccountTab);
        rbQuestionTab = findViewById(R.id.rbQuestionTab);
        rbMyTab = findViewById(R.id.rbMyTab);

        selectView = rbHomeTab;
    }

    @Override
    public void initViewAfter() {
        rbHomeTab.setOnClickListener(this);
        rbKnowledgeSystemTab.setOnClickListener(this);
        rbNavigationTab.setOnClickListener(this);
        rbOfficialAccountTab.setOnClickListener(this);
        rbQuestionTab.setOnClickListener(this);
        rbMyTab.setOnClickListener(this);
    }

    @Override
    public void initData() {
        homeFragment = HomeFragment.newInstance();
        ActTools.addFragment(this, R.id.flHomeContent, homeFragment);


        knowledgeSystemFragment = KnowledgeSystemFragment.newInstance();
        navigationFragment = NavigationFragment.newInstance();
        officialAccountFragment = OfficialAccountFragment.newInstance();
        questionFragment = QuestionFragment.newInstance();
        myFragment = MyFragment.newInstance();

        fragmentMap.put(R.id.rbHomeTab, homeFragment);
        fragmentMap.put(R.id.rbKnowledgeSystemTab, knowledgeSystemFragment);
        fragmentMap.put(R.id.rbNavigationTab, navigationFragment);
        fragmentMap.put(R.id.rbOfficialAccountTab, officialAccountFragment);
        fragmentMap.put(R.id.rbQuestionTab, questionFragment);
        fragmentMap.put(R.id.rbMyTab, myFragment);
    }

    @Override
    public void onNoDoubleClick(View v) {
        selectTab(v,v.getId());
    }

    private void selectTab(View v, int id) {
        if (selectView.getId() == id) {
            return;
        }
        Fragment fragment = fragmentMap.get(id);
        Fragment preFragment = fragmentMap.get(selectView.getId());
        if(fragment==null){
            return;
        }
        if(fragment.isAdded()){
            ActTools.showFragment(this,fragment);
        }else{
            ActTools.addFragment(this,R.id.flHomeContent,fragment);
        }
        ActTools.hideFragment(this,preFragment);
        selectView= (MyRadioButton) v;
        selectView.setChecked(true);
    }
}
