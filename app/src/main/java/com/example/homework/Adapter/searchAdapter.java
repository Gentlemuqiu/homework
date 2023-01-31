package com.example.homework.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.Util.webView;
import com.example.homework.bean.searchBean;

import java.util.ArrayList;
import java.util.List;

public class searchAdapter extends RecyclerView.Adapter<searchAdapter.InnerViewHolder> {
    public static List<searchBean.DataDTO.DatasDTO> mList = new ArrayList<>();

    @NonNull
    @Override
    public searchAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull searchAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_author.setText(mList.get(position).getAuthor());
        holder.tv_title.setText(mList.get(position).getTitle());
        holder.tv_time.setText(mList.get(position).getNiceDate());
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
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

    public void setData(searchBean bean) {
        //增加
        mList.addAll(bean.getData().getDatas());
        //刷新
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time, tv_author, tv_title;
        private LinearLayout mLinearLayout;
        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_author = itemView.findViewById(R.id.tv_author);
            mLinearLayout=itemView.findViewById(R.id.ll_searchText);
        }
    }
}
