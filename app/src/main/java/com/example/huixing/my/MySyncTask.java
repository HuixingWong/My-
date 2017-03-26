package com.example.huixing.my;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by huixing on 2017/1/10.
 */

public class MySyncTask extends AsyncTask<Void,Void,List<finalNews>> {

    private ResponseListener listener;
    private ProgressBar progressBar;

    private Context context;

    public MySyncTask(ResponseListener listener,ProgressBar progressBar) {
        this.listener = listener;
        this.progressBar = progressBar;
    }


    @Override
    protected List<finalNews> doInBackground(Void... params) {


//        File cacheDir = context.getExternalFilesDir(null);
//        int maxSize = 10*1024*1024;
//        Cache cache = new Cache(cacheDir,maxSize);

        OkHttpClient client = new OkHttpClient.Builder().build();

        List<finalNews> finalNewsList = new ArrayList<>();
        try {
            List<String> list = SecondJiexi.secondRequest();

            //便利拼接出来的字符串集合，分别请求将请求到的结果转换成bean类存储在集合中
            for (String str: list
                    ) {
                Request request = new Request.Builder().url(str).
                        cacheControl(new CacheControl.Builder().maxStale(1, TimeUnit.MINUTES).build()).
                        build();
                Response response = client.newCall(request).execute();
                String json = response.body().string();
                JsonObject object =  new JsonParser().parse(json).getAsJsonObject();
                Gson gson = new Gson();
                finalNews finalnew = gson.fromJson(object, com.example.huixing.my.finalNews.class);


                finalNewsList.add(finalnew);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return finalNewsList;
    }

    @Override
    protected void onPostExecute(List<finalNews> finalNewses) {
        super.onPostExecute(finalNewses);

        progressBar.setVisibility(View.GONE);

        listener.onResult(finalNewses);

    }

}
