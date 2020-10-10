package com.vali.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.vali.DAO.OrderDAO;
import com.vali.DAO.OrderDetailDAO;
import com.vali.DAO.ProductDAO;
import com.vali.entity.Customer;
import com.vali.entity.Order;
import com.vali.entity.OrderDetail;
import com.vali.entity.Product;
import com.vali.service.CartService;

@Controller
public class OrderController {

	@Autowired
	HttpSession session;
	
	@Autowired
	CartService cart;
	
	@Autowired
	OrderDAO dao;
	
	@Autowired
	OrderDetailDAO ddao;
	
	@Autowired
	ProductDAO pdao;
	
	@GetMapping("/order/checkout")
	public String showForm(@ModelAttribute("order") Order order) {
		Customer user = (Customer)session.getAttribute("user");
		order.setOrderDate(new Date());
		order.setCustomer(user);
		order.setAmount(cart.getAmount());
		return "order/checkout";
	}
	@PostMapping("/order/checkout")
	public String purchase(Model model,@ModelAttribute("order") Order order) {
		Collection<Product> list=cart.getItems();
		List<OrderDetail> details = new ArrayList<OrderDetail>();
		for(Product p : list) {
			OrderDetail detail = new OrderDetail();
			detail.setProduct(p);
			detail.setUnitPrice(p.getUnitPrice());
			detail.setQuantity(p.getQuantity());
			detail.setDiscount(p.getDiscount());
			details.add(detail);
			Product p1 = pdao.findById(p.getId());
			p1.setQuantity(p1.getQuantity()-p.getQuantity());
			pdao.update(p1);
		}
		dao.create(order,details);
		cart.clear();
		return "redirect:/order/list";
	}
	
	@GetMapping("/order/list")
	public String list(Model model) {
		Customer user = (Customer) session.getAttribute("user");
		List<Order> orders = dao.findByUser(user);
		model.addAttribute("orders",orders);
		return "order/list";
	}
	
	@GetMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Order order = dao.findById(id);
		List<OrderDetail> details = ddao.findByOrder(order);
		model.addAttribute("order",order);
		model.addAttribute("details",details);
		return "order/detail";
	}
	
	@GetMapping("/order/items")
	public String items(Model model) {
		Customer user = (Customer) session.getAttribute("user");
		List<Product> list = dao.findItemsByUser(user);
		model.addAttribute("list",list);
		
		return "product/list";
	}
	
}
