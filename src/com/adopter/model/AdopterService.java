package com.adopter.model;

import java.util.List;
import java.util.Set;

import com.adoptedpets.model.AdoptedPetsVO;

public class AdopterService {

	private AdopterDAO_interface dao;
	
	public AdopterService() {
		dao = new AdopterDAO();
	}
	
	public AdopterVO addAdopter(String adopterName, String adopterGender, String adopterOccupation, String adopterMail) {
		
		AdopterVO adopterVO = new AdopterVO();
		
		adopterVO.setAdopterName(adopterName);
		adopterVO.setAdopterGender(adopterGender);
		adopterVO.setAdopterOccupation(adopterOccupation);
		adopterVO.setAdopterMail(adopterMail);
		dao.insert(adopterVO);
		
		return adopterVO;
	}
	
	public AdopterVO updateAdopter(String adopterNo, String adopterName, String adopterGender, String adopterOccupation, String adopterMail) {
		
		AdopterVO adopterVO = new AdopterVO();
		
		adopterVO.setAdopterNo(adopterNo);
		adopterVO.setAdopterName(adopterName);
		adopterVO.setAdopterGender(adopterGender);
		adopterVO.setAdopterOccupation(adopterOccupation);
		adopterVO.setAdopterMail(adopterMail);
		dao.update(adopterVO);
		
		return adopterVO;
	}
	
	public List<AdopterVO> getAll() {
		return dao.getAll();
	}

	public AdopterVO getOneAdopter(String adopterNo) {
		return dao.findByPrimaryKey(adopterNo);
	}
	
	public AdopterVO getOneMail(String adopterName) {
		return dao.findByAdopterName(adopterName);
	}

	public Set<AdoptedPetsVO> getAdoptedPetsByAdopterNo(String adopterNo) {
		return dao.getAdoptedPetsByAdopterNo(adopterNo);
	}

	public void deleteAdopter(String adopterNo) {
		dao.delete(adopterNo);
	}
}
