package com.productkind.model;

import java.util.List;

public class KindService {
	
	private KindDAO_IN dao;
	
	public KindService() {
		dao = new KindDAO();
	}
	
	public List<KindVO> getAll() {
		return dao.getAll();
	}
}
