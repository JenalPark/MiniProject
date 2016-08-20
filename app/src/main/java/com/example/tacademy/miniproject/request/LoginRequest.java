package com.example.tacademy.miniproject.request;

import android.content.Context;

import com.example.tacademy.miniproject.data.NetworkResult;
import com.example.tacademy.miniproject.data.User;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-09.
 */
public class LoginRequest extends AbstractRequest<NetworkResult<User>> {
    Request request;
    public LoginRequest(Context context,String email, String password, String regId) {  //LoginRequest 만듬.
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("signin")
                .build();
        RequestBody body = new FormBody.Builder()  // body에 넣으니까 POST 방식이다.
                .add("email", email)
                .add("password", password)
                .add("registrationId", regId) //
                .build();

        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
    }

    @Override
    public Request getRequest() {
        return request;
    }

//    @Override
//    protected NetworkResult<User> parse(ResponseBody body) throws IOException {
//        String text = body.string();
//        Gson gson = new Gson();
//        NetworkResultTemp temp = gson.fromJson(text, NetworkResultTemp.class);
//        if (temp.getCode() == 1) {
//            Type type = new TypeToken<NetworkResult<User>>(){}.getType(); //제너릭있는 애들 파싱은 이렇게 한다.
//            NetworkResult<User> result = gson.fromJson(text, type);
//            return result;
//        } else {
//            Type type = new TypeToken<NetworkResult<String>>(){}.getType();
//            NetworkResult<String> result = gson.fromJson(text, type);
//            throw new IOException(result.getResult());
//        }
//    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<User>>(){}.getType();
    }
}
