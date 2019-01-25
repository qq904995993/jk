package jk.service;

import jk.mapper.FinanceMapper;
import jk.model.po.Finance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinanceService {

    @Autowired
    private FinanceMapper financeMapper;

    public Finance getFinance(Long userId) {
        return financeMapper.selectOne(new Finance(userId,null));
    }

}
