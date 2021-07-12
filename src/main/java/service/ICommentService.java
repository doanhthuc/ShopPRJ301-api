package service;

import model.Comment;

import java.util.List;

public interface ICommentService {
    void save(Comment comment);
    List<Comment> findAllByProductId(int productId);
}
