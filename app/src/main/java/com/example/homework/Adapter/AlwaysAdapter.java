package com.example.homework.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.Util.webView;
import com.example.homework.bean.alwaysBean;

import java.util.ArrayList;
import java.util.List;

public class AlwaysAdapter extends RecyclerView.Adapter<AlwaysAdapter.InnerViewHolder> {
    List<alwaysBean.DataDTO> mList = new ArrayList<>();

    @NonNull
    @Override
    public AlwaysAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.always_hotword, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlwaysAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mButton.setText(mList.get(position).getName());
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传输url
                Intent intent = new Intent(view.getContext(), webView.class);
                intent.putExtra("url", mList.get(position).getLink());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setDate(alwaysBean alwaysBean) {
        //清除
        mList.clear();
        //增加
        mList.addAll(alwaysBean.getData());
        //刷新
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private Button mButton;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mButton = itemView.findViewById(R.id.btn_search);
        }
    }
}
