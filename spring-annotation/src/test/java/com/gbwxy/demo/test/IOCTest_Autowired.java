package com.gbwxy.demo.test;


import com.gbwxy.demo.config.MainConifgOfAutowired;
import com.gbwxy.demo.dao2.BookDao;
import com.gbwxy.demo.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Autowired {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifgOfAutowired.class);

        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);


        /**
         *
         */
        BookDao bean = applicationContext.getBean(BookDao.class);
        System.out.println(bean);

        bean = (BookDao) applicationContext.getBean("bookDao2");
        System.out.println(bean);

        com.gbwxy.demo.dao.BookDao bean2 = (com.gbwxy.demo.dao.BookDao) applicationContext.getBean("bookDao");
        System.out.println(bean2);


    }

}
