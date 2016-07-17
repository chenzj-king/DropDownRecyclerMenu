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

package com.dreamliner.dropdownmenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dreamliner.dropdownmenu.adapter.SelectAdapter;
import com.dreamliner.dropdownmenu.interfaces.IGetTitle;
import com.dreamliner.dropdownmenu.listener.OnDropdownItemClickListener;
import com.dreamliner.dropdownmenu.listener.OnRecyclerItemClickListener;
import com.dreamliner.dropdownmenu.widget.FontIcon;

/**
 * @author chenzj
 * @Title: Draw
 * @Description: 类的描述 - 下拉菜单.通过popupwindows来实现
 * @date 2016/5/14 11:43
 * @email admin@chenzhongjin.cn
 */
public class DropdownMenu extends RelativeLayout {

    private static final String TAG = "DropdownMenu";

    @SuppressWarnings("FieldCanBeLocal")
    private Context mContext;
    private PopupWindow mPopupWindow;
    private FixedHeightRecyclerView mRecyclerView;
    @SuppressWarnings("FieldCanBeLocal")
    private RelativeLayout mShadowLayout;
    private TextView mTextTitle;
    private FontIcon mIconView;
    private RecyclerView.Adapter mDropdownAdapter;
    private OnDropdownItemClickListener mOnDropdownItemClickListener;

    private Handler mHandler;

    private static final String ICON_DOWN = "\ue801";
    private static final String ICON_UP = "\ue800";

    private static final int NO_HIGHLIGHT = -1; // 没有设置高亮色时的默认值

    public DropdownMenu(Context context) {
        super(context);
        init(context, null);
    }

    public DropdownMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DropdownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DropdownMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;

        mHandler = new Handler();
        // 初始化属性
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.DropdownMenu);
        String titleText = attributes.getString(R.styleable.DropdownMenu_titleText);
        float textSize = attributes.getDimensionPixelSize(R.styleable.DropdownMenu_titleTextSize, 0);
        final int textColor = attributes.getColor(R.styleable.DropdownMenu_titleColor, 0xff000000);
        int titleBgColor = attributes.getColor(R.styleable.DropdownMenu_titleBgColor, 0xffcccccc);
        int listBgColor = attributes.getColor(R.styleable.DropdownMenu_listBgColor, 0x00ffffff);
        final int iconColor = attributes.getColor(R.styleable.DropdownMenu_iconColor, 0xffcccccc);
        final int highLightColor = attributes.getColor(R.styleable.DropdownMenu_titleHighLight, NO_HIGHLIGHT);

        attributes.recycle();

        //init titleView
        LayoutInflater.from(context).inflate(R.layout.layout_drawdown_title, this, true);
        setBackgroundColor(titleBgColor);

        mTextTitle = (TextView) findViewById(R.id.title_tv);
        mTextTitle.setText(TextUtils.isEmpty(titleText) ? "<请选择>" : titleText);
        mTextTitle.setTextColor(textColor);
        mTextTitle.setBackgroundColor(titleBgColor);
        if (textSize > 0) {
            mTextTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        mIconView = (FontIcon) findViewById(R.id.title_icon);
        mIconView.setTextColor(iconColor);
        mIconView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        mIconView.setText(ICON_DOWN);

        //init popupWindows
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupWindow = inflater.inflate(R.layout.ddm_popup, (ViewGroup) getParent(), false);

        mPopupWindow = new PopupWindow(popupWindow, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);

        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);

        // 不加这个在低版本（测试了 4.1）上会有外部点击事件不会响应的问题
        //noinspection deprecation
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        mRecyclerView = (FixedHeightRecyclerView) popupWindow.findViewById(R.id.lv_menu);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setBackgroundColor(listBgColor);
        mDropdownAdapter = new SelectAdapter(new String[]{});
        mRecyclerView.setAdapter(mDropdownAdapter);

        mShadowLayout = (RelativeLayout) popupWindow.findViewById(R.id.rl_menu_shadow);
        mShadowLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh, View view, int position) {
                if (null != mOnDropdownItemClickListener) {
                    mOnDropdownItemClickListener.onItemClick(view, position);
                }
                if (mDropdownAdapter instanceof IGetTitle) {
                    mTextTitle.setText(((IGetTitle) mDropdownAdapter).getTitleString(position));
                }
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPopupWindow.dismiss();
                    }
                }, 100);
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mIconView.setText(ICON_DOWN);
                mIconView.setTextColor(iconColor);
                mTextTitle.setTextColor(textColor);
            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                    mIconView.setText(ICON_DOWN);
                    mIconView.setTextColor(iconColor);
                    mTextTitle.setTextColor(textColor);
                } else {
                    mPopupWindow.showAsDropDown(DropdownMenu.this);
                    mPopupWindow.setOutsideTouchable(true);
                    mIconView.setText(ICON_UP);

                    if (highLightColor != -1) {
                        mIconView.setTextColor(highLightColor);
                        mTextTitle.setTextColor(highLightColor);
                    }
                }
            }
        });
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(mDropdownAdapter = adapter);
    }

    public void setOnItemClickListener(final OnDropdownItemClickListener listener) {
        mOnDropdownItemClickListener = listener;
    }

    /**
     * 设置默认标题文字
     *
     * @param title 内容
     */
    @SuppressWarnings("unused")
    public void setTitle(String title) {
        mTextTitle.setText(title);
    }

    /**
     * 下拉菜单是否在显示
     */
    @SuppressWarnings("unused")
    public boolean isDropdown() {
        return mPopupWindow != null && mPopupWindow.isShowing();
    }

    /**
     * 如果存在，展开
     */
    @SuppressWarnings("unused")
    public void expand() {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(this);
        }
    }

    /**
     * 如果存在，收起
     */
    public void collapse() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }

    public FixedHeightRecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public RecyclerView.Adapter getDropdownAdapter() {
        return mDropdownAdapter;
    }

    /**
     * 获取下拉菜单标题 TextView，方便对其设置属性
     */
    @SuppressWarnings("unused")
    public TextView getTitleView() {
        return mTextTitle;
    }
}

