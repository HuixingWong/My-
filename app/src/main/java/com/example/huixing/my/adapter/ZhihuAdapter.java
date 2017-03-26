package com.example.huixing.my.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.huixing.my.R;
import com.example.huixing.my.Stories;
import com.example.huixing.my.image.SmartImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huixing on 2017/1/14.
 */

public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.myViewHolder>{

    private List<Stories> mDatas ;
    private List<Integer>  mHeight;

    private int[] colors = {R.color.colorAccent,
            R.color.pink_pearl,R.color.shamrock,
            R.color.light_sky_blue};



    private OnItemClickListener mListener;
    private  Context context;


    public ZhihuAdapter (ArrayList<Stories>  datas, Context context)
    {
        this.context = context;
        if (datas !=null){
            mDatas = datas;
        }else {
            mDatas = new ArrayList<>();

        }
        mHeight = new ArrayList<>();
        for (int i=0;i<mDatas.size();i++){

            mHeight.add((int)(260+Math.random()*100));

        }


    }



    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        myViewHolder holder = new myViewHolder
                (LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.recycle_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {

        final ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.height = mHeight.get(position);
        holder.imageView.setLayoutParams(params);


        holder.imageView.setImageUrl(mDatas.get(position).getImages()[0]);
        holder.tv.setText(mDatas.get(position).getTitle());
//        holder.tv.setBackgroundColor(colors[(int) (Math.random()*4)]);


        if (mListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   mListener.onItemClick(mDatas.get(position),position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public  class  myViewHolder extends RecyclerView.ViewHolder{

        SmartImageView imageView;
        TextView  tv;

        public myViewHolder(View itemView) {
            super(itemView);

            imageView = (SmartImageView) itemView.findViewById(R.id.image_latest);
            tv = (TextView) itemView.findViewById(R.id.tittle_latest);


        }
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener =  listener;
    }

    public  interface  OnItemClickListener{
        void onItemClick(Stories data, int position);
    }
}
