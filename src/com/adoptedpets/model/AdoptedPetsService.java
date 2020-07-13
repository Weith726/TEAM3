package com.adoptedpets.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class AdoptedPetsService {

	private AdoptedPetsDAO_interface dao;

	public AdoptedPetsService() {
		dao = new AdoptedPetsDAO();
	}
	
	public AdoptedPetsVO addAdoptedPets(String adopterNo, String shelterNo, String petBreed, Date adoptedDate, byte[] petPic, Date adoptDate,
			Date interviewDate, String interviewInfo, String petSpecies, String petGender, Integer adoptStatus) {
		
		AdoptedPetsVO adoptedPetsVO = new AdoptedPetsVO();
		
		
		adoptedPetsVO.setAdopterNo(adopterNo);
		adoptedPetsVO.setShelterNo(shelterNo);
		adoptedPetsVO.setPetBreed(petBreed);
		adoptedPetsVO.setAdoptedDate(adoptedDate);
		adoptedPetsVO.setPetPic(petPic);
		adoptedPetsVO.setAdoptDate(adoptDate);
		adoptedPetsVO.setInterviewDate(interviewDate);
		adoptedPetsVO.setInterviewInfo(interviewInfo);
		adoptedPetsVO.setPetSpecies(petSpecies);
		adoptedPetsVO.setPetGender(petGender);
		adoptedPetsVO.setAdoptStatus(adoptStatus);
		dao.insert(adoptedPetsVO);
		
		return adoptedPetsVO;
	}
	
	
	public AdoptedPetsVO updateAdoptedPets(String petNo, String adopterNo, String shelterNo, String petBreed, Date adoptedDate, byte[] petPic, Date adoptDate,
			Date interviewDate, String interviewInfo, String petSpecies, String petGender, Integer adoptStatus) {
		
		AdoptedPetsVO adoptedPetsVO = new AdoptedPetsVO();
		
		adoptedPetsVO.setPetNo(petNo);
		adoptedPetsVO.setAdopterNo(adopterNo);
		adoptedPetsVO.setShelterNo(shelterNo);
		adoptedPetsVO.setPetBreed(petBreed);
		adoptedPetsVO.setAdoptedDate(adoptedDate);
		adoptedPetsVO.setPetPic(petPic);
		adoptedPetsVO.setAdoptDate(adoptDate);
		adoptedPetsVO.setInterviewDate(interviewDate);
		adoptedPetsVO.setInterviewInfo(interviewInfo);
		adoptedPetsVO.setPetSpecies(petSpecies);
		adoptedPetsVO.setPetGender(petGender);
		adoptedPetsVO.setAdoptStatus(adoptStatus);
		dao.update(adoptedPetsVO);
		
		return adoptedPetsVO;
	}
	
	public void deleteAdoptedPets(String petNo) {
		dao.delete(petNo);
	}
	
	public AdoptedPetsVO getOneAdoptedPets(String petNo) {
		return dao.findByPrimaryKey(petNo);
	}
	
	public List<AdoptedPetsVO> getAll() {
		return dao.getAll();
	}
	public List<AdoptedPetsVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
