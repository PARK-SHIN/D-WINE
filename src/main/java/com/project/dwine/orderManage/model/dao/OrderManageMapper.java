package com.project.dwine.orderManage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.orderManage.model.vo.Purchase;

@Mapper
public interface OrderManageMapper {

	int getSearchListCount(Map<String, Object> order);

	List<Purchase> searchOrderList(Map<String, Object> order);	
		
	int updateOrderStatus(String purchaseNo, String orderStatus);

	int updatePoint(String purchaseNo);
	
	int updateMember(int userNo, int usePoint);

	int deleteOrder(String purchaseNo);

	Purchase selectOrderDetail(String purchaseNo);

	int updateAllChange(String purchaseNo, String orderStatus);

}
