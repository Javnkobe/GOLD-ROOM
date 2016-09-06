package com.example.administrator.housekeeperapplication.biz;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.housekeeperapplication.entity.FileInfo;
import com.example.administrator.housekeeperapplication.ui.FileMgrActivity;
import com.example.administrator.housekeeperapplication.util.FileTypeUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 查询手机中的文件
 */
public class FileManager {
    public static File file;
    private static FileManager fileManager;
    private  Context context;
    private  OnFileManagerListener on;
    private int allSize,docSize,audioSize,videoSize,zipSize,exeSize,picSize;
    private  ArrayList<FileInfo> all =new ArrayList<>();
    private  ArrayList<FileInfo> doc =new ArrayList<>();
    private  ArrayList<FileInfo> audio =new ArrayList<>();

    public void setAllSize(int allSize) {
        this.allSize = allSize;
    }

    public void setDocSize(int docSize) {
        this.docSize = docSize;
    }

    public void setAudioSize(int audioSize) {
        this.audioSize = audioSize;
    }

    public void setVideoSize(int videoSize) {
        this.videoSize = videoSize;
    }

    public void setZipSize(int zipSize) {
        this.zipSize = zipSize;
    }

    public void setExeSize(int exeSize) {
        this.exeSize = exeSize;
    }

    public void setPicSize(int picSize) {
        this.picSize = picSize;
    }

    private  ArrayList<FileInfo> video =new ArrayList<>();

    private  ArrayList<FileInfo> zip =new ArrayList<>();
    private  ArrayList<FileInfo> exe =new ArrayList<>();
    private  ArrayList<FileInfo> pic =new ArrayList<>();

    public ArrayList<FileInfo> getAll() {
        return all;
    }

    public ArrayList<FileInfo> getDoc() {
        return doc;
    }

    public ArrayList<FileInfo> getAudio() {
        return audio;
    }

    public ArrayList<FileInfo> getVideo() {
        return video;
    }

    public ArrayList<FileInfo> getZip() {
        return zip;
    }

    public ArrayList<FileInfo> getExe() {
        return exe;
    }

    public ArrayList<FileInfo> getPic() {
        return pic;
    }

    public FileManager(Context context) {
        this.context=context;
    }

    public int getAllSize() {
        return allSize;
    }

    public int getDocSize() {
        return docSize;
    }

    public int getAudioSize() {
        return audioSize;
    }

    public int getVideoSize() {
        return videoSize;
    }

    public int getZipSize() {
        return zipSize;
    }

    public int getExeSize() {
        return exeSize;
    }

    public int getPicSize() {
        return picSize;
    }

    public static FileManager getInstance(Context context) {
        if(fileManager==null){
            fileManager = new FileManager(context);

        }
        return fileManager;
    }

    public void clear() {
        all.clear();                doc.clear();
        audio.clear();                video.clear();
        zip.clear();                exe.clear();
        pic.clear();
        allSize=0;docSize=0;audioSize=0;videoSize=0;zipSize=0;exeSize=0;picSize=0;


    }

    public interface OnFileManagerListener{
        //获取文件的大小
        void onSuccess(int size,String type);
        //搜索文件结束
        void onEnd();
    }
    public  void setOnFileManagerListener( OnFileManagerListener on){
        this.on=on;
    }
    static {
        //判断SD是否存在
        if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
            file = Environment.getExternalStorageDirectory();//SD卡的路径
        }
        else{
            Log.d("SD","123");
        }

    }
    //获取SD卡中的文件
    public void searchAllFile(File f){
        //现获取SD卡中的文件和文件夹
        File[] allFiles = f.listFiles();
        if(allFiles!=null){
            for(int x=0;x<allFiles.length;x++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                File file= allFiles[x];
                if(file.isDirectory()){//文件夹
                    searchAllFile(file);
                }
                else{
                    if(file.length()>0){
                        //文件 //先获取文件后缀
                        String fileName = file.getName();
                        String endName = fileName.substring(fileName.lastIndexOf(".") + 1);
                        String [][] arr=  FileTypeUtil.ICON_TYPE_Table;


                        FileInfo info = new FileInfo();
                        info.setFileUrl(file.getAbsolutePath());
                        //默认给文件默认的图片和类型，则归入全部行列
                        info.setFileIcon("icon_text_plain");
                        info.setType(FileTypeUtil.TYPE_TXT);
                        //判断文件类型
                        w: for (int y = 0; y < arr.length; y++) {
                            for (int z = 0; z < arr[y].length; z++){
                                if (endName.equals(arr[y][z])){
                                    info.setFileIcon(arr[y][1]);
                                    info.setType(arr[y][2]);
                                    break w;
                                }
                            }
                        }
                        info.setName(fileName);
                        long fileLength = file.length();
                        info.setSize(android.text.format.Formatter.formatFileSize(context,fileLength));
                        long l = file.lastModified();
                        String fileDate = dateFormat(l);
                        info.setLastModify(fileDate);
                        allSize+=fileLength;
                        if(info.getType().equals(FileTypeUtil.TYPE_TXT)){
                            docSize += fileLength;
                            on.onSuccess(docSize,info.getType());
                            doc.add(info);
                        }else  if(info.getType().equals(FileTypeUtil.TYPE_IMAGE)){
                            picSize += fileLength;
                            on.onSuccess(picSize,info.getType());
                            pic.add(info);
                        }else  if(info.getType().equals(FileTypeUtil.TYPE_AUDIO)){
                            audioSize += fileLength;
                            on.onSuccess(audioSize,info.getType());
                            audio.add(info);
                        }else  if(info.getType().equals(FileTypeUtil.TYPE_VIDEO)){
                            videoSize += fileLength;
                            on.onSuccess(videoSize,info.getType());
                            video.add(info);
                        }else  if(info.getType().equals(FileTypeUtil.TYPE_ZIP)){
                            zipSize += fileLength;
                            on.onSuccess(zipSize,info.getType());
                            zip.add(info);
                        }else  if(info.getType().equals(FileTypeUtil.TYPE_APK)){
                            exeSize += fileLength;
                            on.onSuccess(exeSize,info.getType());
                            exe.add(info);
                        }
                        all.add(info);
                    }
                }
            }
            //将累加的结果传递给ACTIVITY
            on.onSuccess(allSize,FileTypeUtil.TYPE_ANY);
            on.onEnd();
        }

    }
    //转换日期
    private String dateFormat(long l) {
        Date date =new Date(l);
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        return sdf.format(date);
    }
    //返回集合
    public ArrayList<FileInfo> getFileInfo(){
        return  all;
    }
}