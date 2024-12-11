package com.gdb.visao.Menu;

import com.formdev.flatlaf.FlatClientProperties;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Desc extends JPanel {
    private boolean darkTheme;
    private int idUsuario;
    public Desc(boolean darkTheme, int idUsuario) {
        this.idUsuario = idUsuario;
        this.darkTheme = darkTheme;
        init();
    }

    private void init() {
        this.setLayout(null);
        JButton voltar = new JButton("voltar");
        voltar.setBounds(0, 0, 60, 30);
        voltar.putClientProperty(FlatClientProperties.STYLE, "");
        add(voltar);
        voltar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Menu menu = new Menu(darkTheme, idUsuario);
                Container container = getParent();
                container.removeAll();
                container.add(menu);
                container.revalidate();
                container.repaint();
            }
        });
        JTextPane text = new JTextPane();
        text.setEditable(false);
        text.setBackground(Color.BLACK);
        add(text);
    }
}
