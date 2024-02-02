package com.tourismAgency.View;

import com.mysql.cj.log.Log;
import com.tourismAgency.Helper.*;
import com.tourismAgency.Model.Admin;
import com.tourismAgency.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.tourismAgency.Model.Admin.getList;


public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wTop;
    private JPanel wBottom;
    private JLabel lbl_agencyName;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JLabel lbl_login;

    public LoginGUI() {
        setContentPane(wrapper);
        setSize(400, 400);
        setTitle("Login");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        lbl_agencyName.setText(Config.AGENCY_NAME);
        setLocation(Helper.setCenter("x", getSize()), Helper.setCenter("y", getSize()));

        btn_login.addActionListener(new ActionListener() {
            User loggedInUser = null;
            boolean userFound = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fld_username.getText().trim().isEmpty() && !fld_password.getText().trim().isEmpty()) {
                    for (User user : getList()) {
                        if (user.getUsername().equals(fld_username.getText()) && user.getPassword().equals(fld_password.getText())) {
                            loggedInUser = user;
                            userFound = true;
                            break;
                        }
                    }
                    if (userFound) {
                        if (loggedInUser.getType().equals("admin")) {
                            AdminGUI adminGUI = new AdminGUI(loggedInUser);
                            LoginGUI.super.dispose();
                        } else {
                            EmployeeGUI employeeGUI = new EmployeeGUI();
                            LoginGUI.super.dispose();
                        }
                    } else {
                        Helper.showMsg("incorrect");
                    }
                }else {
                    Helper.showMsg("fill");
                }
            }
        });
    }
}
