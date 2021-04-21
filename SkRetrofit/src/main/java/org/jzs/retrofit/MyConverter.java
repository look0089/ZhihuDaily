package org.jzs.retrofit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;

import org.jzs.AppLog;
import org.jzs.entity.BaseBean;
import org.jzs.entity.BaseEntity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * 自定义Converter
 * 将"data"字段转换成实体类
 *
 * @author Jzs created 2018/6/6
 * @email 355392668@qq.com
 */
public class MyConverter<T> implements Converter<ResponseBody, T> {

    private TypeAdapter<T> typeAdapter;

    public MyConverter(TypeAdapter typeAdapter) {
        this.typeAdapter = typeAdapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            if (null != value) {
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = parser.parse(value.charStream()).getAsJsonObject();
                AppLog.e("SkRetrofit", "请求结果信息：" + jsonObject.toString());
                BaseBean baseBean = new BaseBean();
                if (jsonObject.has("code")) {
                    baseBean.code = jsonObject.get("code").getAsInt();
                } else if (jsonObject.has("success")) {
                    baseBean.code = jsonObject.get("success").getAsInt();
                }
                JsonElement messageElement = null;
                if (jsonObject.has("mess")) {
                    messageElement = jsonObject.get("mess");
                } else if (jsonObject.has("msg")) {
                    messageElement = jsonObject.get("msg");
                }
                if (messageElement != null) {
                    baseBean.mess = messageElement.getAsString();
                }
                JsonElement resultElement = jsonObject.get("data");
                if (resultElement != null) {
                    baseBean.data = resultElement.toString();
                }
                T resultBean;
                if (baseBean.data == null
                        || baseBean.data.equalsIgnoreCase("null")
                        || "[]".equals(baseBean.data)
                        || "\"\"".equals(baseBean.data)
                        || baseBean.data.length() == 0) {
                    resultBean = typeAdapter.fromJson("{}");
                } else {
                    resultBean = typeAdapter.fromJson(baseBean.data);
                }
                BaseEntity baseEntity = (BaseEntity) resultBean;
                baseEntity.setResultCode(baseBean.code);
                baseEntity.setResultMessage(baseBean.mess);
                baseEntity.setResultData(baseBean.data);
                baseEntity.handleField();
                return resultBean;
            }
            return typeAdapter.fromJson(value.charStream());
        } catch (Exception e) {
            e.printStackTrace();
            AppLog.e("SkRetrofit", "json转换失败，请检查返回数据");
            return null;
        } finally {
            value.close();
        }
    }
}
