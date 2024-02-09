package com.tourismAgency;

import com.tourismAgency.View.EmployeeGUI;
import com.tourismAgency.View.LoginGUI;
import com.tourismAgency.View.ReservationGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info);
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
            }
        }

//      LoginGUI login = new LoginGUI();
        EmployeeGUI employeeGUI = new EmployeeGUI();
        //ReservationGUI reservationGUI = new ReservationGUI();
    }
}
