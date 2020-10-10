package com.vali.controller;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vali.DAO.CustomerDAO;
import com.vali.bean.MailInfo;
import com.vali.entity.Customer;
import com.vali.service.CookieService;
import com.vali.service.MailService;

@Controller
public class AccountController {

	@Autowired
	CustomerDAO dao;
	@Autowired
	HttpSession session;
	@Autowired
	CookieService cookie;
	@Autowired
	ServletContext app;
	
	@Autowired
	MailService mailer;
	@Autowired 
	HttpServletRequest request;
	
	@GetMapping("/account/login")
	public String login(Model model) {
		Cookie ckid = cookie.read("userid");
		Cookie ckpwd = cookie.read("pass");
		
		if(ckid !=null) {
		
			String uid = ckid.getValue();
			String pwd = ckpwd.getValue();
			
			model.addAttribute("uid",uid);
			model.addAttribute("pwd",pwd);
		}
		
		return "account/login";
	}
	
	@PostMapping("/account/login")
	public String login(Model model,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam(value="rm", defaultValue = "false") boolean rm) {
		
		Customer user = dao.findById(id);
		if(user == null) {
			model.addAttribute("message","Invalid username!");
			
		}else if(!pw.equals(user.getPassword())){
			model.addAttribute("message","Invalid password!");
		}else if(!user.getActivated()) {
			model.addAttribute("message","Your account is Inactivated!");
		}else {
			//thanh cong
			model.addAttribute("message","login success!");
			session.setAttribute("user", user);
			
			//ghi nho tai khoan bang cookie
			if(rm) {
				cookie.create("userid", user.getId(), 30);
				cookie.create("pass", user.getPassword(), 30);
				
			}else {
				cookie.delete("userid");
				cookie.delete("pass");
			}
			//Quay lai trang duoc bao ve (neu co)
			String backUrl = (String) session.getAttribute("back-url");
			if(backUrl!=null) {
				return "redirect:" +backUrl;
			}
		}
		return "redirect:/home/index";
	}
	
	@RequestMapping("/account/logout")
	public String logout(Model model) {
		session.removeAttribute("user");
		return "redirect:/home/index";
	}


	@GetMapping("/account/edit")
	public String edit(Model model) {
		Customer user = (Customer)session.getAttribute("user");
		model.addAttribute("form", user);
		return "account/edit";
	}
	@PostMapping("/account/edit")
	public String edit(Model model,
			@ModelAttribute("form") Customer user,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException, MessagingException {
		if(!file.isEmpty()) {
			String dir = app.getRealPath("/static/images/customers");
			File f = new File(dir,file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
		}
		dao.update(user);
		session.setAttribute("user", user);
		model.addAttribute("message", "Update succesful");
		return "account/edit";
	}
			
	
	@GetMapping("/account/activate/{id}")
	public String activate(Model model, @PathVariable("id") String id) {
		Customer user = dao.findById(id);
		user.setActivated(true);
		dao.update(user); 
		
		return "redirect:/account/login";
	}

	@GetMapping("/account/forgot")
	public String forgot(Model model) {
		return "account/forgot";
	}
	
	@PostMapping("/account/forgot")
	public String forgot(Model model,
			@RequestParam("id") String id,
			@RequestParam("email") String email
			) throws MessagingException {
		
		Customer user = dao.findById(id);
		if(user==null) {
			model.addAttribute("message","Invalid username");
	
		}
		else if(!email.equals(user.getEmail())) {
			model.addAttribute("message","Invalid email address");
		}
		else {
			//gui mail kich hoat
			String from = "validep99@gmail.com";
			String to = user.getEmail();
			String subject = "Forgot password";
			String body = "Your password is " + user.getPassword();
			MailInfo mail = new MailInfo(from, to, subject, body);
			mailer.send(mail);
			model.addAttribute("message", "Your password was send to your email");
		}
		return "redirect:account/login";
	}
	
	@GetMapping("/account/change")
	public String change(Model model) {
		return "account/change";
	}
	
	@PostMapping("/account/change")
	public String change(Model model,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("pw1") String pw1,
			@RequestParam("pw2") String pw2
			) {
		
		if(!pw1.equals(pw2)) {
			model.addAttribute("message", "Confirm password is not macth!");
		}else {
			Customer user = dao.findById(id);
			if(user==null) {
				model.addAttribute("message","Invalid username");
			}
			else if(!pw.equals(user.getPassword())) {
				model.addAttribute("message","Invalid password");
			}
			else {
				user.setPassword(pw1);
				dao.update(user);
				model.addAttribute("message", "Password was changed successfully");
			}
		}
		
		return "redirect:account/login";
	}

	@GetMapping("/account/register")
	public String register(Model model) {
		Customer user = new Customer();
		model.addAttribute("form", user);
		return "account/register";
	}
	@PostMapping("/account/register")
	public String register(Model model,
			@Validated @ModelAttribute("form") Customer user,BindingResult erros,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException, MessagingException {
		if(erros.hasErrors()) {
			model.addAttribute("message", "Please fix some following erros");
			return "account/register";
		}else {
			Customer user2 = dao.findById(user.getId());
			if(user2 !=null) {
				model.addAttribute("message", "Username is in used");
				return "account/register";
			}
		}
		
		if(file.isEmpty()) {
			user.setPhoto("user.png");
		}else {
			String dir = app.getRealPath("/static/images/customers");
			File f = new File(dir,file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
		}
		user.setActivated(false);
		user.setAdmin(false);
		
		
		dao.create(user);
		model.addAttribute("message", "Register succesful");
		//gui mail kich hoat
		String from = "validep99@gmail.com";
		String to = user.getEmail();
		String subject = "Please Activate You Account";
		String url = request.getRequestURI().toString().replace("register", "activate/"+user.getId());
		String body = "Click "+url+" to Activated";
		MailInfo mail = new MailInfo(from, to, subject, body);
		mailer.send(mail);
		return "account/register";
	}
}
