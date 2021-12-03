package com.project.dwine.orderManage.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.orderManage.model.dao.OrderManageMapper;
import com.project.dwine.orderManage.model.vo.Purchase;
import com.project.dwine.salesInquiry.model.vo.Total;

@Service("orderManageService")
public class OrderManageServiceImpl implements OrderManageService {
	
	private final OrderManageMapper orderManageMapper;
	
	@Autowired
	public OrderManageServiceImpl(OrderManageMapper orderManageMapper) {
		this.orderManageMapper = orderManageMapper;
	}

	@Override
	public List<Purchase> selectOrderList(int startRow, int endRow) {
		return orderManageMapper.selectOrderList(startRow, endRow);
	}

	@Override
	public List<Purchase> stateChangeList(String state) {
		return orderManageMapper.stateChangeList(state);
	}

	@Override public int updateOrderStatus(String purchaseNo, String orderStatus) {
		return orderManageMapper.updateOrderStatus(purchaseNo, orderStatus); 
	}

	@Override
	public int updatePoint(String purchaseNo) {
		return orderManageMapper.updatePoint(purchaseNo);
	}
	 
	@Override
	public int updateMember(int userNo, int usePoint) {
		return orderManageMapper.updateMember(userNo, usePoint);
	}
	 
	@Override
	public int deleteOrder(String purchaseNo) {
		return orderManageMapper.deleteOrder(purchaseNo);
	}

	@Override
	public Purchase selectOrderDetail(String purchaseNo) {
		return orderManageMapper.selectOrderDetail(purchaseNo);
	}

	@Override
	public int updateAllChange(String purchaseNo, String orderStatus) {
		return orderManageMapper.updateAllChange(purchaseNo, orderStatus);
	}


	@Override
	public int getSearchListCount(Map<String, Object> order) {
		return orderManageMapper.getSearchListCount(order);
	}

	@Override
	public List<Purchase> searchOrderList(Map<String, Object> order) {
		return orderManageMapper.searchOrderList(order);
	}




	
}
