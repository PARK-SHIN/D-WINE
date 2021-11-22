package com.project.dwine.orderManage.model.service;

import java.util.List;

import com.project.dwine.orderManage.model.vo.Purchase;

public interface OrderManageService {

	List<Purchase> selectOrderList();
	
	List<Purchase> stateChangeList(String state);
	
	int updateOrderStatus(int purchaseNo, String orderStatus);
}
