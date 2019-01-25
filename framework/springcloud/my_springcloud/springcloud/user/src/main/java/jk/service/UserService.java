package jk.service;

import jk.dao.UserDao;
import jk.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public User getUser(Long userId) {
        try {
            return userDao.findById(userId).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

}

