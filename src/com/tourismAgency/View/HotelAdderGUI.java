package com.tourismAgency.View;

import com.tourismAgency.Helper.Config;
import com.tourismAgency.Helper.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.tourismAgency.Model.Hotel.addHotel;

public class HotelAdderGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_hotelName;
    private JTextField fld_address;
    private JTextField fld_mail;
    private JTextField fld_phone;
    private JComboBox cmbBox_star;
    private JLabel lbl_hotelName;
    private JLabel lbl_address;
    private JLabel lbl_mail;
    private JLabel lbl_phone;
    private JLabel lbl_star;
    private JCheckBox chcBox_carPark;
    private JCheckBox chcBox_wifi;
    private JCheckBox chcBox_pool;
    private JCheckBox chcBox_fitness;
    private JCheckBox chcBox_concierge;
    private JCheckBox chcBox_spa;
    private JCheckBox chcBox_roomService;
    private JButton btn_saveHotel;

    public HotelAdderGUI(EmployeeGUI employeeGUI) {
        setContentPane(wrapper);
        setSize(800, 600);
        setTitle(Config.AGENCY_NAME + " Admin");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.setCenter("x", getSize()), Helper.setCenter("y", getSize()));


        btn_saveHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fld_hotelName.getText().trim().isEmpty() && !fld_address.getText().trim().isEmpty()
                        && !fld_mail.getText().trim().isEmpty() && !fld_phone.getText().trim().isEmpty()
                        && !cmbBox_star.getSelectedItem().toString().isEmpty()) {
                    addHotel(fld_hotelName.getText(), fld_address.getText(), fld_mail.getText(), fld_phone.getText(),
                            cmbBox_star.getSelectedItem().toString(), chcBox_carPark.isSelected(),
                            chcBox_wifi.isSelected(), chcBox_pool.isSelected(), chcBox_fitness.isSelected(),
                            chcBox_concierge.isSelected(), chcBox_spa.isSelected(), chcBox_roomService.isSelected());
                    employeeGUI.hotelLoader(new Object[]{"id", "name", "address", "mail", "phone", "star", "car_park",
                            "wifi", "pool", "fitness", "concierge", "spa", "room_service"});
                    HotelAdderGUI.super.dispose();
                } else {
                    Helper.showMsg("fill");
                }
            }
        });
    }
}
