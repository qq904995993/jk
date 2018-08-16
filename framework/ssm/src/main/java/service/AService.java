package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dao.ADao;
import model.A;

@Service
public class AService{
		
	@Autowired
	private ADao aDao;
	
	@Cacheable(value="myCache", key="'getABy'+#id")  
	public A getAById(String id) {
		return aDao.getAById(id);
	}
}
