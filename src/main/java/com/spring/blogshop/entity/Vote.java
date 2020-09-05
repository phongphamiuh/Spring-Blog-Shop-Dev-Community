package com.spring.blogshop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.spring.blogshop.entity.PostBookMark.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table
@RequiredArgsConstructor
public class Vote {
	@Embeddable
    public static class Id implements Serializable {

        /**
		 * 
		 */
		private static final long serialVersionUID = -2835185974676990358L;

		@Column(name = "BLOG_ID")
        protected Long blogId;

        @Column(name = "User_ID")
        protected Long userId;
             
        public Id() {
        }

        public Id(Long blogId, Long userId) {
            this.blogId = blogId;
            this.userId = userId;
        }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((blogId == null) ? 0 : blogId.hashCode());
			result = prime * result + ((userId == null) ? 0 : userId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Id other = (Id) obj;
			if (blogId == null) {
				if (other.blogId != null)
					return false;
			} else if (!blogId.equals(other.blogId))
				return false;
			if (userId == null) {
				if (other.userId != null)
					return false;
			} else if (!userId.equals(other.userId))
				return false;
			return true;
		}		         
    }

    @EmbeddedId
    protected Id id = new Id();

//    @Column(updatable = false)
//    @NotNull
//    protected String addedBy;

//    @Column(updatable = false)
//    @NotNull
//    protected Date addedOn = new Date();

    @ManyToOne
    @JoinColumn(
        name = "BLOG_ID",
        insertable = false, updatable = false)
    protected Blog blog;

    @ManyToOne
    @JoinColumn(
        name = "USER_ID",
        insertable = false, updatable = false)
    protected User user;
    
    @Column
    private int votes;
    
    public Vote(
    		Blog blog,
    		User user) {

        // Set fields
//        this.addedBy = addedByUsername;
        this.blog = blog;
        this.user = user;

        // Set identifier values
        this.id.blogId = blog.getId();
        this.id.userId = user.getId();

        // Guarantee referential integrity if made bidirectional
        blog.getVotes().add(this);
        user.getVotes().add(this);
    }

    public int increaseVote() {
    	this.setVotes(1);
    	return this.getVotes();
    }
    
    public int decreaseVote() {
    	this.setVotes(0);
    	return this.getVotes();
    }
    
	@Override
	public String toString() {
		return "Vote [id=" + id + ", blog=" + blog + ", user=" + user + "]";
	}
    
}
