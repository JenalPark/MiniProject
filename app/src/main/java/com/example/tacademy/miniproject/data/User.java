package com.example.tacademy.miniproject.data;

import java.io.Serializable;

public class User implements Serializable { //User class에서 Serializable을 implement하여
    // 유저를 직렬화하여 가져오도록 해주었다. intent간 호출은 즉 프로세스간 이동이므로 객체를 serializable해줘야 한다!
    // 액티비티에서 액티비티를 넘어가는 것은 A가 시스템에게 호출해주고 시스템이 B를 호출하는건데 마침 같은 앱에있었네?일 뿐이지
    // 어쨌든간 시스템을 거치는 것이므로 프로세스간 호출이 맞다.
    private long id;
    private String userName;
    private String email;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return userName;
    }
}
