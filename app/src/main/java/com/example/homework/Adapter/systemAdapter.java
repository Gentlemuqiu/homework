package com.example.homework.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.bean.systemBean;

import java.util.ArrayList;
import java.util.List;

public class systemAdapter extends RecyclerView.Adapter<systemAdapter.InnerViewHolder> {
    private List<systemBean.DataDTO> mData = new ArrayList<>();

    @NonNull
    @Override
    public systemAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.systemchild, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull systemAdapter.InnerViewHolder holder, int position) {
        holder.mTextView.setText(mData.get(position).getName());
        systemChildAdapter systemChildAdapter = new systemChildAdapter(mData.get(position).getChildren());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
        holder.mRecyclerViewChild.setLayoutManager(linearLayoutManager);
        holder.mRecyclerViewChild.setAdapter(systemChildAdapter);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setDate(systemBean systembean) {
        //清除
        mData.clear();
        //增加
        mData.addAll(systembean.getData());
        //刷新
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private RecyclerView mRecyclerViewChild;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_title1);
            mRecyclerViewChild = itemView.findViewById(R.id.rv_systemChild);
        }
    }
}