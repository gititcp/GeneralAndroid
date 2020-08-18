package com.luffy.generalandroidlib.template.tabIndicator;

import android.content.Context;

import com.luffy.indicatorlib.buildins.ArgbEvaluatorHolder;

/**
 * Created by diaoyonglong on 2020/3/18
 *
 * @desc 两种颜色过渡的指示器标题
 */
public class TabColorTransitionPagerTitleView extends TabSimplePagerTitleView {

    public TabColorTransitionPagerTitleView(Context context) {
        super(context);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        int color = ArgbEvaluatorHolder.eval(leavePercent, mSelectedColor, mNormalColor);
        setTextColor(color);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        int color = ArgbEvaluatorHolder.eval(enterPercent, mNormalColor, mSelectedColor);
        setTextColor(color);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        super.onSelected(index, totalCount);
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        super.onDeselected(index, totalCount);
    }
}
