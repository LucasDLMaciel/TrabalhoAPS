package com.gdb.visao.login_registro;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;

public class TestLoginRegistro extends JFrame {

    public TestLoginRegistro() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(new Dimension(1280, 720));
        setLocationRelativeTo(null);

        add(new Login(false, 0));
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("login.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new TestLoginRegistro().setVisible(true));
    }
}
