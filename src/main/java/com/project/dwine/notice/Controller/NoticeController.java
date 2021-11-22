package com.project.dwine.notice.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.dwine.member.model.vo.UserImpl;
import com.project.dwine.notice.model.service.NoticeService;
import com.project.dwine.notice.model.vo.Notice;

@Controller
@RequestMapping("/notice")
public class NoticeController {
   
   private NoticeService noticeService;
   
   @Autowired
   public NoticeController(NoticeService noticeService) {
      this.noticeService = noticeService;
   }
   
   @GetMapping("/main")
   public ModelAndView noticeList(ModelAndView mv){
      List<Notice> noticeList = noticeService.selectNoticeList();
      mv.addObject("noticeList" , noticeList);
      mv.setViewName("notice/main");
      System.out.println(noticeList);
      
      return mv;
   }

   @GetMapping("detail/{notice_no}")
   public String selectProductByNo(@PathVariable int notice_no, Model model) {
      Notice notice = noticeService.selectNoticeByNo(notice_no);
      model.addAttribute("notice", notice);
      
      return "notice/detail";
   }
   
   @GetMapping("/regist")
   public void registPage(Model model) {
	   UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       int user_no = user.getUser_no();
       model.addAttribute("user_no", user_no);
   }
  
  
   //@RequestMapping(value = "/regist", method = {RequestMethod.GET, RequestMethod.POST})
   @PostMapping("/regist")
   public String registNotice(Model model, HttpServletRequest request) {
	  String notice_category = request.getParameter("notice_category");
      String notice_title = request.getParameter("notice_title");
      String notice_context = request.getParameter("notice_context");
      UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  int user_no = user.getUser_no();
  	  
      Notice notice = new Notice(notice_category, notice_title, notice_context, user_no);
     
	  int result = noticeService.registNewNotice(notice);
			/*
			 * if(result > 0) { System.out.println("게시글 등록에 성공하였습니다."); } else {
			 * System.out.println("게시글 등록에 실패하였습니다."); }
			 */
		 
      return "redirect:/notice/main";
   }
   
   @GetMapping("modify/{notice_no}")
   public String modifyPage(Model model, @PathVariable int notice_no) {
	   Notice notice = noticeService.selectNoticeByNo(notice_no);
	   model.addAttribute("notice", notice);
	   System.out.println(notice);
	  
	   return "notice/modify";
   }
   
   @PostMapping("modify/noticeForm")
   public String modifyNotice(Model model, HttpServletRequest request) {
	   
	   String notice_category = request.getParameter("notice_category");
	   String notice_title = request.getParameter("notice_title");
	   String notice_context = request.getParameter("notice_context");
	   UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   int user_no = user.getUser_no();
	  	  
	   Notice notice = new Notice(notice_category, notice_title, notice_context, user_no);
	     
	   
	   
	   System.out.println(notice);
	   
	   return "redirect:/notice/main";
   }
   
   
}
   
   
  