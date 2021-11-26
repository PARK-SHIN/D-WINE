package com.project.dwine.manage.model.service;

import java.util.List;

import com.project.dwine.manage.model.vo.Inventory;


public interface InventoryMgService {

	List<Inventory> selectInvenMgList();

	Inventory selectTotalStock();

	Inventory selectTotalShop();

	Inventory selectTodayReceiving();

	int registInventory(Inventory inven);
	
	

}
