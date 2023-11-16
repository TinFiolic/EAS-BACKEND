package me.tin.EAS.service;

import me.tin.EAS.model.Post;
import me.tin.EAS.model.Posts;

public interface PostService {
    Posts getAllPosts();

    Post getPost(String id);

    void deletePost(int id);

    Post createNewPost(String title, String description, int squareId, byte[] image);

    Post editPost(String title, String description, int squareId, byte[] image, String id);

}
