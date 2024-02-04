package com.tourismAgency.Model;

import com.tourismAgency.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Season {

    private int id;
    private int hotelID;
    private String season;
    private String seasonFinish;

    public Season(){}

    public Season(int id, int hotelID, String season, String seasonFinish) {
        this.id = id;
        this.hotelID = hotelID;
        this.season = season;
        this.seasonFinish = seasonFinish;
    }

    public String getSeasonFinish() {
        return seasonFinish;
    }

    public void setSeasonFinish(String seasonFinish) {
        this.seasonFinish = seasonFinish;
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

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public static ArrayList<Season>getSeason(String hotelID) {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM hotel_season WHERE hotel_id =" + hotelID;
        Season obj;
        try {
            Statement statement = DBConnector.getInstances().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                obj = new Season();
                obj.setId(resultSet.getInt("id"));
                obj.setHotelID(resultSet.getInt("hotel_id"));
                obj.setSeason(resultSet.getString("season_start"));
                obj.setSeasonFinish(resultSet.getString("season_finish"));
                seasonList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasonList;
    }

    public static boolean addSeason(int HotelID, String season, String seasonFinish){
        String query = "INSERT INTO hotel_season (hotel_id, season_start, season_finish) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = DBConnector.getInstances().prepareStatement(query);
            preparedStatement.setInt(1, HotelID);
            preparedStatement.setString(2, season);
            preparedStatement.setString(3,seasonFinish);
            return preparedStatement.executeUpdate() !=-1;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
