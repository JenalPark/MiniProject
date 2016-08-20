package com.example.tacademy.miniproject.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tacademy.miniproject.MainActivity;
import com.example.tacademy.miniproject.R;

public class SimpleLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_login);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }

    public void changeSingup() { //singup 버튼이 눌렸을때 할일
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SignUpFragment()) //container에다가 SignUpFragment를 넣으려 한다.
                .addToBackStack(null) //back-key 누르면 pop되게 하고 싶음
                .commit();
    }

    public void moveMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
