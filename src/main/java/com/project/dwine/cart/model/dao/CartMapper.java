package com.project.dwine.cart.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.cart.model.vo.Cart;
import com.project.dwine.purchase.model.vo.Product;

@Mapper
public interface CartMapper {

	/* 장바구니에 상품 추가 */
	int addCart(Cart cart);


	/* 장바구니에 있는 상품인지 확인 */
	Cart checkCart(int user_no, int product_no);
	Cart checkCart(Cart cart);

	/* 회원의 장바구니 리스트 */
	// List<Cart> cartList(int user_no);
	List<Product> cartList(int user_no);

}
