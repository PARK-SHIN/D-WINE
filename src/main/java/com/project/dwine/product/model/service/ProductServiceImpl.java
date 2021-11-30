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

	@Override
	public List<Product> searchProductList(String searchStandard, String searchValue) {
		return productMapper.searchProductList(searchStandard, searchValue);
	}

	@Override
	public int productNameCheck(String kname) {
		return productMapper.productNameCheck(kname);
	}

	/*
	@Override
	public List<Product> sortProductList(String sortStandard, int startRow, int endRow) {
		return productMapper.sortProductList(sortStandard, startRow, endRow);
	}
	*/

	@Override
	public Product selectImgPath(int productNo) {
		return productMapper.selectImgPath(productNo);
	}

	@Override
	public int getTotalListCount() {
		return productMapper.getTotalListCount();
	}

	@Override
	public List<Product> selectProductList(int startRow, int endRow) {
		return productMapper.selectProductList(startRow, endRow);
	}

	@Override
	public List<Product> selectSortProductList(String sortStandard, int startRow, int endRow) {
		return productMapper.selectSortProductList(sortStandard, startRow, endRow);
	}

	@Override
	public int getsearchListCount(String sortStandard, String searchStandard, String searchValue) {
		return productMapper.getsearchListCount(sortStandard, searchStandard, searchValue);
	}

	@Override
	public List<Product> selectSearchProductList(String sortStandard, String searchStandard, String searchValue,
			int startRow, int endRow) {
		return productMapper.selectSearchProductList(sortStandard, searchStandard, searchValue, startRow, endRow);
	}

	
	

}
