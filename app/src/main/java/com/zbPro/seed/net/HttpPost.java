package com.zbPro.seed.net;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2016/6/8.
 * ClassName ：com.zbPro.seed.net
 * 作用：
 */
public class HttpPost {
    //发送Json数据
    public static String SendhttpPostJson(String json, String url) {
        String s = null;
        //
        final MediaType JSON = MediaType.parse( "application/json; charset=utf-8" );
        /*1.获取数据 因为数据在getAllData()方法中，将界面控件的数据都保存到在Roguebean对象中
          2.将数据转换成Json
          3.构建OKHTTP对象，执行相关操作
          4.开启线程发送数据，
          5.获取返回的数据
        * */

        //构建一个OKhttp，请别人别人做某件事情
        OkHttpClient okHttpClient = new OkHttpClient();
        /*如果是Post发送键值对则，
        *
        RequestBody requestBody = new FormEncodingBuilder()
                .add()
                .build() */
        //创建一个RequestBoby（请求身体）对象，用于添加或者规定要上传的数据,表示我要发送的是一些什么东西等
        // 参数1 表示数据类型     参数二 表述数据
        //final MediaType JSON = MediaType.parse( "application/json; charset=utf-8" );
        RequestBody requestBody = RequestBody.create( JSON, json );
        //创建一个Request请求对象，用于发送请求，因为是发送请求，所有要有一个施工者及Builder，
        //同时施工者需要知道发送的Url.请求方法，以及开始施工
        Request request = new Request.Builder()
                .url( url )
                .post( requestBody )
                .build();
        //开始发送请求，并获取一个响应。所以需要OkHttp call一次电话(newCall)告诉对方request的具体数据等,并execute开始执行，并返回个响应
        //最后在相应的线程中 开始Thread.start即可
        try {
            Response response = okHttpClient.newCall( request ).execute();
            //如果成功了response.isSuccessful()
            if (response.isSuccessful()) {
                // String str = response.body().string();
                s = response.body().string();
                System.out.println( s );
            } else {
                throw new IOException( "Unexpected code " + response );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }


  /*  //上传各种数据
    public static void SendhttpPost(String json, String url, MediaType mediaType) {
        // final MediaType JSON = MediaType.parse( "application/json; charset=utf-8" );
        *//*1.获取数据 因为数据在getAllData()方法中，将界面控件的数据都保存到在Roguebean对象中
          2.将数据转换成Json
          3.构建OKHTTP对象，执行相关操作
          4.开启线程发送数据，
          5.获取返回的数据 * *//*
        //构建一个OKhttp，请别人别人做某件事情
        OkHttpClient okHttpClient = new OkHttpClient();
        *//*如果是Post发送键值对则，
        *
        RequestBody requestBody = new FormEncodingBuilder()
                .add()     .build() *//*
        //创建一个RequestBoby（请求身体）对象，用于添加或者规定要上传的数据,表示我要发送的是一些什么东西等
        // 参数1 表示数据类型     参数二 表述数据
        //final MediaType JSON = MediaType.parse( "application/json; charset=utf-8" );
        RequestBody requestBody = RequestBody.create( mediaType, json );
        //创建一个Request请求对象，用于发送请求，因为是发送请求，所有要有一个施工者及Builder，
        //同时施工者需要知道发送的Url.请求方法，以及开始施工
        Request request = new Request.Builder()
                .url( url )
                .post( requestBody )
                .build();
        //开始发送请求，并获取一个响应。所以需要OkHttp call一次电话(newCall)告诉对方request的具体数据等,并execute开始执行，并返回个响应
        //最后在相应的线程中 开始Thread.start即可
        try {
            Response response = okHttpClient.newCall( request ).execute();
            //如果成功了response.isSuccessful()
            if (response.isSuccessful()) {
                System.out.println( response.body().toString() );
            } else {
                throw new IOException( "Unexpected code " + response );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

}
