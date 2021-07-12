package service.impl;

import dao.ICommentDAO;
import model.Comment;
import service.ICommentService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class CommentService implements ICommentService {

    @Inject
    ICommentDAO commentDAO;

    @Override
    public void save(Comment comment) {
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        commentDAO.save(comment);
    }

    @Override
    public List<Comment> findAllByProductId(int productId) {
        return commentDAO.findAllByProductId(productId);
    }
}
