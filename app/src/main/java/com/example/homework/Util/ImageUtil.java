package com.example.homework.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ImageUtil {
    public static String imageToBase64(Bitmap bitmap){
        //创建字节输出流对象
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        //以JPEG的形式转换
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        //转换为字节
        byte[] buffer= byteArrayOutputStream.toByteArray();
        String baseStr= Base64.encodeToString(buffer,Base64.DEFAULT);
        return baseStr;
    }
    public static Bitmap base64ToImage(String bitmap64){
        byte[] bytes=Base64.decode(bitmap64,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        return  bitmap;
    }
}
