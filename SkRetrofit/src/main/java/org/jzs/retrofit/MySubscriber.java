package org.jzs.retrofit;

import android.content.Context;
import android.text.TextUtils;

import org.jzs.entity.BaseEntity;
import org.jzs.skutils.AppLog;
import org.jzs.skutils.dialog.ProgressDialog;
import org.jzs.skutils.utils.ToastUtils;

import java.lang.ref.SoftReference;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/6/13 0013.
 */

public abstract class MySubscriber<T extends BaseEntity> extends Subscriber<T> implements TaskListener<T> {
    private SoftReference<Context> mContextReference;

    public MySubscriber(Context context) {
        mContextReference = new SoftReference<>(context);
    }

    @Override
    public void onStart() {
        taskStart();
    }

    @Override
    public final void onNext(T entity) {
        if (entity.getResultCode() == 0) {
            taskSuccess(entity);
        } else {
            taskFailure(entity);
        }
        taskStop();
    }

    @Override
    public final void onCompleted() {
    }

    @Override
    public final void onError(Throwable throwable) {
        taskError(throwable);
    }

    @Override
    public void taskStart() {
        //加載框
        ProgressDialog.INSTANCE.createLoadingDialog(mContextReference.get());
    }

    @Override
    public void taskStop() {
        ProgressDialog.INSTANCE.dismissDialog();
    }

    @Override
    public void taskFailure(T entity) {
        showToastMsg(entity.getResultMessage());
    }

    @Override
    public void taskError(Throwable throwable) {
        taskStop();
        throwable.printStackTrace();
        ToastUtils.INSTANCE.showToast(mContextReference.get(), "请求失败，请重试");
    }

    private void showToastMsg(final String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        AppLog.e("SkRetrofit", msg);
        ToastUtils.INSTANCE.showToast(mContextReference.get(), msg);
    }

}