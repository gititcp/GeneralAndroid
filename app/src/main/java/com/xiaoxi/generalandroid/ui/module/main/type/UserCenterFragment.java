package com.xiaoxi.generalandroid.ui.module.main.type;


import com.xiaoxi.generalandroid.R;
import com.xiaoxi.generalandroid.base.BaseFragment;
import com.xiaoxi.generalandroid.helper.IntentHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

/**
 * Created by lvlufei on 2018/7/23
 *
 * @desc 用户（个人）中心
 */
public class UserCenterFragment extends BaseFragment {

    @Override
    public int setLayoutView() {
        return R.layout.fragment_user_center;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void detachViewForPresenter() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initReceiveData() {

    }

    @OnClick(R.id.item_set)

    public void onViewClicked() {
        Map<String, Object> mapWeb = new HashMap<>();
        mapWeb.put("url", "https://www.baidu.com");
        mapWeb.put("title", "测试");
        IntentHelper.Web.gotoWebActivity(mContext, mapWeb);
    }

}
