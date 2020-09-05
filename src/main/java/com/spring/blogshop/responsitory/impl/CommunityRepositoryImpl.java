package com.spring.blogshop.responsitory.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.blogshop.entity.BlogCommunity;
import com.spring.blogshop.responsitory.CommunityRepository;
@Repository
@Transactional
public class CommunityRepositoryImpl implements CommunityRepository{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public BlogCommunity save(BlogCommunity blogCommunity) {
		if(blogCommunity==null) {
			em.persist(blogCommunity);
		}else {
			em.merge(blogCommunity);
		}
		return blogCommunity;
	}

}
