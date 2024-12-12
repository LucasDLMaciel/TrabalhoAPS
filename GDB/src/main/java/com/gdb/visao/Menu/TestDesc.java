package com.gdb.visao.Menu;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.awt.*;
import javax.swing.*;

public class TestDesc extends JFrame {
    public TestDesc(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1280, 720));
        setTitle("Descrição - Game Database");
        setLocationRelativeTo(null);
        add(new Desc());
    };

    public static void main(String[] args) {
        TestDesc t = new TestDesc();
        FlatLaf.registerCustomDefaultsSource("login.themes");
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font("Roboto", Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new TestDesc().setVisible(true));
    }
}
