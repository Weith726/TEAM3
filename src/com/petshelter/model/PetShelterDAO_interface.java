package com.petshelter.model;

import java.util.List;
import java.util.Set;

import com.adoptedpets.model.AdoptedPetsVO;

public interface PetShelterDAO_interface {

	public void insert(PetShelterVO petshelterVO);
	public void update(PetShelterVO petshelterVO);
	public void delete(String shelterNo);
	public PetShelterVO findByPrimaryKey(String shelterNo);
	public List<PetShelterVO> getAll();
	public Set<AdoptedPetsVO> getAdoptedPetsByShelterNo(String shelterNo);
}
