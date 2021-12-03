package com.project.dwine.orderManage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.orderManage.model.vo.Purchase;

@Mapper
public interface OrderManageMapper {

	List<Purchase> selectOrderList(int startRow, int endRow);

	List<Purchase> stateChangeList(String state);
	
	int updateOrderStatus(String purchaseNo, String orderStatus);

	int updatePoint(String purchaseNo);
	
	int updateMember(int userNo, int usePoint);

	int deleteOrder(String purchaseNo);

	Purchase selectOrderDetail(String purchaseNo);

	int updateAllChange(String purchaseNo, String orderStatus);

	// int getSearchListCount(String startDate, String endDate, String searchStatus, String searchCondition, String searchValue);

	// List<Purchase> searchOrderList(int startRow, int endRow, String startDate, String endDate, String searchStatus, String searchCondition, String searchValue);

	int getSearchListCount(Map<String, Object> order);

	List<Purchase> searchOrderList(Map<String, Object> order);	
}
