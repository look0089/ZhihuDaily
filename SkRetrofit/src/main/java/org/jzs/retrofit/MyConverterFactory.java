package org.jzs.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class MyConverterFactory extends Converter.Factory {

    private static MyConverterFactory myConverterFactory;

    private MyConverterFactory() {
    }

    public static MyConverterFactory create() {
        if (null == myConverterFactory) {
            myConverterFactory = new MyConverterFactory();
        }
        return myConverterFactory;
    }

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd hh:mm:ss")
            .create();

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> _typeAdapter = gson.getAdapter(TypeToken.get(type));
        if (type instanceof Class) {
//            if (BasiceBean.class.isAssignableFrom((Class<?>) type)) {
//                return new MyConverter<>(_typeAdapter);
//            }
            return new MyConverter<>(_typeAdapter);
        }
        return null;
    }
}
