package com.example.homework.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.bean.hotWordBean;
import com.example.homework.childFragment.search;

import java.util.ArrayList;
import java.util.List;

public class hotWordAdapter extends RecyclerView.Adapter<hotWordAdapter.InnerViewHolder> {
    private List<hotWordBean.DataDTO> mList = new ArrayList<>();
    private search mSearch;

    @NonNull
    @Override
    public hotWordAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.always_hotword, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull hotWordAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mButton.setText(mList.get(position).getName());
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"很抱歉,自动输入功能尚未开启",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(hotWordBean hotWordBean) {
        mList.clear();
        mList.addAll(hotWordBean.getData());
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
