package com.trainbook.controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.trainbook.dao.UserDAO;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        if (UserDAO.validate(user, pass)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            res.sendRedirect("jsp/userHome.jsp");
        } else {
            res.sendRedirect("jsp/login.jsp");
        }
    }
}

