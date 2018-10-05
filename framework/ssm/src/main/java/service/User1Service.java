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
	 * @PostContructע��ķ�������Bean��ʼ�������е���
	 */
	@PostConstruct
	public int updateUser() {
		return userDao.updatePassword("1", "1");
	}
	
	/**
	 * ʵ��InitializingBean����дafterPropertiesSet�ķ�������Bean��ʼ�������е��ã��൱������init-method
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		userDao.updatePassword("1", "2");
    }
	
	/**
	 * ʵ��ApplicationListener����дonApplicationEvent�ķ�������Bean��ʼ�������е���
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		userDao.updatePassword("1", "3");
	}
	
}
