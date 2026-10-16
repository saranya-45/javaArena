package com.javaarena.util;

import java.sql.Connection;

public class DBConnectionTest {

    public static void main(String[] args) {

        try {
            Connection connection = DBConnection.getConnection();

            if (connection != null) {
                System.out.println("Database Connected Successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}