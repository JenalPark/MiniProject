package com.example.tacademy.miniproject.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.tacademy.miniproject.MyApplication;

/**
 * Created by Park_JaeHyun on 2016-08-14.
 */
public class PropertyManager { // 자동로그인에 필요한 정보들을 관리
    private static PropertyManager instance;
    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_REGISTRATION_ID = "regid";

    private PropertyManager() {
        Context context = MyApplication.getContext(); //MyApplication에서 getContext를 가져와서 사용함
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context); //key-value형식으로 값이 저장된 놈. SharedPreference는 값을 읽어오는 객체.
        // 앱에 대한 설정을 쓸 때 SharedPreferences를 쓴다. 주로 DefaultSharedPreferences를 쓴다.
        mEditor = mPrefs.edit(); //Editor는 값을 저장해주는데 필요한 객체.
    }

    public void setEmail(String email) {  //Email 등록해주는 작업
        mEditor.putString(KEY_EMAIL, email);
        mEditor.commit();
    }

    public String getEmail() { // Email을 가져오는 작업
        return mPrefs.getString(KEY_EMAIL, "");
    }

    public void setPassword(String password) { //Password 등록해주는 작업
        mEditor.putString(KEY_PASSWORD, password);
        mEditor.commit();
    }

    public String getPassword() { //Password 가져오는 작업
        return mPrefs.getString(KEY_PASSWORD, "");
    }

    public void setRegistrationId(String regid) { //RegistrationID 등록해주는 작업 // 서버에서 사용하는 ID인 것 같음
        mEditor.putString(KEY_REGISTRATION_ID, regid);
        mEditor.commit();
    }

    public String getRegistrationId() { //RegistrationID 가져오는 작업
        return mPrefs.getString(KEY_REGISTRATION_ID, "");
    }

}
