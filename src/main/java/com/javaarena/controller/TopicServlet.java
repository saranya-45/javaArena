package com.javaarena.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaarena.dao.TopicDAO;
import com.javaarena.model.Topic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/topic")
public class TopicServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Topic ID is required\"}");
            return;
        }

        int id = Integer.parseInt(idParam);

        TopicDAO dao = new TopicDAO();

        Topic topic = dao.getTopicById(id);

        if (topic == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\":\"Topic not found\"}");
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), topic);
    }
}