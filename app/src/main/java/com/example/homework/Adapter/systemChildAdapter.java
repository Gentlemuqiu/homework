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
import com.example.homework.bean.systemBean;
import com.example.homework.childActivity.article;

import java.util.List;

public class systemChildAdapter extends RecyclerView.Adapter<systemChildAdapter.InnerViewHolder> {
    private List<systemBean.DataDTO.ChildrenDTO> mChildrenDTOS;

    public systemChildAdapter(List<systemBean.DataDTO.ChildrenDTO> ChildrenDTDS) {
        this.mChildrenDTOS = ChildrenDTDS;
    }

    @NonNull
    @Override
    public systemChildAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.systembutton, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull systemChildAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.mButton.setText(mChildrenDTOS.get(position).getName());
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = mChildrenDTOS.get(position).getId();
                //拿到id交给article
                Intent intent = new Intent(view.getContext(), article.class);
                intent.putExtra("id",id);
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mChildrenDTOS.size();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        public Button mButton;

        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mButton = itemView.findViewById(R.id.btn_content);
        }
    }
}
