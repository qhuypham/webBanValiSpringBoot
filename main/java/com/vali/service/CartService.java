package com.vali.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.vali.DAO.ProductDAO;
import com.vali.entity.Product;

@SessionScope
@Service
public class CartService {
	@Autowired
	ProductDAO dao;

	Map<Integer, Product> map = new HashMap<Integer, Product>();
	
	public void add(Integer id) {
		Product p = map.get(id);
		Product prod = dao.findById(id);
		if(p!=null ) {
			if(prod.getQuantity()>p.getQuantity()) {
				p.setQuantity(p.getQuantity() +1);
			}
			
		}else {
			if(prod.getQuantity()>0) {
				p = dao.findById(id);
				p.setQuantity(1);
				map.put(id, p);
			}
			
		}
	}
	
	public void remove(Integer id) {
		map.remove(id);
	}

	public void update(Integer id, int quantity) {
		Product p = map.get(id);
		p.setQuantity(quantity);
	}
	public void clear() {
		map.clear();
	}
	public int getCount() {
		Collection <Product> ps = this.getItems();
		int count =0;
		for (Product p : ps) {
			count+=p.getQuantity();
		}
		return count;
	}
	public double getAmount() {
		Collection <Product> ps = this.getItems();
		double amount =0;
		for (Product p : ps) {
			amount+=p.getQuantity()*p.getUnitPrice()*(1-p.getDiscount());
		}
		return amount;
	}
	public Collection<Product> getItems(){
		return map.values();
	}

	public Collection<Product> getProds(){
		Collection <Product> ps = this.getItems();
		Collection<Product> prods = new ArrayList<Product>();
		for (Product p : ps) {
			prods.add(dao.findById(p.getId()));
		}
		return prods;
	}
}
