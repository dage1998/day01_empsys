package com.qfedu.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qfedu.common.JsonBean;

import java.io.IOException;

/***
 *  佛曰:
 *          写字楼里写字间，写字间里程序员；
 *          程序人员写程序，又拿程序换酒钱。
 *          酒醒只在网上坐，酒醉还来网下眠；
 *          酒醉酒醒日复日，网上网下年复年。
 *          但愿老死电脑间，不愿鞠躬老板前；
 *          奔驰宝马贵者趣，公交自行程序员。
 *          别人笑我忒疯癫，我笑自己命太贱；
 *          不见满街漂亮妹，哪个归得程序员？
 */
public class JsonUtils {

   private static ObjectMapper mapper = new ObjectMapper();


    public static JsonBean createJsonBean(Integer code,Object info){

        JsonBean bean = new JsonBean();

        bean.setCode(code);
        bean.setInfo(info);
        return bean;

    }

    //讲java对象转为json格式字符串
    public static String obj2Json(Object obj){

        String s =null;
        try {
            s = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }


    // 讲json字符串转为指定类型对象
    public static <T>T json2Obj(String jsonStr ,Class <T>cls){

        T t = null;

        try {
            t = mapper.readValue(jsonStr,cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }
}
