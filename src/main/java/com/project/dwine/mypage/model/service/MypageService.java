package com.project.dwine.mypage.model.service;

import java.util.List;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.mypage.model.vo.Point;
import com.project.dwine.mypage.model.vo.Purchase;
import com.project.dwine.mypage.model.vo.Review;
import com.project.dwine.mypage.model.vo.Wish;

public interface MypageService {

	int memberModify(int user_no, String user_pw, String user_nickname, String user_name, String user_phone);

	List<Review> findAllReview(int userNo);

	Member selectMember(int user_no);

	int memberDelete(int user_no, String user_pw);

	Review selectOneReview(int review_no);

	int reviewDelete(int review_no);

	List<Point> pointList(int user_no);

	List<Purchase> selectOrderList(int user_no);

	int pickupModify(int purchase_no, String pickup_place, String pickup_date, String pickup_time);

	int reviewInsert(String review_text, String review_image, double star, int user_no, int od_no);

	int pwdModify(int user_no, String user_pwd, String newPw);

	List<Wish> selectWishList(int user_no);

	int deleteOneWish(int wish_no);

	//
	int pwdModfiytest(int user_no, String user_pw);

	int deleteMember(int user_no);

	List<Purchase> purchaseList(int user_no);


	
	
	

}
