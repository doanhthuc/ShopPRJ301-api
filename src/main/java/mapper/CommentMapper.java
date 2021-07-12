package mapper;

import model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet) {
        try {
            Comment comment = new Comment();
            comment.setUsername(resultSet.getString("username"));
            comment.setContent(resultSet.getString("content"));
            comment.setImageUrl(resultSet.getString("image_url"));
            comment.setProductId(resultSet.getInt("product_id"));
            comment.setCreatedAt(resultSet.getTimestamp("created_at"));
            return comment;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
