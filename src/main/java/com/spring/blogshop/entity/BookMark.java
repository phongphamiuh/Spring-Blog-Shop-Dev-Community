package com.spring.blogshop.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table
@RequiredArgsConstructor
public class BookMark {
	
	@Id
	@Column
	private Long id;
	
	@OneToOne(
			fetch = FetchType.EAGER,
			optional = false
			)
	@JoinColumn(unique = true,name="USER_ID") // Defaults to SHIPPINGADDRESS_ID
	@PrimaryKeyJoinColumn
	protected User user;
	
	public BookMark(Long id) {
		super();
		this.id = id;
	}
	
//	@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
//    @JoinTable(
//        name = "BOOKMARK_BLOG",
//        joinColumns = @JoinColumn(name = "BOOKMARK_ID"),
//        inverseJoinColumns = @JoinColumn(name = "BLOG_ID")
//    )
//    protected List<Blog> blogs = new ArrayList<Blog>();


	@OneToMany(mappedBy="bookMark",fetch = FetchType.EAGER)
	private List<PostBookMark> postBookMark=new ArrayList<PostBookMark>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookMark other = (BookMark) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "BookMark [id=" + id + "]";
	}
	
}
