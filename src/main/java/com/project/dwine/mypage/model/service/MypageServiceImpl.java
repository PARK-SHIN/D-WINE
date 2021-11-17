package com.project.dwine.mypage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.mypage.model.dao.MypageMapper;
import com.project.dwine.mypage.model.vo.Point;
import com.project.dwine.mypage.model.vo.Purchase;
import com.project.dwine.mypage.model.vo.Review;
import com.project.dwine.mypage.model.vo.Wish;

@Service
public class MypageServiceImpl implements MypageService{

	private MypageMapper mypageMapper;
	
	@Autowired
	public MypageServiceImpl(MypageMapper mypageMapper) {
		this.mypageMapper = mypageMapper;
	}
	
	@Override
	public int memberModify(int user_no, String user_pw, String user_nickname, String user_name, String user_phone) {
		return mypageMapper.memberModify(user_no, user_pw, user_nickname, user_name, user_phone);
	}


	@Override
	public List<Review> findAllReview(int userNo) {
		return mypageMapper.findAllReview(userNo);
	}

	@Override
	public Member selectMember(int user_no) {
		return mypageMapper.selectMember(user_no);
	}

	@Override
	public int memberDelete(int user_no, String user_pw) {
		return mypageMapper.memberDelete(user_no, user_pw);
	}


	@Override
	public Review selectOneReview(int review_no) {
		return mypageMapper.selectOneReview(review_no);
	}

	@Override
	public int reviewDelete(int review_no) {
		return mypageMapper.reviewDelete(review_no);
	}

	@Override
	public List<Point> pointList(int user_no) {
		return mypageMapper.pointList(user_no);
	}

	@Override
	public List<Purchase> selectOrderList(int user_no) {
		return mypageMapper.selectOrderList(user_no);
	}

	@Override
	public int pickupModify(int purchase_no, String pickup_place, String pickup_date, String pickup_time) {
		return mypageMapper.pickupModify(purchase_no, pickup_place, pickup_date, pickup_time);
	}

	@Override
	public int reviewInsert(String review_text, String review_image, double star, int user_no, int od_no) {
		return mypageMapper.reviewInsert(review_text, review_image, star, user_no, od_no);
	}

	@Override
	public int pwdModify(int user_no, String user_pwd, String newPw) {
		return mypageMapper.pwdModify(user_no, user_pwd, newPw);
	}

	@Override
	public List<Wish> selectWishList(int user_no) {
		return mypageMapper.selectWishList(user_no);
	}

	@Override
	public int deleteOneWish(int wish_no) {
		return mypageMapper.deleteOneWish(wish_no);
	}

	//
	@Override
	public int pwdModfiytest(int user_no, String user_pw) {
		return mypageMapper.pwdModfiytest(user_no, user_pw);
	}

	@Override
	public int deleteMember(int user_no) {
		return mypageMapper.deleteMember(user_no);
	}

	/* 포인트 관련 메소드 */
	@Override
	public List<Purchase> purchaseList(int user_no) {
		return mypageMapper.purchaseList(user_no);
	}



	

}