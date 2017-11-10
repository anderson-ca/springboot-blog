package com.codeup.blog.springbootblog.controller.services;

import com.codeup.blog.springbootblog.controller.models.User;
import com.codeup.blog.springbootblog.controller.models.UserWithRoles;
import com.codeup.blog.springbootblog.controller.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailLoader implements UserDetailsService {
    private final UsersRepository repository;

    @Autowired
    public UserDetailLoader(UsersRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByname(username);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The user with username %s cannot be found", username));
        }
        return new UserWithRoles(user);
    }
}
