package com.project.dwine.notice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	
	
	@GetMapping("main")
	public ModelAndView findMenuList(ModelAndView mv){

		List<Notice> noticeList = noticeService.findAllNotice();
		mv.addObject("noticeList" , noticeList);
		mv.setViewName("notice/main");

	return mv;
	}
	
	

}
