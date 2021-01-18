package com.itcmdas.test;

import com.alibaba.fastjson.JSON;


import java.io.IOException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class tongueTest {
    public static String MD5(String data) throws Exception {
        System.out.println(data);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        System.out.println(sb.toString().toUpperCase());
        return sb.toString().toUpperCase();
    }


    public static void main(String[] args) {

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp=df.format(new Date());
        Map body = new HashMap<>();
        body.put("app_id", "0f091fdf1c704cd7");
        System.out.println(timestamp);
        body.put("timestamp", timestamp);
        body.put("version", "1.0");
        body.put("method", "9ti");
        body.put("imgpath", "https://bys-tonguepicture.oss-cn-beijing.aliyuncs.com/1563412989396.jpg");
        try {
            body.put("sign", MD5(MD5(timestamp)+"你的APPID对应的key"));

        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            String s = new HttpClient().doPostMap("http://www.aibayes.cn/api/analysis", body);
//            System.out.println(s);
//            Map maps = (Map) JSON.parse(s);
//            for (Object map : maps.entrySet()){
//                System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
//            }

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
