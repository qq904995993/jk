package jk.feign;

import com.alibaba.fastjson.JSONObject;
import jk.feign.hystrix.UserHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user", fallback = UserHystrix.class)
public interface UserFeign {

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @GetMapping(value = "/{userId}")
    JSONObject getUser(@PathVariable(value = "userId")Long userId);

}
