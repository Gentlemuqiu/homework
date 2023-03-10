package com.example.homework.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homework.MainActivity;
import com.example.homework.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class loginActivity extends AppCompatActivity {
    private EditText mUsername, mPassword;
    //创建一个cookie集合以便保存
    public static Map<String, List<Cookie>> cookies = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        //初始化数据
        initView();
    }

    private void initView() {
        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password);
    }

    public void login(View view) {
        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password);
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();
        //使用Okhttp的客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //最大连接时间为3s
                .connectTimeout(3000, TimeUnit.MILLISECONDS)
                .cookieJar(new CookieJar() {
                    //拿到cookies
                    @Override
                    public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                        cookies.put(httpUrl.host(), list);
                    }

                    @NonNull
                    @Override
                    public List<okhttp3.Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                        List<Cookie> cookies = loginActivity.this.cookies.get(httpUrl.host());
                        //防止集合为空
                        return cookies == null ? new ArrayList<>() : cookies;
                    }
                })
                .build();
        //请求体
        FormBody body = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        //请求
        Request request = new Request.Builder()
                .post(body)
                .url("https://www.wanandroid.com/user/login")
                .build();
        //返回数据的处理
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String str = response.body().string();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(str);
                    //拿到错误编码
                    int errorCode = jsonObject.getInt("errorCode");
                    //拿到错误信息
                    String errorMeg = jsonObject.getString("errorMsg");
                    if (errorCode == 0) {
                        Intent intent = new Intent(loginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //如果错误就弹出对应的错误
                        Toast.makeText(loginActivity.this, errorMeg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}