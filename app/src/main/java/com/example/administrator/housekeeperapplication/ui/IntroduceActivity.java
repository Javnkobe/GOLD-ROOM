package com.example.administrator.housekeeperapplication.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.fragment.IntroduceFragment;

public class IntroduceActivity extends BaseActivity {

    Intent musicIntent;
    public int[] resIds = {R.mipmap.adware_style_applist,
            R.mipmap.adware_style_banner,R.mipmap.adware_style_creditswall};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        SharedPreferences preferences =
                getSharedPreferences("lead_config", Context.MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean("isFirstRun", true);
        if (!isFirstRun) {
            mystartactivity(WelcomeActivity.class);
            finish();
        }
        else{
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstRun", false);
            editor.apply();
            Intent serviceIntent = new Intent(this,MusicService.class);
            startService(serviceIntent);
            initview();
        }
    }

    public void jump(View view){
        musicIntent = new Intent(this,MusicService.class);
        stopService(musicIntent);
        mystartactivity(WelcomeActivity.class);
        finish();
    }

    @Override
    public void initview() {
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        MyFragmentStatePagerAdapter  adapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        final LinearLayout tv_container = (LinearLayout) findViewById(R.id.tv_container);
        viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                int count = tv_container.getChildCount();
                for (int i=0;i<count;i++){
                    View v = tv_container.getChildAt(i);
                    v.setBackgroundDrawable(getResources().getDrawable(R.drawable.adware_style_default));
                }
                View v = tv_container.getChildAt(position);
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.adware_style_selected));
            }
        });
        /*musicIntent = new Intent(this,MusicService.class);
        stopService(musicIntent);*/

    }

    class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter{

        public MyFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            IntroduceFragment fragment = new IntroduceFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("i",position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return resIds.length;
        }
    }


}
