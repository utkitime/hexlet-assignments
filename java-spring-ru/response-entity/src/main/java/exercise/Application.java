package exercise;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    GET /posts — список всех постов. Должен возвращаться статус 200 и заголовок X-Total-Count, в котором содержится количество постов
//    GET /posts/{id} – просмотр конкретного поста. Если пост найден, должен возвращаться статус 200, если нет — статус 404
//    POST /posts – создание нового поста. Должен возвращаться статус 201
//    PUT /posts/{id} – Обновление поста. Должен возвращаться статус 200. Если пост уже не существует, то должен возвращаться 204

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> postsSize() {
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (post.isEmpty()) {
            System.out.println("Пост с id " + id + " не найден");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Найден пост: " + post.get());
        return ResponseEntity.ok(post.get());
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        posts.add(post); // Добавляем пост в список
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(post.getId())
//                .toUri(); // Формируем URI для созданного ресурса
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(post); // Возвращаем созданный пост со статусом 201 Created и URI созданного ресурса
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post data) {
        var potentialPost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (potentialPost.isPresent()) {
            var post = potentialPost.get();
            post.setId(data.getId());
            post.setTitle(data.getTitle()); // Обновляем заголовок
            post.setBody(data.getBody());   // Обновляем тело
            return ResponseEntity.ok(post); // Возвращаем обновленный пост со статусом 200 OK
        } else {
            return ResponseEntity.noContent().build(); // Возвращаем статус 204 No Content, если пост не найден
        }
    }

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
