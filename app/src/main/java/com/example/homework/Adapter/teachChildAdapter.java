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
import com.example.homework.bean.teachChildBean;

import java.util.ArrayList;
import java.util.List;

public class teachChildAdapter extends RecyclerView.Adapter<teachChildAdapter.InnerViewHolder> {
    List<teachChildBean.DataDTO.DatasDTO> mList=new ArrayList<>();
    @NonNull
    @Override
    public teachChildAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutchild_teach,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull teachChildAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_author.setText(mList.get(position).getAuthor());
        holder.tv_title.setText(mList.get(position).getTitle());
        holder.tv_time.setText(mList.get(position).getNiceDate());
        holder.tv_charpetName.setText(mList.get(position).getChapterName());
        holder.tv_titleFrom.setText(mList.get(position).getSuperChapterName());
        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), webView.class);
                intent.putExtra("url",mList.get(position).getLink());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(teachChildBean teachChildBean) {
        //清空
        mList.clear();
        //增加
        mList.addAll(teachChildBean.getData().getDatas());
        //刷新
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mRelativeLayout;
        private TextView tv_author, tv_title, tv_time, tv_titleFrom, tv_charpetName;
        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_charpetName = itemView.findViewById(R.id.tv_charpetName);
            tv_titleFrom = itemView.findViewById(R.id.tv_titleFrom);
            mRelativeLayout=itemView.findViewById(R.id.rl_item);
        }
    }
}
