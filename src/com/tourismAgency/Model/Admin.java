package com.tourismAgency.Model;

import com.tourismAgency.Helper.DBConnector;
import com.tourismAgency.Helper.Helper;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Admin extends User{
    public Admin() {
    }

    public Admin(int id, String name, String username, String password, String type) {
    }

    public static ArrayList<User> getList() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";
        User obj;
        try {
            Statement statement = DBConnector.getInstances().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                obj = new User();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                obj.setUsername(resultSet.getString("username"));
                obj.setPassword(resultSet.getString("password"));
                obj.setType(resultSet.getString("type"));
                userList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public static ArrayList<User> getList(String type) {
        ArrayList<User> userList = new ArrayList<>();
        if (type == ""){
            String query = "SELECT * FROM user";
            User obj;
            try {
                Statement statement = DBConnector.getInstances().createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    obj = new User();
                    obj.setId(resultSet.getInt("id"));
                    obj.setName(resultSet.getString("name"));
                    obj.setUsername(resultSet.getString("username"));
                    obj.setPassword(resultSet.getString("password"));
                    obj.setType(resultSet.getString("type"));
                    userList.add(obj);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if (type == "Admin"){
            String query = "SELECT * FROM user WHERE type='admin'";
            User obj;
            try {
                Statement statement = DBConnector.getInstances().createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    obj = new User();
                    obj.setId(resultSet.getInt("id"));
                    obj.setName(resultSet.getString("name"));
                    obj.setUsername(resultSet.getString("username"));
                    obj.setPassword(resultSet.getString("password"));
                    obj.setType(resultSet.getString("type"));
                    userList.add(obj);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }else {
            String query = "SELECT * FROM user WHERE type='Employee'";
            User obj;
            try {
                Statement statement = DBConnector.getInstances().createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    obj = new User();
                    obj.setId(resultSet.getInt("id"));
                    obj.setName(resultSet.getString("name"));
                    obj.setUsername(resultSet.getString("username"));
                    obj.setPassword(resultSet.getString("password"));
                    obj.setType(resultSet.getString("type"));
                    userList.add(obj);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return userList;
    }

    public static boolean addUser(String name, String username, String password, String type) {

        if (!isUserNameUnique(username)){
            Helper.showMsg("Böyle bir kullanıcı bulunmakta, lütfen farklı bir kullanıcı adı deneyiniz.");
            return false;
        }

        String query = "INSERT INTO user (name,username,password,type) VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, type);
            Helper.showMsg("success");
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateUser(String id, String name, String username, String password, String type) {

        String query = "UPDATE user SET name=?, password=?, type=?, username=? WHERE id= " +id;

        try {
            PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, type);
            preparedStatement.setString(4, username);
            Helper.showMsg("success");
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean deleteUser(String id) {
        String query = "DELETE FROM user WHERE id = ?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DBConnector.getInstances().prepareStatement(query);
            preparedStatement.setString(1, id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Hata", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }



    public static boolean isUserNameUnique(String username) {
        String query = "SELECT * FROM user WHERE username = ?";

        try {
            PreparedStatement pr = DBConnector.getInstances().prepareStatement(query);
            pr.setString(1, username);
            ResultSet result = pr.executeQuery();

            int rowNum = getRowCount(result);

            return rowNum == 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getRowCount(ResultSet resultSet) {
        int count = 0;

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ++count;
        }
        return count;

    }
}
