package ru.vsu.cs.iachnyi_m_a.java.servlets.api_servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.iachnyi_m_a.java.context.ApplicationContextProvider;
import ru.vsu.cs.iachnyi_m_a.java.service.ProductService;
import ru.vsu.cs.iachnyi_m_a.java.service.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/products/*")
public class ProductServlet extends HttpServlet {
    private UserService userService;
    private ProductService productService;
    private Gson gson;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = ApplicationContextProvider.getContext().getBean(UserService.class);
        productService = ApplicationContextProvider.getContext().getBean(ProductService.class);
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if(pathInfo.trim().equals("/all")){
            processAllProductsRequest(req, resp);
        }
    }

    private void processAllProductsRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.getWriter().println(gson.toJson(productService.getAllProducts()));
        resp.getWriter().flush();
    }
}
