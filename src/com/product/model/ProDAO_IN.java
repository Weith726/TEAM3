package com.product.model;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;


public interface ProDAO_IN {
	public Integer insert(ProVO provo);
    public void update(ProVO provo);
    public ProVO  findByPrimaryKey(Integer proid);
    public List<ProVO> getAll();
    public List<ProVO> getAllforShop();
    public List<ProVO> getCat();
    public List<ProVO> getDog();
    public List<ProVO> getAnother();
    public List<ProVO> getSelect(String name);
    public List<ProVO> getSelectdog(String name);
    public List<ProVO> getSelectcat(String name);
    public List<ProVO> getSelectanother(String name);
    public List<ProVO> getSelectForIndex(String name);
}
