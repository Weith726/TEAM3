package com.interaction.model;

import java.util.List;
import java.util.Set;

import com.adoptedpets.model.AdoptedPetsVO;

public interface InteractionDAO_interface {

	public void insert(InteractionVO interactionVO);
	public void update(InteractionVO interactionVO);
	public void delete(String interactionNo);
	public InteractionVO findByPrimaryKey(String interactionNo);
	public Set<InteractionVO> findByAdopterNo(String adopterNo);
	public List<InteractionVO> getAll();
	
}
