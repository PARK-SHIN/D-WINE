package com.project.dwine.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.dwine.product.model.service.ProductService;
import com.project.dwine.product.model.vo.Country;
import com.project.dwine.product.model.vo.Product;
import com.project.dwine.product.model.vo.Type;
import com.project.dwine.product.model.vo.Variety;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("list")
	public ModelAndView productList(ModelAndView mv) {
		
		List<Product> productList = productService.selectProductList();
		
		mv.addObject("productList", productList);
		mv.setViewName("product/list");
		
		return mv;
	}
	
	@GetMapping("category")
	@ResponseBody
	public Map<String, Object> findCategoryList() {
		Map<String, Object> map = new HashMap<>();
		List<Type> typeList = productService.selectTypeList();
		List<Country> countryList = productService.selectCountryList();
		List<Variety> varietyList = productService.selectVarietyList();
		
		map.put("type", typeList);
		map.put("country", countryList);
		map.put("variety", varietyList);
		
		return map;
	}
	
	@GetMapping("detail/{productNo}")
	public String selectProductByNo(@PathVariable int productNo, Model model) {
		Product product = productService.selectProductByNo(productNo);
		model.addAttribute("product", product);
		
		return "product/detail";
	}
	
	@GetMapping("regist") 
	public void registPage() {}
	
	@PostMapping("productNameCheck")
	public void nameCheck(@RequestParam String kname, HttpServletResponse response) throws IOException {
		
		int result = productService.productNameCheck(kname);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result);
	}
	
	@PostMapping("regist")
	public String registProduct(RedirectAttributes rttr, @RequestParam MultipartFile thumbnail, @RequestParam MultipartFile labelImg, HttpServletRequest request) {
		// 맛그래프, 이미지 제외 정보들
		String kname = request.getParameter("kname");
		String ename = request.getParameter("ename");
		int costPrice = Integer.parseInt(request.getParameter("costPrice"));
		int salePrice = Integer.parseInt(request.getParameter("salePrice"));
		int productCount = Integer.parseInt(request.getParameter("productCount"));
		String winary = request.getParameter("winary");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		double abv = Double.parseDouble(request.getParameter("abv"));
		String information = request.getParameter("information");
		String award = request.getParameter("award");
		String tip = request.getParameter("tip");
		
		// 카테고리
		int typeNo = Integer.parseInt(request.getParameter("typeNo"));
		int varietyNo = Integer.parseInt(request.getParameter("varietyNo"));
		int countryNo = Integer.parseInt(request.getParameter("countryNo"));
		String typeName = request.getParameter("typeName");
		String varietyName = request.getParameter("varietyName");
		String countryName = request.getParameter("countryName");
		
		// 맛 그래프
		String sweetness = request.getParameter("sweetness");
		String acidity = request.getParameter("acidity");
		String body = request.getParameter("body");
		String tannin = request.getParameter("tannin");
		String tasteGraph = sweetness + "/" + acidity + "/" + body + "/" + tannin;
		
		// 해시태그 
		int hash1 = Integer.parseInt(request.getParameter("mood1"));
		int hash2 = Integer.parseInt(request.getParameter("mood2"));
		int hash3 = Integer.parseInt(request.getParameter("food1"));
		int hash4 = Integer.parseInt(request.getParameter("food2"));
		int[] hashArr = {hash1, hash2, hash3, hash4};
			
		// 이미지 처리(썸네일, 라벨이미지)
		String currentDir = System.getProperty("user.dir");
		// System.getProperty("user.dir"); => 현재 작업중인 디렉터리 가져오는 메소드
		// C:\Users\이경민\AppData\Roaming\SPB_16.6\git\DWine        
		
		// 사진이 저장되어야 할 경로
		// currentDir + \src\main\resources\static\images
		String filePath = currentDir + "\\src\\main\\resources\\static\\images\\product\\uploadFiles";
		
		// 이미지가 저장 될 폴더 생성
		File mkdir = new File(filePath);
		if(!mkdir.exists()) mkdir.mkdirs();
		
		// 파일명 랜덤명으로 변경
		String thumbnail_originFileName = thumbnail.getOriginalFilename();
		String thumbnail_ext = thumbnail_originFileName.substring(thumbnail_originFileName.lastIndexOf(".")); // 확장자 추출
		String thumbnail_savedName = UUID.randomUUID().toString().replace("-", "") + thumbnail_ext;
		
		String label_originFileName = labelImg.getOriginalFilename();
		String label_ext = label_originFileName.substring(label_originFileName.lastIndexOf(".")); // 확장자 추출
		String label_savedName = UUID.randomUUID().toString().replace("-", "") + label_ext;
		
		try {
			// 사진 저장
			thumbnail.transferTo(new File(filePath + "\\" + thumbnail_savedName));
			labelImg.transferTo(new File(filePath + "\\" + label_savedName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		String path = "\\images\\product\\uploadFiles";
		String thumbPath = path + "\\" + thumbnail_savedName;
		String labelPath = path + "\\" + label_savedName;
		
		Variety variety = new Variety(varietyNo, varietyName);
		Type type = new Type(typeNo, typeName);
		Country country = new Country(countryNo, countryName);
		
		Product product = new Product(kname, ename, costPrice, salePrice, productCount,
				winary, thumbPath, capacity, abv, tasteGraph, information, award, tip, labelPath, variety, type, country);
		
		int result = productService.registProduct(product);
		int result2 = 0;
		// int productNo = 0;
		
		if(result > 0) {
			for(int i = 0; i < 4; i++) {
				result2 += productService.registProductHash(hashArr[i]);
				
				if(result2 == hashArr.length) {
					// productNo = productService.selectLastSeqNo();
					rttr.addFlashAttribute("message", "상품 등록에 성공하였습니다.");
				} else {
					rttr.addFlashAttribute("message", "상품 등록에 실패하였습니다.");
				}
			}	
		}
		return "redirect:/product/list";
		// return "redirect:/product/detail/" + productNo;
	}
	
	@GetMapping("modify/{productNo}")
	public String modifyPage(Model model, @PathVariable int productNo) {
		Product product = productService.selectProductByNo(productNo);
		model.addAttribute("product", product);
		
		return "product/modify";
	}
	
	@PostMapping("modify")
	public String modifyProduct(RedirectAttributes rttr, @RequestParam(required = false) MultipartFile thumbnail, @RequestParam(required = false) MultipartFile labelImg, HttpServletRequest request) throws IllegalStateException, IOException {
		// 맛그래프, 이미지 제외 정보들
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		String kname = request.getParameter("kname");
		String ename = request.getParameter("ename");
		int costPrice = Integer.parseInt(request.getParameter("costPrice"));
		int salePrice = Integer.parseInt(request.getParameter("salePrice"));
		int productCount = Integer.parseInt(request.getParameter("productCount"));
		String winary = request.getParameter("winary");
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		double abv = Double.parseDouble(request.getParameter("abv"));
		String information = request.getParameter("information");
		String award = request.getParameter("award");
		String tip = request.getParameter("tip");
		
		// 카테고리
		int typeNo = Integer.parseInt(request.getParameter("typeNo"));
		int varietyNo = Integer.parseInt(request.getParameter("varietyNo"));
		int countryNo = Integer.parseInt(request.getParameter("countryNo"));
		String typeName = request.getParameter("typeName");
		String varietyName = request.getParameter("varietyName");
		String countryName = request.getParameter("countryName");
		
		// 맛 그래프
		String sweetness = request.getParameter("sweetness");
		String acidity = request.getParameter("acidity");
		String body = request.getParameter("body");
		String tannin = request.getParameter("tannin");
		String tasteGraph = sweetness + "/" + acidity + "/" + body + "/" + tannin;
		
		// 수정 할 해시태그 
		int hash1 = Integer.parseInt(request.getParameter("mood1"));
		int hash2 = Integer.parseInt(request.getParameter("mood2"));
		int hash3 = Integer.parseInt(request.getParameter("food1"));
		int hash4 = Integer.parseInt(request.getParameter("food2"));
		int[] hashArr = {hash1, hash2, hash3, hash4};
			
		// 수정 전 해시태그
		String preHash = request.getParameter("pre_hash");
		String[] preHashArr = preHash.split("/");
		
		// 이미지 처리(썸네일, 라벨이미지)
		String currentDir = System.getProperty("user.dir");
		// System.getProperty("user.dir"); => 현재 작업중인 디렉터리 가져오는 메소드
		// currentDir : C:\Users\이경민\AppData\Roaming\SPB_16.6\git\DWine   
		
		// 이미지 삭제
		// file이 null이 아니면 삭제할 사진 경로
		String delete_path = currentDir + "\\src\\main\\resources\\static";
		String old_thumbPath = request.getParameter("old_thumbPath");
		// \images\\uploadFiles\product\ea5107009fc84643b4e4b6047dcedac3.PNG
		String old_labelPath = request.getParameter("old_labelPath");
		
		// file이 null이 아니면 서버에 저장했던 사진 삭제
		if(!thumbnail.isEmpty()) {
			if (!old_thumbPath.equals("")) {
				File deleteThumbnail = new File(delete_path + old_thumbPath);
				deleteThumbnail.delete();
			}
		}
		if(!labelImg.isEmpty()) {
			if (!old_labelPath.equals("")) {
				File deleteLabel = new File(delete_path + old_labelPath);
				deleteLabel.delete();
			}
		}

		// 이미지 저장
		// 사진이 저장되어야 할 경로
		// C:\Users\이경민\AppData\Roaming\SPB_16.6\git\DWine\src\main\resources\static\images
		String filePath = currentDir + "\\src\\main\\resources\\static\\images\\product\\uploadFiles";
		
		// 이미지가 저장 될 폴더 생성
		File mkdir = new File(filePath);
		if(!mkdir.exists()) mkdir.mkdirs();
		
		String save_path = "\\images\\product\\uploadFiles";
		String new_thumbPath = "";
		String new_labelPath = "";
		
		// file이 null이 아니면 새로운 사진 저장
		if(!thumbnail.isEmpty()) {
			
			// 파일명 랜덤명으로 변경
			String thumbnail_originFileName = thumbnail.getOriginalFilename();
			String thumbnail_ext = thumbnail_originFileName.substring(thumbnail_originFileName.lastIndexOf(".")); // 확장자 추출
			String thumbnail_savedName = UUID.randomUUID().toString().replace("-", "") + thumbnail_ext;
			
			thumbnail.transferTo(new File(filePath + "\\" + thumbnail_savedName));
			new_thumbPath = save_path + "\\" + thumbnail_savedName;
		}
		if(!labelImg.isEmpty()) {
			
			String label_originFileName = labelImg.getOriginalFilename();
			String label_ext = label_originFileName.substring(label_originFileName.lastIndexOf(".")); // 확장자 추출
			String label_savedName = UUID.randomUUID().toString().replace("-", "") + label_ext;
			
			labelImg.transferTo(new File(filePath + "\\" + label_savedName));
			new_labelPath = save_path + "\\" + label_savedName;;
		}
		
		// 카테고리(와인 종류, 포도 품종, 국가) 설정
		Variety variety = new Variety(varietyNo, varietyName);
		Type type = new Type(typeNo, typeName);
		Country country = new Country(countryNo, countryName);
		
		Product product = new Product(productNo, kname, ename, costPrice, salePrice, productCount,
				winary, new_thumbPath, capacity, abv, tasteGraph, information, award, tip, new_labelPath, variety, type, country);
		
		int result = productService.modifyProduct(product);
		int result2 = 0;
		
		if(result > 0) {
			for(int i = 0; i < 4; i++) {
				result2 += productService.modifyProductHash(productNo, hashArr[i], Integer.parseInt(preHashArr[i]));
				
				if(result2 == hashArr.length) {
					rttr.addFlashAttribute("message", "상품 수정에 성공하였습니다.");
				} else {
					rttr.addFlashAttribute("message", "상품 수정에 실패하였습니다.");
				}
			}
		}
		
		return "redirect:/product/list";
		// return "redirect:/product/detail/" + productNo;
	}
	
	@PostMapping("delete") 
	public String deleteProduct(@RequestParam int productNo, RedirectAttributes rttr) {
		int result = productService.deleteProduct(productNo);
		
		if(result > 0) {
			rttr.addFlashAttribute("message", "상품 삭제에 성공하였습니다.");
		} else {
			rttr.addFlashAttribute("message", "상품 삭제에 실패하였습니다.");
		}
		return "redirect:/product/list";
	}
	
	@PostMapping("deleteMulti") 
	public String deleteMultiProduct(HttpServletRequest request, RedirectAttributes rttr) {
		String[] productNos = request.getParameterValues("delete_nums");

		int result = 0;
		for(String productNo : productNos) {
			result += productService.deleteMultiProduct(Integer.parseInt(productNo));
		}
		
		if(result == productNos.length) {
			rttr.addFlashAttribute("message", "선택 상품 삭제에 성공하였습니다.");
		} else {
			rttr.addFlashAttribute("message", "선택 상품 삭제에 실패하였습니다.");
		}
	
		return "redirect:/product/list";
	}
	
	@PostMapping("search")
	@ResponseBody
	public List<Product> seachProduct(@RequestParam String searchStandard, @RequestParam String searchValue) throws IOException {
		List<Product> searchList = productService.searchProductList(searchStandard, searchValue);
		
		return searchList;
	}
}
