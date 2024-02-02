package com.tourismAgency.Model;

import com.tourismAgency.Helper.DBConnector;
import com.tourismAgency.Helper.Helper;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String mail;
    private String phone;
    private String star;
    private boolean carPark;
    private boolean wifi;
    private boolean pool;
    private boolean fitness;
    private boolean concierge;
    private boolean spa;
    private boolean roomService;

    public Hotel(int id, String name, String address, String mail, String phone, String star, boolean carPark,
                 boolean wifi, boolean pool, boolean fitness, boolean concierge, boolean spa, boolean roomService) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.star = star;
        this.carPark = carPark;
        this.wifi = wifi;
        this.pool = pool;
        this.fitness = fitness;
        this.concierge = concierge;
        this.spa = spa;
        this.roomService = roomService;
    }

    public Hotel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public boolean isCarPark() {
        return carPark;
    }

    public void setCarPark(boolean carPark) {
        this.carPark = carPark;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isFitness() {
        return fitness;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }

    public static ArrayList<Hotel> getHotels() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotel";
        Hotel obj;
        try {
            Statement statement = DBConnector.getInstances().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                obj = new Hotel();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                obj.setAddress(resultSet.getString("address"));
                obj.setMail(resultSet.getString("mail"));
                obj.setPhone(resultSet.getString("phone"));
                obj.setStar(resultSet.getString("star"));
                obj.setCarPark(resultSet.getBoolean("car_park"));
                obj.setWifi(resultSet.getBoolean("wifi"));
                obj.setPool(resultSet.getBoolean("pool"));
                obj.setFitness(resultSet.getBoolean("fitness"));
                obj.setConcierge(resultSet.getBoolean("concierge"));
                obj.setSpa(resultSet.getBoolean("spa"));
                obj.setRoomService(resultSet.getBoolean("room_service"));
                hotelList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    public static boolean addHotel(String name, String address, String mail, String phone, String star, boolean carPark,
                                  boolean wifi, boolean pool, boolean fitness, boolean concierge, boolean spa,
                                  boolean roomService) {

        String query = "INSERT INTO hotel (name,address,mail,phone, star, car_park, wifi, pool, fitness, concierge, spa, room_service) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, star);
            preparedStatement.setBoolean(6, carPark);
            preparedStatement.setBoolean(7, wifi);
            preparedStatement.setBoolean(8, pool);
            preparedStatement.setBoolean(9, fitness);
            preparedStatement.setBoolean(10, concierge);
            preparedStatement.setBoolean(11,spa);
            preparedStatement.setBoolean(12,roomService);

            Helper.showMsg("success");
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteHotelByID(String id){
        {
            String query = "DELETE FROM hotel WHERE id = ?";

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
    }

    public static boolean updateHotelByID(int id, String name, String address, String mail, String phone, String star,
                                          boolean carPark, boolean wifi, boolean pool, boolean fitness,
                                          boolean concierge, boolean spa, boolean roomService){

        String query = "UPDATE hotel SET name=?, address=?, mail=?, phone=?, star=?, car_park=?, wifi=?, pool=?, fitness=?, concierge=?, spa=?, room_service=? WHERE id=" +id;

        try {
            PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, star);
            preparedStatement.setBoolean(6, carPark);
            preparedStatement.setBoolean(7, wifi);
            preparedStatement.setBoolean(8, pool);
            preparedStatement.setBoolean(9, fitness);
            preparedStatement.setBoolean(10, concierge);
            preparedStatement.setBoolean(11,spa);
            preparedStatement.setBoolean(12,roomService);
            Helper.showMsg("success");
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
