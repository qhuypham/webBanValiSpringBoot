package com.vali.DAO;

import java.util.List;

import com.vali.entity.Customer;
import com.vali.entity.Order;
import com.vali.entity.OrderDetail;
import com.vali.entity.Product;

public interface OrderDAO {

	Order findById(Integer id);
	List<Order> findAll();
	Order create(Order entity);
	void update(Order entity);
	Order delete(Integer id);
	void create(Order order, List<OrderDetail> details);
	List<Order> findByUser(Customer user);
	List<Product> findItemsByUser(Customer user);
}
