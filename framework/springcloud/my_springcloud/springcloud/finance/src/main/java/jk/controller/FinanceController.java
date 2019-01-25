package jk.controller;

import com.alibaba.fastjson.JSONObject;
import jk.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    /**
     * 获取用户财务数据
     * @return
     */
    @GetMapping(value = "/{userId:\\d}", produces = "application/json")
    public JSONObject getFinance(@PathVariable Long userId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("finance", financeService.getFinance(userId));
        return jsonObject;
    }
}
