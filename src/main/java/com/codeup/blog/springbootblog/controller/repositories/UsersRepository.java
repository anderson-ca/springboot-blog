package com.codeup.blog.springbootblog.controller.repositories;
import org.springframework.data.repository.CrudRepository;
import com.codeup.blog.springbootblog.controller.models.User;

public interface UsersRepository extends CrudRepository<User, Long> {
    public User findByname(String name);
}
