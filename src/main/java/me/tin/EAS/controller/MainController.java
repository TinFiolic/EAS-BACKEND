package me.tin.EAS.controller;

import me.tin.EAS.model.Post;
import me.tin.EAS.model.Posts;
import me.tin.EAS.model.User;
import me.tin.EAS.model.dto.LoginDTO;
import me.tin.EAS.model.dto.RegisterDTO;
import me.tin.EAS.model.dto.UploadDTO;
import me.tin.EAS.service.PostService;
import me.tin.EAS.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MainController {
    public UserService userService;
    public PostService postService;

    public MainController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping (value = "/login")
    public ResponseEntity<User> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO.username, loginDTO.password);

        if(user == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        else
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping (value = "/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO) {
        User user = userService.register(registerDTO.username, registerDTO.password, registerDTO.authLevel);

        if(user == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        else
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping (value = "/all-posts")
    public ResponseEntity<Posts> getPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @PostMapping (value = "/new-post")
    public ResponseEntity<Post> createPost(@RequestParam String title,
                                           @RequestParam String description,
                                           @RequestParam String squareId,
                                           @RequestParam MultipartFile image) throws IOException {
        Post post = postService.createNewPost(title, description, Integer.parseInt(squareId),
                image.getBytes());

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping (value = "/get-post")
    public ResponseEntity<Post> getPost(@RequestParam String id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @PostMapping (value = "/delete")
    public void deletePost(@RequestParam String id) {
        postService.deletePost(Integer.parseInt(id));
    }

    @PostMapping (value = "/edit-post")
    public ResponseEntity<Post> editPost(@RequestBody String title, @RequestBody String description,
                                           @RequestBody int squareId, @RequestBody MultipartFile image,
                                           @RequestBody String id) throws IOException {
        Post post = postService.editPost(title, description, squareId,
                image.getBytes(), id);

        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
