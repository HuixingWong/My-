package com.example.huixing.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  ResponseListener{

    WebView webView ;
    private ImageView mWebImage;
    int id;

    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=getIntent();
//        String id = intent.getStringExtra("id");

         id = intent.getIntExtra("id", 0);


        webView = (WebView) findViewById(R.id.webview);
        pb = (ProgressBar) findViewById(R.id.pb);

        List<finalNews> finalnews = new ArrayList<>();

        new MySyncTask(this,pb).execute();


    }

    @Override
    public void onResult(List<finalNews> result) {
        finalNews finalnew = result.get(id);

        //到重头戏webview了，比较麻烦，因为要设置一些参数
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //反斜杠是为了保证不冲突，后面发蓝是请求头的内容
        String head = "<head>\n" +
                "\t<link rel=\"stylesheet\" href=\""+ finalnew.getCss().get(0) +"\"/>\n" +
                "</head>";

        //这种标语全不要，替换为空
        String img = "<div class=\"headline\">";
        String html =head + finalnew.getBody().replace(img," ");
        //载入数据吧

        webView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);

    }
}
