package com.gbwxy.service;


import com.gbwxy.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class BookService {

    //@Qualifier("bookDao")
    //@Autowired(required=false)
    //@Resource(name="bookDao2")
    //@Inject

    // @Autowired
    //@Resource(name = "bookDao") //Ĭ���Ǹ���name ��
    @Resource
    private BookDao bookDao;

    public void print() {
        System.out.println(bookDao);
    }

    @Override
    public String toString() {
        return "BookService [bookDao=" + bookDao + "]";
    }


}
