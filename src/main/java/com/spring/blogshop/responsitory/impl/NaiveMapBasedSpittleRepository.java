package com.spring.blogshop.responsitory.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.naming.NoInitialContextException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.blogshop.entity.Comment;
import com.spring.blogshop.entity.NotificationMessage;
import com.spring.blogshop.entity.Spittle;
import com.spring.blogshop.responsitory.SpittleRepository;

import net.sf.ehcache.CacheOperationOutcomes;

@Repository
@Transactional
public class NaiveMapBasedSpittleRepository implements SpittleRepository{

//	private AtomicLong nextId = new AtomicLong(1L);
//	
//	private Map<Long, Comment> comments = new HashMap<Long, Comment>();
//	
//	@Override
//	public Comment save(Comment comment) {
//		try {
//			Field idField = Comment.class.getField("id");
//			idField.setAccessible(true);
//			long id = nextId.incrementAndGet();
//			idField.set(comment, id);
//			comments.put(id, comment);
//		} catch (Exception e) {
//			// don't worry about it.
//		}
//		
//		return comment;
//	}
//	
//	@Override
//	public Comment findOne(Long id) {
//		return comments.get(id);
//	}
	
	@PersistenceContext
	EntityManager em;

	@Override
	public Comment save(Comment comment) {
		em.persist(comment);
		return comment;
	}
		
	@Override
	public Comment findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Comment> findByIdBlog(Long id) {
		return em.createQuery("SELECT c FROM Comment c INNER JOIN c.blog b WHERE b.id=:id")
					.setParameter("id",id)
					.getResultList();
				
	}

	@Override
	public NotificationMessage saveNotification(NotificationMessage notification) {
		em.persist(notification);
		return notification;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationMessage> findByIdUser(String userNotification) {
		return em.createQuery("SELECT c FROM NotificationMessage c INNER JOIN FETCH c.user u WHERE c.userNotification=:userNotification")
				.setParameter("userNotification", userNotification)
				.getResultList();
	}
	
}
