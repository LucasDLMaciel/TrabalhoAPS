package com.gdb.visao.Menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.gdb.visao.login_registro.Login;
import com.gdb.visao.login_registro.Registro;
import com.gdb.visao.login_registro.TestLoginRegistro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ColorModel;
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
