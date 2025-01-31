package com.example.homework.login;

import static com.example.homework.Util.NetWorkPost.sendPostNetRequest;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homework.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUsername, mFirstPassword, mSecondPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //加载数据
        initView();
    }

    private void initView() {
        mFirstPassword = findViewById(R.id.et_firstPassword);
        mSecondPassword = findViewById(R.id.et_secondPassword);
        mUsername = findViewById(R.id.et_username);
    }

    public void register(View view) {
        //从EditText中得到对应的String
        String username = mUsername.getText().toString();
        String password1 = mFirstPassword.getText().toString();
        String password2 = mSecondPassword.getText().toString();
        //将数据以Hashmap的方式传递
        HashMap<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password1);
        params.put("repassword", password2);
        String url = "https://www.wanandroid.com/user/register";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = sendPostNetRequest(url, params);
                //跳到主线程
                updateUI(result);
            }
        }).start();
    }

    private void updateUI(String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    //解析得到的数据
                    JSONObject jsonObject = new JSONObject(result);
                    //拿到错误编码
                    int errorCode= jsonObject.getInt("errorCode");
                    String errorMeg= jsonObject.getString("errorMsg");
                    if(errorCode==0){
                        //为0表示成功
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        //否则弹出相应的错误信息
                        Toast.makeText(RegisterActivity.this, errorMeg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
