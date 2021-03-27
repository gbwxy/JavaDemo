package com.gbwxy.demo.service;


import com.gbwxy.demo.dao.BookDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class BookService {

    //@Qualifier("bookDao")
    //@Autowired(required=false)
    //@Resource(name="bookDao2")
    //@Inject

    // @Autowired
    //@Resource(name = "bookDao") //默认是根据name 找
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
