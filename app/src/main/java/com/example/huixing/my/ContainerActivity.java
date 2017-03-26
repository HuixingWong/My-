package com.example.huixing.my;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import com.example.huixing.my.Fragment.Type1Fragment;

import java.util.ArrayList;
import java.util.List;

public class ContainerActivity extends AppCompatActivity implements View.OnClickListener {

    NavigationView mNavView;
    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    FloatingActionButton mFab;
    FloatingActionButton mFab2;
    FloatingActionButton mFab3;
    FloatingActionButton mFab4;
    FloatingActionButton mFab5;
    CoordinatorLayout mCoordinatorLayout;


    //悬浮按钮的状态
    private boolean isshow = false;

    //floating actionBar 数组
    private  List<FloatingActionButton> list = new ArrayList<>();
    private int [] ids = {R.id.fab,R.id.fab2,R.id.fab3,R.id.fab4,R.id.fab5};

    private ArrayList<Type1Fragment> mFragments;

    private ArrayList<String> mTitles;

    private ArrayList<String> mUrls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        initContent();
    }


    private void initContent(){

        mFragments = new ArrayList<>();
        mFragments.add(Type1Fragment.newInstance("知乎日报",""));
        mFragments.add(Type1Fragment.newInstance("豆瓣一刻",""));


        mTitles = new ArrayList<>();
        mTitles.add("知乎日报");
        mTitles.add("豆瓣一刻");
        mTitles.add("科技");
        mTitles.add("娱乐");

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);

        TabpagerAdapter adapter = new TabpagerAdapter(getSupportFragmentManager());
        adapter.setArguments(mFragments,mTitles,mUrls);

        mViewPager.setAdapter(adapter);


        //设置TabLayout可滚动，保证Tab数量过多时也可正常显示
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //两个参数分别对应Tab未选中的文字颜色和选中的文字颜色
        mTabLayout.setTabTextColors(Color.WHITE, Color.RED);
        //绑定ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
        //设置TabLayout的布局方式（GRAVITY_FILL、GRAVITY_CENTER）
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //设置TabLayout的选择监听
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());
                mFragments.get(tab.getPosition()).getTypeList();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        mFab = (FloatingActionButton) findViewById(R.id.fab);
//        mFab2 = (FloatingActionButton) findViewById(R.id.fab2);
//        mFab3 = (FloatingActionButton) findViewById(R.id.fab3);
//        mFab4 = (FloatingActionButton) findViewById(R.id.fab4);
//        mFab5 = (FloatingActionButton) findViewById(R.id.fab5);

        for (int i=0;i<ids.length;i++){
            FloatingActionButton button = (FloatingActionButton) findViewById(ids[i]);
            list.add(button);
            button.setOnClickListener(this);
        }

//        mFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ContainerActivity.this, "分享", Toast.LENGTH_SHORT).show();
//
//
//
//            }
//        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.fab:
//                Toast.makeText(this, "11111", Toast.LENGTH_SHORT).show();
                if (isshow){
                    stopAnimator();
                }else {
                    startAnimator();


                }
                break;
        }

    }


    private void startAnimator() {

        for (int i=1;i<list.size();i++){


            Float f = Float.valueOf(String.valueOf(Math.sin((i-1)*30*Math.PI/180)*300));
            Float f1 = Float.valueOf(String.valueOf(Math.sqrt(90000-f*f)));

            ObjectAnimator animator = ObjectAnimator.ofFloat(list.get(i),
                    "TranslationY",0f, -f);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(list.get(i),"TranslationX",0f,-f1);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(list.get(i),"rotation",0f,720f);
            ObjectAnimator animator3 = ObjectAnimator.ofFloat(list.get(i),"alpha",0f,1f);
            ObjectAnimator animator4 = ObjectAnimator.ofFloat(list.get(i),"rotationX",0f,-720f);

            //Z轴的移动。
//            ObjectAnimator animator2 = ObjectAnimator.ofFloat(list.get(i),"TranslationZ",0f,300f);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(500);
            animatorSet.setInterpolator(new BounceInterpolator());
            animatorSet.playTogether(animator,animator1,animator2,animator3,animator4);

            animatorSet.start();
//
//            animator.setStartDelay(i*200);
//            //插值器的使用
//            animator.setInterpolator(new BounceInterpolator());
//            animator.setDuration(300);
//            animator.start();
            isshow = true;


        }


    }
    private void stopAnimator() {

        for (int i=1;i<list.size();i++){


            Float f = Float.valueOf(String.valueOf(Math.sin((i-1)*30*Math.PI/180)*500));
            Float f1 = Float.valueOf(String.valueOf(Math.sqrt(250000-f*f)));

            ObjectAnimator animator = ObjectAnimator.ofFloat(list.get(i),
                    "TranslationY",-f,0f);
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(list.get(i),"TranslationX",-f1,0f);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(300);
            animatorSet.setInterpolator(new BounceInterpolator());
            animatorSet.playTogether(animator,animator1);

            animatorSet.start();
//
//            animator.setStartDelay(i*200);
//            //插值器的使用
//            animator.setInterpolator(new BounceInterpolator());
//            animator.setDuration(300);
//            animator.start();
            isshow = false;


        }


    }


    public class TabpagerAdapter extends FragmentPagerAdapter{

        private List<Type1Fragment> fragments;
        private List<String> titles;
        private List<String> url;


        public TabpagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);
        }


        public void setArguments(List<Type1Fragment> fragments,
                                 List<String> titles, List<String> url) {
            this.fragments = fragments;
            this.titles = titles;
            this.url = url;
        }



        @Override
        public int getCount() {
            return fragments.size();
        }
        //返回TabLayout对应Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}
