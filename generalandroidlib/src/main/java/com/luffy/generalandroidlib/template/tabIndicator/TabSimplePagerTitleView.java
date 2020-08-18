package com.luffy.generalandroidlib.template.tabIndicator;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import com.luffy.indicatorlib.buildins.UIUtil;
import com.luffy.indicatorlib.buildins.commonnavigator.abs.IMeasurablePagerTitleView;

/**
 * Created by diaoyonglong on 2020/3/18
 *
 * @desc 带文本的指示器标题
 */
public class TabSimplePagerTitleView extends TextView implements IMeasurablePagerTitleView {
    protected int mSelectedColor;
    protected int mNormalColor;
    protected int mNormalTextStyle;
    protected int mSelectedTextStyle;
    protected float mSelectedSize;
    protected float mNormalSize;

    public TabSimplePagerTitleView(Context context) {
        super(context, null);
        init(context);
    }

    private void init(Context context) {
        setGravity(Gravity.CENTER);
        int padding = UIUtil.dip2px(context, 10);
        setPadding(padding, 0, padding, 0);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        setTextColor(mSelectedColor);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, mSelectedSize);
        setTypeface(Typeface.defaultFromStyle(mSelectedTextStyle));
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        setTextColor(mNormalColor);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, mNormalSize);
        setTypeface(Typeface.defaultFromStyle(mNormalTextStyle));
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
    }

    @Override
    public int getContentLeft() {
        Rect bound = new Rect();
        String longestString = "";
        if (getText().toString().contains("\n")) {
            String[] brokenStrings = getText().toString().split("\\n");
            for (String each : brokenStrings) {
                if (each.length() > longestString.length()) longestString = each;
            }
        } else {
            longestString = getText().toString();
        }
        getPaint().getTextBounds(longestString, 0, longestString.length(), bound);
        int contentWidth = bound.width();
        return getLeft() + getWidth() / 2 - contentWidth / 2;
    }

    @Override
    public int getContentTop() {
        Paint.FontMetrics metrics = getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 - contentHeight / 2);
    }

    @Override
    public int getContentRight() {
        Rect bound = new Rect();
        String longestString = "";
        if (getText().toString().contains("\n")) {
            String[] brokenStrings = getText().toString().split("\\n");
            for (String each : brokenStrings) {
                if (each.length() > longestString.length()) longestString = each;
            }
        } else {
            longestString = getText().toString();
        }
        getPaint().getTextBounds(longestString, 0, longestString.length(), bound);
        int contentWidth = bound.width();
        return getLeft() + getWidth() / 2 + contentWidth / 2;
    }

    @Override
    public int getContentBottom() {
        Paint.FontMetrics metrics = getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 + contentHeight / 2);
    }

    /*字体颜色*/
    public int getNormalColor() {
        return mNormalColor;
    }

    public void setNormalColor(int normalColor) {
        mNormalColor = normalColor;
    }

    public int getSelectedColor() {
        return mSelectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        mSelectedColor = selectedColor;
    }

    /*字体大小*/
    public float getNormalSize() {
        return mNormalSize;
    }

    public void setNormalSize(float normalSize) {
        mNormalSize = normalSize;
    }

    public float getSelectedSize() {
        return mSelectedSize;
    }

    public void setSelectedSize(float selectedSize) {
        mSelectedSize = selectedSize;
    }

    /*字体样式*/
    public int getNormalTextStyle() {
        return mNormalTextStyle;
    }

    public void setNormalTextStyle(int normalTextStyle) {
        mNormalTextStyle = normalTextStyle;
    }

    public int getSelectedTextStyle() {
        return mSelectedTextStyle;
    }

    public void setSelectedTextStyle(int selectedTextStyle) {
        mSelectedTextStyle = selectedTextStyle;
    }

}

