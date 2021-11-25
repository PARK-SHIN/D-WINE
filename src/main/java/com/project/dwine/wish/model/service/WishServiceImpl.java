package com.project.dwine.wish.model.service;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.wish.model.dao.WishMapper;
import com.project.dwine.wish.model.vo.Wish;
  
@Service("WishService")
public class WishServiceImpl implements WishService {
	  
	private WishMapper wishMapper;
  
	@Autowired
	public WishServiceImpl(WishMapper wishMapper){ 
	  this.wishMapper = wishMapper; 
	}
	
	/* 상품 찜 추가 */
	@Override
	public int addWish(int user_no, int product_no) {
		return wishMapper.addWish(user_no, product_no);
	}

	/* 상품 찜 삭제 */
	@Override
	public int deleteWish(int user_no, int product_no) {
		return wishMapper.deleteWish(user_no, product_no);
	}

	

  
	
	
  }
 