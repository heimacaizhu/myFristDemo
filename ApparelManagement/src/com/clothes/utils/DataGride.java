package com.clothes.utils;

import java.io.Serializable;
import java.util.List;

/*
 *作者：杨聂；    
 *邮箱：1024938222@qq.com；      
 *时间：2016年11月11日上午10:51:39；      
 */
public class DataGride implements Serializable {

	private static final long serialVersionUID = 3371581709305246355L;

    private Long total;
    private int pages;
    private List<?> rows;
    
    
    
	public DataGride() {

	}
	
	public DataGride(Long total,int pages ,List<?> rows) {
		this.total = total;
		this.rows = rows;
		this.pages = pages;
	}

	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}
	
    
	


}
