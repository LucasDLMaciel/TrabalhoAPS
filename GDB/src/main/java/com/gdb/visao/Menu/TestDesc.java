package com.gdb.visao.Menu;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.awt.*;
import javax.swing.*;

public class TestDesc extends JFrame {
    public TestDesc(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1280, 720));
        setResizable(false);
        FlatSVGIcon iconFrame = new FlatSVGIcon("login/icon/logo.svg", 0.067F);
        this.setIconImage(iconFrame.getImage());
        setTitle("Descrição - Game Database");
        setLocationRelativeTo(null);
        add(new Desc(true, 0));
    };

    public static void main(String[] args) {
        TestDesc t = new TestDesc();
        FlatLaf.registerCustomDefaultsSource("login.themes");
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font("Roboto", Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new TestDesc().setVisible(true));
    }
}
