package com.gdb.visao.gerenciar;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;

public class TestGerenciarConta extends JFrame {

    public TestGerenciarConta() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(new Dimension(1280, 720));
        setLocationRelativeTo(null);

        //add(new Login());
        add(new GerenciarUsuario(true, 0));
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("login.themes");
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new TestGerenciarConta().setVisible(true));
    }
}
