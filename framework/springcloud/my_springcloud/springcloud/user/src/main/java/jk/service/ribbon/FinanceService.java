package jk.service.ribbon;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import jk.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FinanceService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取财务信息
     * @param userId
     * @return
     */
    @HystrixCommand(fallbackMethod = "getFinanceFallBack")
    public JSONObject getFinance(Long userId) {
        return restTemplate.getForObject("http://finance/" + userId, JSONObject.class);
    }
    //fallback 的参数应该与方法参数一直，返回类型应该与getForObject后的类型一致
    public JSONObject getFinanceFallBack(Long userId) {
        return JSONUtils.buildResultJson(false, "timeout");
    }
}
