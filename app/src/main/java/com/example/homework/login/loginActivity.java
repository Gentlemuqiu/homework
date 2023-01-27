package com.example.homework.login;

import static com.example.homework.Util.NetWorkPost.sendPostNetRequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homework.MainActivity;
import com.example.homework.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class loginActivity extends AppCompatActivity {
    private EditText mUsername, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();
    }

    private void initView() {
        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password);
    }

    public void login(View view) {
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        String url = "https://www.wanandroid.com/user/login";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = sendPostNetRequest(url, params);
                updateUI(result);
            }
        }).start();
    }

    private void updateUI(String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    int errorCode = jsonObject.getInt("errorCode");
                    String errorMeg = jsonObject.getString("errorMsg");
                    if (errorCode == 0) {
                        Intent intent=new Intent(loginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(loginActivity.this, errorMeg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}