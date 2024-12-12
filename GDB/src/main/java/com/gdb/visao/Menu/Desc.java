package com.gdb.visao.Menu;

import com.formdev.flatlaf.FlatClientProperties;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Desc extends JPanel {
    public Desc() {
        init();
    }

    private void init() {
        JButton Voltar = new JButton("Voltar");
        Voltar.putClientProperty(FlatClientProperties.STYLE, "");
        Voltar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //logica de voltar pro menu
            }
        });
        JTextPane text = new JTextPane();
        text.setEditable(false);
        text.setBackground(Color.BLACK);
        add(text);
    }
}
