package jk.service;

import jk.dao.UserDao;
import jk.model.po.Role;
import jk.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsService implements
        org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名错误！");
        }
        if(user.getRoles() != null && user.getRoles().size() > 0) {
            user.getRoles().get(0).getName();
        }

        jk.config.security.UserDetails userDetails = new jk.config.security.UserDetails();
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDetails.setRoles(user.getRoles());
        return userDetails;
    }

}
