package com.example.homework.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homework.R;
import com.example.homework.bean.commentBean;

import java.util.ArrayList;
import java.util.List;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.InnerViewHolder> {
    List<commentBean.DataDTO.DatasDTO.ReplyCommentsDTO> mList = new ArrayList<>();

    @NonNull
    @Override
    public commentAdapter.InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull commentAdapter.InnerViewHolder holder, @SuppressLint("RecyclerView") int position) {
         holder.mTime.setText(mList.get(position).getNiceDate());
         holder.mName.setText(mList.get(position).getToUserName());
         holder.mUser.setText(mList.get(position).getUserName());
         holder.mContent.setText(mList.get(position).getContent());
         //不能是int
         holder.mGood.setText(mList.get(position).getZan().toString());
         //设置点击监听,点击后出现整个content
         holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                 builder.setMessage(mList.get(position).getContent());
                 AlertDialog dialog=builder.create();
                 dialog.show();
             }
         });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setData(commentBean commentBean) {
        //清空
        mList.clear();
        //增加
        mList.addAll(commentBean.getData().getDatas().get(0).getReplyComments());
        //刷新
        notifyDataSetChanged();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        private TextView mUser, mContent, mName, mTime,mGood;
        private RelativeLayout mRelativeLayout;
        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            mUser = itemView.findViewById(R.id.tv_User);
            mContent = itemView.findViewById(R.id.tv_content);
            mName = itemView.findViewById(R.id.tv_name);
            mTime = itemView.findViewById(R.id.tv_time);
            mGood=itemView.findViewById(R.id.tv_good);
            mRelativeLayout= itemView.findViewById(R.id.rl_comment);
        }
    }
}
