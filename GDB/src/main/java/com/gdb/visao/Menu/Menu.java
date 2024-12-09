package com.gdb.visao.Menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.gdb.controle.UsuarioControle;
import com.gdb.modelo.Usuario;
import com.gdb.visao.gerenciar.GerenciarGenero;
import com.gdb.visao.gerenciar.GerenciarUsuarios;
import com.gdb.visao.login_registro.Login;
import com.gdb.visao.gerenciar.GerenciarConta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Menu extends JPanel {
    private JTextField Buscar;
    private JButton Sair_Botao;
    private JButton Login_Botao;
    private JButton todosButton;
    private JButton recomendadosButton;
    JButton toggleThemeButton;
    private JButton database;
    private JButton jogo1;
    private JButton jogo2;
    private JButton jogo3;
    private JButton jogo4;
    private JLabel desc1;
    private JLabel desc2;
    private JLabel desc3;
    private JLabel desc4;
    private JButton gerenciarConta;
    private JButton gerenciarGenero;
    private JButton gerenciarUsuarios;

    private Integer idUsuario;
    private boolean admin = true;
    private boolean selecionado;
    private boolean darkTheme;
    private UsuarioControle usuarioControle;
    private Usuario usuario;



    public Menu(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        this.usuarioControle = new UsuarioControle();
        usuario = usuarioControle.buscarUsuarioPorId(idUsuario);
        if(usuario != null){
            this.admin = usuario.isAdministrador();
        }
        this.init();
    }

    private void toggleTheme() {
        // Alterna o tema e redefine o estilo
        if (darkTheme) {
            FlatMacLightLaf.setup();
        } else {
            FlatMacDarkLaf.setup();
        }
        darkTheme = !darkTheme;

        // Atualiza o tema para todos os componentes
        SwingUtilities.updateComponentTreeUI(SwingUtilities.getWindowAncestor(this));
    }

    private void init() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.selecionado = true;
        this.gerenciarConta = new JButton(new FlatSVGIcon("login/icon/usuario.svg", 0.035f));
        this.gerenciarUsuarios = new JButton();
        this.gerenciarGenero = new JButton();
        this.database = new JButton(new FlatSVGIcon("Menu/database.svg", 0.067F));
        gerenciarGenero.setText("Gerenciar gêneros");
        gerenciarUsuarios.setText("Gerenciar usuarios");
        gerenciarConta.setText("Conta");
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
        this.setLayout(null);  // Define layout como null para posicionamento absoluto
        this.Login_Botao = new JButton("Login");
        this.todosButton = new JButton("Todos");
        this.recomendadosButton = new JButton("Recomendados");
        this.toggleThemeButton = new JButton(new FlatSVGIcon("Menu/luaSol.svg", 1f));

        this.atulizarPosObjetos();

        // Configuração de botões e descrições
        this.jogo1.setBackground(Color.red);
        this.jogo2.setBackground(Color.blue);
        this.jogo3.setBackground(Color.green);
        this.jogo4.setBackground(Color.black);
        this.desc1.setForeground(Color.black);
        this.desc2.setForeground(Color.black);
        this.desc3.setForeground(Color.black);
        this.desc4.setForeground(Color.black);
        gerenciarUsuarios.setBackground(new Color(109, 1, 235));
        gerenciarGenero.setBackground(new Color(109, 1, 235));
        gerenciarUsuarios.setFocusable(false);
        gerenciarGenero.setFocusable(false);
        todosButton.setFocusable(true);
        Sair_Botao.setFocusable(false);
        Login_Botao.setFocusable(false);
        gerenciarConta.setFocusable(false);
        this.Buscar.setText("Buscar");
        this.Buscar.setFocusable(false);
        this.database.setFocusPainted(false);
        this.database.setBorderPainted(false);
        this.database.setContentAreaFilled(false);

        // Adiciona componentes ao painel
        if (idUsuario > 0) {
          add(this.gerenciarConta);
        } else this.add(this.Login_Botao);
        if (idUsuario > 0 && admin){
            add(this.gerenciarUsuarios);
            add(this.gerenciarGenero);
        }
        this.add(this.Buscar);
        this.add(this.Sair_Botao);
        this.add(this.todosButton);
        this.add(this.recomendadosButton);
        this.add(this.toggleThemeButton);
        this.add(this.database);
        this.add(this.jogo1);
        this.add(this.jogo2);
        this.add(this.jogo3);
        this.add(this.jogo4);
        this.add(this.desc1);
        this.add(this.desc2);
        this.add(this.desc3);
        this.add(this.desc4);

        toggleThemeButton.addActionListener(e -> toggleTheme());


        gerenciarUsuarios.putClientProperty(FlatClientProperties.STYLE, "");
        gerenciarGenero.putClientProperty(FlatClientProperties.STYLE, "");
        Buscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Menu/icon-buscar.svg", 0.009f));
        this.gerenciarConta.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, null);
        gerenciarConta.putClientProperty(FlatClientProperties.STYLE, "");
        // Configurações dos botões
        this.Sair_Botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.Login_Botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.todosButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.recomendadosButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.gerenciarConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gerenciarUsuarios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gerenciarGenero.setCursor(new Cursor(Cursor.HAND_CURSOR));
        database.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

        this.addMouseListener(new MouseAdapter() {
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
                if (JOptionPane.showConfirmDialog(Menu.this, "Você tem certeza que quer sair da aplicação?", "Sair", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }

            }
        });

        gerenciarGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciarGenero gerenciarGenero1 = new GerenciarGenero(darkTheme, idUsuario);
                Container container = Menu.this.getParent();
                container.removeAll();
                container.add(gerenciarGenero1);
                container.revalidate();
                container.repaint();
            }
        });

        gerenciarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciarUsuarios gerenciarUsuarios1 = new GerenciarUsuarios(darkTheme, idUsuario);
                Container container = Menu.this.getParent();
                container.removeAll();
                container.add(gerenciarUsuarios1);
                container.revalidate();
                container.repaint();
            }
        });

        gerenciarConta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GerenciarConta conta = new GerenciarConta(false, idUsuario);
                Container container = Menu.this.getParent();
                container.removeAll();
                container.add(conta);
                container.revalidate();
                container.repaint();
            }
        });

        Login_Botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Substitui o painel do menu pelo painel de login
                Login login = new Login(darkTheme, idUsuario);
                Container container = Menu.this.getParent();
                container.removeAll();
                container.add(login);
                container.revalidate();
                container.repaint();
            }
        });

        // Função dos botões de categoria
        Color corTodos = todosButton.getBackground();
        Color corRecomendado = recomendadosButton.getBackground();
        todosButton.setBackground(new Color(149, 149, 149));
        this.todosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selecionado = true;
                recomendadosButton.setBackground(corRecomendado);
                todosButton.setBackground(new Color(149, 149, 149));
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
                recomendadosButton.setBackground(new Color(149, 149, 149));
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
        this.toggleThemeButton.setBounds(this.getWidth() - 40, this.getHeight() - 40, 40, 40);
        this.Sair_Botao.setSize(60, 30);
        this.database.setBounds(this.getWidth() / 2 - 45, 50, 90, 90);
        this.Sair_Botao.setBounds(this.getWidth() - this.Sair_Botao.getWidth(), 0, 60, 30);
        this.gerenciarConta.setBounds(this.Sair_Botao.getX() - 85,0,85,30);
        this.Buscar.setBounds(0, 0, 0, 30);
        this.Login_Botao.setBounds(this.Sair_Botao.getX() - 70, 0, 70, 30);
        this.todosButton.setBounds(this.Buscar.getX(), this.Buscar.getHeight(), 70, 30);
        gerenciarUsuarios.setBounds(recomendadosButton.getWidth()+recomendadosButton.getX(), 0, 140, 30);
        gerenciarGenero.setBounds(gerenciarUsuarios.getWidth()+gerenciarUsuarios.getX(), 0, 140, 30);
        this.jogo1.setSize(200, 200);
        this.jogo1.setBounds(100, 300, 200, 200);
        this.desc1.setBounds(this.jogo1.getX(), this.jogo1.getY() + this.jogo1.getHeight(), 100, 50);
        this.jogo4.setBounds(this.getWidth() - 300, this.jogo1.getY(), 200, 200);
        this.desc4.setBounds(this.jogo4.getX(), this.jogo1.getY() + this.jogo1.getHeight(), 100, 50);
        this.jogo2.setBounds(this.jogo1.getX() + this.jogo1.getWidth()+90, this.jogo1.getY(), 200, 200);
        this.desc2.setBounds(this.jogo2.getX(), this.jogo1.getY() + this.jogo1.getHeight(), 100, 50);
        this.jogo3.setBounds(this.jogo4.getX() - this.jogo4.getWidth()-90, this.jogo1.getY(), 200, 200);
        this.desc3.setBounds(this.jogo3.getX(), this.jogo1.getY() + this.jogo1.getHeight(), 100, 50);
        this.recomendadosButton.setBounds(this.todosButton.getX() + this.todosButton.getWidth(), this.todosButton.getY(), this.todosButton.getWidth() + 60, 30);
        this.Buscar.setSize(this.todosButton.getWidth() + this.recomendadosButton.getWidth(), this.Buscar.getHeight());
    }
}
