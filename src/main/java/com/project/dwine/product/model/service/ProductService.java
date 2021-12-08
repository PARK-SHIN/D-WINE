package com.project.dwine.product.model.service;

import java.util.List;

import com.project.dwine.product.model.vo.Country;
import com.project.dwine.product.model.vo.Product;
import com.project.dwine.product.model.vo.Type;
import com.project.dwine.product.model.vo.Variety;

public interface ProductService {

	List<Type> selectTypeList();

	List<Country> selectCountryList();

	List<Variety> selectVarietyList();

	Product selectProductByNo(int productNo);

	int registProduct(Product product);

	int registProductHash(int hashNo);

	int deleteProduct(int productNo);

	int modifyProduct(Product product);

	int modifyProductHash(int productNo, int hashNo, int preHashNo);

	int deleteMultiProduct(int productNo);
	
	int productNameCheck(String kname);

	Product selectImgPath(int productNo);

	int getListCount(String sortStandard, String searchStandard, String searchValue);

	List<Product> selectProductList(String sortStandard, String searchStandard, String searchValue, int startRow,
			int endRow);
	






}
