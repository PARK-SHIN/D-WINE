package com.project.dwine.orderManage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.orderManage.model.vo.Purchase;

@Mapper
public interface OrderManageMapper {

	List<Purchase> selectOrderList();

	List<Purchase> stateChangeList(String state);
	
	int updateOrderStatus(int purchaseNo, String orderStatus);

	int deleteOrder(int purchaseNo);

	Purchase selectOrderDetail(int purchaseNo);

	int updateAllChange(int purchaseNo, String orderStatus);
	
}
