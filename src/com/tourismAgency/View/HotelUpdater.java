package com.tourismAgency.View;

import com.tourismAgency.Helper.Config;
import com.tourismAgency.Helper.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.tourismAgency.Model.Hotel.updateHotelByID;

public class HotelUpdater extends JFrame {
    private JPanel wrapper;
    private JLabel lbl_update;
    private JLabel lbl_name;
    public JTextField fld_name;
    public JTextField fld_address;
    public JTextField fld_mail;
    public JTextField fld_phone;
    public JCheckBox chcBox_carPark;
    public JCheckBox chcBox_wifi;
    public JCheckBox chcBox_pool;
    public JCheckBox chcBox_fitness;
    public JCheckBox chcBox_concierge;
    public JComboBox cmbBox_star;
    public JCheckBox chcBox_spa;
    public JCheckBox chcBox_roomService;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JPanel pnl_right;
    private JLabel lbl_address;
    private JLabel lbl_mail;
    private JLabel lbl_phone;
    private JLabel lbl_star;
    private JButton btn_update;
    public JLabel lbl_id;
    public int hotelID;

    public HotelUpdater(EmployeeGUI employeeGUI) {
        setContentPane(wrapper);
        setSize(800, 600);
        setTitle(Config.AGENCY_NAME + " Admin");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.setCenter("x", getSize()), Helper.setCenter("y", getSize()));


        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHotelByID(hotelID, fld_name.getText(), fld_address.getText(), fld_mail.getText(), fld_phone.getText(),
                        cmbBox_star.getSelectedItem().toString(), chcBox_carPark.isSelected(), chcBox_wifi.isSelected(),
                        chcBox_pool.isSelected(), chcBox_fitness.isSelected(), chcBox_concierge.isSelected(),
                        chcBox_spa.isSelected(), chcBox_roomService.isSelected());
                employeeGUI.hotelLoader(new Object[]{"id", "name", "address", "mail", "phone", "star", "car_park",
                        "wifi", "pool", "fitness", "concierge", "spa", "room_service"});
                HotelUpdater.super.dispose();
            }
        });
    }


}
