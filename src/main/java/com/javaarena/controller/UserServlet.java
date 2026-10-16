package com.javaarena.controller;

import com.javaarena.dao.ProgressDAO;
import com.javaarena.model.User;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Get real XP from completed topics
        ProgressDAO progressDAO = new ProgressDAO();

        int xp = progressDAO.getTotalXP(user.getId());

        String level = "Beginner";
        int streak = 0;
        int progress = 0;

        String json = String.format(
                "{\"name\":\"%s\",\"email\":\"%s\",\"xp\":%d,\"level\":\"%s\",\"streak\":%d,\"progress\":%d}",
                user.getName(),
                user.getEmail(),
                xp,
                level,
                streak,
                progress
        );

        response.getWriter().write(json);
    }
}