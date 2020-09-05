package com.spring.blogshop.responsitory;

import com.spring.blogshop.entity.User;

public interface UserRepository {
	User findByUsername(String username);
	User save(User user);
}
