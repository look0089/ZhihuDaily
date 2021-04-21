package org.jzs.entity;


import android.databinding.BaseObservable;

public class BaseEntity extends BaseObservable {
    /**
     * 请求成功
     */
    public static final int RESULT_OK = 0;

    public int resultCode;
    public String resultMessage;
    public String resultData;

    public boolean favorite;//收藏

    public String headerTitle;
    public boolean showTitle;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public boolean isSuccessful() {
        return resultCode == RESULT_OK;
    }

    public void handleField() {
    }
}

