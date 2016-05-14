package com.sq580.dropdownmenu.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author chenzj
 * @Title: Draw
 * @Description: 类的描述 - 字体图标
 * @date 2016/5/14 11:43
 * @email admin@chenzhongjin.cn
 */
public class FontIcon extends TextView {

    public FontIcon(Context context) {
        super(context);
        init();
    }

    public FontIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontIcon(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init() {
        Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "icon.ttf");
        setTypeface(typeFace);
    }

    @SuppressWarnings("unused")
    public void setFontIconColor(int textColor) {
        this.setTextColor(textColor);
    }

    @SuppressWarnings("unused")
    public void setFontIconColor(ColorStateList colors) {
        this.setTextColor(colors);
    }
}
