package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.Product;
import paging.PageRequest;
import paging.Pageable;
import service.IProductService;
import sort.Sorter;
import utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {

    @Inject
    IProductService productService;

    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if (request.getParameter("page") != null && request.getParameter("limit") != null) {
            int page = Integer.parseInt(request.getParameter("page"));
            int limit = Integer.parseInt(request.getParameter("limit"));
            Sorter sorter = new Sorter(request.getParameter("sortName"), request.getParameter("sortBy"));
            Pageable pageable = new PageRequest(page, limit, sorter);
            List<Product> products = productService.findAll(pageable);
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(products));
        } else {
            List<Product> products = productService.findAll();
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(products));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
//        Product product = HttpUtil.of(request.getReader()).toModel(Product.class);

        Product product = gson.fromJson(HttpUtil.of(request.getReader()).getValue(), Product.class);
        product = productService.save(product);
        PrintWriter out = response.getWriter();
        out.write(gson.toJson(product));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        int[] ids = new Gson().fromJson(req.getParameter("ids"), int[].class);
        productService.delete(ids);
        String message = "delete success";
        PrintWriter out = resp.getWriter();
        out.write(gson.toJson(message));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Product updateProduct = gson.fromJson(HttpUtil.of(req.getReader()).getValue(), Product.class);
        updateProduct = productService.update(updateProduct);
        PrintWriter out = resp.getWriter();
        out.write(gson.toJson(updateProduct));
    }
}
