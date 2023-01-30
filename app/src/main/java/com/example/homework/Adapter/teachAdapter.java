package com.example.homework.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homework.R;
import com.example.homework.bean.teachBean;
import com.example.homework.childActivity.teachActivity;

import java.util.ArrayList;
import java.util.List;

public class teachAdapter extends RecyclerView.Adapter<teachAdapter.InnerViewHolder> {
    List<teachBean.DataDTO> mList=new ArrayList<>();
    @NonNull
    @Override
    public teachAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_teach,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull teachAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_desc.setText(mList.get(position).getDesc());
        holder.tv_title.setText(mList.get(position).getName());
        holder.tv_author.setText(mList.get(position).getAuthor());
        //加载图片
        Glide.with(holder.mImageView).load(mList.get(position).getCover()).into(holder.mImageView);
        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(), teachActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(teachBean teachBean) {
        //清除
        mList.clear();
        //增加
        mList.addAll(teachBean.getData());
        //刷新
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView tv_title, tv_desc, tv_author;
        private RelativeLayout mRelativeLayout;
        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_item);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
            tv_author = itemView.findViewById(R.id.tv_author);
            mRelativeLayout=itemView.findViewById(R.id.rl_tool);
        }
    }
}
