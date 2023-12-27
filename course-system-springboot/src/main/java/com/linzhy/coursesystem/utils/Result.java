package com.linzhy.coursesystem.utils;

import java.util.HashMap;
import java.util.Map;

public class Result {

    public static Map<String, Object> message(Object str){
        Map<String, Object> ret = new HashMap<>();
        ret.put("message", str);
        return ret;
    }
    public static Map<String, Object> count(Object count){
        Map<String, Object> ret = new HashMap<>();
        ret.put("count", count);
        return ret;
    }
//    public static Map<String, Object> code
}
