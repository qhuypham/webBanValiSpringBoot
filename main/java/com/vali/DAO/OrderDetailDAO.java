package com.vali.DAO;

import java.util.List;

import com.vali.entity.Order;
import com.vali.entity.OrderDetail;

public interface OrderDetailDAO {

	OrderDetail findById(Integer id);
	List<OrderDetail> findAll();
	OrderDetail create(OrderDetail entity);
	void update(OrderDetail entity);
	OrderDetail delete(Integer id);
	List<OrderDetail> findByOrder(Order order);
}
