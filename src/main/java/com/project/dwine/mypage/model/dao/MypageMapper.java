package com.project.dwine.mypage.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.mypage.model.vo.Payment;
import com.project.dwine.mypage.model.vo.Point;
import com.project.dwine.mypage.model.vo.Purchase;
import com.project.dwine.mypage.model.vo.Review;
import com.project.dwine.mypage.model.vo.Wish;

@Mapper
public interface MypageMapper {

	//int memberModify(Member m);
	int memberModify(int user_no, String user_pw, String user_nickname, String user_name, String user_phone);

	List<Review> findAllReview(int userNo);

	Member selectMember(int user_no);

	int memberDelete(int user_no, String user_pw);

	int insertReview(Review r);

	Review selectOneReview(int review_no);

	int reviewDelete(int review_no);
	
	List<Wish> selectWishList(int user_no);

	List<Point> pointList(int user_no);

	List<Purchase> selectOrderList(int user_no);

	int pickupModify(String purchase_no, String pickup_place, String pickup_date, String pickup_time);

	int reviewInsert(String review_text, String review_image, double star, int user_no, int od_no);

	int pwdModify(int user_no, String user_pwd, String newPw);

	int deleteOneWish(int wish_no);

	int pwdModfiytest(int user_no, String user_pw);

	int deleteMember(int user_no);

	List<Purchase> purchaseList(int user_no);

	int nicknameCheck(String user_nickname);

	int insertReviewPoint(int user_no, int review_no);

	int findReviewNo();

	int insertWishToCart(int user_no, int product_no);

	int reviewUpdate(int review_no, String review_text, String review_image);

	int updateCancelPayment(String purchase_no);

	Member selectMemberPoint(int user_no);

	int orderListCnt(int user_no);

	int updateAddMemberPoint(int user_no);




	

}
