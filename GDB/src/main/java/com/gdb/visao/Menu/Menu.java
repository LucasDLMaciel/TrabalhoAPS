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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JFrame {
    private JTextField Buscar;
    private JButton Sair_Botao;
    private JPanel MenuPainel;
    private JButton Login_Botao;
    private JButton todosButton;
    private JButton recomendadosButton;
    private JButton database;
    private JButton jogo1;
    private JButton jogo2;
    private JButton jogo3;
    private JButton jogo4;
    private JLabel desc1;
    private JLabel desc2;
    private JLabel desc3;
    private JLabel desc4;
    private boolean selecionado;

    public Menu() {
        this.init();
    }

    private void init() {
        this.setSize(new Dimension(1280, 720));
        this.selecionado = true;
        this.database = new JButton(new FlatSVGIcon("Menu/database.svg", 0.067F));
        this.desc1 = new JLabel("Descrição jogo 1");
        this.desc2 = new JLabel("Descrição jogo 2");
        this.desc3 = new JLabel("Descrição jogo 3");
        this.desc4 = new JLabel("Descrição jogo 4");
        this.jogo1 = new JButton("jogo1");
        this.jogo2 = new JButton("jogo2");
        this.jogo3 = new JButton("jogo3");
        this.jogo4 = new JButton("jogo4");
        this.Buscar = new JTextField();
        this.Sair_Botao = new JButton("Sair");
        this.MenuPainel = new JPanel();
        this.MenuPainel.setLayout((LayoutManager) null);
        this.Login_Botao = new JButton("Login");
        this.todosButton = new JButton("Todos");
        this.recomendadosButton = new JButton("Recomendados");

        this.atulizarPosObjetos();

        this.jogo1.setBackground(Color.red);
        this.jogo2.setBackground(Color.blue);
        this.jogo3.setBackground(Color.green);
        this.jogo4.setBackground(Color.black);
        this.desc1.setForeground(Color.black);
        this.desc2.setForeground(Color.black);
        this.desc3.setForeground(Color.black);
        this.desc4.setForeground(Color.black);
        this.Buscar.setText("Buscar");
        Color corTodos = todosButton.getBackground();
        Color corRecomendado = recomendadosButton.getBackground();
        this.Buscar.setFocusable(false);
        Login_Botao.setFocusable(false);
        Sair_Botao.setFocusable(false);
        todosButton.setFocusable(true);
        todosButton.setBackground(new Color(200,200,200));
        this.database.setContentAreaFilled(false);
        this.MenuPainel.add(this.Buscar);
        this.MenuPainel.add(this.Sair_Botao);
        this.MenuPainel.add(this.Login_Botao);
        this.MenuPainel.add(this.todosButton);
        this.MenuPainel.add(this.recomendadosButton);
        this.MenuPainel.add(this.database);
        this.setContentPane(this.MenuPainel);

        Buscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Menu/icon-buscar.svg", 0.009f));
        this.database.putClientProperty(FlatClientProperties.STYLE, "foreground: #FFFFFF");
        jogo1.putClientProperty(FlatClientProperties.STYLE, "foreground: #FFFFFF");
        jogo2.putClientProperty(FlatClientProperties.STYLE, "foreground: #FFFFFF");
        jogo3.putClientProperty(FlatClientProperties.STYLE, "foreground: #FFFFFF");
        jogo4.putClientProperty(FlatClientProperties.STYLE, "foreground: #FFFFFF");
        Buscar.putClientProperty(FlatClientProperties.STYLE, "foreground: #FFFFFF");
        Sair_Botao.putClientProperty(FlatClientProperties.STYLE, "foreground: #FFFFFF");
        Login_Botao.putClientProperty(FlatClientProperties.STYLE, "foreground: #FFFFFF");
        todosButton.putClientProperty(FlatClientProperties.STYLE, "foreground: #FFFFFF");
        recomendadosButton.putClientProperty(FlatClientProperties.STYLE, "foreground: #FFFFFF");
        FlatSVGIcon iconFrame = new FlatSVGIcon("login/icon/logo.svg", 0.067F);
        this.setIconImage(iconFrame.getImage());
        this.setTitle("Menu");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(this.jogo1);
        this.add(this.jogo2);
        this.add(this.jogo3);
        this.add(this.jogo4);
        this.add(this.desc1);
        this.add(this.desc2);
        this.add(this.desc3);
        this.add(this.desc4);

        this.Sair_Botao.setCursor(new Cursor(12));
        this.Login_Botao.setCursor(new Cursor(12));
        this.todosButton.setCursor(new Cursor(12));
        this.recomendadosButton.setCursor(new Cursor(12));
        this.database.setCursor(new Cursor(12));

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Menu.this.atulizarPosObjetos();
            }
        });
        this.Buscar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Menu.this.Buscar.setFocusable(true);
            }
        });
        this.MenuPainel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Menu.this.Buscar.setFocusable(false);
            }
        });
        this.Buscar.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (Menu.this.Buscar.getText().equals("Buscar")) {
                    Menu.this.Buscar.setText("");
                }

            }

            public void focusLost(FocusEvent e) {
                if (Menu.this.Buscar.getText().isEmpty()) {
                    Menu.this.Buscar.setText("Buscar");
                }

            }
        });
        this.Sair_Botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sair_Botao.setFocusable(true);
                if (JOptionPane.showConfirmDialog(Menu.this, "Você tem certeza que quer sair da aplicação?", "Sair", 0) == 0) {
                    System.exit(0);
                }

            }
        });
        this.Login_Botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login_Botao.setFocusable(true);
                JOptionPane.showMessageDialog(Menu.this, "Você está sendo redirecionado para a tela de login");
                EventQueue.invokeLater(() -> new TestLoginRegistro().setVisible(true));
                MenuPainel.setVisible(false);
            }
        });
        this.todosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selecionado = true;
                recomendadosButton.setBackground(corRecomendado);
                todosButton.setBackground(new Color(200, 200, 200));
                Menu.this.selecionado = true;
                Menu.this.jogo1.setVisible(true);
                Menu.this.jogo4.setVisible(true);
                Menu.this.desc1.setVisible(true);
                Menu.this.desc4.setVisible(true);
                Menu.this.atulizarPosObjetos();
            }
        });
        this.recomendadosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                todosButton.setBackground(corTodos);
                recomendadosButton.setBackground(new Color(200, 200, 200));
                Menu.this.jogo1.setVisible(false);
                Menu.this.jogo4.setVisible(false);
                Menu.this.desc1.setVisible(false);
                Menu.this.desc4.setVisible(false);
                Menu.this.jogo3.setBounds(Menu.this.jogo2.getX(), Menu.this.jogo2.getY(), Menu.this.jogo2.getWidth(), Menu.this.jogo2.getHeight());
                Menu.this.desc3.setBounds(Menu.this.desc2.getX(), Menu.this.desc2.getY(), Menu.this.desc2.getWidth(), Menu.this.desc2.getHeight());
                Menu.this.jogo2.setBounds(Menu.this.jogo1.getX(), Menu.this.jogo1.getY(), Menu.this.jogo1.getWidth(), Menu.this.jogo1.getHeight());
                Menu.this.desc2.setBounds(Menu.this.desc1.getX(), Menu.this.desc1.getY(), Menu.this.desc1.getWidth(), Menu.this.desc1.getHeight());
                Menu.this.jogo2.setBounds(Menu.this.jogo1.getX(), Menu.this.jogo1.getY(), 200, 200);
                Menu.this.desc2.setBounds(Menu.this.jogo2.getX(), Menu.this.desc1.getY(), 100, 50);
                Menu.this.jogo3.setBounds(Menu.this.jogo2.getX() + Menu.this.jogo2.getWidth() + 150, Menu.this.jogo1.getY(), 200, 200);
                Menu.this.desc3.setBounds(Menu.this.jogo3.getX(), Menu.this.desc1.getY(), 100, 50);
                Menu.this.jogo2.setVisible(true);
                Menu.this.jogo3.setVisible(true);
                Menu.this.desc2.setVisible(true);
                Menu.this.desc3.setVisible(true);
            }
        });
    }

    private void atulizarPosObjetos() {
        this.Sair_Botao.setSize(60, 30);
        this.database.setBounds(this.getWidth() / 2 - 45, 50, 90, 90);
        this.Sair_Botao.setBounds(this.getWidth() - this.Sair_Botao.getWidth() - 15, 0, 60, 30);
        this.Buscar.setBounds(0, 0, 0, 30);
        this.Login_Botao.setBounds(this.Sair_Botao.getX() - 70, 0, 70, 30);
        this.todosButton.setBounds(this.Buscar.getX(), this.Buscar.getHeight(), 70, 30);
        this.jogo1.setSize(200, 200);
        this.jogo1.setBounds(this.getWidth() / 28, 300, 200, 200);
        this.desc1.setBounds(this.jogo1.getX(), this.jogo1.getY() + this.jogo1.getHeight(), 100, 50);
        this.jogo2.setBounds(this.jogo1.getX() + this.jogo1.getWidth() + 150, this.jogo1.getY(), 200, 200);
        this.desc2.setBounds(this.jogo2.getX(), this.jogo1.getY() + this.jogo1.getHeight(), 100, 50);
        this.jogo3.setBounds(this.jogo2.getX() + this.jogo1.getWidth() + 150, this.jogo1.getY(), 200, 200);
        this.desc3.setBounds(this.jogo3.getX(), this.jogo1.getY() + this.jogo1.getHeight(), 100, 50);
        this.jogo4.setBounds(this.jogo3.getX() + this.jogo1.getWidth() + 150, this.jogo1.getY(), 200, 200);
        if (this.jogo4.getX() + this.jogo4.getWidth() > this.getWidth()) {
            jogo2.setBounds(this.jogo1.getX() + this.jogo1.getWidth() + 130, this.jogo1.getY(), 200, 200);
            this.desc2.setBounds(this.jogo2.getX(), this.jogo1.getY() + this.jogo1.getHeight(), 100, 50);
            this.jogo3.setBounds(this.jogo2.getX() + this.jogo1.getWidth() + 150, this.jogo1.getY(), 200, 200);
            this.desc3.setBounds(this.jogo3.getX(), this.jogo1.getY() + this.jogo1.getHeight(), 100, 50);
            this.jogo4.setBounds(this.getWidth() - this.jogo4.getWidth() - 15, this.jogo1.getY(), 200, 200);
            this.jogo1.setBounds(0, this.jogo1.getY(), this.jogo1.getWidth(), this.jogo1.getHeight());
        }

        this.desc4.setBounds(this.jogo4.getX(), this.jogo1.getY() + this.jogo1.getHeight(), 100, 50);
        this.recomendadosButton.setBounds(this.todosButton.getX() + this.todosButton.getWidth(), this.todosButton.getY(), this.todosButton.getWidth() + 60, 30);
        this.Buscar.setSize(this.todosButton.getWidth() + this.recomendadosButton.getWidth(), this.Buscar.getHeight());
    }
}