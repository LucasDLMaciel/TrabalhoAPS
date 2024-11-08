package TrabalhoAPS.Programa.GDB.Visao;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame {

    public Menu() {
        //setContentPane(painel);
        setTitle("Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        device.setFullScreenWindow(this);
        setSize(500, 300);
        setLocationRelativeTo(null);

        setLayout(null);
        JButton Sair = new JButton("Sair");
        Sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Menu.this, "Você está saindo da sua conta");
            }
        });
        Sair.setSize(50, 30);
        add(Sair);


        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }

}

