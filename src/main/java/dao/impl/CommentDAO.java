package dao.impl;

import dao.ICommentDAO;
import mapper.CommentMapper;
import model.Comment;

import java.util.List;

public class CommentDAO extends AbstractDAO<Comment> implements ICommentDAO {
    @Override
    public void save(Comment comment) {
        if (comment.getImageUrl() != null) {
            String sql = "INSERT INTO comment (username, image_url, product_id, content, created_at) VALUES (?, ?, ?, ?, ?)";
            insert(sql, comment.getUsername(), comment.getImageUrl(),
                    comment.getProductId(), comment.getContent(), comment.getCreatedAt());
        } else {
            String sql = "INSERT INTO comment (username, product_id, content, created_at) VALUES (?, ?, ?, ?)";
            insert(sql, comment.getUsername(), comment.getProductId(), comment.getContent(), comment.getCreatedAt());
        }
    }

    @Override
    public List<Comment> findAllByProductId(int productId) {
        String sql = "SELECT * FROM comment WHERE product_id = ?";
        return query(sql, new CommentMapper(), productId);
    }
}
