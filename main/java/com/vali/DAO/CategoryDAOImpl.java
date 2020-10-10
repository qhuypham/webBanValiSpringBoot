package com.vali.DAO;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vali.entity.Category;
import com.vali.entity.Product;

@Transactional
@Repository
public class CategoryDAOImpl implements CategoryDAO {
	

	@Autowired
	SessionFactory factory;

	@Override
	public Category findById(Integer id) {
		Session session = factory.getCurrentSession();
		Category entity = session.find(Category.class, id);
		return entity;
	}

	@Override
	public List<Category> findAll() {
		String hql = "FROM Category";
		Session session = factory.getCurrentSession();
		TypedQuery<Category> query = session.createQuery(hql,Category.class);
		List<Category> list = query.getResultList();
		return list;
	}

	@Override
	public Category create(Category entity) {
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(Category entity) {
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public Category delete(Integer id) {

		Session session = factory.getCurrentSession();
		Category entity = session.find(Category.class, id);
		session.delete(entity);
		return entity;
	}

	@Override
	public long getPageCount(int pageSize) {
		String hql = "SELECT count(c) FROM Category c";
		Session session = factory.getCurrentSession();
		TypedQuery<Long> query = session.createQuery(hql,Long.class);
		long rowCount = query.getSingleResult();
		long pageCount = (long)Math.ceil(1.0*rowCount/pageSize);
		return pageCount;
	}

	@Override
	public List<Category> getPageCount(int pageNo, int pageSize) {
		String hql = "FROM Category";
		Session session = factory.getCurrentSession();
		TypedQuery<Category> query = session.createQuery(hql,Category.class);
		query.setFirstResult(pageNo*pageSize);
		query.setMaxResults(pageSize);
		List<Category> list = query.getResultList();
		return list;
	}

	@Override
	public List<Category> getRandoms() {
		String hql = "FROM Category c WHERE size(c.products) >=4";
		Session session = factory.getCurrentSession();
		TypedQuery<Category> query = session.createQuery(hql,Category.class);
		List<Category> list = query.getResultList();
		Collections.shuffle(list);
		list = list.subList(0, 4);
		list.forEach(c->{
			List<Product> prods = c.getProducts();
			Collections.shuffle(prods);
			c.setProducts(prods.subList(0, 4));
		});
		return list;
	}

	
}
