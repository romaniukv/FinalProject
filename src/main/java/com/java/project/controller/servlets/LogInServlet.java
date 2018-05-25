package com.java.project.controller.servlets;

import com.java.project.factory.ServiceFactory;
import com.java.project.model.domain.User;
import com.java.project.utils.AppUtils;
import com.java.project.utils.LocalizationUtils;
import com.java.project.utils.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (AppUtils.getLoginedUser(req.getSession()) != null) {
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
        else {
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = ServiceFactory.getUserService().findUserByUsername(username);

        if (user == null || !PasswordUtils.checkPassword(password, user.getPassword())) {
            req.setAttribute("errorMsg", LocalizationUtils.WRONG_USERNAME_PASS);
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
        else {
            req.getSession().setAttribute("user", user);
            try {
                int redirectId = Integer.parseInt(req.getParameter("redirectId"));
                String requestUri = AppUtils.getRedirectUrl(redirectId);
                if (requestUri != null) {
                    resp.sendRedirect(requestUri);
                }
            } catch (Exception e) {
                resp.sendRedirect(req.getContextPath() + "/profile");
            }
        }
    }
}
