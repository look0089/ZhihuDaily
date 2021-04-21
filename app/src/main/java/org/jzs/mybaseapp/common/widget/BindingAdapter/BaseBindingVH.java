package org.jzs.mybaseapp.common.widget.BindingAdapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Jzs on 2018/4/8.
 */

public class BaseBindingVH<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    protected final T mBinding;

    public BaseBindingVH(T t) {
        super(t.getRoot());
        mBinding = t;
    }

    public T getBinding() {
        return mBinding;
    }
}