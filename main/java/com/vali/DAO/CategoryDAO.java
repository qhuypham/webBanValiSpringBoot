package com.vali.DAO;

import java.util.List;

import com.vali.entity.Category;

public interface CategoryDAO {

	Category findById(Integer id);
	List<Category> findAll();
	Category create(Category entity);
	void update(Category entity);
	Category delete(Integer id);
	long getPageCount(int pageSize);
	List<Category> getPageCount(int pageNo, int pageSize);
	List<Category> getRandoms();
}
