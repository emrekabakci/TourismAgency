package com.tourismAgency.Model;

import com.tourismAgency.Helper.DBConnector;
import com.tourismAgency.Helper.Helper;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Room {
    private int id;
    private int hotelID;
    private String pensionType;
    private String roomType;
    private int stock;
    private int adultPrice;
    private int childPrice;
    private int bedCapacity;
    private int m2;
    private boolean television;
    private boolean minibar;
    private boolean console;
    private boolean safe;
    private boolean projection;

    public Room(int id, int hotelID, String pensionType, String roomType, int stock, int adultPrice, int childPrice,
                int bedCapacity, int m2, boolean television, boolean minibar, boolean console, boolean safe,
                boolean projection) {
        this.id = id;
        this.hotelID = hotelID;
        this.pensionType = pensionType;
        this.roomType = roomType;
        this.stock = stock;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.bedCapacity = bedCapacity;
        this.m2 = m2;
        this.television = television;
        this.minibar = minibar;
        this.console = console;
        this.safe = safe;
        this.projection = projection;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getPensionType() {
        return pensionType;
    }

    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(int adultPrice) {
        this.adultPrice = adultPrice;
    }

    public int getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(int childPrice) {
        this.childPrice = childPrice;
    }

    public int getBedCapacity() {
        return bedCapacity;
    }

    public void setBedCapacity(int bedCapacity) {
        this.bedCapacity = bedCapacity;
    }

    public int getM2() {
        return m2;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isConsole() {
        return console;
    }

    public void setConsole(boolean console) {
        this.console = console;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }



    public static ArrayList<Room> getRooms() {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM hotel_room";
        Room obj;
        try {
            Statement statement = DBConnector.getInstances().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                obj = new Room();
                obj.setId(resultSet.getInt("id"));
                obj.setHotelID(resultSet.getInt("hotel_id"));
                obj.setPensionType(resultSet.getString("pension_type"));
                obj.setStock(resultSet.getInt("stock"));
                obj.setAdultPrice(resultSet.getInt("adult_price"));
                obj.setChildPrice(resultSet.getInt("child_price"));
                obj.setBedCapacity(resultSet.getInt("bed_capacity"));
                obj.setM2(resultSet.getInt("m2"));
                obj.setTelevision(resultSet.getBoolean("television"));
                obj.setMinibar(resultSet.getBoolean("minibar"));
                obj.setConsole(resultSet.getBoolean("console"));
                obj.setSafe(resultSet.getBoolean("safe"));
                obj.setProjection(resultSet.getBoolean("projection"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }

    public static Room getRoom(String roomID){
        String query = "SELECT * FROM hotel_room WHERE id=" + roomID;
        Room obj;
        try {
            Statement statement = DBConnector.getInstances().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            obj = new Room();
            while (resultSet.next()) {
                obj.setHotelID((resultSet.getInt("hotel_id")));
                obj.setPensionType(resultSet.getString("pension_type"));
                obj.setBedCapacity(resultSet.getInt("bed_capacity"));
                obj.setM2(resultSet.getInt("m2"));
                obj.setTelevision(resultSet.getBoolean("television"));
                obj.setMinibar(resultSet.getBoolean("minibar"));
                obj.setConsole(resultSet.getBoolean("console"));
                obj.setSafe(resultSet.getBoolean("safe"));
                obj.setProjection(resultSet.getBoolean("projection"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;

    }

    public static ArrayList<Room> getRoomsBySearch(String name, String city) {
        ArrayList<Room> roomList = new ArrayList<>();

        String query = "SELECT * FROM hotel_room r " +
                "LEFT JOIN hotel h ON r.hotel_id = h.id " +
                "WHERE r.stock > 0 AND h.name LIKE '%" + name + "%' AND h.address LIKE '%" + city + "%'";

        Room obj;
        try {
            Statement statement = DBConnector.getInstances().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                obj = new Room();
                obj.setId(resultSet.getInt("id"));
                obj.setHotelID(resultSet.getInt("hotel_id"));
                obj.setPensionType(resultSet.getString("pension_type"));
                obj.setStock(resultSet.getInt("stock"));
                obj.setAdultPrice(resultSet.getInt("adult_price"));
                obj.setChildPrice(resultSet.getInt("child_price"));
                obj.setBedCapacity(resultSet.getInt("bed_capacity"));
                obj.setM2(resultSet.getInt("m2"));
                obj.setTelevision(resultSet.getBoolean("television"));
                obj.setMinibar(resultSet.getBoolean("minibar"));
                obj.setConsole(resultSet.getBoolean("console"));
                obj.setSafe(resultSet.getBoolean("safe"));
                obj.setProjection(resultSet.getBoolean("projection"));
                roomList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomList;
    }

    public static boolean addRoom(int hotelID, String pensionType, String stock, String adultPrice,
                                  String childPrice, String bedCapacity, String m2, boolean television, boolean minibar,
                                  boolean console, boolean safe, boolean projection) {
        String query = "INSERT INTO hotel_room (hotel_id,pension_type,stock, adult_price, child_price, bed_capacity, m2, television, minibar, console, safe, projection) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
            preparedStatement.setInt(1, hotelID);
            preparedStatement.setString(2, pensionType);
            preparedStatement.setString(3, stock);
            preparedStatement.setString(4, adultPrice);
            preparedStatement.setString(5, childPrice);
            preparedStatement.setString(6, bedCapacity);
            preparedStatement.setString(7, m2);
            preparedStatement.setBoolean(8, television);
            preparedStatement.setBoolean(9, minibar);
            preparedStatement.setBoolean(10, console);
            preparedStatement.setBoolean(11, safe);
            preparedStatement.setBoolean(12, projection);

            Helper.showMsg("success");
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteRoom(String id){
        {
            String query = "DELETE FROM hotel_room WHERE id =" + id;

            try {
                PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
                return preparedStatement.executeUpdate() != -1;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e, "Hata", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public static boolean deleteRoomWithHotel(String hotelId){
        {
            String query = "DELETE FROM hotel_room WHERE hotel_id =" + hotelId;

            try {
                PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
                return preparedStatement.executeUpdate() != -1;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e, "Hata", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    public static boolean increaseStock(String id) {

        String query = "UPDATE hotel_room SET stock = stock + 1 WHERE id=" +id;
        try {
            PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean decreaseStock(String id) {

        String query = "UPDATE hotel_room SET stock = stock - 1 WHERE id=" +id;
        try {
            PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
