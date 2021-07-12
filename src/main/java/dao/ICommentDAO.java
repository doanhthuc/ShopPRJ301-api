package dao;

import model.Comment;

import java.util.List;

public interface ICommentDAO {
    void save(Comment comment);

    List<Comment> findAllByProductId(int productId);
}
