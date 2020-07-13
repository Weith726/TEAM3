package com.interaction.model;

import java.util.List;
import java.util.Set;

public class InteractionService {

	private InteractionDAO_interface dao;

	public InteractionService() {
		dao = new InteractionDAO();
	}

	public InteractionVO addInteraction(String petNo, String adopterNo, java.sql.Timestamp interactionDate, String interactionStatus, Integer adoptDesire) {

		InteractionVO interactionVO = new InteractionVO();
		
		interactionVO.setPetNo(petNo);
		interactionVO.setAdopterNo(adopterNo);
		interactionVO.setInteractionDate(interactionDate);
		interactionVO.setInteractionStatus(interactionStatus);
		interactionVO.setAdoptDesire(adoptDesire);
		
		dao.insert(interactionVO);

		return interactionVO;
	}

	public InteractionVO updateInteraction(String interactionNo, String petNo, String adopterNo, java.sql.Timestamp interactionDate, String interactionStatus, Integer adoptDesire) {

		InteractionVO interactionVO = new InteractionVO();

		interactionVO.setInteractionNo(interactionNo);
		interactionVO.setPetNo(petNo);
		interactionVO.setAdopterNo(adopterNo);
		interactionVO.setInteractionDate(interactionDate);
		interactionVO.setInteractionStatus(interactionStatus);
		interactionVO.setAdoptDesire(adoptDesire);

		dao.update(interactionVO);

		return interactionVO;
	}

	public List<InteractionVO> getAll() {
		return dao.getAll();
	}

	public InteractionVO getOneInteraction(String interactionNo) {
		return dao.findByPrimaryKey(interactionNo);
	}

	public void deleteInteraction(String interactionNo) {
		dao.delete(interactionNo);
	}
	
	public Set<InteractionVO> getInteractionByAdopterNo(String adopterNo){
		return dao.findByAdopterNo(adopterNo);
	}

}
