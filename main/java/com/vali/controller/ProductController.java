package com.vali.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vali.DAO.ProductDAO;
import com.vali.bean.MailInfo;
import com.vali.entity.Product;
import com.vali.service.CookieService;
import com.vali.service.MailService;

@Controller
public class ProductController {

	
	@Autowired
	ProductDAO pdao;
	
	@Autowired
	CookieService cookie;
	
	@Autowired
	MailService mail;
	
	@RequestMapping("/product/list-by-category/{cid}")
	public String listByCategory(Model model,
			@PathVariable("cid") Integer categoryId) {
		List<Product> list = pdao.findByCategoryId(categoryId);
		model.addAttribute("list",list);
		return "product/list";
	}
	
	
	@RequestMapping("/product/list-by-keyword")
	public String listByKeywords(Model model,
			@RequestParam("keywords") String keywords) {
		List<Product> list = pdao.findByKeywords(keywords);
		model.addAttribute("list",list);
		return "product/list";
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model,
			@PathVariable("id") Integer id) {
		
		//Chi tiet san pham
		Product p = pdao.findById(id);
		model.addAttribute("prod",p);
		
		//Tang viewCount +1
		p.setViewCount(p.getViewCount()+1);
		pdao.update(p);
		
		//Hang Cung Loai
		List<Product> list = pdao.findByCategoryId(p.getCategory().getId());
		model.addAttribute("list",list);
		
		//Hang favorite
		Cookie favo = cookie.read("favo");
		if(favo!=null) {
		
			String ids = favo.getValue();
			if(ids!=null) {
				List<Product> list_favo = pdao.findByIds(ids);
				model.addAttribute("favo",list_favo);
			}
			
		}
		
		
		// Hang da xem
		Cookie viewed = cookie.read("viewed");
		String value = id.toString();
		if(viewed!=null) {
			value = viewed.getValue();
			value += "," + id.toString();
		}
		cookie.create("viewed", value, 10);
		List<Product> list_viewed = pdao.findByIds(value);
		model.addAttribute("viewed", list_viewed);
		
		return "product/detail";
	}
	
	@ResponseBody
	@RequestMapping("/product/add-to-favo/{id}")
	public boolean addToFavorite(Model model, @PathVariable("id") Integer id) {
		Cookie favo = cookie.read("favo");
		String value = id.toString();
		if(favo!=null) {
			value =favo.getValue();
			if(!value.contains(id.toString())) {
				value += "," + id.toString();
			}
			else {
				return false;
			}
		}
		favo = cookie.create("favo", value , 30);
		return true;
	}
	
	@RequestMapping("/product/list-by-special/{id}")
	public String listBySpecial(Model model,
			@PathVariable("id") Integer id) {
		List<Product> list = pdao.findBySpecial(id);
		model.addAttribute("list",list);
		return "product/list";
	}
	
	@ResponseBody
	@RequestMapping("/product/send-email")
	public boolean sendEmail(Model model, MailInfo info, HttpServletRequest req, HttpServletResponse res) {
		//gui mail
		info.setSubject("Thong tin hang hoa");
		info.setFrom("validep99@gmail.com");
		try {
			String id = req.getParameter("id");
			String link = req.getRequestURL().toString().replace("send-email", "detail/"+id);
			info.setBody("Hang hoa dep.Xem chi tiet tai:"+link);
			mail.send(info);
			return true;
		} catch (MessagingException e) {
			return false;
		}
	}
}
