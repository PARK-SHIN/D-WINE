package com.project.dwine.notice.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

   @GetMapping("{noticeNo}")
   public String selectProductByNo(@PathVariable int notice_no, Model model) {
      Notice notice = noticeService.selectNoticeByNo(notice_no);
      model.addAttribute("Notice", notice);
      
      return "notice/detail";
   }
   
   
   @GetMapping("/regist")
   public void registPage(Model model) {
		/*
		 * UserImpl user =
		 * (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal
		 * (); int user_no = user.getUser_no(); System.out.println("이거맞는지확안 : " +
		 * user_no);
		 * 
		 * mv.addObject("user_no", user_no); mv.setViewName("notice/regist");
		 * 
		 * return mv;
		 */
	   
	   UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       int user_no = user.getUser_no();
       System.out.println("ㅇㅇ" + user_no);
       model.addAttribute("user_no", user_no);
   }
  
  
   //@RequestMapping(value = "/regist", method = {RequestMethod.GET, RequestMethod.POST})
   @PostMapping("/regist")
   public String registNotice(Model model, HttpServletRequest request) {
      int notice_category = Integer.parseInt(request.getParameter("notice_category"));
      String notice_title = request.getParameter("notice_title");
      String notice_context = request.getParameter("notice_context");
      UserImpl user = (UserImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  int user_no = user.getUser_no();
      
	  System.out.println(notice_category);
	  System.out.println(notice_title);
	  System.out.println(notice_context);
	  
      //Notice notice = new Notice(notice_category, notice_title, notice_context, user_no);
      
		/*
		 * int result = noticeService.registNewNotice(notice_category, notice_title,
		 * notice_context, user_no); if(result > 0) {
		 * System.out.println("게시글 등록에 성공하였습니다."); } else {
		 * System.out.println("게시글 등록에 실패하였습니다."); }
		 */
      
      return "redirect:/notice/list";
   }

}