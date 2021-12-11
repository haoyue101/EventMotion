package com.example.app.base.adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.app.common.listener.OnItemEventListener;

public abstract class BaseViewHolder<Item extends SortedItem, BindingView extends ViewBinding> extends RecyclerView.ViewHolder {

    protected OnItemEventListener mListener;

    protected BindingView viewBinding;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewData(Item data);

    public void setOnItemEventListener(OnItemEventListener l) {
        mListener = l;
    }

}
