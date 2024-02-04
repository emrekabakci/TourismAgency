package com.tourismAgency.View;

import com.tourismAgency.Helper.Config;
import com.tourismAgency.Helper.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.tourismAgency.Model.Season.addSeason;

public class SeasonAdderGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_top;
    private JTextField fld_season;
    private JLabel lbl_season;
    private JLabel lbl_addSeason;
    public JLabel lbl_hotelID;
    private JButton btn_add;
    private JTextField fld_season_finish;
    private JLabel lbl_season_finish;
    public int HotelID;

    public SeasonAdderGUI(EmployeeGUI employeeGUI){
        setContentPane(wrapper);
        setSize(600, 800);
        setTitle(Config.AGENCY_NAME + " Personel");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.setCenter("x", getSize()), Helper.setCenter("y", getSize()));
        lbl_hotelID.setText("Otel ID: " + HotelID);
        Object[] col_season_list = {"Id", "Otel Id", "Sezon Başlangıcı", "Sezon Bitişi"};

        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSeason(HotelID, fld_season.getText(), fld_season_finish.getText());
                employeeGUI.seasonLoader(col_season_list);
                SeasonAdderGUI.super.dispose();
            }
        });
    }
}
