package ru.vsu.cs.iachnyi_m_a.java.servlets.api_servlets;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.iachnyi_m_a.java.entity.User;
import ru.vsu.cs.iachnyi_m_a.java.servlets.ServletUtils;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/api/register-user")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = ServletUtils.parseJson(req, User.class);
        resp.setContentType("text/json");
        resp.getWriter().println(new Gson().toJson(user.getName()));
        resp.getWriter().flush();
    }
}
