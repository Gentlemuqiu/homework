package com.example.homework.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class NetWorkPost {
    public static String sendPostNetRequest(String mUrl, HashMap<String, String> params) {
        BufferedReader reader1;
        StringBuilder builder = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            URL url = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");//设置请求方式为POST
            connection.setConnectTimeout(5000);//设置最大连接时间，单位为毫
            connection.setReadTimeout(5000);//设置最大的读取时间，单位为毫秒，
            connection.setDoOutput(true);//允许输入流
            connection.setDoInput(true);//允许输出流
            StringBuilder dataToWrite = new StringBuilder();//构建参数值
            for (String key : params.keySet()) {
                dataToWrite.append(key).append("=").append(params.get(key)).append("&");
            }
            //开
            outputStream = connection.getOutputStream();
            outputStream.write(dataToWrite.substring(0, dataToWrite.length() - 1).getBytes());//去除最后一个 &
            //从接口处获取输入
            inputStream = connection.getInputStream();
            reader1 = new BufferedReader(new InputStreamReader(inputStream));
            //从BufferedReader中读取String字符串
            String line;
            builder = new StringBuilder();
            while ((line = reader1.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }

    public static String sendPost(String mUrl) {
        BufferedReader reader1;
        StringBuilder builder = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            URL url = new URL(mUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");//设置请求方式为POST
            connection.setConnectTimeout(5000);//设置最大连接时间，单位为毫
            connection.setReadTimeout(5000);//设置最大的读取时间，单位为毫秒，
            connection.setDoOutput(true);//允许输入流
            connection.setDoInput(true);//允许输出流
            outputStream = connection.getOutputStream();
            //从接口处获取输入
            inputStream = connection.getInputStream();
            reader1 = new BufferedReader(new InputStreamReader(inputStream));
            //从BufferedReader中读取String字符串
            String line;
            builder = new StringBuilder();
            while ((line = reader1.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }
}
