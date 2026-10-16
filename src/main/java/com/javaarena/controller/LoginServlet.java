package com.javaarena.controller;

import com.javaarena.dao.UserDAO;

import java.io.IOException;
import com.javaarena.model.User;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
         
        @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    UserDAO dao = new UserDAO();

    User user = dao.login(email, password);

    if (user != null) {

        HttpSession session = request.getSession();

        session.setAttribute("user", user);

        response.sendRedirect("dashboard.html");

    } else {

        response.sendRedirect("register.html");

    }
}

}