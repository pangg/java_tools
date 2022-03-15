package com.xxx.spring.iocBookShop;

import com.xxx.spring.iocBookShop.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BookShopApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/bookService/applicationContext-*.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.purchase();
    }
}
