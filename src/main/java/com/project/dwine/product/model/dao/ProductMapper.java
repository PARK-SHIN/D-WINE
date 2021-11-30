package com.project.dwine.product.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.product.model.vo.Country;
import com.project.dwine.product.model.vo.Product;
import com.project.dwine.product.model.vo.Type;
import com.project.dwine.product.model.vo.Variety;

@Mapper
public interface ProductMapper {

	List<Type> selectTypeList();

	List<Country> selectCountryList();

	List<Variety> selectVarietyList();

	Product selectProductByNo(int productNo);

	int registProduct(Product product);

	int selectLastSeqNo();

	int registProductHash(int hashNo);

	int deleteProduct(int productNo);

	int modifyProduct(Product product);

	int modifyProductHash(int productNo, int hashNo, int preHashNo);

	int deleteMultiProduct(int productNo);

	List<Product> searchProductList(String searchStandard, String searchValue);

	int productNameCheck(String kname);

	// List<Product> sortProductList(String sortStandard, int startRow, int endRow);

	Product selectImgPath(int productNo);

	int getTotalListCount();

	List<Product> selectProductList(int startRow, int endRow);

	List<Product> selectSortProductList(String sortStandard, int startRow, int endRow);

	int getsearchListCount(String sortStandard, String searchStandard, String searchValue);

	List<Product> selectSearchProductList(String sortStandard, String searchStandard, String searchValue, int startRow,
			int endRow);


}
