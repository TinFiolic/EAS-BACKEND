package me.tin.EAS.service;

import me.tin.EAS.model.Post;
import me.tin.EAS.model.Posts;
import me.tin.EAS.util.Utility;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Override
    public Posts getAllPosts() {
        return Utility.loadFromJsonFile("posts.json", Posts.class);
    }

    @Override
    public Post getPost(String id){
        Posts posts = new Posts();

        Posts fetchedPosts = Utility.loadFromJsonFile("posts.json", Posts.class);
        if(fetchedPosts != null && fetchedPosts.getPosts() != null)
            posts.setPosts(fetchedPosts.getPosts());

        for(Post post : posts.getPosts()) {
            if(post.getId().equals(id))
                return post;
        }

        return null;
    }

    @Override
    public void deletePost(int id) {
        Posts posts = new Posts();

        Posts fetchedPosts = Utility.loadFromJsonFile("posts.json", Posts.class);
        if(fetchedPosts != null && fetchedPosts.getPosts() != null)
            posts.setPosts(fetchedPosts.getPosts());

        for(Post post : posts.getPosts()) {
            if(post.getSquareId() == id) {
                post.setImage(Base64.getDecoder().decode("iVBORw0KGgoAAAANSUhEUgAAAAoAAAAICAIAAABPmPnhAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAAYSURBVBhXY/z//z8DbsAEpXGAoSrNwAAA5/QDDa3EjHsAAAAASUVORK5CYII="));
                post.setTitle("Empty Slot");
                post.setDescription("Empty Slot");
            }
        }

        Utility.saveToJsonFile(posts, "posts.json");
    }

    @Override
    public Post createNewPost(String title, String description, int squareId, byte[] image) {
        Posts posts = new Posts();

        Posts fetchedPosts = Utility.loadFromJsonFile("posts.json", Posts.class);
        if(fetchedPosts != null && fetchedPosts.getPosts() != null)
            posts.setPosts(fetchedPosts.getPosts());

        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setSquareId(squareId);
        post.setImage(image);
        post.setId(Utility.generateRandomString());

        for(Post post_ : posts.getPosts()) {
            if(post_.getSquareId() == squareId)
                post_.setSquareId(0);
        }

        if(posts.getPosts() != null)
            posts.getPosts().add(post);
        else
            posts.setPosts(List.of(post));

        Utility.saveToJsonFile(posts, "posts.json");
        return post;
    }

    @Override
    public Post editPost(String title, String description, int squareId, byte[] image, String id) {
        Posts posts = new Posts();

        Posts fetchedPosts = Utility.loadFromJsonFile("posts.json", Posts.class);
        if(fetchedPosts != null && fetchedPosts.getPosts() != null)
            posts.setPosts(fetchedPosts.getPosts());

        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setSquareId(squareId);
        post.setImage(image);
        post.setId(id);

        if(posts.getPosts() != null) {
            posts.getPosts().removeIf(post_ -> post_.getId().equals(id));
            posts.getPosts().add(post);
        } else {
            posts.setPosts(List.of(post));
        }

        Utility.saveToJsonFile(posts, "posts.json");
        return post;
    }
}
