package com.example.homework.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.bean.NavBean;

import java.util.ArrayList;
import java.util.List;

public class NavLeftAdapter extends RecyclerView.Adapter<NavLeftAdapter.InnerViewHolder> {
    List<NavBean.DataDTO> mList = new ArrayList<>();
    private RecyclerView mRecyclerViewRight;
    private NavRightAdapter mNavRightAdapter;

    public NavLeftAdapter(RecyclerView recyclerViewRight, NavRightAdapter navRightAdapter) {
        this.mRecyclerViewRight=recyclerViewRight;
        this.mNavRightAdapter=navRightAdapter;
    }

    @NonNull
    @Override
    public NavLeftAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_left, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavLeftAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mButton.setText(mList.get(position).getName());
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNavRightAdapter.setData(mList.get(position).getArticles());
                mRecyclerViewRight.setAdapter(mNavRightAdapter);
                mRecyclerViewRight.setLayoutManager(new LinearLayoutManager(mRecyclerViewRight.getContext()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setDate(NavBean navBean) {
        mList.clear();
        mList.addAll(navBean.getData());
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private Button mButton;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mButton = itemView.findViewById(R.id.btn_navText);
        }
    }
}
