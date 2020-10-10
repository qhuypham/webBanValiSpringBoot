package com.vali.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vali.DAO.CategoryDAO;
import com.vali.DAO.ProductDAO;
import com.vali.entity.Category;
import com.vali.entity.Product;

@Controller
public class HomeController {

	@Autowired
	CategoryDAO cdao;
	@Autowired
	ProductDAO pdao;
	
	
	@RequestMapping("home/index")
	public String index(Model model) {
		List<Category> list = cdao.getRandoms();
		List<Product> prods = pdao.findBySpecial(4);
		
		model.addAttribute("rands", list);
		model.addAttribute("list",prods);
		return "home/index";
	}
	
	@RequestMapping("home/about")
	public String about() {
		return "home/about";
	}
	@RequestMapping("home/contact")
	public String contact() {
		return "home/contact";
	}
	@RequestMapping("home/feedback")
	public String feedback() {
		return "home/feedback";
	}
	@RequestMapping("home/faq")
	public String faq() {
		return "home/faq";
	}
	@ResponseBody
	@RequestMapping("/home/language")
	public void lang() {}
}
