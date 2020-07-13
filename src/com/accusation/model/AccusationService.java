package com.accusation.model;

import java.util.List;


public class AccusationService {
	
	private Accusation_interface dao;
	
	public AccusationService() {
		dao = new AccusationDAO();
	}
	
	public AccusationVO addA(String accusationType,String accusationContent,Integer accusationStatue) {
		AccusationVO  aVO = new AccusationVO();
		
		aVO.setAccusationType(accusationType);
		aVO.setAccusationContent(accusationContent);
		aVO.setAccusationStatue(accusationStatue);
		dao.insert(aVO);		
		return aVO;
	}
	
	public AccusationVO updateA(String accusationType,String accusationContent,Integer accusationStatue,String accusationNo) {
		AccusationVO  aVO = new AccusationVO();
		
		
		aVO.setAccusationType(accusationType);
		aVO.setAccusationContent(accusationContent);
		aVO.setAccusationStatue(accusationStatue);
		aVO.setAccusationNo(accusationNo);
		dao.update(aVO);		
		return aVO;
	}
	
	public void deleteA(String accusationNo) {
		dao.delete(accusationNo);
	}
	
	public AccusationVO getOneA(String accusationNo) {
		return dao.findByPrimaryKey(accusationNo);
	}
	
	public List<AccusationVO > getAll() {
		return dao.getAll();
	}
	
	
	public static void main(String[] args) {
		AccusationService aa = new AccusationService();
		List<AccusationVO> list = aa.getAll();
		for(AccusationVO ac:list) {
			System.out.println(ac.getAccusationNo() + ',' + ac.getAccusationType() + ',' + ac.getAccusationContent() + ',' + ac.getAccusationtime() + ',' + ac.getAccusationStatue());
		}
//		AccusationVO av = aa.getOneA("M0001");	
//		System.out.println(av.getAccusationType());
		
//		aa.addA("aa", "aa", 2);
//		aa.deleteA("M0001");
	}
	
}
