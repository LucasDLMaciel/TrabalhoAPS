package com.gdb.visao.Menu;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class TestMenu extends JFrame {
    public TestMenu() {
    }

    public static void main(String[] args) {
        FlatLaf.registerCustomDefaultsSource("login.themes");
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font("Roboto", Font.PLAIN, 13));
        new Menu();
    }
}