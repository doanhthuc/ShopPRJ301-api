package controller;

import com.google.gson.Gson;
import model.Order;
import service.IOrderService;
import utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {

    @Inject
    IOrderService orderService;

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Integer customerId = Integer.valueOf(request.getParameter("customerId"));
        List<Order> orders = orderService.findAllByCustomerId(customerId);
        PrintWriter out = response.getWriter();
        out.write(gson.toJson(orders));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Order newOrder = gson.fromJson(HttpUtil.of(request.getReader()).getValue(), Order.class);
        newOrder = orderService.save(newOrder);
        PrintWriter out = response.getWriter();
        out.write(gson.toJson(newOrder));
    }
}
