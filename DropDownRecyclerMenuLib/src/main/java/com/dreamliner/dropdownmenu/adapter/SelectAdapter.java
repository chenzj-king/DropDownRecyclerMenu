package com.dreamliner.dropdownmenu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamliner.dropdownmenu.R;
import com.dreamliner.dropdownmenu.interfaces.IGetTitle;

/**
 * @author chenzj
 * @Title: SelectAdapter
 * @Description: 类的描述 - 默认实现的一個adapter.
 * @date
 * @email admin@chenzhongjin.cn
 */
public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> implements IGetTitle {

    private String[] mDatas;

    public SelectAdapter(String[] data) {
        mDatas = data;
    }

    @Override
    public SelectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectAdapter.ViewHolder holder, int position) {
        holder.mTextView.setText(String.valueOf(mDatas[position]));
    }

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.length : 0;
    }


    public void update(String[] datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public String getTitleString(int position) {
        return mDatas[position];
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.select_tv);
        }
    }
}

