package com.spring.blogshop.responsitory.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.blogshop.entity.User;
import com.spring.blogshop.responsitory.UserRepository;
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		Query query=em.createQuery("FROM User WHERE username=:username")
				.setParameter("username", username);
		return (User)query.getSingleResult();
	}
	
	@Override
	public User save(User user) {
		if(user.getId()==null) {
			em.persist(user);
		}else {
			em.merge(user);
		}
		return user;
	}

}
