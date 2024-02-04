package com.tourismAgency.View;

import com.tourismAgency.Helper.Config;
import com.tourismAgency.Helper.Helper;
import com.tourismAgency.Model.Pension;
import com.tourismAgency.Model.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.tourismAgency.Model.Room.addRoom;

public class RoomAdderGUI extends JFrame {
    private JPanel wrapper;
    private JComboBox cmbBox_pensionType;
    private JComboBox cmbBox_season;
    private JComboBox cmbBox_roomType;
    private JTextField fld_stock;
    private JTextField fld_adultPrice;
    private JTextField fld_childPrice;
    private JLabel lbl_pensionAdd;
    private JTextField fld_bedCapacity;
    private JTextField fld_m2;
    private JRadioButton rBtn_tv;
    private JRadioButton rBtn_minibar;
    private JRadioButton rBtn_console;
    private JRadioButton rBtn_safe;
    private JRadioButton rBtn_projection;
    public JLabel lbl_choosed;
    private JButton btn_save;
    private JButton btn_getCmbBox;


    Object[] col_room_list = {"id", "Otel Adı", "Pansiyon", "Stok", "Yetişkin Fiyat", "Çocuk Fiyat", "Yatak Kapasitesi", "m2",
            "Tv", "Mini Bar", "Konsol", "Kasa", "Projeksiyon"};

    public RoomAdderGUI(EmployeeGUI employeeGUI) {
        setContentPane(wrapper);
        setSize(800, 600);
        setTitle(Config.AGENCY_NAME + " Admin");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.setCenter("x", getSize()), Helper.setCenter("y", getSize()));


        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoom(Integer.parseInt(lbl_choosed.getText()), cmbBox_pensionType.getSelectedItem().toString(),
                        fld_stock.getText(), fld_adultPrice.getText(), fld_childPrice.getText(),
                        fld_bedCapacity.getText(), fld_m2.getText(), rBtn_tv.isSelected(), rBtn_minibar.isSelected(),
                        rBtn_console.isSelected(), rBtn_safe.isSelected(), rBtn_projection.isSelected());
                employeeGUI.roomLoader(col_room_list);
                RoomAdderGUI.super.dispose();
            }
        });
        btn_getCmbBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pensionsCmbBox();
                seasonCmbBox();
            }
        });
    }

    private void pensionsCmbBox() {

        ArrayList<Pension> pensions = Pension.getPension(lbl_choosed.getText());

        cmbBox_pensionType.removeAllItems();

        for (Pension pension : pensions) {
            cmbBox_pensionType.addItem(pension.getType());
        }


    }

    private void seasonCmbBox(){
        ArrayList<Season> seasons = Season.getSeason(lbl_choosed.getText());

        cmbBox_season.removeAllItems();

        for (Season season : seasons) {
            cmbBox_season.addItem(season.getSeason());
        }
    }


}
