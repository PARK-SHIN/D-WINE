package com.project.dwine.purchase.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.purchase.model.dao.PurchaseMapper;
import com.project.dwine.purchase.model.vo.Hashtag;
import com.project.dwine.purchase.model.vo.Product;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

	private final PurchaseMapper purchaseMapper;
	
	@Autowired
	public PurchaseServiceImpl(PurchaseMapper purchaseMapper) {
		this.purchaseMapper = purchaseMapper;
	}
	
	/* wineList 리턴*/
	@Override
	public List<Product> wineList() {
		return purchaseMapper.wineList();
	}

	/* WineDetail 리턴 */
	@Override
	public Product wineDetail(String id) {
		return purchaseMapper.wineDetail(id);
	}

	@Override
	public List<Hashtag> hashtag(String id) {
		return purchaseMapper.hashList();
	}

	/* Filter된 wineList 리턴 **/
	@Override
	public List<Product> filterWineList(String type, String price, String country, String variety, String name) {
		return purchaseMapper.filterWineList(type,price,country,variety,name);
	}


	
	/* Sort된 wineList 리턴 **/
	@Override
	public List<Product> sortWineList(String val) {
		return purchaseMapper.sortWineList(val);
	}

}











