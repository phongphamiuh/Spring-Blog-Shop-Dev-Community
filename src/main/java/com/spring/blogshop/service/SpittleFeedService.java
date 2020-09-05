package com.spring.blogshop.service;

import com.spring.blogshop.entity.Comment;
import com.spring.blogshop.entity.Spittle;

public interface SpittleFeedService {
	void broadcastSpittle(Spittle spittle);
}
