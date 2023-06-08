package com.example.accessingdatamysql;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class MainController {
	
	@Autowired
	private BookRepository bookRepository;

   @PostMapping(path="/add") // ONLY POST Requests
   public @ResponseBody String addNewBook (@RequestBody Book book) {

     Book n = new Book();
     n.setId(book.getId());
     n.setTitle(book.getTitle());
     n.setStatus("open");
     n.setCreated(new Date());
     n.setModified(new Date());
     bookRepository.save(n);
     return "Saved";
    }
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Book> getAllBooks() {
	  // This returns a JSON or XML with the users
	  return bookRepository.findAll();
	}

}
