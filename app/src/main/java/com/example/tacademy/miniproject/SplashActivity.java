package com.example.tacademy.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.tacademy.miniproject.data.NetworkResult;
import com.example.tacademy.miniproject.data.User;
import com.example.tacademy.miniproject.login.SimpleLoginActivity;
import com.example.tacademy.miniproject.manager.NetworkManager;
import com.example.tacademy.miniproject.manager.NetworkRequest;
import com.example.tacademy.miniproject.manager.PropertyManager;
import com.example.tacademy.miniproject.request.LoginRequest;
import com.example.tacademy.miniproject.request.ProfileRequest;

public class SplashActivity extends AppCompatActivity { // Splash 뜨는 동안 로그인 하는 거다 , 로그인 시도 함.
    //login 시에 sharedPreference에 값을 저장한다. 값이 있으면 splash에서 sharedPreference에
    // 저장된 id와 pw로 로그인하는 것이고 없으면 loginActivity띄워주는 것이다.
    //logout은 sharedPreferencs에 저장된 값을 지움. 세션이 살아있으면 앱이 죽어도 세션을
    // 받아와서 로그인된 상태를 유지할 수 있다. logout하면 세션도 같이 죽여야 됨.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProfileRequest request = new ProfileRequest(this);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() {
            @Override
            public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                moveMainActivity();
            }  //로그인이 되어있으면(세션이 존재하면) 계속 ListView가 있는 MainActivity를 띄워주는 것

            @Override
            public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                if (errorCode == -1) {  //로그인이 안되어 있을 때에는 내가 만들어준 autoLogin 메소드가 호출되는 것
                    if (errorMessage.equals("not login")) {
                        loginSharedPreference();
                        return;
                    }
                }
                moveLoginActivity();
            }
        });
    }

    private void loginSharedPreference() {
        if (isAutoLogin()) {
            processAutoLogin();
        } else {
            moveLoginActivity();
        }
    }
    private boolean isAutoLogin() {
        String email = PropertyManager.getInstance().getEmail();
        return !TextUtils.isEmpty(email);
    }
    private void processAutoLogin() {
        String email = PropertyManager.getInstance().getEmail(); // Email 가져와서
        if (!TextUtils.isEmpty(email)) { //가져온 Email값이 null이 아니면 아래 작업을 수행하겠다.
            String password = PropertyManager.getInstance().getPassword();
            String regid = PropertyManager.getInstance().getRegistrationId();
            LoginRequest request = new LoginRequest(this, email, password, regid);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkResult<User>>() { //네트워크 매니저 소환해서
                @Override
                public void onSuccess(NetworkRequest<NetworkResult<User>> request, NetworkResult<User> result) {
                    moveMainActivity(); // 성공했을 시 main 액티비티를 띄운다.
                }

                @Override
                public void onFail(NetworkRequest<NetworkResult<User>> request, int errorCode, String errorMessage, Throwable e) {
                    moveLoginActivity(); // 실패 했을 시 로그인 하라는 액티비티를 띄운다.
                }
            });
        }
    }

    private void moveMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void moveLoginActivity() {
        mHandler.postDelayed(new Runnable() {  //여기에 왜 Handler, 스레드가 필요하나?
            @Override                           // 자동로그인에 실패 했으니까 로그인 할수있는 액티비티 띄워준다.
            public void run() {
                startActivity(new Intent(SplashActivity.this, SimpleLoginActivity.class));
                finish();
            }
        }, 1000); //SplashActivity 는 자동 로그인 하는거니까 fail 이니까 수동 로그인 화면으로 이동하는 것이다.
    }

    Handler mHandler = new Handler(Looper.getMainLooper());
}
