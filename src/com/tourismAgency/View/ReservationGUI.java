package com.tourismAgency.View;

import com.tourismAgency.Helper.Config;
import com.tourismAgency.Helper.Helper;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import static com.tourismAgency.Model.Hotel.getHotel;
import static com.tourismAgency.Model.Reservation.addReservation;
import static com.tourismAgency.Model.Reservation.updateReservation;
import static com.tourismAgency.Model.Room.decreaseStock;
import static com.tourismAgency.Model.Room.increaseStock;

public class ReservationGUI extends JFrame {
    private JPanel wrapper;
    public JTextField fld_hotelName;
    public JTextField fld_city;
    public JTextField fld_star;
    public JRadioButton rBtn_otopark;
    public JRadioButton rBtn_wifi;
    public JRadioButton rBtn_pool;
    public JRadioButton rBtn_fitness;
    public JRadioButton rBtn_concierge;
    public JRadioButton rBtn_spa;
    public JRadioButton rBtn_roomService;
    private JPanel pnl_hotelInfo;
    private JPanel pnl_roomInfo;
    public JTextField fld_pensionType;
    public JTextField fld_seasonStart;
    public JTextField fld_seasonFinish;
    public JTextField fld_totalPrice;
    public JTextField fld_bedCapacity;
    public JTextField fld_m2;
    public JRadioButton rBtn_minibar;
    public JRadioButton rBtn_tv;
    public JRadioButton rBtn_console;
    public JRadioButton rBtn_safe;
    public JRadioButton rBtn_projection;
    private JPanel pnl_customerInfo;
    public JTextField fld_totalCustomer;
    public JTextField fld_customerName;
    public JTextField fld_customerID;
    public JTextField fld_customerMail;
    public JTextField fld_customerPhone;
    public JButton btn_saveReservation;
    public String roomId;
    public String id;

    public ReservationGUI(EmployeeGUI employeeGUI) {

        setContentPane(wrapper);
        setSize(900, 600);
        setTitle(Config.AGENCY_NAME + " Personel");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.setCenter("x", getSize()), Helper.setCenter("y", getSize()));

        Object[] col_reservation_list = {"Id", "Oda Id", "Giriş Tarihi", "Çıkış Tarihi", "Toplam Tutar",
                "Misafir Sayısı", "Misafir Adı", "Misafir Kimlik No", "Mail", "Telefon"};

        Object[] col_room_list = {"id", "Otel Adı", "Pansiyon", "Stok", "Yetişkin Fiyat", "Çocuk Fiyat", "Yatak Kapasitesi", "m2",
                "Tv", "Mini Bar", "Konsol", "Kasa", "Projeksiyon"};

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                fld_city.setText(getHotel(fld_hotelName.getText()).getAddress());
                fld_star.setText(getHotel(fld_hotelName.getText()).getStar());
                if (getHotel(fld_hotelName.getText()).isCarPark()) {
                    rBtn_otopark.setSelected(true);
                }
                if (getHotel(fld_hotelName.getText()).isWifi()) {
                    rBtn_wifi.setSelected(true);
                }
                if (getHotel(fld_hotelName.getText()).isPool()) {
                    rBtn_pool.setSelected(true);
                }
                if (getHotel(fld_hotelName.getText()).isFitness()) {
                    rBtn_fitness.setSelected(true);
                }
                if (getHotel(fld_hotelName.getText()).isConcierge()) {
                    rBtn_concierge.setSelected(true);
                }
                if (getHotel(fld_hotelName.getText()).isSpa()) {
                    rBtn_spa.setSelected(true);
                }
                if (getHotel(fld_hotelName.getText()).isRoomService()) {
                    rBtn_roomService.setSelected(true);
                }
            }
        };
        timer.schedule(task, 100);
        btn_saveReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btn_saveReservation.getText().equals("Rezervasyon Güncelle")) {
                    updateReservation(id, fld_totalCustomer.getText(), fld_customerName.getText(), fld_customerID.getText(),
                            fld_customerMail.getText(), fld_customerPhone.getText());
                    employeeGUI.reservationLoader(col_reservation_list);
                    ReservationGUI.super.dispose();
                } else {
                    addReservation(roomId, fld_seasonStart.getText(), fld_seasonFinish.getText(), fld_totalPrice.getText(),
                            fld_totalCustomer.getText(), fld_customerName.getText(), fld_customerID.getText(),
                            fld_customerMail.getText(), fld_customerPhone.getText());
                    decreaseStock(roomId);
                    employeeGUI.reservationLoader(col_reservation_list);
                    employeeGUI.roomLoader(col_room_list);
                    ReservationGUI.super.dispose();
                }
            }
        });
    }
}


