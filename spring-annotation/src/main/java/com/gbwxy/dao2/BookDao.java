package com.gbwxy.dao2;

import org.springframework.stereotype.Repository;

//名字默认是类名首字母小写
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
