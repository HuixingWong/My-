package com.example.huixing.my;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huixing on 2017/1/17.
 */

public class TittleAsyncTask extends AsyncTask<Void,Void,List<Stories>> {

    private TittleResponslistener tittleResponslistener;

     ProgressBar progressBar;


    public TittleAsyncTask(TittleResponslistener tittleResponslistener,ProgressBar progressBar){

        this.tittleResponslistener = tittleResponslistener;
        this.progressBar = progressBar;

    }



    @Override
    protected List<Stories> doInBackground(Void... params) {

        List<Stories> stories = new ArrayList<>();

        try {
            List<Stories> stories1 = Gsonjiexizhihu.request();

            stories = stories1;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stories;
    }

    @Override
    protected void onPostExecute(List<Stories> stories) {
        super.onPostExecute(stories);

        progressBar.setVisibility(View.GONE);

        tittleResponslistener.onResult(stories);

    }
}
