
package com.javaarena.controller;

import com.javaarena.dao.TopicDAO;
import com.javaarena.model.Topic;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.List;


@WebServlet("/learn")
public class LearnServlet extends HttpServlet {

   @Override
protected void doGet(HttpServletRequest request,
                     HttpServletResponse response)
        throws ServletException, IOException {

    TopicDAO dao = new TopicDAO();

    List<Topic> topics = dao.getAllTopics();

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    ObjectMapper mapper = new ObjectMapper();

    mapper.writeValue(response.getWriter(), topics);
}
}