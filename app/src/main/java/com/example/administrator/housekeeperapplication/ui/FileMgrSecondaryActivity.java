package com.example.administrator.housekeeperapplication.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.housekeeperapplication.R;
import com.example.administrator.housekeeperapplication.adapter.FileAdapter;
import com.example.administrator.housekeeperapplication.biz.FileManager;
import com.example.administrator.housekeeperapplication.entity.FileInfo;
import com.example.administrator.housekeeperapplication.util.FileTypeUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileMgrSecondaryActivity extends BaseActivity {
    private String type;
    private ListView listView;
    private Button btnDel;
    private TextView fileCount,fileSize;
    private FileManager fileManager;
    private long size;
    private String s;
    private int count;
    private ArrayList<FileInfo> datas;
    private FileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_mgr_secondary);
        initToolbar();
        getIntentData();
        initview();
        setListener();
    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mime="*/*";
                //先获取文件后缀名
                List<FileInfo> fileInfos = adapter.getData();
                FileInfo info = fileInfos.get(position);
                //获取文件路径
                String url = info.getFileUrl();
                String name = info.getName();
                String end = name.substring(name.lastIndexOf(".") + 1);
                //根据后缀名判断minetype
                String[][] mimeType = FileTypeUtil.MIME_Table;
                w:
                for (int x = 0; x < mimeType.length; x++)
                    for (int y = 0; y < mimeType[x].length; y++) {
                        if (end.equals(mimeType[x][0])) {
                            mime = mimeType[x][1];
                            break w;
                        }
                    }
                //使用意图打开应用程序
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://"+url),mime);
                startActivity(intent);
            }

        });
    }

    private void getIntentData() {
       type= getIntent().getBundleExtra("bundle").getString("type");
    }

    @Override
    public void initview() {
        fileManager = FileManager.getInstance(this);
        listView =(ListView)findViewById(R.id.filemgr_secondary_lv);
        btnDel  = (Button) findViewById(R.id.filemgr_secondary_bt);
        fileCount  = (TextView) findViewById(R.id.filemgr_secondary_tv1);
        fileSize  = (TextView) findViewById(R.id.filemgr_secondary_tv2);

        adapter = new FileAdapter(datas,this);
        listView.setAdapter(adapter);

        if(type.equals(FileTypeUtil.TYPE_ANY)){
           size = fileManager.getAllSize();
           count= fileManager.getAll().size();
           s = Formatter.formatFileSize(this,size);
           fileSize.setText(s);
           fileCount.setText(count+"");
           datas = fileManager.getAll();
        }
        else if(type.equals(FileTypeUtil.TYPE_APK)){
            size = fileManager.getExeSize();
            count= fileManager.getExe().size();
            s = Formatter.formatFileSize(this,size);
            fileSize.setText(s);
            fileCount.setText(count+"");
            datas = fileManager.getExe();
        } else if(type.equals(FileTypeUtil.TYPE_ZIP)){
            size = fileManager.getZipSize();
            count= fileManager.getZip().size();
            s = Formatter.formatFileSize(this,size);
            fileSize.setText(s);
            fileCount.setText(count+"");
            datas = fileManager.getZip();
        } else if(type.equals(FileTypeUtil.TYPE_AUDIO)){
            size = fileManager.getAudioSize();
            count= fileManager.getAudio().size();
            s = Formatter.formatFileSize(this,size);
            fileSize.setText(s);
            fileCount.setText(count+"");
            datas = fileManager.getAudio();
        } else if(type.equals(FileTypeUtil.TYPE_VIDEO)){
            size = fileManager.getVideoSize();
            count= fileManager.getVideo().size();
            s = Formatter.formatFileSize(this,size);
            fileSize.setText(s);
            fileCount.setText(count+"");
            datas = fileManager.getVideo();
        } else if(type.equals(FileTypeUtil.TYPE_IMAGE)){
            size = fileManager.getPicSize();
            count= fileManager.getPic().size();
            s = Formatter.formatFileSize(this,size);
            fileSize.setText(s);
            fileCount.setText(count+"");
            datas = fileManager.getPic();
        } else if(type.equals(FileTypeUtil.TYPE_TXT)){
            size = fileManager.getDocSize();
            count= fileManager.getDoc().size();
            s = Formatter.formatFileSize(this,size);
            fileSize.setText(s);
            fileCount.setText(count+"");
            datas = fileManager.getDoc();
        }
//将数据传递给适配器
        adapter.setData(datas);
        adapter.notifyDataSetChanged();
    }


    //删除所选文件
    public void deleteFile(View view){
        ArrayList<FileInfo> datas = (ArrayList<FileInfo>) adapter.getData();
        if(datas ==null|| datas.size()<=0){
            return;
        }

        ArrayList<FileInfo> delInfo = new ArrayList<>();
        for (FileInfo info : this.datas) {
            //判断文件是否被选中
            if(info.isChecked()){
                delInfo.add(info);            }
        }

        for (int x=0;x<delInfo.size();x++) {
//将info包装成文件类型
            FileInfo info = delInfo.get(x);
            File file =new File(info.getFileUrl());
            int s = (int) file.length();
            if(file.delete()){
                //若文件被删除了、
                //1.将文件从集合中删除
                datas.remove(info);
                //3.更新上方文件数字
                fileManager.getAll().remove(info);
                //更新文件大小
                type=info.getType();
                fileManager.setAllSize(fileManager.getAllSize()-s);
                if(type.equals(FileTypeUtil.TYPE_TXT)){
                    fileManager.getDoc().remove(info);
                    fileManager.setDocSize( fileManager.getDocSize()-s);
                }else  if(type.equals(FileTypeUtil.TYPE_IMAGE)){
                    fileManager.getPic().remove(info);
                    fileManager.setPicSize( fileManager.getPicSize()-s);
                }else  if(type.equals(FileTypeUtil.TYPE_AUDIO)){
                    fileManager.getAudio().remove(info);
                    fileManager.setAudioSize(fileManager.getAudioSize()-s);
                }else  if(type.equals(FileTypeUtil.TYPE_VIDEO)){
                    fileManager.getVideo().remove(info);
                    fileManager.setVideoSize(fileManager.getVideoSize()-s);
                }else  if(type.equals(FileTypeUtil.TYPE_ZIP)){
                    fileManager.getZip().remove(info);
                    fileManager.setZipSize(fileManager.getZipSize()-s);
                }else  if(type.equals(FileTypeUtil.TYPE_APK)){
                    fileManager.getExe().remove(info);
                    fileManager.setExeSize(fileManager.getExeSize()-s);
                }
            }
        }
        //4.更新上方文件大小
        fileCount.setText(datas.size()+"");
        if(type.equals(FileTypeUtil.TYPE_TXT)){
         fileSize.setText(Formatter.formatFileSize(this, fileManager.getDocSize()));
        }else  if(type.equals(FileTypeUtil.TYPE_IMAGE)){
            fileSize.setText(Formatter.formatFileSize(this,fileManager.getPicSize()));
        }else  if(type.equals(FileTypeUtil.TYPE_AUDIO)){
            fileSize.setText(Formatter.formatFileSize(this,fileManager.getAudioSize()));
        }else  if(type.equals(FileTypeUtil.TYPE_VIDEO)){
            fileSize.setText(Formatter.formatFileSize(this,fileManager.getVideoSize()));
        }else  if(type.equals(FileTypeUtil.TYPE_ZIP)){
            fileSize.setText(Formatter.formatFileSize(this,fileManager.getZipSize()));
        }else  if(type.equals(FileTypeUtil.TYPE_APK)){
            fileSize.setText(Formatter.formatFileSize(this,fileManager.getExeSize()));
        }else  if(type.equals(FileTypeUtil.TYPE_ANY)){
            fileSize.setText(Formatter.formatFileSize(this,fileManager.getAllSize()));
        }
        //2.通知适配器更新界面
        adapter.notifyDataSetChanged();
    }
}
