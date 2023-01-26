package com.example.homework.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.homework.Util.webView;
import com.example.homework.bean.itemChildBean;

import java.util.ArrayList;
import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.InnerViewHolder> {
    List<itemChildBean.DataDTO.DatasDTO> mDataDTOList = new ArrayList<>();
    Context mContext;

    public itemAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public itemAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mAuthor.setText(mDataDTOList.get(position).getAuthor());
        holder.mDesc.setText(mDataDTOList.get(position).getDesc());
        holder.mTitle.setText(mDataDTOList.get(position).getTitle());
        Glide.with(mContext).load(mDataDTOList.get(position).getEnvelopePic()).into(holder.mImageView);
        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(), webView.class);
                intent.putExtra("url",mDataDTOList.get(position).getLink());
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataDTOList.size();
    }

    public void setData(itemChildBean itemChildBean) {
        mDataDTOList.addAll(itemChildBean.getData().getDatas());
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTitle, mDesc, mAuthor;
        private RelativeLayout mRelativeLayout;
        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mRelativeLayout=itemView.findViewById(R.id.rl_item1);
            mImageView = itemView.findViewById(R.id.iv_item);
            mTitle = itemView.findViewById(R.id.item_title);
            mDesc = itemView.findViewById(R.id.tv_desc);
            mAuthor = itemView.findViewById(R.id.tv_author);
        }
    }
}
