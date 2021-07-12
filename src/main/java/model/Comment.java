package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String username;
    private String imageUrl;
    private String content;
    private int productId;
    private Timestamp createdAt;

    private List<Comment> commentList;
}
