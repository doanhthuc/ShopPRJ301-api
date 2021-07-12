package controller;

import com.google.gson.Gson;
import model.OrderDetailModel;
import service.IOrderDetailService;
import utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OrderDetailServlet", value = "/OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {

    @Inject
    IOrderDetailService orderDetailService;

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String username = (String) request.getAttribute("username");
        OrderDetailModel orderDetailModel = new OrderDetailModel();
        List<OrderDetailModel> orderDetailList = orderDetailService.findAllOrderDetailByUsername(username);
        orderDetailModel.setOrderDetailList(orderDetailList);
        PrintWriter out = response.getWriter();
        out.write(gson.toJson(orderDetailModel));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        OrderDetailModel orderDetailModel = gson.fromJson(HttpUtil.of(request.getReader()).getValue(), OrderDetailModel.class);
        orderDetailService.saveOrderDetail(orderDetailModel.getOrderDetailList());
        response.setStatus(200);
    }
}
