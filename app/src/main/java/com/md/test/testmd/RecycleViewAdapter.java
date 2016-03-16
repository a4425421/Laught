package com.md.test.testmd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.md.test.testmd.bean.News;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/16.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    private OnRecyclerItemClickListener OnRecyclerItemClickListener;
    private Context context;
    private ArrayList<News> list;
    public RecycleViewAdapter(Context context,ArrayList<News> list){
        this.context =context;
        this.list =list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item,null));

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        News news = list.get(position);
        holder.tv.setText(news.getTitle());
        MyApplication.imageLoader.displayImage(news.getPurl(),holder.iv,MyApplication.getListOptions());

        if(OnRecyclerItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnRecyclerItemClickListener.onItemClick(v,position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv ;
        ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
             tv = (TextView)itemView.findViewById(R.id.title);
            iv =(ImageView)itemView.findViewById(R.id.image);
        }
    }


    public interface OnRecyclerItemClickListener{
        public void onItemClick(View view,int position);
    }

    public void setOnRecyclerItemClickListener(RecycleViewAdapter.OnRecyclerItemClickListener onRecyclerItemClickListener) {
        OnRecyclerItemClickListener = onRecyclerItemClickListener;
    }
}
