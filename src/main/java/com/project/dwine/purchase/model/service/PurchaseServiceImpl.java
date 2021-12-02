package com.project.dwine.purchase.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dwine.manage.model.vo.Report;
import com.project.dwine.member.model.vo.Member;
import com.project.dwine.purchase.model.dao.PurchaseMapper;
import com.project.dwine.purchase.model.vo.Hashtag;
import com.project.dwine.purchase.model.vo.OrderDetail;
import com.project.dwine.purchase.model.vo.Payment;
import com.project.dwine.purchase.model.vo.Point;
import com.project.dwine.purchase.model.vo.Product;
import com.project.dwine.purchase.model.vo.Purchase;
import com.project.dwine.purchase.model.vo.Review;
import com.project.dwine.wish.model.vo.Wish;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

	private final PurchaseMapper purchaseMapper;
	
	@Autowired
	public PurchaseServiceImpl(PurchaseMapper purchaseMapper) {
		this.purchaseMapper = purchaseMapper;
	}
	
	/* wineList 리턴*/
	@Override
	public List<Product> wineList() {
		return purchaseMapper.wineList();
	}

	/* WineDetail 리턴 */
	@Override
	public Product wineDetail(String id) {
		return purchaseMapper.wineDetail(id);
	}

	@Override
	public List<Hashtag> hashtag(String id) {
		return purchaseMapper.hashList();
	}

	/* Filter된 wineList 리턴 **/
	@Override
	public List<Product> filterWineList(String type, String price, String country, String variety, String name) {
		return purchaseMapper.filterWineList(type,price,country,variety,name);
	}

	/* 와인 인기순으로 정렬*/
	@Override
	public List<Product> popularList(String value) {
		return purchaseMapper.popularList(value);
	}

	
	/* Sort된 wineList 리턴 **/
	@Override
	public List<Product> sortWineList(String val) {
		return purchaseMapper.sortWineList(val);
	}

	/* 찜여부 확인 */
	@Override
	public Wish checkWish(int user_no, int product_no) {
		return purchaseMapper.checkWish(user_no, product_no);
	}

	/* purchase Table 값 넣기  */
	@Override
	public int purchaseInsert(Purchase purchase) {
		return purchaseMapper.purchaseInsert(purchase);
	}

	/* 회원 테이블 포인트 Update */
	@Override
	public int memberPoint(int user_no, int user_point) {
		return purchaseMapper.pointUpdate(user_no, user_point);
	}

	/* 포인트 테이블 Insert */
	@Override
	public int pointPlus(int user_no, int point, String purchase_no) {
		return purchaseMapper.pointPlus(user_no, point, purchase_no);
	}

	/* orderDetail Table 값 넣기  */
	@Override
	public int orderDetailInsert(OrderDetail orderDetail) {
		return purchaseMapper.orderDetailInsert(orderDetail);
	}

	/* payment Table 값 넣기  */
	@Override
	public int paymentInsert(Payment payment) {
		System.out.println(payment);
		return purchaseMapper.paymentInsert(payment);
	}

	/* 장바구니 삭제 */
	@Override
	public int cartDelete(int user_no, List<Integer> cart_no) {
		return purchaseMapper.cartDelete(user_no, cart_no);
	}

	/* 상품 재고 업데이트 */
	@Override
	public int stockUpdate(int product_no, int product_count) {
		return purchaseMapper.stockUpdate(product_no, product_count);	
	}

	/* 회원 정보 불러오기 */
	@Override
	public Member memberinfo(int user_no) {
		return purchaseMapper.memberinfo(user_no);
	}

	/* 구매 정보 불러오기 */
	@Override
	public Purchase selectPurchase(String purchase_no) {
		return purchaseMapper.selectPurchase(purchase_no);
	}

	/* 주문 상세 불러오기 */
	@Override
	public List<OrderDetail> selectOrderDetail(String purchase_no) {
		return purchaseMapper.selectOrderDetail(purchase_no);
	}

	/* 포인트 내역 불러오기 */
	@Override
	public Point selectPoint(String purchase_no) {
		return purchaseMapper.selectPoint(purchase_no);
	}

	/* 댓글 리스트 불러오기 */
	@Override
	public List<Review> reviewList(String id) {
		return purchaseMapper.reviewList(id);
	}

	/* 리뷰 중복 체크 */
	@Override
	public Report checkReport(int user_no, int reviewNo) {
		return purchaseMapper.checkReport(user_no, reviewNo);
	}

	/* 리뷰 등록  */
	@Override
	public int insertReport(int user_no, int userNo, int reviewNo, int reasonNo) {
		return purchaseMapper.insertReport(user_no, userNo, reviewNo, reasonNo);
	}


}











