package com.gbwxy.src.controller;

import com.gbwxy.src.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;

}
