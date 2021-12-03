package com.project.dwine.orderManage.model.service;

import java.util.List;
import java.util.Map;

import com.project.dwine.orderManage.model.vo.Purchase;
import com.project.dwine.salesInquiry.model.vo.Total;

public interface OrderManageService {

	List<Purchase> selectOrderList(int startRow, int endRow);
	
	List<Purchase> stateChangeList(String state);
	
	int updateOrderStatus(String purchaseNo, String orderStatus);

	int updatePoint(String purchaseNo);
	
	int updateMember(int userNo, int usePoint);

	int deleteOrder(String purchaseNo);

	Purchase selectOrderDetail(String purchaseNo);

	int updateAllChange(String purchaseNo, String orderStatus);

	/*
	 * int getSearchListCount(String startDate, String endDate, String
	 * searchCondition, String searchValue, String searchStatus);
	 */

	/*
	 * List<Purchase> searchOrderList(int startRow, int endRow, String startDate,
	 * String endDate, String searchCondition, String searchValue, String
	 * searchStatus);
	 */

	int getSearchListCount(Map<String, Object> order);

	List<Purchase> searchOrderList(Map<String, Object> order);


}
