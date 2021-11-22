package com.project.dwine.product.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.product.model.dao.ProductMapper;
import com.project.dwine.product.model.vo.Country;
import com.project.dwine.product.model.vo.Product;
import com.project.dwine.product.model.vo.Type;
import com.project.dwine.product.model.vo.Variety;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductMapper productMapper;
	
	// 생성자 주입
	@Autowired
	public ProductServiceImpl(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	@Override
	public List<Product> selectProductList() {
		return productMapper.selectProductList();
	}

	@Override
	public List<Type> selectTypeList() {
		return productMapper.selectTypeList();
	}

	@Override
	public List<Country> selectCountryList() {
		return productMapper.selectCountryList();
	}

	@Override
	public List<Variety> selectVarietyList() {
		return productMapper.selectVarietyList();
	}

	@Override
	public Product selectProductByNo(int productNo) {
		return productMapper.selectProductByNo(productNo);
	}

	@Override
	public int registProduct(Product product) {
		return productMapper.registProduct(product);
	}

	@Override
	public int selectLastSeqNo() {
		return productMapper.selectLastSeqNo();
	}

	@Override
	public int registProductHash(int hashNo) {
		return productMapper.registProductHash(hashNo);
	}

	@Override
	public int deleteProduct(int productNo) {
		return productMapper.deleteProduct(productNo);
		
	}

	@Override
	public int modifyProduct(Product product) {
		return productMapper.modifyProduct(product);
	}

	@Override
	public int modifyProductHash(int productNo, int hashNo, int preHashNo) {
		return productMapper.modifyProductHash(productNo, hashNo, preHashNo);
	}

	@Override
	public int deleteMultiProduct(int productNo) {
		return productMapper.deleteMultiProduct(productNo);
	}

	

}
