package org.jzs.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jzs on 2017/8/2.
 */

public class BaseBean extends BaseEntity {
    @SerializedName("code")
    public int code = -1;
    @SerializedName("success")
    public int success = -1;
    @SerializedName("mess")
    public String mess;
    @SerializedName("msg")
    public String msg;
    @SerializedName("data")
    public String data;
}
