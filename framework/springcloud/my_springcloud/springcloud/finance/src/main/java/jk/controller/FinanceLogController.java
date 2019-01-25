package jk.controller;

import com.github.pagehelper.PageInfo;
import jk.model.po.FinanceLog;
import jk.service.FinanceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FinanceLogController {

    @Autowired
    private FinanceLogService financeLogService;

    @GetMapping(value = "/logs/{userId:\\d}", produces = "application/json")
    public PageInfo<FinanceLog> getFinanceLogs(@PathVariable Long userId,
                                               @RequestParam(defaultValue = "1", required = false) int pageNo) {
        final int pageSize = 10;
        return financeLogService.getFinanceLogsPageInfo(userId, pageNo, pageSize);
    }

}
