package com.spring.blogshop.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "CATEGORY_ITEM")
@org.hibernate.annotations.Immutable
public class PostBookMark {
	
	@Embeddable
    public static class Id implements Serializable {

        /**
		 * 
		 */
		private static final long serialVersionUID = -2835185974676990358L;

		@Column(name = "BLOG_ID")
        protected Long blogId;

        @Column(name = "BOOKMARK_ID")
        protected Long bookMarkId;


        
        public Id() {
        }

        public Id(Long blogId, Long bookMarkId) {
            this.blogId = blogId;
            this.bookMarkId = bookMarkId;
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
			if (bookMarkId == null) {
				if (other.bookMarkId != null)
					return false;
			} else if (!bookMarkId.equals(other.bookMarkId))
				return false;
			return true;
		}

        @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((blogId == null) ? 0 : blogId.hashCode());
			result = prime * result + ((bookMarkId == null) ? 0 : bookMarkId.hashCode());
			return result;
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
        insertable = false, updatable = false
        )
    protected Blog blog;

    @ManyToOne
    @JoinColumn(
        name = "BOOKMARK_ID",
        insertable = false, updatable = false)
    protected BookMark bookMark;

    
    public PostBookMark(
    		Blog blog,
    		BookMark bookMark) {

        // Set fields
//        this.addedBy = addedByUsername;
        this.blog = blog;
        this.bookMark = bookMark;

        // Set identifier values
        this.id.blogId = blog.getId();
        this.id.bookMarkId = bookMark.getId();

        // Guarantee referential integrity if made bidirectional
        blog.getPostBookMark().add(this);
        bookMark.getPostBookMark().add(this);
    }

	@Override
	public String toString() {
		return "PostBookMark [id=" + id + ", blog=" + blog + ", bookMark=" + bookMark + "]";
	}

}
