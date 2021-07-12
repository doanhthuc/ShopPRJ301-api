package controller;

import com.google.gson.Gson;
import model.Comment;
import model.RespMessage;
import service.ICommentService;
import utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CommentServlet", value = "/CommentServlet")
public class CommentServlet extends HttpServlet {

    @Inject
    private ICommentService commentService;

    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        if (request.getParameter("productId") != null) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            List<Comment> commentList = commentService.findAllByProductId(productId);
            Comment resp = new Comment();
            resp.setProductId(productId);
            resp.setCommentList(commentList);
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(resp));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String username = (String) request.getAttribute("username");
        Comment comment = gson.fromJson(HttpUtil.of(request.getReader()).getValue(), Comment.class);
        comment.setUsername(username);
        commentService.save(comment);
        RespMessage resp = new RespMessage(true, "post comment success");
        PrintWriter out = response.getWriter();
        out.write(gson.toJson(resp));
    }
}
