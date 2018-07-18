package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ADao;
import model.A;

@Service
public class AService {
	
	@Autowired
	private ADao aDao;

	public A getAById(String id) {
		return aDao.getAById(id);
	}
		
}
