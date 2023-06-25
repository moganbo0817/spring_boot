package com.example.api.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
    private UserService userService;

    @PostMapping(path="/") // ONLY POST Requests
    public @ResponseBody UserEntity addNewUser (@RequestBody UserEntity user) {
	    return userService.postUser(user);
    }
	
	@GetMapping(path="/")
	public @ResponseBody Iterable<UserEntity> getAllUsers() {
	    // This returns a JSON or XML with the users
	    return userService.getUsers();
    }
	
	@GetMapping(path="/{id}")
	public @ResponseBody Optional<UserEntity> getUser(@PathVariable("id") Integer id) {
	    // This returns a JSON or XML with the users
	    // ToDo nullの時の処理
        return userService.getUser(id);
    }
	
    @PutMapping(path="/{id}") // ONLY PUT Requests
    public @ResponseBody UserEntity updateUser (@PathVariable("id") Integer id, @RequestBody UserEntity user) {
        user.setId(id);
	    return userService.putUser(user);
    }
    
    @DeleteMapping(path="/{id}") // ONLY PUT Requests
    public @ResponseBody void deleteUser (@PathVariable("id") Integer id) {
	    userService.deleteUser(id);
    }
}
