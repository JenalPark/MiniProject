package com.example.tacademy.miniproject.data;

/**
 * Created by Administrator on 2016-08-09.
 */
public class NetworkResultTemp {  // code 값만 갖는 클래스 만든다,
                                 // 왜냐면 성공일때와 실패일때 code 값에 따라서 result 값이 다르므로
                                 // 미리 code 값 먼저 파싱해서 result 에 올 값을 예측한다.
    private int code;

    public int getCode() {
        return code;
    }
}
