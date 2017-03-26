package com.example.huixing.my.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.huixing.my.*;
import com.example.huixing.my.adapter.ZhihuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huixing on 2017/1/12.
 */

public class Type1Fragment extends Fragment implements TittleResponslistener{

    RecyclerView mTypelist;
    ProgressBar progressBar;

    private Context mContext;

    private String url;

    public  Type1Fragment(String  url){
        this.url = url;
    }

    public  Type1Fragment(){}

    public  static  Type1Fragment newInstance(String type,String url){

        Type1Fragment fragment = new Type1Fragment(url);
        Bundle args = new Bundle();
        args.putString("type",type);
        args.putString("url",url);
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_type1, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.pb);

        mTypelist = (RecyclerView) view.findViewById(R.id.recycleview);

        new TittleAsyncTask(this,progressBar).execute();


        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);


        mTypelist.setLayoutManager(layoutManager);

        return view;

    }

    public  void init(final  List<Stories> storiesList){

        ZhihuAdapter adapter = new ZhihuAdapter((ArrayList<Stories>) storiesList,getActivity());

        adapter.setOnItemClickListener(new ZhihuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Stories data, int position) {

                Stories stories = storiesList.get(position);

//                String image = stories.getImages()[0];
//                String title = stories.getTitle();
//                Bundle bundle = new Bundle();
//                bundle.putString("image",image);
//                bundle.putString("tittle",title);

                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("id",position);

                startActivity(intent);
            }
        });

        mTypelist.setAdapter(adapter);



    }


    public RecyclerView getTypeList() {
        return mTypelist;
    }




    @Override
    public void onResult(List<Stories> result) {


        init(result);


    }
}
