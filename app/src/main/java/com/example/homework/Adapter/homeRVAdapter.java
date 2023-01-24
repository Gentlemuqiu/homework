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
import com.example.homework.bean.homePager;
import com.example.homework.bean.homePagerTop;

import java.util.ArrayList;
import java.util.List;

public class homeRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<homePager.DataDTO.DatasDTO> mData = new ArrayList<>();
    private List<homePagerTop.DataDTO> mData2 = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {
        if (position < mData2.size()) {
            return 1;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home, parent, false));
        } else {
            return new headerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_top, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (holder instanceof InnerHolder) {
            ((InnerHolder)holder).mUser.setText(mData.get(position).getAuthor());
            ((InnerHolder)holder).mNameFrom.setText(mData.get(position).getSuperChapterName());
            ((InnerHolder)holder).mTitle.setText(mData.get(position).getTitle());
            ((InnerHolder)holder).mName.setText(mData.get(position).getChapterName());
            ((InnerHolder)holder).mTime.setText(mData.get(position).getNiceShareDate());
            ((InnerHolder)holder).mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), webView.class);
                    intent.putExtra("url", mData.get(position).getLink());
                    view.getContext().startActivity(intent);
                }
            });
        } else if (holder instanceof headerViewHolder) {
            ((headerViewHolder)holder).mUser.setText(mData2.get(position).getAuthor());
            ((headerViewHolder)holder).mNameFrom.setText(mData2.get(position).getSuperChapterName());
            ((headerViewHolder)holder).mTitle.setText(mData2.get(position).getTitle());
            ((headerViewHolder)holder).mName.setText(mData2.get(position).getChapterName());
            ((headerViewHolder)holder).mTime.setText(mData2.get(position).getNiceShareDate());
            ((headerViewHolder)holder).mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), webView.class);
                    intent.putExtra("url", mData2.get(position).getLink());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setDate1(homePager homePager) {
        mData.addAll(homePager.getData().getDatas());
        notifyDataSetChanged();
    }

    public void setDate(homePagerTop homePagerTop) {
        mData2.clear();
        mData2.addAll(homePagerTop.getData());
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        private TextView mName, mNameFrom, mTitle, mTime, mUser;
        private RelativeLayout mRelativeLayout;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mUser = itemView.findViewById(R.id.tv_User);
            mName = itemView.findViewById(R.id.tv_name);
            mTitle = itemView.findViewById(R.id.tv_title);
            mTime = itemView.findViewById(R.id.tv_time);
            mNameFrom = itemView.findViewById(R.id.tv_nameFrom);
            mRelativeLayout = itemView.findViewById(R.id.rl_item);
        }
    }

    public class headerViewHolder extends RecyclerView.ViewHolder {
        private TextView mName, mNameFrom, mTitle, mTime, mUser;
        private RelativeLayout mRelativeLayout;

        public headerViewHolder(@NonNull View itemView) {
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
