package com.project.dwine.orderManage.model.service;

import java.util.List;

import com.project.dwine.orderManage.model.vo.Purchase;

public interface OrderManageService {

	List<Purchase> selectOrderList();
	
	List<Purchase> stateChangeList(String state);
	
	int updateOrderStatus(String purchaseNo, String orderStatus);

	int deleteOrder(String purchaseNo);

	Purchase selectOrderDetail(String purchaseNo);

	int updateAllChange(String purchaseNo, String orderStatus);

	List<Purchase> selectSearchList(String searchStatus, String searchCondition, String searchValue);
}
