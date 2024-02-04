package com.tourismAgency.Model;

import com.tourismAgency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Pension {
    private int id;
    private int hotelId;
    private  String type;

    public Pension(){}

    public Pension(int id, int hotelId, String type) {
        this.id = id;
        this.hotelId = hotelId;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<Pension> getPension(String hotelID) {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM hotel_pension WHERE hotel_id =" + hotelID;
        Pension obj;
        try {
            Statement statement = DBConnector.getInstances().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                obj = new Pension();
                obj.setId(resultSet.getInt("id"));
                obj.setHotelId(resultSet.getInt("hotel_id"));
                obj.setType(resultSet.getString("pension_type"));
                pensionList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pensionList;
    }

    public static boolean addPension(int HotelID, String pensionType){
        String query = "INSERT INTO hotel_pension (hotel_id, pension_type) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
            preparedStatement.setInt(1, HotelID);
            preparedStatement.setString(2, pensionType);
            return preparedStatement.executeUpdate() !=-1;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
