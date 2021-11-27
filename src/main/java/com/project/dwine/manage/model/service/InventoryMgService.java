package com.project.dwine.manage.model.service;

import java.util.List;

import com.project.dwine.manage.model.vo.Inventory;


public interface InventoryMgService {
	//입고리스트
	List<Inventory> selectInvenMgList();
	
	//cnt 세개
	Inventory selectTotalStock();

	Inventory selectTotalShop();

	Inventory selectTodayReceiving();

	//입고등록
	int registInventory(Inventory inven);
	//입고취소
	int cancleInventory(int inven_no);
	
	

}
