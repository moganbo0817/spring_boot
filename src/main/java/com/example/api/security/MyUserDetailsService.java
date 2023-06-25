package com.example.api.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
	
    private final PersonRepository repository;

    public MyUserDetailsService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Person person = repository.findByUsername(username).orElseThrow(
                    () -> new UsernameNotFoundException("User not found"));
            // パスワードはエンコードしないとエラーになる、めんどい
            return new User(person.getUsername(), PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(person.getPassword()), new ArrayList<>());
        }catch (Exception e) {
            throw new UsernameNotFoundException("ユーザーが見つかりません");
        }
    }
}
