package com.project.dwine.manage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.project.dwine.manage.model.vo.Inventory;

@Mapper
public interface InventoryMgMapper {

	List<Inventory> selectInvenMgList();

	Inventory selectTotalStock();

	Inventory selectTotalShop();

	Inventory selectTotalReceiving();

	int registInventory(Inventory inven);

	int cancleInventory(int inven_no);

	
}
