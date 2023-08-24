package com.learning.hello.contoller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.learning.hello.model.Notice;

public class NoticesController {
    private static final String URL = "jdbc:mysql://localhost:3306/Notices";;
    private static final String USER_NAME = "SandhyaShevatre";
    private static final String PASSWORD = "sandhya@123";

    public List<Notice> getNotices() {
        List<Notice> notices = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            String query = "SELECT n.id, n.title, n.content, c.name, c.phone FROM notices n INNER JOIN contacts c ON n.contact_id = c.id";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String contactName = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                
                
                Notice notice = new Notice(title, content,contactName,phone);

                notices.add(notice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
        }

        return notices;
    }
}
