package com.product.model;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

public class ProService {

	private ProDAO_IN dao;

	public ProService() {
		dao = new ProDAO();
	}

	public ProVO updatePro(Integer kindno, String productname, Integer productprice, java.sql.Date producton,
			Integer productstock, Integer productsafe, byte[] productpic, String productintro, Integer productstatus,
			Integer proid) {
		ProVO pro = new ProVO();
		pro.setProductid(proid);
		pro.setKindno(kindno);
		pro.setProductname(productname);
		pro.setProductprice(productprice);
		pro.setProducton(producton);
		pro.setProductstock(productstock);
		pro.setProductsafe(productsafe);
		pro.setProductpic(productpic);
		pro.setProductintro(productintro);
		pro.setProductstatus(productstatus);
		dao.update(pro);

		return pro;

	}

	public ProVO getOneproduct(Integer proid) {
		return dao.findByPrimaryKey(proid);
	}

	public Integer addProduct(Integer kindno, String productname, Integer productprice, Date producton,
			Integer productstock, Integer productsafe, byte[] productpic, String productintro, Integer productstatus) {
		ProVO provo = new ProVO();
		Integer seq = null;
		provo.setKindno(kindno);
		provo.setProductname(productname);
		provo.setProductprice(productprice);
		provo.setProducton(producton);
		provo.setProductstock(productstock);
		provo.setProductsafe(productsafe);
		provo.setProductpic(productpic);
		provo.setProductintro(productintro);
		provo.setProductstatus(productstatus);
		seq = dao.insert(provo);

		return seq;
	}

	public List<ProVO> getAll() {
		return dao.getAll();
	}
	public List<ProVO> getAllforshop() {
		return dao.getAllforShop();
	}

	public List<ProVO> getDog() {
		return dao.getDog();
	}

	public List<ProVO> getCat() {
		return dao.getCat();
	}

	public List<ProVO> getAnother() {
		return dao.getAnother();
	}

	public List<ProVO> getSelect(String name) {
		return dao.getSelect(name);
	}

	public List<ProVO> getSelectdog(String name) {
		return dao.getSelectdog(name);
	}

	public List<ProVO> getSelectcat(String name) {
		return dao.getSelectcat(name);
	}

	public List<ProVO> getSelectanother(String name) {
		return dao.getSelectanother(name);
	}
	
	public List<ProVO> getSelectForIndex(String name) {
		return dao.getSelectForIndex(name);
	}
}
