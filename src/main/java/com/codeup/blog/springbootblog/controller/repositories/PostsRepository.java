package com.codeup.blog.springbootblog.controller.repositories;

import com.codeup.blog.springbootblog.controller.models.Post;
import org.springframework.data.repository.CrudRepository;

//////////////////////////////////////////////////////////////////////////////////
// The post interface extends CRUD (create, read, update, and delete). Therefore,
// is has all its basic methods for querying a database, life findOne, findAll,
// delete, and save. But, I can also create custom queries inside the interface class.
//////////////////////////////////////////////////////////////////////////////////
public interface PostsRepository extends CrudRepository<Post, Long> {
}
