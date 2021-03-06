package com.project.dwine.personalQnA.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.dwine.member.model.vo.UserImpl;
import com.project.dwine.paging.PageInfo;
import com.project.dwine.personalQnA.model.service.PersonalQnAService;
import com.project.dwine.personalQnA.model.vo.PersonalQ;

@Controller
@RequestMapping("/qna")
public class PersonalQnAController {
	
	private PersonalQnAService qnaService;
	
	@Autowired
	public PersonalQnAController(PersonalQnAService qnaService) {
		this.qnaService = qnaService;
	}
	
	@GetMapping("userqna")
	public String userQnAView(Model model, @RequestParam(value="page", required=false) String page) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    
	    int listCount = qnaService.getTotalQnaListCount(user_no);
	    int resultPage = 1;
	    
	    if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		
		PageInfo pi = new PageInfo(resultPage, listCount, 10, 5);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
        List<PersonalQ> userQnaList = qnaService.findUserQnaListPage(user_no, startRow, endRow);
		model.addAttribute("qnaList", userQnaList);
		model.addAttribute("pi", pi);
		return "/qna/userqnaList";
	}
	
	// ??????????????? ??????
	@GetMapping("insertQ")
	public String userInsertQ() {
		return "/qna/insertQ";
	}
	
	// ???????????? ??????
	@PostMapping("insertQform")
	public String insertQform(@RequestParam MultipartFile singleFile, @RequestParam String qna_title, 
			@RequestParam String qna_content, Model model, RedirectAttributes rttr) {
		UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    int user_no = user.getUser_no();
	    if(singleFile.isEmpty()) {
	    	int result1 = qnaService.insertUserQnaNoImage(qna_title, qna_content, user_no);
	    	if(result1 > 0) {
				rttr.addFlashAttribute("message", "1:1?????? ????????? ?????????????????????.");
			} else {
				rttr.addFlashAttribute("message", "1:1?????? ????????? ?????????????????????.");
			}
	    } else {
	    	String currentDir = System.getProperty("user.dir");
			System.out.println(user_no);
		      
			String filePath = currentDir + "\\src\\main\\resources\\static\\images\\uploadFiles\\qna";
			
			File mkdir = new File(filePath); if(!mkdir.exists()) mkdir.mkdirs();
			  
			String originFileName = singleFile.getOriginalFilename();
			
			String ext = originFileName.substring(originFileName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
			  
			String qna_image = "/images/uploadFiles/qna/" + savedName;
			try {
				singleFile.transferTo(new File(filePath + "\\" + savedName));
				
				int result = qnaService.insertUserQna(qna_title, qna_content, qna_image, user_no);
				if(result > 0) {
					rttr.addFlashAttribute("message", "1:1?????? ????????? ?????????????????????.");
				} else {
					rttr.addFlashAttribute("message", "1:1?????? ????????? ?????????????????????.");
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
	    }
		return "redirect:/qna/userqna";
	}
	
	// ????????? => ?????? ??????
	@GetMapping("delete/{qna_no}")
	public String deleteUserQna(@PathVariable int qna_no, RedirectAttributes rttr) {
		int result = qnaService.deleteUserQna(qna_no);
		if(result > 0) {
			rttr.addFlashAttribute("message", "?????????????????????.");
		} else {
			rttr.addFlashAttribute("message", "????????? ?????????????????????.");
		}
		return "redirect:/qna/userqna";
	}
	
	// ????????? => ?????? ?????? ????????? ??????
	@GetMapping("/modify/{qna_no}")
	public String qnaUpdateView(@PathVariable int qna_no, Model model) {
		PersonalQ person = qnaService.selectOneQna(qna_no);
		model.addAttribute("person", person);
		return "/qna/qnaUpdate";
	}
	
	// ????????? => ????????????
	@PostMapping("/modifyform")
	public String qnaUpdateForm(@RequestParam MultipartFile singleFile, @RequestParam String qna_title, 
			@RequestParam String qna_content, @RequestParam int qna_no , RedirectAttributes rttr) {
		System.out.println("modify singleFile : " + singleFile);
		if(singleFile.isEmpty()) {
			int result2 = qnaService.modifyUserQnaNoImage(qna_title, qna_content, qna_no);
			if(result2 > 0) {
				rttr.addFlashAttribute("message", "?????????????????????.");
			} else {
				rttr.addFlashAttribute("message", "??? ????????? ?????????????????????.");
			}
		} else {
			String currentDir = System.getProperty("user.dir");
		      
			String filePath = currentDir + "\\src\\main\\resources\\static\\images\\uploadFiles\\qna";
	      
			File mkdir = new File(filePath);
			if(!mkdir.exists()) mkdir.mkdirs();
	      
			String originFileName = singleFile.getOriginalFilename();
			String ext = originFileName.substring(originFileName.lastIndexOf(".")); // ????????? ??????
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
	      
			String qna_image = "/images/uploadFiles/qna/" + savedName;
			
			try {
				singleFile.transferTo(new File(filePath + "\\" + savedName));
				
				int result = qnaService.modifyUserQna(qna_title, qna_content, qna_image, qna_no);
				if(result > 0) {
					rttr.addFlashAttribute("message", "?????????????????????.");
				} else {
					rttr.addFlashAttribute("message", "??? ????????? ?????????????????????.");
				}
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/qna/userqna";
	}
	
	
	// ????????? 1:1?????? ?????????
	@GetMapping("adminQnaList")
	public String findAllQnaList(Model model, @RequestParam(value="page", required=false) String page) {
		int listCount = qnaService.getTotalQnaListCount();
	    int resultPage = 1;
	    
	    if(page != null) {
			resultPage = Integer.parseInt(page);
		}
		
		PageInfo pi = new PageInfo(resultPage, listCount, 10, 5);
		
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
        int endRow = startRow + pi.getBoardLimit() - 1;
		
        List<PersonalQ> adminQnaList = qnaService.findAllQnaListPage(startRow, endRow);
		model.addAttribute("adminQnaList", adminQnaList);
		model.addAttribute("pi", pi);
		return "/qna/adminqnaList";
	}
	
	@PostMapping("adminAnswerInsertForm")
	public String adminInsertAnswer(@RequestParam int qna_no, @RequestParam String answer_content, RedirectAttributes rttr) {
		int result = qnaService.insertAdminAnswer(qna_no, answer_content);
		if(result > 0) {
			rttr.addFlashAttribute("message", "????????? ?????????????????????.");
			System.out.println("admin insert success");
		} else {
			rttr.addFlashAttribute("message", "?????? ????????? ?????????????????????.");
			System.out.println("admin insert fail");
		}
		return "redirect:/qna/adminQnaList";
	}
	
	@PostMapping("adminAnswerModifyForm")
	public String adminAnswerModify(@RequestParam int qna_no, @RequestParam String answer_content, RedirectAttributes rttr) {
		int result = qnaService.adminAnswerModify(qna_no, answer_content);
		if(result > 0) {
			rttr.addFlashAttribute("message", "????????? ?????????????????????.");
		} else {
			rttr.addFlashAttribute("message", "?????? ????????? ?????????????????????.");
		}
		return "redirect:/qna/adminQnaList";
	}
	
	@GetMapping("amdinDeleteQna")
	public String adminDeleteQna(@RequestParam int qna_no, RedirectAttributes rttr) {
		int result = qnaService.adminDeleteUserQna(qna_no);
		if(result > 0) {
			rttr.addFlashAttribute("message", "????????? ?????????????????????.");
		} else {
			rttr.addFlashAttribute("message", "?????? ????????? ?????????????????????.");
		}
		return "redirect:/qna/adminQnaList";
	}
}
