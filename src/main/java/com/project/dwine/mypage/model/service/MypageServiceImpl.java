package com.project.dwine.mypage.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.member.model.vo.Member;
import com.project.dwine.mypage.model.dao.MypageMapper;
import com.project.dwine.mypage.model.vo.OrderDetail;
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
	public Member selectMember(int user_no) {
		return mypageMapper.selectMember(user_no);
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
	public int pickupModify(String purchase_no, String pickup_place, String pickup_date, String pickup_time) {
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

	@Override
	public int nicknameCheck(String user_nickname) {
		return mypageMapper.nicknameCheck(user_nickname);
	}

	@Override
	public int insertReviewPoint(int user_no, int review_no) {
		return mypageMapper.insertReviewPoint(user_no, review_no);
	}

	@Override
	public int findReviewNo() {
		return mypageMapper.findReviewNo();
	}

	@Override
	public int insertWishToCart(int user_no, String product_no) {
		return mypageMapper.insertWishToCart(user_no, Integer.parseInt(product_no));
	}

	@Override
	public int reviewUpdate(int review_no, String review_text, String review_image) {
		return mypageMapper.reviewUpdate(review_no, review_text, review_image);
	}

	@Override
	public int updateCancelPayment(String purchase_no) {
		return mypageMapper.updateCancelPayment(purchase_no);
	}

	@Override
	public Member selectMemberPoint(int user_no) {
		return mypageMapper.selectMemberPoint(user_no);
	}

	@Override
	public int orderListCnt(int user_no) {
		return mypageMapper.orderListCnt(user_no);
	}

	@Override
	public int updateAddMemberPoint(int user_no) {
		return mypageMapper.updateAddMemberPoint(user_no);
	}

	@Override
	public List<Purchase> selectOrderListPage(int user_no, int startRow, int endRow) {
		return mypageMapper.selectOrderListPage(user_no, startRow, endRow);
	}

	@Override
	public int getTotalListCount(int user_no) {
		return mypageMapper.getTotalListCount(user_no);
	}

	@Override
	public int getTotalWishListCount(int user_no) {
		return mypageMapper.getTotalWishListCount(user_no);
	}

	@Override
	public List<Wish> selectWishListPage(int user_no, int startRow, int endRow) {
		return mypageMapper.selectWishListPage(user_no, startRow, endRow);
	}

	@Override
	public List<Review> findAllReviewPage(int user_no, int startRow, int endRow) {
		return mypageMapper.findAllReviewPage(user_no, startRow, endRow);
	}

	@Override
	public int getTotalReviewListCount(int user_no) {
		return mypageMapper.getTotalReviewListCount(user_no);
	}

	@Override
	public int memberPointReviewDelete(int user_no) {
		return mypageMapper.memberPointReviewDelete(user_no);
	}

	@Override
	public Point findPurchasePoint(String purchase_no) {
		return mypageMapper.findPurchasePoint(purchase_no);
	}

	@Override
	public int memberPointPayCancelDelete(int user_no, int point, int use_point) {
		return mypageMapper.memberPointPayCancelDelete(user_no, point, use_point);
	}

	@Override
	public int getTotalReviewListCountTest(String sortStandard, int user_no) {
		return mypageMapper.getTotalReviewListCountTest(sortStandard, user_no);
	}

	@Override
	public List<Review> findAllReviewPageTest(String sortStandard, int user_no, int startRow, int endRow) {
		return mypageMapper.findAllReviewPageTest(sortStandard, user_no, startRow, endRow);
	}

	@Override
	public Review selectReviewImagePath(int review_no) {
		return mypageMapper.selectReviewImagePath(review_no);
	}

	@Override
	public int pointModifyStatus(String purchase_no) {
		return mypageMapper.pointModifyStatus(purchase_no);
	}

	@Override
	public List<OrderDetail> selectProductCount(String purchase_no) {
		return mypageMapper.selectProductCount(purchase_no);
	}

	@Override
	public int productCountModify(int product_count, int product_no) {
		return mypageMapper.productCountModify(product_count, product_no);
	}

}
