package com.javaarena.controller;

import com.javaarena.dao.ProgressDAO;
import com.javaarena.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/complete-topic")
public class CompleteTopicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Get logged-in user
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(
                    "{\"error\":\"User not logged in\"}"
            );
            return;
        }

        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(
                    "{\"error\":\"User not logged in\"}"
            );
            return;
        }

        // Get topic ID
        String topicIdParam = request.getParameter("topicId");

        if (topicIdParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(
                    "{\"error\":\"Topic ID is required\"}"
            );
            return;
        }

        int topicId;

        try {
            topicId = Integer.parseInt(topicIdParam);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(
                    "{\"error\":\"Invalid topic ID\"}"
            );
            return;
        }

        // Mark topic as completed
        ProgressDAO dao = new ProgressDAO();

        boolean completed = dao.markCompleted(
                user.getId(),
                topicId
        );

        if (completed) {

            response.getWriter().write(
                    "{\"success\":true,\"message\":\"Topic completed\"}"
            );

        } else {

            response.getWriter().write(
                    "{\"success\":false,\"message\":\"Topic already completed\"}"
            );
        }
    }
}