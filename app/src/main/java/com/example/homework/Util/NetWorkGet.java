package com.example.homework.Util;

import com.example.homework.login.loginActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class NetWorkGet {
    //不带Cookie的请求
    public static String doGet(String url) {
        HttpURLConnection urlConnection;
        InputStream inputStream = null;
        BufferedReader reader;
        String json = null;
        try {    //1.建立连接
            URL requestUrl = new URL(url);
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Charset", "utf-8");
            //  urlConnection.addRequestProperty("Cookie", String.valueOf(loginActivity.cookies));
            urlConnection.setConnectTimeout(3000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                //获取二进制流
                inputStream = urlConnection.getInputStream();
                //3.将二进制流包装
                reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                //从BufferedReader中读取String字符串
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append("\n");
                }
                if (builder.length() == 0) {
                    return null;
                }
                json = builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return json;
    }
    //带Cookie的Get请求
    public static String doCookieGet(String url) {
        HttpURLConnection urlConnection;
        InputStream inputStream = null;
        BufferedReader reader;
        String json = null;
        try {    //1.建立连接
            URL requestUrl = new URL(url);
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Charset", "utf-8");
            urlConnection.addRequestProperty("Cookie", String.valueOf(loginActivity.cookies));
            urlConnection.setConnectTimeout(3000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                //获取二进制流
                inputStream = urlConnection.getInputStream();
                //3.将二进制流包装
                reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                //从BufferedReader中读取String字符串
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append("\n");
                }
                if (builder.length() == 0) {
                    return null;
                }
                json = builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }
}

