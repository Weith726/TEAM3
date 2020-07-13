package com.adopter.model;

import java.util.List;
import java.util.Set;
import com.adoptedpets.model.AdoptedPetsVO;

public interface AdopterDAO_interface {

	public void insert(AdopterVO adopterVO);
	public void update(AdopterVO adopterVO);
	public void delete(String adopterNo);
	public AdopterVO findByPrimaryKey(String adopterNo);
	public AdopterVO findByAdopterName(String adopterName);
	public List<AdopterVO> getAll();
	public Set<AdoptedPetsVO> getAdoptedPetsByAdopterNo(String adopterNo);
}
