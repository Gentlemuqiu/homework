package com.example.homework.Adapter;

import static com.example.homework.Util.NetWorkPost.sendPost;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.Util.webView;
import com.example.homework.bean.loveBean;

import java.util.ArrayList;
import java.util.List;

public class loveAdapter extends RecyclerView.Adapter<loveAdapter.InnerViewHolder> {
    public static List<loveBean.DataDTO.DatasDTO> mData = new ArrayList<>();

    @NonNull
    @Override
    public loveAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull loveAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mUser.setText(mData.get(position).getAuthor());
        holder.mTitle.setText(mData.get(position).getTitle());
        holder.mName.setText(mData.get(position).getChapterName());
        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), webView.class);
                intent.putExtra("url", mData.get(position).getLink());
                view.getContext().startActivity(intent);
            }
        });
        holder.mRelativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("你是否要取消收藏");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = mData.get(position).getId();
                        int originId=mData.get(position).getOriginId();
                        upNetLoad(id,originId);
                        Toast.makeText(view.getContext(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    private void upNetLoad(int id, int originId) {
        String url = "https://www.wanandroid.com/lg/uncollect/" + id + "/json"+"?originId="+originId;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = sendPost(url);
                Log.d("hui", "run: "+result);
            }
        }).start();
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(loveBean loveBean) {
        mData.clear();
        mData.addAll(loveBean.getData().getDatas());
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private TextView mName, mTitle, mUser;
        private RelativeLayout mRelativeLayout;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mUser = itemView.findViewById(R.id.tv_User);
            mName = itemView.findViewById(R.id.tv_name);
            mTitle = itemView.findViewById(R.id.tv_title);
            mRelativeLayout = itemView.findViewById(R.id.rl_item);
        }
    }
}
