package ru.vsu.cs.iachnyi_m_a.java.servlets.api_servlets.cart;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.iachnyi_m_a.java.context.ApplicationContext;
import ru.vsu.cs.iachnyi_m_a.java.context.ApplicationContextProvider;
import ru.vsu.cs.iachnyi_m_a.java.service.CartService;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/cart/quantity")
public class CartQuantityServlet extends HttpServlet {
    private CartService cartService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cartService = ApplicationContextProvider.getContext().getBean(CartService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quantity_str = req.getParameter("quantity");
        String user_id_str = req.getParameter("user_id");
    }
}
