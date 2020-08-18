package com.luffy.generalandroidlib.template.tabIndicator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.luffy.generalandroidlib.R;
import com.luffy.generalandroidlib.android.activity.BaseLayerActivity;
import com.luffy.generalandroidlib.android.application.BaseLayerApplication;
import com.luffy.generalandroidlib.android.fragment.BaseLayerFragmentPageAdapter;
import com.luffy.generalandroidlib.android.model.BaseLayerTabIndicatorItemModel;
import com.luffy.indicatorlib.MagicIndicator;
import com.luffy.indicatorlib.ViewPagerHelper;
import com.luffy.indicatorlib.buildins.commonnavigator.CommonNavigator;
import com.luffy.indicatorlib.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.luffy.indicatorlib.buildins.commonnavigator.abs.IPagerIndicator;
import com.luffy.indicatorlib.buildins.commonnavigator.abs.IPagerTitleView;
import com.luffy.indicatorlib.buildins.commonnavigator.indicators.LinePagerIndicator;

import java.util.ArrayList;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 公用的TabIndicator（选项卡指示器）模板
 */
public abstract class BaseLayerTabIndicatorActivity extends BaseLayerActivity implements IBaseLayerTabIndicator {
    /*控件*/
    MagicIndicator mMagicIndicator;
    ViewPager mViewPager;
    /*数据*/
    protected ArrayList<BaseLayerTabIndicatorItemModel> tabIndicatorItemModelList = new ArrayList<>();
    /*Fragment*/
    protected ArrayList<Fragment> fragmentList = new ArrayList<>();
    /*当前选中项*/
    private int currentItem = 0;
    /*IndicatorConfig*/
    private int indicatorBackgroundColor = Color.WHITE;
    private boolean indicatorAdjustMode = true;
    private float indicatorTextSize = 16;
    private float indicatorSelectTextSize = 16;
    private int indicatorNormalTextStyle = Typeface.NORMAL;
    private int indicatorSelectedTextStyle = Typeface.BOLD;
    private int indicatorNormalColor = Color.GRAY;
    private int indicatorSelectedColor = ContextCompat.getColor(BaseLayerApplication.getInstance(), R.color.colorPrimary);
    private int indicatorMode = LinePagerIndicator.MODE_WRAP_CONTENT;
    private float indicatorLineHeight = 5;
    private float indicatorLineWidth = 40;
    private int indicatorColor = ContextCompat.getColor(BaseLayerApplication.getInstance(), R.color.colorPrimary);

    @Override
    public int setLayoutView() {
        return R.layout.activity_base_layer_tab_indicator;
    }

    @Override
    public void bindButterKnife(Activity target) {

    }

    @Override
    public void initView() {
        findView();
        init();
        bindTitle();
        bindFragments();
        initIndicatorConfig();
        initMagicIndicator();
        initAdapter();
    }

    @Override
    public void findView() {
        mMagicIndicator = findViewById(R.id.magicIndicator);
        mViewPager = findViewById(R.id.viewPager);
    }

    @Override
    public void initMagicIndicator() {
        mMagicIndicator.setBackgroundColor(getIndicatorBackgroundColor());
        /*通用导航器*/
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        /*调整模式（true：标题平分屏幕宽度；false：默认模式）*/
        commonNavigator.setAdjustMode(isIndicatorAdjustMode());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabIndicatorItemModelList == null ? 0 : tabIndicatorItemModelList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
//                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
//                simplePagerTitleView.setText(tabIndicatorItemModelList.get(index).getName());
//                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getIndicatorTextSize());
//                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(getIndicatorTextStyle()));
//                simplePagerTitleView.setNormalColor(getIndicatorNormalColor());
//                simplePagerTitleView.setSelectedColor(getIndicatorSelectedColor());

                TabSimplePagerTitleView simplePagerTitleView = new TabColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(tabIndicatorItemModelList.get(index).getName());
                simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, getIndicatorTextSize());
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(getIndicatorNormalTextStyle()));
                simplePagerTitleView.setNormalColor(getIndicatorNormalColor());
                simplePagerTitleView.setSelectedColor(getIndicatorSelectedColor());
                simplePagerTitleView.setNormalSize(getIndicatorTextSize());
                simplePagerTitleView.setSelectedSize(getIndicatorSelectTextSize());
                simplePagerTitleView.setNormalTextStyle(getIndicatorNormalTextStyle());
                simplePagerTitleView.setSelectedTextStyle(getIndicatorSelectedTextStyle());
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(getIndicatorMode());
                linePagerIndicator.setLineHeight(getIndicatorLineHeight());
                linePagerIndicator.setRoundRadius(5);
                linePagerIndicator.setColors(getIndicatorColor());
                return linePagerIndicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    @Override
    public void initAdapter() {
        mViewPager.setAdapter(new BaseLayerFragmentPageAdapter(fragmentList, getSupportFragmentManager()));
        mViewPager.setCurrentItem(getCurrentItem());
    }

    @Override
    public void notifyChange() {
        initMagicIndicator();
        initAdapter();
    }

    public int getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    public int getIndicatorBackgroundColor() {
        return indicatorBackgroundColor;
    }

    public void setIndicatorBackgroundColor(int indicatorBackgroundColor) {
        this.indicatorBackgroundColor = indicatorBackgroundColor;
    }

    public boolean isIndicatorAdjustMode() {
        return indicatorAdjustMode;
    }

    public void setIndicatorAdjustMode(boolean indicatorAdjustMode) {
        this.indicatorAdjustMode = indicatorAdjustMode;
    }

    public float getIndicatorTextSize() {
        return indicatorTextSize;
    }

    public void setIndicatorTextSize(float indicatorTextSize) {
        this.indicatorTextSize = indicatorTextSize;
    }

    public float getIndicatorSelectTextSize() {
        return indicatorSelectTextSize;
    }

    public void setIndicatorSelectTextSize(float indicatorSelectTextSize) {
        this.indicatorSelectTextSize = indicatorSelectTextSize;
    }

    public int getIndicatorNormalTextStyle() {
        return indicatorNormalTextStyle;
    }

    public void setIndicatorNormalTextStyle(int indicatorNormalTextStyle) {
        this.indicatorNormalTextStyle = indicatorNormalTextStyle;
    }

    public int getIndicatorSelectedTextStyle() {
        return indicatorSelectedTextStyle;
    }

    public void setIndicatorSelectedTextStyle(int indicatorSelectedTextStyle) {
        this.indicatorSelectedTextStyle = indicatorSelectedTextStyle;
    }

    public int getIndicatorNormalColor() {
        return indicatorNormalColor;
    }

    public void setIndicatorNormalColor(int indicatorNormalColor) {
        this.indicatorNormalColor = indicatorNormalColor;
    }

    public int getIndicatorSelectedColor() {
        return indicatorSelectedColor;
    }

    public void setIndicatorSelectedColor(int indicatorSelectedColor) {
        this.indicatorSelectedColor = indicatorSelectedColor;
    }

    public int getIndicatorMode() {
        return indicatorMode;
    }

    public void setIndicatorMode(int indicatorMode) {
        this.indicatorMode = indicatorMode;
    }

    public float getIndicatorLineHeight() {
        return indicatorLineHeight;
    }

    public void setIndicatorLineHeight(float indicatorLineHeight) {
        this.indicatorLineHeight = indicatorLineHeight;
    }

    public float getIndicatorLineWidth() {
        return indicatorLineWidth;
    }

    public void setIndicatorLineWidth(float indicatorLineWidth) {
        this.indicatorLineWidth = indicatorLineWidth;
    }

    public int getIndicatorColor() {
        return indicatorColor;
    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
    }
}
