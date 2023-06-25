package com.example.api.user;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.exception.UserNotFoundException;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserEntity postUser(UserEntity user) {
   	    user.setCreated(new Date());
   	    user.setModified(new Date());
        userRepository.save(user);
        return userRepository.save(user);
    }
    
    public Optional<UserEntity>  getUser(Integer id) {
    	return userRepository.findById(id);
    }

    public Iterable<UserEntity>  getUsers() {
    	return userRepository.findAll();
    }
    
    public UserEntity putUser(UserEntity user) {
    	Optional<UserEntity> originalUser = userRepository.findById(user.getId());
    	if (originalUser.isPresent()) {
    		if(user.getName() != null) {originalUser.get().setName(user.getName());};
    		if(user.getE_mail() != null) {originalUser.get().setE_mail(user.getE_mail());};
    		if(user.getPassword() != null) {originalUser.get().setPassword(user.getPassword());};
    		originalUser.get().setModified(new Date());
    	} else {
    		throw new UserNotFoundException(user.getId().toString());
    	}
        return userRepository.save(originalUser.get());
    }
    
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
