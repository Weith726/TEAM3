package com.adoptedpets.model;

import java.util.List;
import java.util.Map;

public interface AdoptedPetsDAO_interface {

	public void insert(AdoptedPetsVO adoptedpetsVO);

	public void update(AdoptedPetsVO adoptedpetsVO);

	public void delete(String petNo);

	public AdoptedPetsVO findByPrimaryKey(String petNo);

	public List<AdoptedPetsVO> getAll();

	public List<AdoptedPetsVO> getAll(Map<String, String[]> map);
}
