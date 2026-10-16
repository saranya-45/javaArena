package com.javaarena.dao;

import com.javaarena.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressDAO {
    public int getTotalXP(int userId) {

    String sql =
            "SELECT COALESCE(SUM(t.xp), 0) AS total_xp " +
            "FROM user_progress up " +
            "JOIN topics t ON up.topic_id = t.id " +
            "WHERE up.user_id = ? " +
            "AND up.completed = TRUE";

    try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
    ) {

        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("total_xp");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return 0;
}

    public boolean markCompleted(int userId, int topicId) {

        String checkSql =
                "SELECT completed FROM user_progress " +
                "WHERE user_id = ? AND topic_id = ?";

        String insertSql =
                "INSERT INTO user_progress(user_id, topic_id, completed) " +
                "VALUES (?, ?, TRUE)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement checkPs = con.prepareStatement(checkSql)
        ) {

            checkPs.setInt(1, userId);
            checkPs.setInt(2, topicId);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {

                // Topic was already completed
                return false;
            }

            try (PreparedStatement insertPs = con.prepareStatement(insertSql)) {

                insertPs.setInt(1, userId);
                insertPs.setInt(2, topicId);

                int rows = insertPs.executeUpdate();

                return rows > 0;
            }

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}