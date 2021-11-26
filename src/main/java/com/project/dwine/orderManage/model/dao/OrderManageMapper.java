package com.project.dwine.orderManage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.orderManage.model.vo.Purchase;

@Mapper
public interface OrderManageMapper {

	List<Purchase> selectOrderList();

	List<Purchase> stateChangeList(String state);
	
	int updateOrderStatus(String purchaseNo, String orderStatus);

	int deleteOrder(String purchaseNo);

	Purchase selectOrderDetail(String purchaseNo);

	int updateAllChange(String purchaseNo, String orderStatus);

	List<Purchase> selectSearchList(String searchStatus, String searchCondition, String searchValue);
	
}
