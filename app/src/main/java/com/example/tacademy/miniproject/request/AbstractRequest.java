package com.example.tacademy.miniproject.request;

import com.example.tacademy.miniproject.data.NetworkResult;
import com.example.tacademy.miniproject.data.NetworkResultTemp;
import com.example.tacademy.miniproject.manager.NetworkRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

/**
 * Created by Tacademy on 2016-08-09.
 */
public abstract class AbstractRequest<T> extends NetworkRequest<T> {

    protected HttpUrl.Builder getBaseUrlBuilder() { //HttpUrl 을 만들어서 넘긴다.
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https");
        builder.host("mysample-1470719922738.appspot.com"); //여기는 내 서버 host를 넣어야 한다.
        return builder;  //똑같은거 반복할 필요 없으니까 이렇게 만들어서 사용한다!
    }

    @Override
    protected T parse(ResponseBody body) throws IOException { //여기서는 파싱을 두번해주는데 강사님이 급하게만든거라 code와 result를 같은 라인에 두고 만들어서 파싱을 두번하는거다.
                                                              // 이 규격이 꼭 맞는건 아니니 그냥 temp는 너무 심각하게 생각하지말고 넘어가자.
        String text = body.string();
        Gson gson = new Gson();
        NetworkResultTemp temp = gson.fromJson(text, NetworkResultTemp.class);
        if (temp.getCode() == 1) {
            T result = gson.fromJson(text, getType());
            return result;
        } else {
            Type type = new TypeToken<NetworkResult<String>>(){}.getType();
            NetworkResult<String> result = gson.fromJson(text, type);
            throw new IOException(result.getResult());
        }
    }

    protected abstract Type getType();
}
