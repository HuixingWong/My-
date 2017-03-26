package com.example.huixing.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import bean.Start;
import com.example.huixing.my.image.SmartImageView;
import com.google.gson.Gson;

public class SplashActivity extends AppCompatActivity {


    HttpUtils utils;

    private SmartImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = (SmartImageView) findViewById(R.id.smart);

        String url = "http://news-at.zhihu.com/api/4/start-image/1080*1776";

       utils= new HttpUtils();
        utils.getJson(url, new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                Gson gson = new Gson();
                Start start = gson.fromJson(data,Start.class);
                String img = start.getImg();

                imageView.setImageUrl(img);
                //执行跳转动画
                AlphaAnimation animation = new AlphaAnimation(0.1f,1.0f);
                animation.setDuration(5000);
                imageView.startAnimation(animation);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        startActivity(new Intent(SplashActivity.this,ContainerActivity.class));
                        SplashActivity.this.finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });





    }
}
