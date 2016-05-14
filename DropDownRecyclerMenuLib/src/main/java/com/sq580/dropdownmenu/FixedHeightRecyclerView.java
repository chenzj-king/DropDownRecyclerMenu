package com.sq580.dropdownmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author chenzj
 * @Title: FixedHeightRecyclerView
 * @Description: 类的描述 - 自定义的recyclerview.可以设置maxHeight
 * @date
 * @email admin@chenzhongjin.cn
 */
public class FixedHeightRecyclerView extends RecyclerView {

    private int mMaxHeight = 0;

    public FixedHeightRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public FixedHeightRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FixedHeightRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void setMaxHeight(int maxHeight) {
        this.mMaxHeight = maxHeight;
        invalidate();
    }

    private void init(@SuppressWarnings("UnusedParameters") Context context, AttributeSet attrs) {
        // 初始化属性
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.FixedHeightRecyclerview);
        mMaxHeight = attributes.getDimensionPixelOffset(R.styleable.FixedHeightRecyclerview_maxHeight, dp2px(300));
        attributes.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public int dp2px(int dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}

