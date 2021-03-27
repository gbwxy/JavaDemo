package com.gbwxy.test;


import com.gbwxy.config.MainConifgOfAutowired;
import com.gbwxy.dao2.BookDao;
import com.gbwxy.service.BookService;
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

        com.gbwxy.dao.BookDao bean2 = (com.gbwxy.dao.BookDao) applicationContext.getBean("bookDao");
        System.out.println(bean2);


    }

}
