package jk.feign.hystrix;

import com.alibaba.fastjson.JSONObject;
import jk.feign.UserFeign;
import jk.utils.JSONUtils;
import org.springframework.stereotype.Component;

@Component
public class UserHystrix implements UserFeign {

    @Override
    public JSONObject getUser(Long userId) {
        return JSONUtils.buildResultJson(false, "timeout");
    }
}
