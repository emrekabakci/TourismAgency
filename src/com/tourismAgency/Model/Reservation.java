package com.tourismAgency.Model;

import com.tourismAgency.Helper.DBConnector;
import com.tourismAgency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Reservation {
    private int id;
    private int roomId;
    private String seasonStart;
    private String seasonFinish;
    private int totalPrice;
    private int totalCustomer;
    private String customerName;
    private String CustomerId;
    private String customerMail;
    private String customerPhone;

    public Reservation() {
    }

    public Reservation(int id, int roomId, String seasonStart, String seasonFinish, int totalPrice, int totalCustomer, String customerName, String customerId, String customerMail, String customerPhone) {
        this.id = id;
        this.roomId = roomId;
        this.seasonStart = seasonStart;
        this.seasonFinish = seasonFinish;
        this.totalPrice = totalPrice;
        this.totalCustomer = totalCustomer;
        this.customerName = customerName;
        CustomerId = customerId;
        this.customerMail = customerMail;
        this.customerPhone = customerPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getSeasonStart() {
        return seasonStart;
    }

    public void setSeasonStart(String seasonStart) {
        this.seasonStart = seasonStart;
    }

    public String getSeasonFinish() {
        return seasonFinish;
    }

    public void setSeasonFinish(String seasonFinish) {
        this.seasonFinish = seasonFinish;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(int totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public static ArrayList<Reservation> getReservations() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM reservation";
        Reservation obj;
        try {
            Statement statement = DBConnector.getInstances().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                obj = new Reservation();
                obj.setId(resultSet.getInt("id"));
                obj.setRoomId(resultSet.getInt("room_id"));
                obj.setSeasonStart(resultSet.getString("season_start"));
                obj.setSeasonFinish(resultSet.getString("season_finish"));
                obj.setTotalPrice(resultSet.getInt("total_price"));
                obj.setTotalCustomer(resultSet.getInt("total_customer"));
                obj.setCustomerName(resultSet.getString("customer_name"));
                obj.setCustomerId(resultSet.getString("customer_id"));
                obj.setCustomerMail(resultSet.getString("customer_mail"));
                obj.setCustomerPhone(resultSet.getString("customer_phone"));
                reservationList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservationList;
    }
    public static boolean addReservation(String roomId, String seasonStart, String seasonFinish, String totalPrice,
                                         String totalCustomer, String customerName, String customerId, String customerMail, String customerPhone) {

        String query = "INSERT INTO reservation (room_id,season_start,season_finish,total_price, total_customer, customer_name, customer_id, customer_mail, customer_phone) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
            preparedStatement.setString(1, roomId);
            preparedStatement.setString(2, seasonStart);
            preparedStatement.setString(3, seasonFinish);
            preparedStatement.setString(4, totalPrice);
            preparedStatement.setString(5, totalCustomer);
            preparedStatement.setString(6, customerName);
            preparedStatement.setString(7, customerId);
            preparedStatement.setString(8, customerMail);
            preparedStatement.setString(9, customerPhone);

            Helper.showMsg("success");
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

