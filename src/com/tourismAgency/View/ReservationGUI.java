package com.tourismAgency.View;

import com.tourismAgency.Helper.Config;
import com.tourismAgency.Helper.Helper;

import javax.swing.*;

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
    private JTextField fld_customerName;
    private JTextField fld_customerID;
    private JTextField fld_customerMail;
    private JTextField fld_customerNo;
    private JButton btn_saveReservation;

    public ReservationGUI(){

        setContentPane(wrapper);
        setSize(900, 600);
        setTitle(Config.AGENCY_NAME + " Personel");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.setCenter("x", getSize()), Helper.setCenter("y", getSize()));
    }
}
