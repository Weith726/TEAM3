package com.accusation.model;

import java.util.List;


public interface Accusation_interface {
	public void insert(AccusationVO accusationVO);

	public void update(AccusationVO accusationVO);

	public void delete(String accusationNo);

	public AccusationVO findByPrimaryKey(String accusationNo);

	public List<AccusationVO> getAll();
}
