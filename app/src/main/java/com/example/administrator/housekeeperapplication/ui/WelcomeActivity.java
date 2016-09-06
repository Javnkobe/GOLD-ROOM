package com.example.administrator.housekeeperapplication.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.administrator.housekeeperapplication.R;

/**
 * Created by Administrator on 2016/8/3 0003.
 */
public class WelcomeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initview();
    }


    @Override
    public void initview(){
            setContentView(R.layout.acyivity_welcome);
            ImageView iv = (ImageView) findViewById(R.id.imagewel);
            ObjectAnimator animator = ObjectAnimator.ofFloat(
                    iv,
                    "alpha",
                    0.0f,
                    1.0f
            );
            animator.setDuration(3000);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    //Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                    mystartactivity(MainActivity.class);
                    finish();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();
        }
}
