package com.memlatestinfo.model;

import java.util.*;

public interface Memlatestinfo_interface {
	
	 public void insert(MemlatestinfoVO memliVO);
     public void update(MemlatestinfoVO memliVO);
     public void delete(Integer memLatestInfoNo);
     public MemlatestinfoVO findByPrimaryKey(Integer memLatestInfoNo);    
     public List<MemlatestinfoVO> getAll();
}
