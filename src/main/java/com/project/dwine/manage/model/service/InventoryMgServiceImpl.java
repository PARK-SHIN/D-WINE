package com.project.dwine.manage.model.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.manage.model.dao.InventoryMgMapper;
import com.project.dwine.manage.model.vo.Inventory;

@Service("InventoryMgService")
public class InventoryMgServiceImpl implements InventoryMgService {
	
	private final InventoryMgMapper inventoryMgMapper;
	
	@Autowired
	public InventoryMgServiceImpl(InventoryMgMapper inventoryMgMapper) {
		this.inventoryMgMapper =  inventoryMgMapper;
	}
	
	
	@Override
	public List<Inventory> selectInvenMgList() {
		return inventoryMgMapper.selectInvenMgList();
	}


	@Override
	public Inventory selectTotalStock() {
		return inventoryMgMapper.selectTotalStock();
	}


	@Override
	public Inventory selectTotalShop() {
		return inventoryMgMapper.selectTotalShop();
	}


	@Override
	public Inventory selectTodayReceiving() {
		return inventoryMgMapper.selectTotalReceiving();
	}


	@Override
	public int registInventory(Inventory inven) {
		return inventoryMgMapper.registInventory(inven);
	}


	@Override
	public int cancleInventory(int inven_no) {
		return inventoryMgMapper.cancleInventory(inven_no);	
	}




	@Override
	public int invenTotalListCnt() {
		return inventoryMgMapper.invenTotalListCnt();
	}

	@Override
	public List<Inventory> invenTotalList(int startRow, int endRow) {
		return inventoryMgMapper.invenTotalList(startRow, endRow);
	}

	@Override
	public int invenSearchListCnt(String searchStandard, String searchValue) {
		return inventoryMgMapper.invenSearchListCnt(searchStandard, searchValue);
	}


	@Override
	public List<Inventory> searchInvenList(String searchStandard, String searchValue, String startDate, 
			String endDate, int startRow, int endRow) {
		return inventoryMgMapper.searchInvenList(searchStandard, searchValue, startDate, 
				endDate, startRow, endRow);
	}

}
