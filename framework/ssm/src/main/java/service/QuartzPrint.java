package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dao.UserDao;

@Component
public class QuartzPrint{
	
	private static int aa = 0;
	
	@Autowired
	private UserDao userDao;
	 
	@Scheduled(cron = "0/1 * *  * * ? ")
	public void run() {
		userDao.updatePassword("1", "quartz" + (aa ++));
	}

}
