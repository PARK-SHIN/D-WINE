package com.project.dwine.wish.model.dao;


import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface WishMapper {

	/* 상품 찜 추가 */
	int addWish(int user_no, int product_no);

	/* 상품 찜 삭제 */
	int deleteWish(int user_no, int product_no);

}
