package com.example.huixing.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.huixing.sample.image.SmartImageView;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SmartImageView imageView;
    OkHttpClient okHttpClient = new OkHttpClient();

    String Baseurl = "http://192.168.56.1:8080/okhttp/";


    public  static  final  String TAG = "MY";
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView) findViewById(R.id.tv_result);
        imageView = (SmartImageView) findViewById(R.id.iv);

    }

    public  void doPost(View view){

        RequestBody body = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"),
                "{username:hyman,password:123}");

        //构造request
        Request.Builder builder = new Request.Builder();
//        builder.url("").post(body);

        Request request = builder.url(Baseurl+"login").post(body).build();
        executeRequest(request);



    }

    private void executeRequest(Request request) {

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.i(TAG, "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.i(TAG, "onResponse: ");

            }
        });
    }


    public  void doGet(View view) throws IOException {

        //第一步，拿到okhttpclient对象
        //构造request
        Request.Builder builder = new Request.Builder();
        final Request request = builder.get().url
                ("http://news-at.zhihu.com/api/4/start-image/1080*1776").build();

        //将request封装为call
        Call call = okHttpClient.newCall(request);

//        Response execute = call.execute();

        //执行call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                Log.i(TAG, "onResponse: "+response.body().string());
//                final String str = response.body().string();
                final String s = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        result.setText(s);
//                        Start start = new Start();

                        Gson gson = new Gson();
                        Start start = gson.fromJson(s,Start.class);

//                        result.setText(start.getImg());

                        imageView.setImageUrl(start.getImg());


                    }
                });
            }
        });

    }


}
