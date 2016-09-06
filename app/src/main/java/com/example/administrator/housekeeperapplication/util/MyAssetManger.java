package com.example.administrator.housekeeperapplication.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jiangjiawen on 2016/8/4.
 */
public class MyAssetManger {

    public static File copyAssetsFileToSDFile(Context context){
        AssetManager assets = context.getAssets();
        File file = null;
        try {
            InputStream is = assets.open("db/commonnum.db");
            File filedir = context.getFilesDir();
            file = new File(filedir,"commonnum.db");
            readAssetDBDataToFile(is,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static void readAssetDBDataToFile(InputStream is, File file) {
        BufferedInputStream bis =null;
        BufferedOutputStream bos = null;
        try {
            bis= new BufferedInputStream(is);
            bos= new BufferedOutputStream(new FileOutputStream(file));
            int temp = 0;
            while((temp=bis.read())!= -1){
                bos.write(temp);
            }
        } catch (IOException e) {
            throw new RuntimeException("文件失败");
        }
        finally {
            if( bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    throw new RuntimeException("读入流关闭失败");
                }
                if(bos!=null){
                    try {
                        bos.close();
                    } catch (IOException e) {
                        throw new RuntimeException("写入流关闭失败");
                    }
                }
            }

        }
    }
}
