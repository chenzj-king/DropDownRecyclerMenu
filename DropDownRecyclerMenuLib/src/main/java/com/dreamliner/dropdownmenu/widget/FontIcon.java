/*
 * Copyright (c) 2016  DreamLiner Studio
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dreamliner.dropdownmenu.widget;

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
