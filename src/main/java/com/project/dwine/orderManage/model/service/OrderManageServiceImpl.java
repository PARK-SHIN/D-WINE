package com.project.dwine.orderManage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.orderManage.model.dao.OrderManageMapper;
import com.project.dwine.orderManage.model.vo.Purchase;

@Service("orderManageService")
public class OrderManageServiceImpl implements OrderManageService {
	
	private final OrderManageMapper orderManageMapper;
	
	@Autowired
	public OrderManageServiceImpl(OrderManageMapper orderManageMapper) {
		this.orderManageMapper = orderManageMapper;
	}

	@Override
	public List<Purchase> selectOrderList() {
		return orderManageMapper.selectOrderList();
	}

	@Override
	public List<Purchase> stateChangeList(String state) {
		return orderManageMapper.stateChangeList(state);
	}

	
	 @Override public int updateOrderStatus(String purchaseNo, String orderStatus) {
	 return orderManageMapper.updateOrderStatus(purchaseNo, orderStatus); 
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
	public List<Purchase> selectSearchList(String searchStatus, String searchCondition, String searchValue) {
		return orderManageMapper.selectSearchList(searchStatus, searchCondition, searchValue);
	}

	
	
	
	
}
