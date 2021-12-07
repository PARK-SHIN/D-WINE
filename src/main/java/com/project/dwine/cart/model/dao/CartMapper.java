package com.project.dwine.cart.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.dwine.cart.model.vo.Cart;
import com.project.dwine.member.model.vo.Member;
import com.project.dwine.purchase.model.vo.Product;

@Mapper
public interface CartMapper {

	/* 장바구니에 상품 추가 */
	int addCart(Cart cart);


	/* 장바구니에 있는 상품인지 확인 */
	Cart checkCart(Cart cart);

	/* 회원의 장바구니 리스트 */
	List<Product> cartList(int user_no);

	/* 장바구니 상품 업데이트 */
	int updateCart(Cart cart);

	/* 장바구니 상품 삭제*/
	int deleteCart(int user_no, int product_no);

	/* 구매 디테일 이동 */
	List<Product> purchaseList(String[] cart_no);

	/* 회원 정보 가져오기*/
	Member selectMember(int user_no);

}
