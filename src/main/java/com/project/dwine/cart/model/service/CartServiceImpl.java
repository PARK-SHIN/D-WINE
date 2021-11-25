package com.project.dwine.cart.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.cart.model.dao.CartMapper;
import com.project.dwine.cart.model.vo.Cart;
import com.project.dwine.member.model.vo.Member;
import com.project.dwine.purchase.model.vo.Product;

@Service("CartService")
public class CartServiceImpl implements CartService{

	private CartMapper cartMapper;
	
	@Autowired
	public CartServiceImpl(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}
	
	/* 장바구니에 상품 추가*/
	@Override
	public int addCart(Cart cart) {
		return cartMapper.addCart(cart);
	}

	/* 장바구니에 있는 상품인지 확인 */
	@Override
	public Cart checkCart(int user_no, int product_no) {
		return cartMapper.checkCart(user_no, product_no);
	}
	@Override
	public Cart checkCart(Cart cart) {
		return cartMapper.checkCart(cart);
	}


	/* 회원의 장바구니 리스트 */
//	@Override
//	public List<Cart> cartList(int user_no) {
//		return cartMapper.cartList(user_no);
//	}

	/* 장바구니에 있는 상품인지 확인 */
	@Override
	public List<Product> cartList(int user_no) {
		return cartMapper.cartList(user_no);
	}
	
	/* 장바구니 상품 업데이트 */
	@Override
	public int updateCart(Cart cart) {
		return cartMapper.updateCart(cart);
	}

	/* 장바구니 상품 삭제 */
	@Override
	public int deleteCart(int user_no, int product_no) {
		return cartMapper.deleteCart(user_no, product_no);
	}

	/* 구매 디테일 이동 */
	@Override
	public List<Product> purchaseList(String[] cart_no) {
		return cartMapper.purchaseList(cart_no);
	}

	/* 회원 정보 가져오기*/
	@Override
	public Member selectMember(int user_no) {
		return cartMapper.selectMember(user_no);

	}
}
