package service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import dao.UserDao;

@Component
public class User1Service implements InitializingBean, ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private UserDao userDao;
	
	/**
	 * @PostContruct注解的方法，在Bean初始化过程中调用
	 */
	@PostConstruct
	public int updateUser() {
		return userDao.updatePassword("1", "1");
	}
	
	/**
	 * 实现InitializingBean并重写afterPropertiesSet的方法，在Bean初始化过程中调用，相当与配置init-method
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		userDao.updatePassword("1", "2");
    }
	
	/**
	 * 实现ApplicationListener并重写onApplicationEvent的方法，在Bean初始化过程中调用
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		userDao.updatePassword("1", "3");
	}
	
}
