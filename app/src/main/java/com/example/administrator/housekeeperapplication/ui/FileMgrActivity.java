package com.example.administrator.housekeeperapplication.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.biz.FileManager;
import com.example.administrator.housekeeperapplication.util.FileTypeUtil;

public class FileMgrActivity extends BaseActivity implements FileManager.OnFileManagerListener {

    private TextView tvTotal,tvAll,tvTxt,tvVideo,tvAudio,tvImage,tvApk,tvZip;
    private ImageView ivAll,ivTxt,ivVideo,ivAudio,ivImage,ivApk,ivZip;
    private ProgressBar pbAll,pbTxt,pbVideo,pbAudio,pbImage,pbApk,pbZip;
    private FileManager fileMgr;
    private String allsize;
    private static final int ON_END = 0;
    private static final int ON_SUCCESS =1;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ON_END:
                    //隐藏进度条
                    pbAll.setVisibility(View.GONE);
                    pbTxt .setVisibility(View.GONE);
                    pbVideo.setVisibility(View.GONE);
                    pbAudio.setVisibility(View.GONE);
                    pbImage .setVisibility(View.GONE);
                    pbZip .setVisibility(View.GONE);
                    pbApk .setVisibility(View.GONE);
                    //显示箭头图标
                    ivAll.setVisibility(View.VISIBLE);
                    ivTxt.setVisibility(View.VISIBLE);
                    ivVideo.setVisibility(View.VISIBLE);
                    ivAudio.setVisibility(View.VISIBLE);
                    ivImage.setVisibility(View.VISIBLE);
                    ivZip.setVisibility(View.VISIBLE);
                    ivApk.setVisibility(View.VISIBLE);
                    //设置全部文件大小
                    tvTotal.setText(allsize);
                    break;
                case ON_SUCCESS :
                    //将大小设置在UI界面
                    Bundle bundle = msg.getData();
                    String type = bundle.getString("type");
                    int size = bundle.getInt("size");
                    if(type.equals(FileTypeUtil.TYPE_TXT)){
                        tvTxt.setText(Formatter.formatFileSize(FileMgrActivity.this,size));
                    }else  if(type.equals(FileTypeUtil.TYPE_IMAGE)){
                        tvImage.setText(Formatter.formatFileSize(FileMgrActivity.this,size));
                    }else  if(type.equals(FileTypeUtil.TYPE_AUDIO)){
                        tvAudio.setText(Formatter.formatFileSize(FileMgrActivity.this,size));
                    }else  if(type.equals(FileTypeUtil.TYPE_VIDEO)){
                        tvVideo.setText(Formatter.formatFileSize(FileMgrActivity.this,size));
                    }else  if(type.equals(FileTypeUtil.TYPE_ZIP)){
                        tvZip.setText(Formatter.formatFileSize(FileMgrActivity.this,size));
                    }else  if(type.equals(FileTypeUtil.TYPE_APK)){
                        tvApk.setText(Formatter.formatFileSize(FileMgrActivity.this,size));
                    }else  if(type.equals(FileTypeUtil.TYPE_ANY)){
                        allsize=  Formatter.formatFileSize(FileMgrActivity.this,size);
                        tvAll.setText(allsize);
                    }
                    break;
            }

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        //耗时操作
        new  Thread(){
            @Override
            public void run() {
                fileMgr.clear();
                fileMgr.searchAllFile(fileMgr.file);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_mgr);
        initToolbar();
        initview();
    }

    @Override
    public void initview() {
        tvTotal = (TextView) findViewById(R.id.tv_total_file);
        tvAll = (TextView) findViewById(R.id.tv_allfile_size);
        tvTxt = (TextView) findViewById(R.id.tv_txt_size);
        tvVideo = (TextView) findViewById(R.id.tv_video_size);
        tvAudio = (TextView) findViewById(R.id.tv_audio_size);
        tvImage = (TextView) findViewById(R.id.tv_image_size);
        tvApk = (TextView) findViewById(R.id.tv_apk_size);
        tvZip = (TextView) findViewById(R.id.tv_zip_size);

        ivAll = (ImageView) findViewById(R.id.iv_all);
        ivTxt = (ImageView) findViewById(R.id.iv_txt);
        ivVideo = (ImageView) findViewById(R.id.iv_video);
        ivAudio = (ImageView) findViewById(R.id.iv_audio);
        ivImage = (ImageView) findViewById(R.id.iv_image);
        ivApk = (ImageView) findViewById(R.id.iv_apk);
        ivZip = (ImageView) findViewById(R.id.iv_zip);

        pbAll = (ProgressBar) findViewById(R.id.pb_allfile);
        pbTxt = (ProgressBar) findViewById(R.id.pb_txt);
        pbVideo = (ProgressBar) findViewById(R.id.pb_video);
        pbAudio = (ProgressBar) findViewById(R.id.pb_audio);
        pbImage = (ProgressBar) findViewById(R.id.pb_image);
        pbApk = (ProgressBar) findViewById(R.id.pb_apk);
        pbZip = (ProgressBar) findViewById(R.id.pb_zip);

        fileMgr = new FileManager(this);
        fileMgr.setOnFileManagerListener(FileMgrActivity.this);
    /*    //耗时操作
        //耗时操作
        new  Thread(){
            @Override
            public void run() {
                super.run();
                fileMgr.searchAllFile(fileMgr.file);
            }
        }.start();
        //ArrayList<FileInfo> infos = fileManager.getFileInfo();
        //   LogUtil.d("all",infos.toString());*/
    }
    public void showFile(View view){
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.rl_allfile:
                bundle.putString("type",FileTypeUtil.TYPE_ANY);
                mystartactivity(FileMgrSecondaryActivity.class,bundle);
                break;
            case R.id.rl_alltxt:
                bundle.putString("type",FileTypeUtil.TYPE_TXT);
                mystartactivity(FileMgrSecondaryActivity.class,bundle);
                break;
            case R.id.rl_allvideo:
                bundle.putString("type",FileTypeUtil.TYPE_VIDEO);
                mystartactivity(FileMgrSecondaryActivity.class,bundle);
                break;
            case R.id.rl_allaudio:
                bundle.putString("type",FileTypeUtil.TYPE_AUDIO);
                mystartactivity(FileMgrSecondaryActivity.class,bundle);
                break;
            case R.id.rl_allimage:
                bundle.putString("type",FileTypeUtil.TYPE_IMAGE);
                mystartactivity(FileMgrSecondaryActivity.class,bundle);
                break;
            case R.id.rl_allzip:
                bundle.putString("type",FileTypeUtil.TYPE_ZIP);
                mystartactivity(FileMgrSecondaryActivity.class,bundle);
                break;
            case R.id.rl_allapk:
                bundle.putString("type",FileTypeUtil.TYPE_APK);
                mystartactivity(FileMgrSecondaryActivity.class,bundle);
                break;
        }

    }
    @Override
    public void onSuccess(final int size ,final String type) {
        Message msg = handler.obtainMessage();
        msg.what= ON_SUCCESS;
        Bundle bundle = new Bundle();
        bundle.putInt("size",size);
        bundle.putString("type",type);
        msg.setData(bundle);
        handler.sendMessage(msg);

    }

    @Override
    public void onEnd() {
        handler.sendEmptyMessage(ON_END);
    }
}
