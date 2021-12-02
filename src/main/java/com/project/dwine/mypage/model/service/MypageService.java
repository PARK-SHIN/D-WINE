package com.project.dwine.mypage.model.service;

import java.util.List;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.mypage.model.vo.Point;
import com.project.dwine.mypage.model.vo.Purchase;
import com.project.dwine.mypage.model.vo.Review;
import com.project.dwine.mypage.model.vo.Wish;

public interface MypageService {
	
	// ==== member ====
	int memberModify(int user_no, String user_pw, String user_nickname, String user_name, String user_phone);
	
	int pwdModify(int user_no, String user_pwd, String newPw);
	
	int pwdModfiytest(int user_no, String user_pw);

	Member selectMember(int user_no);
	
	int deleteMember(int user_no);
	
	int nicknameCheck(String user_nickname);

	// ===== review =====
	Review selectOneReview(int review_no);
	
	int reviewInsert(String review_text, String review_image, double star, int user_no, int od_no);

	int reviewDelete(int review_no);

	int insertReviewPoint(int user_no, int review_no);
	
	int findReviewNo();
	
	List<Review> findAllReviewPage(int user_no, int startRow, int endRow);

	int getTotalReviewListCount(int user_no);

	// ===== point =====
	List<Point> pointList(int user_no);
	
	Member selectMemberPoint(int user_no);
	
	int updateAddMemberPoint(int user_no);

	// ===== order =====
	int pickupModify(String purchase_no, String pickup_place, String pickup_date, String pickup_time);
	
	List<Purchase> purchaseList(int user_no);
	
	int updateCancelPayment(String purchase_no);
	
	int orderListCnt(int user_no);
	
	List<Purchase> selectOrderListPage(int user_no, int startRow, int endRow);

	int getTotalListCount(int user_no);


	// ===== wish =====
	int deleteOneWish(int wish_no);
	
	int insertWishToCart(int user_no, String product_no);

	int reviewUpdate(int review_no, String review_text, String review_image);

	int getTotalWishListCount(int user_no);
	
	List<Wish> selectWishListPage(int user_no, int startRow, int endRow);

	int memberPointReviewDelete(int user_no);

	Point findPurchasePoint(String purchase_no);

	int memberPointPayCancelDelete(int user_no, int point, int use_point);
	
}
