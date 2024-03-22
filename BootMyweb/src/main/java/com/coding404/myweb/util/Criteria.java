package com.coding404.myweb.util;

import lombok.Data;


@Data
public class Criteria {
	
	private int page; //페이지번호
	private int amount; //데이터개수	
	
	public Criteria() {
		this.page = 1;
		this.amount = 5;
	}

	public Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}
	
	//sql에 전달될 페이지 start getter
	public int getPageStart() {
		return (page - 1) * amount;
	}
	

}
