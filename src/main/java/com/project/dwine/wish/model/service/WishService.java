package com.project.dwine.wish.model.service;

import com.project.dwine.wish.model.vo.Wish;

public interface WishService {

	/* 상품 찜 추가 */
	int addWish(int user_no, int product_no);

	/* 상품 찜 삭제 */
	int deleteWish(int user_no, int product_no);


}
