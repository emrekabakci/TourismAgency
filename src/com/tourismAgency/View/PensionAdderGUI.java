package com.tourismAgency.View;

import com.tourismAgency.Helper.Config;
import com.tourismAgency.Helper.Helper;
import com.tourismAgency.Model.Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.tourismAgency.Model.Pension.addPension;

public class PensionAdderGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_top;
    private JLabel lbl_name;
    private JComboBox cmbBox_type;
    public JLabel lbl_hotelID;
    private JButton btn_add;
    private JPanel pnl_bottom;
    public int HotelID;

    public PensionAdderGUI(EmployeeGUI employeeGUI){
        setContentPane(wrapper);
        setSize(600, 800);
        setTitle(Config.AGENCY_NAME + " Personel");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.setCenter("x", getSize()), Helper.setCenter("y", getSize()));
        Object[] col_pension_list = {"Id", "Otel Id", "Pansiyon Tipi"};
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPension(HotelID, cmbBox_type.getSelectedItem().toString());
                employeeGUI.pensionLoader(col_pension_list);
                PensionAdderGUI.super.dispose();
            }
        });
    }
}
