package com.tourismAgency.View;

import com.tourismAgency.Helper.Config;
import com.tourismAgency.Helper.Helper;
import com.tourismAgency.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.tourismAgency.Model.Admin.getList;
import static com.tourismAgency.Model.Hotel.deleteHotelByID;
import static com.tourismAgency.Model.Hotel.getHotels;
import static com.tourismAgency.Model.Pension.getPension;
import static com.tourismAgency.Model.Reservation.deleteReservation;
import static com.tourismAgency.Model.Reservation.getReservations;
import static com.tourismAgency.Model.Room.*;
import static com.tourismAgency.Model.Season.getSeason;


public class EmployeeGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JPanel pnl_hotel;
    private JPanel Room;
    private JPanel pnl_reservation;
    private JTable tbl_hotel;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JScrollPane scrl_hotel;
    private JScrollPane scrl_pension;
    private JScrollPane scrl_season;
    private JButton btn_addHotel;
    private JTextField fld_name;
    private JTextField fld_city;
    private JTextField fld_seasonStart;
    private JTextField fld_seasonFinish;
    private JButton btn_search;
    private JButton btn_add;
    private JButton btn_reset;
    private JTable tbl_room;
    private JLabel lbl_hotelName;
    private JLabel lbl_cityName;
    private JLabel lbl_adult;
    private JLabel lbl_child;
    private JLabel lbl_checkInDate;
    private JLabel lbl_CheckOutDate;
    private JTextField fld_adult;
    private JTextField fld_child;
    private JTable tbl_reservation;
    private JScrollPane scrl_reservation;
    private DefaultTableModel mdl_hotel_list;
    private DefaultTableModel mdl_pension_list;
    private DefaultTableModel mdl_season_list;
    private DefaultTableModel mdl_room_list;
    private DefaultTableModel mdl_reservation_list;
    private JPopupMenu action_menu;
    private JPopupMenu room_action_menu;
    private JPopupMenu reservation_action_menu;
    private int selectedRow;
    private int selectedRoomRow;
    private int selectedReservationRow;

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

        mdl_season_list = new DefaultTableModel();
        Object[] col_season_list = {"Id", "Otel Id", "Sezon Başlangıç", "Sezon Bitiş"};
        mdl_season_list.setColumnIdentifiers(col_season_list);

        seasonLoader(col_season_list);

        tbl_season.setModel(mdl_season_list);
        tbl_season.getTableHeader().setReorderingAllowed(false);


        mdl_room_list = new DefaultTableModel();
        Object[] col_room_list = {"id", "Otel Adı", "Pansiyon", "Stok", "Yetişkin Fiyat", "Çocuk Fiyat", "Yatak Kapasitesi", "m2",
                "Tv", "Mini Bar", "Konsol", "Kasa", "Projeksiyon"};
        mdl_room_list.setColumnIdentifiers(col_room_list);

        roomLoader(col_room_list);

        tbl_room.setModel(mdl_room_list);
        tbl_room.getTableHeader().setReorderingAllowed(false);

        mdl_reservation_list = new DefaultTableModel();
        Object[] col_reservation_list = {"Id", "Oda Id", "Giriş Tarihi", "Çıkış Tarihi", "Toplam Tutar",
                "Misafir Sayısı", "Misafir Adı", "Misafir Kimlik No", "Mail", "Telefon"};
        mdl_reservation_list.setColumnIdentifiers(col_reservation_list);

        reservationLoader(col_reservation_list);

        tbl_reservation.setModel(mdl_reservation_list);
        tbl_reservation.getTableHeader().setReorderingAllowed(false);


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
                pensionLoader(col_pension_list);
                seasonLoader(col_season_list);


            }
        });

        tbl_room.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                selectedRoomRow = tbl_room.getSelectedRow();

            }
        });

        tbl_reservation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                selectedReservationRow = tbl_reservation.getSelectedRow();

            }
        });


        room_action_menu = new JPopupMenu();
        room_action_menu.add("Rezervasyon Oluştur").addActionListener(e -> {
            if (!fld_adult.getText().trim().isEmpty() && !fld_child.getText().trim().isEmpty() &&
                    !fld_seasonStart.getText().trim().isEmpty() && !fld_seasonFinish.getText().trim().isEmpty()) {
                if (Integer.parseInt(mdl_room_list.getValueAt(selectedRoomRow, 3).toString()) <= 0) {
                    Helper.showMsg("Yeterli stok bulunmamakta");
                } else {
                    String firstDate = fld_seasonStart.getText();
                    String finishDate = fld_seasonFinish.getText();
                    Date date1;
                    Date date2;
                    Date date3;
                    Date date4;
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");


                    try {
                        date1 = formatter.parse(firstDate);
                        date2 = formatter.parse(finishDate);
                        date3 = formatter.parse(getCheckIn());
                        date4 = formatter.parse(getCheckout());
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }

                    if (date1.getTime() >= date3.getTime() && date2.getTime() <= date4.getTime()) {
                        long differenceInMillis = Math.abs(date2.getTime() - date1.getTime());
                        long difference = TimeUnit.MILLISECONDS.toDays(differenceInMillis);
                        int param1 = Integer.parseInt(fld_child.getText());
                        int param2 = Integer.parseInt(fld_adult.getText());
                        int totalCustomer = param1 + param2;
                        int childPrice = Integer.parseInt(mdl_room_list.getValueAt(selectedRoomRow, 5).toString()) * param1;
                        int adultPrice = Integer.parseInt(mdl_room_list.getValueAt(selectedRoomRow, 4).toString()) * param2;
                        long result = (childPrice + adultPrice) * difference;
                        ReservationGUI reservationGUI = new ReservationGUI(EmployeeGUI.this);
                        reservationGUI.fld_hotelName.setText(mdl_room_list.getValueAt(selectedRoomRow, 1).toString());
                        reservationGUI.roomId = mdl_room_list.getValueAt(selectedRoomRow, 0).toString();
                        reservationGUI.fld_totalPrice.setText(String.valueOf(result));
                        reservationGUI.fld_totalCustomer.setText(String.valueOf(totalCustomer));
                        reservationGUI.fld_pensionType.setText(mdl_room_list.getValueAt(selectedRoomRow, 2).toString());
                        reservationGUI.fld_seasonStart.setText(fld_seasonStart.getText());
                        reservationGUI.fld_seasonFinish.setText(fld_seasonFinish.getText());
                        reservationGUI.fld_bedCapacity.setText(mdl_room_list.getValueAt(selectedRoomRow, 6).toString());
                        reservationGUI.fld_m2.setText(mdl_room_list.getValueAt(selectedRoomRow, 7).toString());
                        if (Boolean.parseBoolean(mdl_room_list.getValueAt(selectedRoomRow, 8).toString())) {
                            reservationGUI.rBtn_tv.setSelected(true);
                        }
                        if (Boolean.parseBoolean(mdl_room_list.getValueAt(selectedRoomRow, 9).toString())) {
                            reservationGUI.rBtn_minibar.setSelected(true);
                        }
                        if (Boolean.parseBoolean(mdl_room_list.getValueAt(selectedRoomRow, 10).toString())) {
                            reservationGUI.rBtn_console.setSelected(true);
                        }
                        if (Boolean.parseBoolean(mdl_room_list.getValueAt(selectedRoomRow, 11).toString())) {
                            reservationGUI.rBtn_safe.setSelected(true);
                        }
                        if (Boolean.parseBoolean(mdl_room_list.getValueAt(selectedRoomRow, 12).toString())) {
                            reservationGUI.rBtn_projection.setSelected(true);
                        }
                    } else {
                        Helper.showMsg("error");
                    }
                }
            } else {
                Helper.showMsg("fill");
            }

        });

        room_action_menu.add("Oda Sil").addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null,
                    mdl_reservation_list.getValueAt(selectedReservationRow, 0) +
                            " id'li odayı" +
                            " silmek istediğinize" +
                            " emin misiniz?\n" +
                            "Oda bilgileri ve odaya kayıtlı olan bütün rezervasyonlar kaybolacak.",
                    "Emin misin?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                deleteRoom(mdl_room_list.getValueAt(selectedRoomRow, 0).toString());
                increaseStock(mdl_room_list.getValueAt(selectedRoomRow, 1).toString());
                roomLoader(col_room_list);
                reservationLoader(col_reservation_list);
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

        tbl_room.setComponentPopupMenu(room_action_menu);

        reservation_action_menu = new JPopupMenu();
        reservation_action_menu.add("Rezervasyon Güncelle").addActionListener(e -> {
            ReservationGUI reservationGUI = new ReservationGUI(EmployeeGUI.this);
            reservationGUI.id = mdl_reservation_list.getValueAt(selectedReservationRow, 0).toString();
            reservationGUI.btn_saveReservation.setText("Rezervasyon Güncelle");
            reservationGUI.fld_hotelName.setText(String.valueOf(getRoom(mdl_reservation_list.
                    getValueAt(selectedReservationRow, 1).toString()).getHotelID()));

            reservationGUI.fld_pensionType.setText(getRoom(mdl_reservation_list.
                    getValueAt(selectedReservationRow, 1).toString()).getPensionType());

            reservationGUI.fld_bedCapacity.setText(String.valueOf(getRoom(mdl_reservation_list.getValueAt(selectedReservationRow, 1).toString()).getBedCapacity()));

            reservationGUI.fld_m2.setText(String.valueOf(getRoom(mdl_reservation_list.getValueAt(selectedReservationRow, 1).toString()).getM2()));

            reservationGUI.fld_seasonStart.setText(mdl_reservation_list.getValueAt(selectedReservationRow, 2).toString());
            reservationGUI.fld_seasonFinish.setText(mdl_reservation_list.getValueAt(selectedReservationRow, 3).toString());
            reservationGUI.fld_totalPrice.setText(mdl_reservation_list.getValueAt(selectedReservationRow, 4).toString());
            reservationGUI.fld_totalCustomer.setText(mdl_reservation_list.getValueAt(selectedReservationRow, 5).toString());
            reservationGUI.fld_customerName.setText(mdl_reservation_list.getValueAt(selectedReservationRow, 6).toString());
            reservationGUI.fld_customerID.setText(mdl_reservation_list.getValueAt(selectedReservationRow, 7).toString());
            reservationGUI.fld_customerMail.setText(mdl_reservation_list.getValueAt(selectedReservationRow, 8).toString());
            reservationGUI.fld_customerPhone.setText(mdl_reservation_list.getValueAt(selectedReservationRow, 9).toString());


            if (getRoom(mdl_reservation_list.getValueAt(selectedReservationRow, 1).toString()).isTelevision()) {
                reservationGUI.rBtn_tv.setSelected(true);
            }
            if (getRoom(mdl_reservation_list.getValueAt(selectedReservationRow, 1).toString()).isMinibar()) {
                reservationGUI.rBtn_minibar.setSelected(true);
            }
            if (getRoom(mdl_reservation_list.getValueAt(selectedReservationRow, 1).toString()).isConsole()) {
                reservationGUI.rBtn_console.setSelected(true);
            }
            if (getRoom(mdl_reservation_list.getValueAt(selectedReservationRow, 1).toString()).isSafe()) {
                reservationGUI.rBtn_safe.setSelected(true);
            }
            if (getRoom(mdl_reservation_list.getValueAt(selectedReservationRow, 1).toString()).isProjection()) {
                reservationGUI.rBtn_projection.setSelected(true);
            }
        });
        reservation_action_menu.add("Rezervasyon Sil").addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null,
                    mdl_reservation_list.getValueAt(selectedReservationRow, 0) +
                            " id'li rezervasyonu" +
                            " silmek istediğinize" +
                            " emin misiniz?", "Emin misin?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                deleteReservation(mdl_reservation_list.getValueAt(selectedReservationRow, 0).toString());
                increaseStock(mdl_reservation_list.getValueAt(selectedReservationRow, 1).toString());
                reservationLoader(col_reservation_list);
                roomLoader(col_room_list);
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
        tbl_reservation.setComponentPopupMenu(reservation_action_menu);

        action_menu = new JPopupMenu();
        action_menu.add("Hotel Sil").addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null,
                    mdl_hotel_list.getValueAt(selectedRow, 0) +
                            " id'li hoteli" +
                            " silmek istediğinize" +
                            " emin misiniz?\n" +
                            "Hotele kayıtlı bütün oda, rezervasyonlar pansiyonlar ve sezonlar kaybedilecek.",
                    "Emin misin?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                deleteHotelByID(mdl_hotel_list.getValueAt(selectedRow, 0).toString());
                hotelLoader(col_hotel_list);
                roomLoader(col_room_list);
                reservationLoader(col_reservation_list);
                mdl_pension_list.setRowCount(0);
                mdl_season_list.setRowCount(0);
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
            hotelUpdater.hotelID = Integer.parseInt(mdl_hotel_list.getValueAt(selectedRow, 0).toString());
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

        action_menu.add("Pansiyon Ekle").addActionListener(e -> {
            PensionAdderGUI pensionAdderGUI = new PensionAdderGUI(EmployeeGUI.this);
            pensionAdderGUI.HotelID = Integer.parseInt(mdl_hotel_list.getValueAt(selectedRow, 0).toString());
        });

        action_menu.add("Sezon Ekle").addActionListener(e -> {
            SeasonAdderGUI seasonAdderGUI = new SeasonAdderGUI(EmployeeGUI.this);
            seasonAdderGUI.HotelID = Integer.parseInt(mdl_hotel_list.getValueAt(selectedRow, 0).toString());
            seasonAdderGUI.lbl_hotelID.setText("Otel ID: " + mdl_hotel_list.getValueAt(selectedRow, 0).toString());
        });

        action_menu.add("Oda Ekle").addActionListener(e -> {
            RoomAdderGUI roomAdderGUI = new RoomAdderGUI(EmployeeGUI.this);
            roomAdderGUI.lbl_choosed.setText(mdl_hotel_list.getValueAt(selectedRow, 0).toString());
        });

        tbl_hotel.setComponentPopupMenu(action_menu);


        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomLoader(col_room_list);
            }
        });

        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = fld_city.getText();
                String name = fld_name.getText();
                roomLoaderForSearch(col_room_list, city, name);
            }
        });
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

    public void pensionLoader(Object[] col_pension_list) {
        mdl_pension_list.setRowCount(0);

        for (Pension pensions : getPension(mdl_hotel_list.getValueAt(selectedRow, 0).toString())) {
            Object[] row = new Object[col_pension_list.length];
            row[0] = pensions.getId();
            row[1] = pensions.getHotelId();
            row[2] = pensions.getType();
            mdl_pension_list.addRow(row);
        }
    }

    public void seasonLoader(Object[] col_season_list) {
        mdl_season_list.setRowCount(0);

        for (Season seasons : getSeason(mdl_hotel_list.getValueAt(selectedRow, 0).toString())) {
            Object[] row = new Object[col_season_list.length];
            row[0] = seasons.getId();
            row[1] = seasons.getHotelID();
            row[2] = seasons.getSeason();
            row[3] = seasons.getSeasonFinish();
            mdl_season_list.addRow(row);
        }
    }

    public void roomLoader(Object[] col_room_list) {
        mdl_room_list.setRowCount(0);

        for (Room rooms : getRooms()) {
            Object[] row = new Object[col_room_list.length];
            row[0] = rooms.getId();
            row[1] = rooms.getHotelID();
            row[2] = rooms.getPensionType();
            row[3] = rooms.getStock();
            row[4] = rooms.getAdultPrice();
            row[5] = rooms.getChildPrice();
            row[6] = rooms.getBedCapacity();
            row[7] = rooms.getM2();
            row[8] = rooms.isTelevision();
            row[9] = rooms.isMinibar();
            row[10] = rooms.isConsole();
            row[11] = rooms.isSafe();
            row[12] = rooms.isProjection();
            mdl_room_list.addRow(row);
        }
    }

    public void reservationLoader(Object[] col_reservation_list) {
        mdl_reservation_list.setRowCount(0);

        for (Reservation reservations : getReservations()) {
            Object[] row = new Object[col_reservation_list.length];
            row[0] = reservations.getId();
            row[1] = reservations.getRoomId();
            row[2] = reservations.getSeasonStart();
            row[3] = reservations.getSeasonFinish();
            row[4] = reservations.getTotalPrice();
            row[5] = reservations.getTotalCustomer();
            row[6] = reservations.getCustomerName();
            row[7] = reservations.getCustomerId();
            row[8] = reservations.getCustomerMail();
            row[9] = reservations.getCustomerPhone();
            mdl_reservation_list.addRow(row);
        }
    }

    public void roomLoaderForSearch(Object[] col_room_list, String name, String city) {
        mdl_room_list.setRowCount(0);

        for (Room rooms : getRoomsBySearch(name, city)) {
            Object[] row = new Object[col_room_list.length];
            row[0] = rooms.getId();
            row[1] = rooms.getHotelID();
            row[2] = rooms.getPensionType();
            row[3] = rooms.getStock();
            row[4] = rooms.getAdultPrice();
            row[5] = rooms.getChildPrice();
            row[6] = rooms.getBedCapacity();
            row[7] = rooms.getM2();
            row[8] = rooms.isTelevision();
            row[9] = rooms.isMinibar();
            row[10] = rooms.isConsole();
            row[11] = rooms.isSafe();
            row[12] = rooms.isProjection();
            mdl_room_list.addRow(row);
        }
    }

    private String getCheckIn() {
        String checkIn = null;
        for (Season seasons : getSeason(mdl_room_list.getValueAt(selectedRoomRow, 1).toString())) {

            checkIn = seasons.getSeason();

        }
        return checkIn;
    }

    private String getCheckout() {
        String checkOut = null;
        for (Season seasons : getSeason(mdl_room_list.getValueAt(selectedRoomRow, 1).toString())) {

            checkOut = seasons.getSeasonFinish();

        }
        return checkOut;
    }

}
