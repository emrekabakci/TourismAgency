package com.tourismAgency.View;

import com.tourismAgency.Helper.Config;
import com.tourismAgency.Helper.Helper;
import com.tourismAgency.Model.Hotel;
import com.tourismAgency.Model.Pension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.tourismAgency.Model.Admin.deleteUser;
import static com.tourismAgency.Model.Admin.getList;
import static com.tourismAgency.Model.Hotel.deleteHotelByID;
import static com.tourismAgency.Model.Hotel.getHotels;
import static com.tourismAgency.Model.Pension.getPension;

public class EmployeeGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JPanel pnl_hotel;
    private JPanel Room;
    private JPanel Reservation;
    private JTable tbl_hotel;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JScrollPane scrl_hotel;
    private JScrollPane scrl_pension;
    private JScrollPane scrl_season;
    private JButton btn_addHotel;
    private DefaultTableModel mdl_hotel_list;
    private DefaultTableModel mdl_pension_list;
    private JPopupMenu action_menu;
    private int selectedRow;

    public EmployeeGUI() {
        setContentPane(wrapper);
        setSize(1920, 1080);
        setTitle(Config.AGENCY_NAME + " Personel");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(Helper.setCenter("x", getSize()), Helper.setCenter("y", getSize()));

        mdl_hotel_list = new DefaultTableModel();
        Object[] col_hotel_list = {"id", "name", "address", "mail", "phone", "star", "car_park", "wifi", "pool",
                "fitness", "concierge", "spa", "room_service"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);

        hotelLoader(col_hotel_list);

        tbl_hotel.setModel(mdl_hotel_list);
        tbl_hotel.getTableHeader().setReorderingAllowed(false);

        mdl_pension_list = new DefaultTableModel();
        Object[] col_pension_list = {"Id", "Otel Id", "Pansiyon Tipi"};
        mdl_pension_list.setColumnIdentifiers(col_pension_list);

        pensionLoader(col_pension_list);

        tbl_pension.setModel(mdl_pension_list);
        tbl_pension.getTableHeader().setReorderingAllowed(false);


        btn_addHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelAdderGUI hotelAdderGUI = new HotelAdderGUI(EmployeeGUI.this);
            }
        });

        tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                selectedRow = tbl_hotel.getSelectedRow();

            }
        });


        action_menu = new JPopupMenu();
        action_menu.add("Hotel Sil").addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null,
                    mdl_hotel_list.getValueAt(selectedRow, 0) +
                            " id'li kullanıcıyı" +
                            " silmek istediğinize" +
                            " emin misiniz?", "Emin misin?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                deleteHotelByID(mdl_hotel_list.getValueAt(selectedRow, 0).toString());
                hotelLoader(col_hotel_list);
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
        });
        action_menu.add("Otel Güncelle").addActionListener(e -> {
            HotelUpdater hotelUpdater = new HotelUpdater(EmployeeGUI.this);
            hotelUpdater.hotelID = Integer.parseInt(mdl_hotel_list.getValueAt(selectedRow,0).toString());
            hotelUpdater.lbl_id.setText("id: " + mdl_hotel_list.getValueAt(selectedRow, 0).toString());
            hotelUpdater.fld_name.setText(mdl_hotel_list.getValueAt(selectedRow, 1).toString());
            hotelUpdater.fld_address.setText(mdl_hotel_list.getValueAt(selectedRow, 2).toString());
            hotelUpdater.fld_mail.setText(mdl_hotel_list.getValueAt(selectedRow, 3).toString());
            hotelUpdater.fld_phone.setText(mdl_hotel_list.getValueAt(selectedRow, 4).toString());
            hotelUpdater.cmbBox_star.setSelectedItem(mdl_hotel_list.getValueAt(selectedRow, 5));
            if (Boolean.parseBoolean(mdl_hotel_list.getValueAt(selectedRow, 6).toString())) {
                hotelUpdater.chcBox_carPark.setSelected(true);
            }
            if (Boolean.parseBoolean(mdl_hotel_list.getValueAt(selectedRow, 7).toString())) {
                hotelUpdater.chcBox_wifi.setSelected(true);
            }
            if (Boolean.parseBoolean(mdl_hotel_list.getValueAt(selectedRow, 8).toString())) {
                hotelUpdater.chcBox_pool.setSelected(true);
            }
            if (Boolean.parseBoolean(mdl_hotel_list.getValueAt(selectedRow, 9).toString())) {
                hotelUpdater.chcBox_fitness.setSelected(true);
            }
            if (Boolean.parseBoolean(mdl_hotel_list.getValueAt(selectedRow, 10).toString())) {
                hotelUpdater.chcBox_concierge.setSelected(true);
            }
            if (Boolean.parseBoolean(mdl_hotel_list.getValueAt(selectedRow, 11).toString())) {
                hotelUpdater.chcBox_spa.setSelected(true);
            }
            if (Boolean.parseBoolean(mdl_hotel_list.getValueAt(selectedRow, 12).toString())) {
                hotelUpdater.chcBox_roomService.setSelected(true);
            }
        });
        tbl_hotel.setComponentPopupMenu(action_menu);
    }


    public void hotelLoader(Object[] col_hotel_list) {
        mdl_hotel_list.setRowCount(0);

        for (Hotel hotels : getHotels()) {
            Object[] row = new Object[col_hotel_list.length];
            row[0] = hotels.getId();
            row[1] = hotels.getName();
            row[2] = hotels.getAddress();
            row[3] = hotels.getMail();
            row[4] = hotels.getPhone();
            row[5] = hotels.getStar();
            row[6] = hotels.isCarPark();
            row[7] = hotels.isWifi();
            row[8] = hotels.isPool();
            row[9] = hotels.isFitness();
            row[10] = hotels.isConcierge();
            row[11] = hotels.isSpa();
            row[12] = hotels.isRoomService();
            mdl_hotel_list.addRow(row);
        }

    }

    private void pensionLoader(Object[] col_pension_list) {
        mdl_pension_list.setRowCount(0);

        for (Pension pensions : getPension()) {
            Object[] row = new Object[col_pension_list.length];
            row[0] = pensions.getId();
            row[1] = pensions.getHotelId();
            row[2] = pensions.getType();
            mdl_pension_list.addRow(row);
        }
    }


}
