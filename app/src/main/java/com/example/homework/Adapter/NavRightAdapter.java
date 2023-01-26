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
import com.example.homework.bean.NavBean;

import java.util.ArrayList;
import java.util.List;

public class NavRightAdapter extends RecyclerView.Adapter<NavRightAdapter.InnerViewHolder> {
    List<NavBean.DataDTO.ArticlesDTO> mList = new ArrayList<>();

    @NonNull
    @Override
    public NavRightAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_right, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavRightAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mButton.setText(mList.get(position).getTitle());
        holder.mButton.setOnClickListener(new View.OnClickListener() {
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

    public void setData(List<NavBean.DataDTO.ArticlesDTO> articles) {
        mList.clear();
        mList.addAll(articles);
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private Button mButton;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mButton = itemView.findViewById(R.id.btn_right);
        }
    }
}
