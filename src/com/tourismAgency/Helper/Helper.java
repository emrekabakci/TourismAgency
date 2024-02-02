package com.tourismAgency.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static int setCenter(String axis, Dimension size) {
        int point;
        switch (axis) {
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }

    public static void showMsg(String string) {
        String message = "";
        String title = "";
        switch (string) {
            case "error":
                message = "Hata oluştu.";
                title = "Hata!";
                break;
            case "fill":
                message = "Lütfen tüm boşlukları doldurunuz.";
                title = "Hata!";
                break;
            case "success":
                message = "İşlem Başarıyla tamamlandı.";
                title = "Başarılı!";
                break;
            case "incorrect":
                message = "Lütfen girdiğiniz bilgileri tekrar kontrol ediniz.";
                title = "Hata!";
                break;
            default:
                message = string;
                title = "Info";
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
