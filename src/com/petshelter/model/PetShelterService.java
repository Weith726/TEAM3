package com.petshelter.model;

import java.util.List;
import java.util.Set;

import com.adoptedpets.model.AdoptedPetsVO;
import com.adopter.model.AdopterVO;

public class PetShelterService {

	private PetShelterDAO_interface dao;

	public PetShelterService() {
		dao = new PetShelterDAO();
	}

	public PetShelterVO addPetShetler(java.sql.Date cleanDate, Integer shelterStatus) {

		PetShelterVO petShelterVO = new PetShelterVO();

		petShelterVO.setCleanDate(cleanDate);
		petShelterVO.setShelterStatus(shelterStatus);

		dao.insert(petShelterVO);

		return petShelterVO;
	}

	public PetShelterVO updatePetShetler(String shelterNo, java.sql.Date cleanDate, Integer shelterStatus) {

		PetShelterVO petShelterVO = new PetShelterVO();

		petShelterVO.setShelterNo(shelterNo);
		petShelterVO.setCleanDate(cleanDate);
		petShelterVO.setShelterStatus(shelterStatus);

		dao.update(petShelterVO);

		return petShelterVO;
	}

	public List<PetShelterVO> getAll() {
		return dao.getAll();
	}

	public PetShelterVO getOnePetShelter(String shelterNo) {
		return dao.findByPrimaryKey(shelterNo);
	}

	public Set<AdoptedPetsVO> getAdoptedPetsByShelterNo(String shelterNo) {
		return dao.getAdoptedPetsByShelterNo(shelterNo);
	}

	public void deletePetShelter(String shelterNo) {
		dao.delete(shelterNo);
	}

}
