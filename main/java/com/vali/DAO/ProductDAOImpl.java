package com.vali.DAO;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vali.entity.Product;

@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {
	

	@Autowired
	SessionFactory factory;

	@Override
	public Product findById(Integer id) {
		Session session = factory.getCurrentSession();
		Product entity = session.find(Product.class, id);
		return entity;
	}

	@Override
	public List<Product> findAll() {
		String sql = "FROM Product";
		Session session = factory.getCurrentSession();
		TypedQuery<Product> query = session.createQuery(sql,Product.class);
		List<Product> list = query.getResultList();
		return list;
	}

	@Override
	public Product create(Product entity) {
		Session session = factory.getCurrentSession();
		session.save(entity);
		return entity;
	}

	@Override
	public void update(Product entity) {
		Session session = factory.getCurrentSession();
		session.update(entity);
	}

	@Override
	public Product delete(Integer id) {

		Session session = factory.getCurrentSession();
		Product entity = session.find(Product.class, id);
		session.delete(entity);
		return entity;
	}

	@Override
	public List<Product> findByCategoryId(Integer categoryId) {
		String hql = "FROM Product p WHERE p.category.id =:cid";
		Session session = factory.getCurrentSession();
		TypedQuery <Product> query = session.createQuery(hql,Product.class);
		query.setParameter("cid", categoryId);
		List<Product> list =query.getResultList();
		return list;
	}

	@Override
	public List<Product> findByKeywords(String keywords) {
		String hql = "FROM Product p WHERE p.name LIKE :kw OR p.category.name LIKE :kw OR p.category.nameVN LIKE :kw";
		Session session = factory.getCurrentSession();
		TypedQuery <Product> query = session.createQuery(hql,Product.class);
		query.setParameter("kw", "%"+keywords + "%");
		List<Product> list =query.getResultList();
		return list;
	}

	@Override
	public List<Product> findByIds(String ids) {
		String hql = "FROM Product p WHERE p.id in("+ids+")";
		Session session = factory.getCurrentSession();
		TypedQuery <Product> query = session.createQuery(hql,Product.class);
		List<Product> list =query.getResultList();
		return list;
	}

	@Override
	public List<Product> findBySpecial(Integer id) {
		String hql = "FROM Product p";
		Session session = factory.getCurrentSession();
		TypedQuery <Product> query = session.createQuery(hql,Product.class);
		switch(id) {
		case 0://hang moi
			hql = "FROM Product p ORDER BY p.productDate DESC";			
			break;
		case 1:// ban chay
			hql = "FROM Product p ORDER BY size(p.orderDetails) DESC";
			break;
		case 2:// xem nhieu
			hql = "FROM Product p ORDER BY p.viewCount DESC";
			break;
		case 3://giam gia
			hql = "FROM Product p ORDER BY p.discount DESC";
			break;
		case 4://DAC BIET
			hql = "FROM Product p WHERE p.special=true";
			break;
		}
		query = session.createQuery(hql,Product.class);
		query.setMaxResults(12);
		List<Product> list = query.getResultList();
		return list;
	}

	
}
