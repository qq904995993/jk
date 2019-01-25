package jk.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jk.mapper.FinanceLogMapper;
import jk.model.po.FinanceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinanceLogService {

    @Autowired
    private FinanceLogMapper financeLogMapper;

    public PageInfo<FinanceLog> getFinanceLogsPageInfo(Long userId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo financeLogPageInfo = new PageInfo<FinanceLog>(financeLogMapper.selectAll());
        return financeLogPageInfo;
    }

}
