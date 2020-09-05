package com.spring.blogshop.util;

import java.util.List;

import com.spring.blogshop.entity.Book;

public class BookGrid {
	private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<Book> singerData;
    public BookGrid() {
		// TODO Auto-generated constructor stub
	}
    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

	public List<Book> getSingerData() {
		return singerData;
	}

	public void setSingerData(List<Book> singerData) {
		this.singerData = singerData;
	}
	@Override
	public String toString() {
		return "BookGrid [totalPages=" + totalPages + ", currentPage=" + currentPage + ", totalRecords=" + totalRecords
				+ ", singerData=" + singerData + "]";
	}
	
    
}
