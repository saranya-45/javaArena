package com.javaarena.dao;

import com.javaarena.model.Topic;
import com.javaarena.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class TopicDAO {

    public List<Topic> getAllTopics() {

        List<Topic> topics = new ArrayList<>();

        String sql = "SELECT * FROM topics ORDER BY topic_order";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Topic topic = new Topic();

                topic.setId(rs.getInt("id"));
                topic.setTitle(rs.getString("title"));
                topic.setDescription(rs.getString("description"));
                topic.setDifficulty(rs.getString("difficulty"));
                topic.setXp(rs.getInt("xp"));
                topic.setTopicOrder(rs.getInt("topic_order"));
                topic.setExternalUrl(rs.getString("external_url"));

                topics.add(topic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topics;
    }

    public Topic getTopicById(int id) {

        String sql = "SELECT * FROM topics WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Topic topic = new Topic();

                topic.setId(rs.getInt("id"));
                topic.setTitle(rs.getString("title"));
                topic.setDescription(rs.getString("description"));
                topic.setDifficulty(rs.getString("difficulty"));
                topic.setXp(rs.getInt("xp"));
                topic.setTopicOrder(rs.getInt("topic_order"));

                topic.setContent(rs.getString("content"));
                topic.setIntroduction(rs.getString("introduction"));
                topic.setExample(rs.getString("example"));
                topic.setRules(rs.getString("rules"));
                topic.setPractice(rs.getString("practice"));
                topic.setExternalUrl(rs.getString("external_url"));

                return topic;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}