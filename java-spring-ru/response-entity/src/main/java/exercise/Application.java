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


//    @GetMapping("/pages")
//    public ResponseEntity<List<Page>> index(@RequestParam(defaultValue = "10") Integer limit) {
//        var result = pages.stream().limit(limit).toList();
//
//        return ResponseEntity.ok()
//                .header("X-Total-Count", String.valueOf(pages.size()))
//                .body(result);
//    }

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

    //    @PostMapping("/pages") // Создание страницы
//    public Page create(@RequestBody Page page) {
//        pages.add(page);
//        return page;
//    }
    @PostMapping("/pages")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.created().body(post);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post data) {
        var potentialPost = posts.stream()
                .filter(p -> p.getSlug().equals(id))
                .findFirst();
        if (potentialPost.isPresent()) {
            var post = potentialPost.get();
            post.setId(data.getId());
            post.setTitle(data.getTitle());
            post.setBody(data.getBody());
        } else {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(post.get);
    }


//    @PutMapping("/pages/{id}") // Обновление страницы
//    public Page update(@PathVariable String id, @RequestBody Page data) {
//        var maybePage = pages.stream()
//                .filter(p -> p.getSlug().equals(id))
//                .findFirst();
//        if (maybePage.isPresent()) {
//            var page = maybePage.get();
//            page.setSlug(data.getSlug());
//            page.setName(data.getName());
//            page.setBody(data.getBody());
//        }
//        return data;
//    }

//    @GetMapping("/pages/{id}")
//    public ResponseEntity<Page> show(@PathVariable String id) {
//        var page = pages.stream()
//                .filter(p -> p.getId().equals(id))
//                .findFirst();
//        return ResponseEntity.of(page);
//    }

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
