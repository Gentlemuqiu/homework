package com.example.homework.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.bean.basicAccountBean;
import com.example.homework.childActivity.Account;

import java.util.ArrayList;
import java.util.List;

public class basicAccountAdapter extends RecyclerView.Adapter<basicAccountAdapter.InnerView> {
    List<basicAccountBean.DataDTO> mList = new ArrayList<>();

    @NonNull
    @Override
    public basicAccountAdapter.InnerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerView(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_btnaccount, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull basicAccountAdapter.InnerView holder, int position) {
        holder.mButton.setText(mList.get(position).getName());
        int id=mList.get(position).getId();
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Account.class);
                intent.putExtra("id",id);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setDate(basicAccountBean basicAccountBean) {
        mList.clear();
        mList.addAll(basicAccountBean.getData());
        notifyDataSetChanged();
    }

    public class InnerView extends RecyclerView.ViewHolder {
        private Button mButton;

        public InnerView(@NonNull View itemView) {
            super(itemView);
            mButton = itemView.findViewById(R.id.btn_account);
        }
    }
}
