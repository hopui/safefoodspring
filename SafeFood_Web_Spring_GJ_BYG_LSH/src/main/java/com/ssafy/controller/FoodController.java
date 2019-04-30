package com.ssafy.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.model.dto.User;
import com.ssafy.service.FoodService;

@Controller
public class FoodController
{
	private static final Logger logger = LoggerFactory.getLogger(FoodController.class);
	
	@Autowired
	FoodService service;
	
	
	@GetMapping("/search")
	public String allInfo(Model model) {
		model.addAttribute("foods", service.selectAll());
		model.addAttribute("foodlist","전체");
		return "index";
	}
	
	//pathvarable 방식 restful ip
	@GetMapping("/detail/{code}")
	public String getTest(Model model, @PathVariable int code) {
		model.addAttribute("food",service.selectCode(code));
		return "food_detail";
	}
	
		@GetMapping("/detail/{code}/modi")
		public String getDetailMy(Model model, @PathVariable int code) {
			model.addAttribute("action","modi");
			model.addAttribute("food",service.selectCode(code));
			return "food_detail";
		}
	
	@PostMapping("/search")
	public String getSearch(Model model, String sort, String search_text) {
		search_text = search_text.trim();
		
		if(search_text == null || search_text.equals(""))
		{	
			model.addAttribute("foods", service.selectAll());
		}else {
			switch(sort) {
			case "productname":
				model.addAttribute("foods", service.selectName(search_text));
				break;
			case "maker":
				model.addAttribute("foods", service.selectMaker(search_text));
				break;
			case "material":
				model.addAttribute("foods", service.selectMaterial(search_text));
				break;
			}
		}
		model.addAttribute("foodlist","검색");
		return "index";
	}
	
	@GetMapping("/session/myTakenInfo")
	public String doMyTakenInfo(Model model, HttpSession session)
	{
		User user = (User)session.getAttribute("loginUser");
		//model.addAttribute("food",service.deleteMyfood(user.getEmail(), String.valueOf(code)));
		model.addAttribute(service.selectMyfoodAll(user.getEmail()));
		return "session/MyTakenInfo";
	}
	
	@PostMapping("/session/modify")
	public String doInsert(Model model, String eat,int quantity,String delete,
			HttpSession session) {
		User user = (User)session.getAttribute("loginUser");
		if(eat !=null) {
			if(service.insertMyfood(user.getEmail(),eat,quantity)>0)
				model.addAttribute("alarm","섭취 등록 성공했습니다.");
			else
				model.addAttribute("alarm","섭취 등록 실패했습니다.");
			return "redirect:detail/"+eat;
		}
	
		if(service.deleteMyfood(user.getEmail(), delete)>0)
			model.addAttribute("alarm","식품 삭제 성공했습니다.");
		else
			model.addAttribute("alarm","식품 삭제 실패했습니다.");
		return "redirect:session/MyTakenInfo";
	}
	
}
