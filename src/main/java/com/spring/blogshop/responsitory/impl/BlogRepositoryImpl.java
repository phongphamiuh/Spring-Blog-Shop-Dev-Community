package com.spring.blogshop.responsitory.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;


import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.Session;
import com.spring.blogshop.entity.Blog;
import com.spring.blogshop.entity.BookMark;
import com.spring.blogshop.entity.PostBookMark;
import com.spring.blogshop.entity.Vote;
import com.spring.blogshop.responsitory.BlogRepository;
@Repository
@Transactional
public class BlogRepositoryImpl implements BlogRepository{
	private static Logger logger = Logger.getLogger(BlogRepositoryImpl.class);
	
	@PersistenceContext
	EntityManager em;

	
	@Override
    public Blog addBlog(Blog blog) {
        if (blog.getId() == null) {
            logger.info("Inserting new Blog");
            em.persist(blog);
        } else {
            em.merge(blog);
            logger.info("Updating existing blog");
        }
        logger.info("Blog saved with id: " + blog.getId());
        return blog;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> showBlogList() {	
		return em.createQuery("FROM Blog").getResultList();
	}

	
	@Override
	@Transactional(readOnly = true)
	public Blog findByBlogId(Long id) {
		return em.createQuery("SELECT b FROM Blog b JOIN fetch b.user u WHERE b.id=:id",Blog.class)
				.setParameter("id", id).getSingleResult();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Blog findById1(Long id) {
		return em.createQuery("SELECT b FROM Blog b INNER JOIN FETCH b.user u WHERE u.id=:id",Blog.class)
				.setParameter("id", id).getSingleResult();
	}
	
	

	@Override
	public void deleteById(Blog blog) {
		Blog mergedSinger = em.merge(blog);
		em.remove(mergedSinger);
		logger.info("Blog with id: " + blog.getId() + " deleted successfully");
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Blog> blogPost(Long id) {
		return em.createQuery("SELECT b FROM Blog b INNER JOIN b.user u WHERE u.id=:id")
				.setParameter("id", id).getResultList();
	}

	@Override
	public BookMark bookMark(BookMark bookMark) {
		if (bookMark.getId() == null) {
            logger.info("Inserting new Blog");
            em.persist(bookMark);
        } else {
            em.merge(bookMark);
            logger.info("Updating existing blog");
        }
        logger.info("Blog saved with id: " + bookMark.getId());
        return bookMark;
	}

	@Override
	public BookMark findByIdBookMark(Long id) {
		return em.createQuery("SELECT b FROM BookMark b JOIN fetch b.user u WHERE b.id=:id",BookMark.class)
				.setParameter("id", id).getSingleResult();
	}

	@Override
	public PostBookMark bookMarkPost(PostBookMark postBookMark) {
		em.persist(postBookMark);
        return postBookMark;
	}
	
	@Override
	public Vote vote(Vote vote) {
		if(vote.getBlog().getId()==null) {
			em.persist(vote);
		}else {
			em.merge(vote);
		}	
        return vote;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<PostBookMark> showPostBookMark(Long id) {
		return em.createQuery("SELECT p FROM PostBookMark p INNER JOIN p.bookMark b WHERE b.id=:id")
					.setParameter("id", id).getResultList();
	}

	@Override
	public List<Vote> findByUsernameBlog(Long id) {
		return em.createQuery("SELECT v FROM Vote v INNER JOIN v.blog b  "
				+ "WHERE b.id=:id ",Vote.class)
					.setParameter("id", id)		
					.getResultList();
	}

	
	
	

}
