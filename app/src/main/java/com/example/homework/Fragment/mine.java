package com.example.homework.Fragment;

import static com.example.homework.Util.NetWorkGet.doCookieGet;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.homework.R;
import com.example.homework.bean.mineBean;
import com.google.gson.Gson;

public class mine extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private TextView tv_user, tv_email, tv_score;

    public static mine newInstance(String param1, String param2) {
        mine fragment = new mine();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_user = view.findViewById(R.id.tv_user);
        tv_score = view.findViewById(R.id.tv_score);
        tv_email = view.findViewById(R.id.tv_email);
        NetLoad();
    }

    private void NetLoad() {
        String url = "https://wanandroid.com//user/lg/userinfo/json";
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = doCookieGet(url);
                Gson gson=new Gson();
                Log.d("hui", "run: "+result);
                mineBean mineBean=gson.fromJson(result, com.example.homework.bean.mineBean.class);
                upUI(mineBean);
            }
        }).start();
    }
    //更新
    private void upUI(mineBean mineBean) {
        if(getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("hui", "run: "+ mineBean.getData().getUserInfo().getUsername());
                    Log.d("hui", "run: "+mineBean.getData().getCoinInfo().getCoinCount());
                    Log.d("hui", "run: "+mineBean.getData().getUserInfo().getEmail());
                  tv_user.setText(mineBean.getData().getUserInfo().getUsername());
                  //不能int 只能String
                  tv_score.setText(mineBean.getData().getCoinInfo().getCoinCount().toString());
                  tv_email.setText(mineBean.getData().getUserInfo().getEmail());
                }
            });
        }
    }
}