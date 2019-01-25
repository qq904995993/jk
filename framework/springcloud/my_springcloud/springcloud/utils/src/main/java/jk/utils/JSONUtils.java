package jk.utils;

import com.alibaba.fastjson.JSONObject;

public class JSONUtils {

    /**
     *  返回通用的结果json
     * @param result    结果
     * @param message   附带信息，可为空
     * @return
     */
    public static JSONObject buildResultJson(boolean result, String message) {
        JSONObject json = new JSONObject();
        json.put("result", result);
        if (message != null) {
            json.put("message", message);
        }
        return json;
    }

}
