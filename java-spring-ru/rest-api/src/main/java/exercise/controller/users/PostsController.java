package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import java.net.URI;

import exercise.model.Post;
import exercise.Data;

@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/posts/{id}")
    public ResponseEntity<List<Post>> postsListById(@PathVariable int id) {
        var userPosts = posts.stream()
                .filter(p -> p.getUserId() == id)
                .toList();
        return ResponseEntity.ok(userPosts);
    }

    @PostMapping("/users/{id}/posts")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post) {
        post.setUserId(id);
        posts.add(post);
        URI location = URI.create(String.format("/api/users/%d/posts/%d", id, post.getUserId()));
        return ResponseEntity.created(location).body(post);
    }
}

