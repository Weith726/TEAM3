package com.div.model;

import java.util.*;
import com.div.model.DivVO;
import com.doc.model.DocVO;

public interface DivDAO_interface {
	      public void insert(DivVO divVO);
          public void update(DivVO divVO);
          public void delete(String divVO);
          public DivVO findByPrimaryKey(String divVO);
	      public List<DivVO> getAll();
	      //�d�߬Y���������u(�@��h)(�^�� Set)
	      public Set<DocVO> getDocsByDivno(String divVO);
}
