package com.tourismAgency.View;

import com.tourismAgency.Helper.Config;
import com.tourismAgency.Helper.Helper;
import com.tourismAgency.Model.Admin;
import com.tourismAgency.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.tourismAgency.Model.Admin.*;
import static com.tourismAgency.Model.User.*;

public class AdminGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_admin;
    private JPanel pnl_users;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private DefaultTableModel mdl_user_list;
    private JLabel lbl_welcome;
    private JButton btn_addUser;
    private JLabel lbl_name;
    private JTextField fld_name;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_password;
    private JTextField fld_password;
    private JComboBox cmbBox_type;
    private JButton btn_deleteUser;
    private JComboBox cmb_type;
    private JButton btn_search;
    private JButton btn_update;
    private Object[] row_user_list;
    int selectedRow;

    private final User user;

    public AdminGUI(User user) {
        this.user = user;
        setContentPane(wrapper);
        setSize(1920, 1080);
        setTitle(Config.AGENCY_NAME + " Admin");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.setCenter("x", getSize()), Helper.setCenter("y", getSize()));


        lbl_welcome.setText("Hoşgeldin: " + user.getName());

        //Userlist Model

        mdl_user_list = new DefaultTableModel();
        Object[] col_user_list = {"ID", "İsim", "Kullanıcı Adı", "Şifre", "Rol"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        userLoader(col_user_list);


        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        btn_addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fld_name.getText().trim().isEmpty() && !fld_username.getText().trim().isEmpty() && !fld_password.getText().trim().isEmpty()) {
                    addUser(fld_name.getText(), fld_username.getText(), fld_password.getText(), cmbBox_type.
                            getSelectedItem().toString());
                    userLoader(col_user_list);
                    fld_name.setText("");
                    fld_username.setText("");
                    fld_password.setText("");
                } else {
                    Helper.showMsg("fill");
                }
            }
        });
        tbl_user_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                selectedRow = tbl_user_list.getSelectedRow();

                fld_name.setText(mdl_user_list.getValueAt(selectedRow, 1).toString());
                fld_username.setText(mdl_user_list.getValueAt(selectedRow, 2).toString());
                fld_password.setText(mdl_user_list.getValueAt(selectedRow, 3).toString());

            }
        });
        btn_deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, fld_username.getText() +
                        " Kullanıcısını" +
                        " silmek istediğinize" +
                        " emin misiniz?", "Emin misin?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    deleteUser(mdl_user_list.getValueAt(selectedRow, 0).toString());
                    userLoader(col_user_list);
                    fld_name.setText("");
                    fld_username.setText("");
                    fld_password.setText("");
                    JOptionPane.showMessageDialog(
                            null,
                            "İşlem başarıyla gerçekleşti",
                            "Başarılı",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "İşlem başarıyla iptal edildi",
                            "Başarılı",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userLoader(col_user_list);
            }
        });
        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateUser(mdl_user_list.getValueAt(selectedRow, 0).toString(), fld_name.getText(),
                        fld_username.getText(), fld_password.getText(), cmbBox_type.
                                getSelectedItem().toString());
                userLoader(col_user_list);
            }
        });
    }


    private void userLoader(Object[] col_user_list) {
        mdl_user_list.setRowCount(0);

        for (User users : getList(cmb_type.getSelectedItem().toString())) {
            Object[] row = new Object[col_user_list.length];
            row[0] = users.getId();
            row[1] = users.getName();
            row[2] = users.getUsername();
            row[3] = users.getPassword();
            row[4] = users.getType();
            mdl_user_list.addRow(row);
        }

    }
}

