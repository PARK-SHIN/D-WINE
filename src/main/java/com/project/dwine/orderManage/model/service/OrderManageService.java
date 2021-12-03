package com.project.dwine.orderManage.model.service;

import java.util.List;
import java.util.Map;

import com.project.dwine.orderManage.model.vo.Purchase;
import com.project.dwine.salesInquiry.model.vo.Total;

public interface OrderManageService {
	
	int getSearchListCount(Map<String, Object> order);

	List<Purchase> searchOrderList(Map<String, Object> order);
	
	int updateOrderStatus(String purchaseNo, String orderStatus);

	int updatePoint(String purchaseNo);
	
	int updateMember(int userNo, int usePoint);

	int updateAllChange(String purchaseNo, String orderStatus);

	int deleteOrder(String purchaseNo);

	Purchase selectOrderDetail(String purchaseNo);

}
