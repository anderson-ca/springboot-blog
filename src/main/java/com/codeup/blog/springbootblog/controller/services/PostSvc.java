package com.codeup.blog.springbootblog.controller.services;
import com.codeup.blog.springbootblog.controller.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("PostSvc")
public class PostSvc {

    public PostSvc() {
        createDummy();
    }

    public Post save(Post post) {
        post.setId((long) (posts.size() + 1));

        posts.add(post);

        return post;
    }

    ArrayList<Post> posts = new ArrayList<>();

    public List<Post> findAll() {

        return posts;

    }

    public Post findOnePost(Long id) {

        return posts.get((int) (id - 1));

    }

    private void createDummy() {
        this.save(new Post("Example 1", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci atque commodi eligendi necessitatibus voluptates. At distinctio dolores minus molestiae mollitia nemo sapiente ut veniam voluptates! Corporis distinctio error quaerat vel!"));

        this.save(new Post( "Example 2", "QWE Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci atque commodi eligendi necessitatibus voluptates. At distinctio dolores minus molestiae mollitia nemo sapiente ut veniam voluptates! Corporis distinctio error quaerat vel!"));

        this.save(new Post("Example 3", "ASD Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci atque commodi eligendi necessitatibus voluptates. At distinctio dolores minus molestiae mollitia nemo sapiente ut veniam voluptates! Corporis distinctio error quaerat vel!"));

    }

}