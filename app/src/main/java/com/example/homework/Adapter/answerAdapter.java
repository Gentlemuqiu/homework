package com.example.homework.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.Util.webView;
import com.example.homework.bean.answerBean;
import com.example.homework.childActivity.comment;

import java.util.ArrayList;
import java.util.List;

public class answerAdapter extends RecyclerView.Adapter<answerAdapter.InnerViewHolder> {
    List<answerBean.DataDTO.DatasDTO> mData = new ArrayList<>();

    @NonNull
    @Override
    public answerAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_answer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull answerAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_time.setText(mData.get(position).getNiceDate());
        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_author.setText(mData.get(position).getAuthor());
        //点击跳转页面
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), webView.class);
                intent.putExtra("url", mData.get(position).getLink());
                view.getContext().startActivity(intent);
            }
        });
        //点击跳转评论
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //携带id跳转到评论页面
                Intent intent = new Intent(view.getContext(), comment.class);
                intent.putExtra("id", mData.get(position).getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(answerBean answerBean) {
        //在集合中加入元素
        mData.addAll(answerBean.getData().getDatas());
        //刷新界面
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_author, tv_title, tv_time;
        private Button mButton;
        private LinearLayout mLinearLayout;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_title = itemView.findViewById(R.id.tv_title);
            mButton = itemView.findViewById(R.id.btn_answer);
            mLinearLayout = itemView.findViewById(R.id.ll_answer);
        }
    }
}
