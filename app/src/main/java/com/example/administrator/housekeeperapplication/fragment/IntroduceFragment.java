package com.example.administrator.housekeeperapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.ui.IntroduceActivity;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class IntroduceFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*ImageView imageview = new ImageView(getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        imageview.setLayoutParams(params);*/
        ImageView imageview = (ImageView) inflater.inflate(R.layout.fragment_introduce_layout,null);
        Bundle bundle = getArguments();
        int i = bundle.getInt("i");
        IntroduceActivity activity = (IntroduceActivity) getActivity();
        imageview.setBackgroundResource(activity.resIds[i]);
        return imageview;
    }
}
