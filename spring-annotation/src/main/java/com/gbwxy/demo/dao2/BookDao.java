package com.gbwxy.demo.dao2;

import org.springframework.stereotype.Repository;

//����Ĭ������������ĸСд
@Repository(value = "bookDao2")
public class BookDao {
	
	private String lable = "222";

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	@Override
	public String toString() {
		return "BookDao [lable=" + lable + "]";
	}
	
	
	
	

}
