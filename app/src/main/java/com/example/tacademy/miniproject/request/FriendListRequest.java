package com.example.tacademy.miniproject.request;

import android.content.Context;

import com.example.tacademy.miniproject.data.NetworkResult;
import com.example.tacademy.miniproject.data.User;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Park_JaeHyun on 2016-08-11.
 */
public class FriendListRequest extends AbstractRequest<NetworkResult<List<User>>> {

    Request request;
    public FriendListRequest(Context context) { // GET방식으로  data가져오는 거 해본다.
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("friendlist") //생성자에 세그먼트로 friendlist 넣어주고
                .build();
        request = new Request.Builder()
                .url(url) // 리퀘스트에 때려박음
                .tag(context) //tag를 넣어주는 이유는 네트워크를 종료했을 때 cancel 시키지 위해서다.
                .build();
    }

    @Override
    public Request getRequest()  {
        return request;
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<List<User>>>(){}.getType();
        // 요청할때 마다 타입을 같이 던져줘야 하는데 이게 Type을 얻어오는 것다.
        // Gson 라이브러리 쓰려면 이렇게 쓰라고 규칙이 되어있는거다.
        //참고!
        //NetworResult는 받아올때 code랑 result를 받아오는 거다. 보낼때는 Type!
    }
}
