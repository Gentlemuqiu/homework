package com.example.homework.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.Util.webView;
import com.example.homework.bean.articleBean;

import java.util.ArrayList;
import java.util.List;

public class articleAdapter extends RecyclerView.Adapter<articleAdapter.InnerViewHolder> {
    List<articleBean.DataDTO.DatasDTO> mData = new ArrayList<>();

    @NonNull
    @Override
    public articleAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull articleAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mUser.setText(mData.get(position).getAuthor());
        holder.mNameFrom.setText(mData.get(position).getSuperChapterName());
        holder.mTitle.setText(mData.get(position).getTitle());
        holder.mName.setText(mData.get(position).getChapterName());
        holder.mTime.setText(mData.get(position).getNiceShareDate());
        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将url传给webView加载
                Intent intent = new Intent(view.getContext(), webView.class);
                intent.putExtra("url", mData.get(position).getLink());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setDate(articleBean article) {
        //刷新
        mData.addAll(article.getData().getDatas());
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private TextView mName, mNameFrom, mTitle, mTime, mUser;
        private RelativeLayout mRelativeLayout;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mUser = itemView.findViewById(R.id.tv_User);
            mName = itemView.findViewById(R.id.tv_name);
            mTitle = itemView.findViewById(R.id.tv_title);
            mTime = itemView.findViewById(R.id.tv_time);
            mNameFrom = itemView.findViewById(R.id.tv_nameFrom);
            mRelativeLayout = itemView.findViewById(R.id.rl_item);
        }
    }
}

