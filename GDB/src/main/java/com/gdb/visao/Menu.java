package com.gdb.visao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame {

    private JTextField Buscar;
    private JButton Sair_Botao;
    private JPanel MenuPainel;
    private JButton Login_Botao;
    private JButton todosButton;
    private JButton recomendadosButton;
    private JButton jogo1;
    private JButton jogo2;
    private JButton jogo3;
    private JButton jogo4;
    private JLabel desc1;
    private JLabel desc2;
    private JLabel desc3;
    private JLabel desc4;
    private Image imagem_redimensionada;
    private ImageIcon icon_jogo1;
    private int selecionado;

    public Menu() {
        selecionado = 0;
        icon_jogo1 = new ImageIcon("Menu/database.png");
        desc1 = new JLabel("Descrição jogo 1");
        desc2 = new JLabel("Descrição jogo 2");
        desc3 = new JLabel("Descrição jogo 3");
        desc4 = new JLabel("Descrição jogo 4");
        jogo1 = new JButton("jogo1");
        jogo2 = new JButton("jogo2");
        jogo3 = new JButton("jogo3");
        jogo4 = new JButton("jogo4");
        jogo1.setBorderPainted(false);
        jogo2.setBorderPainted(false);
        jogo3.setBorderPainted(false);
        jogo4.setBorderPainted(false);
        jogo1.setBackground(Color.red);
        jogo2.setBackground(Color.blue);
        jogo3.setBackground(Color.green);
        jogo4.setBackground(Color.black);

        Buscar = new JTextField();
        Sair_Botao = new JButton("Sair");
        MenuPainel = new JPanel();
        MenuPainel.setLayout(null);
        Login_Botao = new JButton("Login");
        todosButton = new JButton("Todos");
        recomendadosButton = new JButton("Recomendados");
        todosButton.setFocusPainted(false);
        recomendadosButton.setFocusPainted(false);
        Sair_Botao.setFocusPainted(false);
        Login_Botao.setFocusPainted(false);
        Sair_Botao.setBounds(1285, 0, 60, 30);
        Buscar.setBounds(0, 0, 0, 30);
        Login_Botao.setBounds(Sair_Botao.getX()-70, 0, 70, 30);
        todosButton.setBounds(Buscar.getX(), Buscar.getHeight(), 70, 30);
        jogo1.setBounds(50, 200, 200, 200);
        desc1.setBounds(jogo1.getX(), jogo1.getY()+jogo1.getHeight(), 100, 50);
        jogo2.setBounds(jogo1.getX()+jogo1.getWidth()+150, 200, 200, 200);
        desc2.setBounds(jogo2.getX(), jogo1.getY()+jogo1.getHeight(), 100, 50);
        jogo3.setBounds(jogo2.getX()+jogo1.getWidth()+150, 200, 200, 200);
        desc3.setBounds(jogo3.getX(), jogo1.getY()+jogo1.getHeight(), 100, 50);
        jogo4.setBounds(jogo3.getX()+jogo1.getWidth()+150, 200, 200, 200);
        desc4.setBounds(jogo4.getX(), jogo1.getY()+jogo1.getHeight(), 100, 50);
        recomendadosButton.setBounds(todosButton.getX()+todosButton.getWidth(), todosButton.getY(), todosButton.getWidth()+60, 30);
        Buscar.setSize(todosButton.getWidth()+recomendadosButton.getWidth(), Buscar.getHeight());
        Buscar.setText("Buscar");
        Login_Botao.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        todosButton.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        recomendadosButton.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        Sair_Botao.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        MenuPainel.add(Buscar);
        MenuPainel.add(Sair_Botao);
        MenuPainel.add(Login_Botao);
        MenuPainel.add(todosButton);
        MenuPainel.add(recomendadosButton);
        MenuPainel.createImage(50, 50);
        Sair_Botao.setBackground(Color.lightGray);
        Login_Botao.setBackground(Color.lightGray);
        todosButton.setBackground(Color.lightGray);
        recomendadosButton.setBackground(Color.lightGray);
        Buscar.setBackground(Color.lightGray);
        Buscar.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        setContentPane(MenuPainel);
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        add(jogo1);
        add(jogo2);
        add(jogo3);
        add(jogo4);
        add(desc1);
        add(desc2);
        add(desc3);
        add(desc4);
        Buscar.setFocusable(false);
        Sair_Botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Login_Botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        todosButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        recomendadosButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Buscar.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        Login_Botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Login_Botao.setBackground(Color.gray);
                Login_Botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, false));
            }
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Login_Botao.setBackground(Color.lightGray);
                Login_Botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            }
        });
        Sair_Botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Sair_Botao.setBackground(Color.gray);
                Sair_Botao.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
            }
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Sair_Botao.setBackground(Color.lightGray);
                Sair_Botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            }
        });
        recomendadosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (selecionado == 0){
                    recomendadosButton.setBackground(Color.gray);
                };
                recomendadosButton.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
            }
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (selecionado == 0){
                    recomendadosButton.setBackground(Color.lightGray);
                };
                recomendadosButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            }
        });
        todosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (selecionado == 0){
                    todosButton.setBackground(Color.gray);
                };
                todosButton.setBorder(BorderFactory.createLineBorder(Color.black, 3, false));
            }
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (selecionado == 0){
                    todosButton.setBackground(Color.lightGray);
                };
                todosButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            }
        });
        Buscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Buscar.setFocusable(true);
            }
        });
        MenuPainel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Buscar.setFocusable(false);
            }
        });
        Buscar.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (Buscar.getText().equals("Buscar")) {
                    Buscar.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (Buscar.getText().equals("")) {
                    Buscar.setText("Buscar");
                }
            }
        });
        Sair_Botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sair_Botao.setBackground(Color.gray);
                if (JOptionPane.showConfirmDialog(Menu.this, "Você tem certeza que quer sair da aplicação?", "Sair", JOptionPane.YES_NO_OPTION)  == 0) {
                    System.exit(0);
                } else Sair_Botao.setBackground(Color.lightGray);
            }
        });
        Login_Botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login_Botao.setBackground(Color.gray);
                JOptionPane.showMessageDialog(Menu.this, "Você está sendo redirecionado para a tela de login");
            }
        });
        todosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selecionado = 1;
                if (selecionado == 1){
                    todosButton.setBackground(Color.gray);
                    MenuPainel.setBackground(Color.gray);
                    jogo2.setVisible(false);
                    jogo3.setVisible(false);
                    desc2.setVisible(false);
                    desc3.setVisible(false);
                    jogo4.setBounds(jogo2.getX(), jogo2.getY(), jogo2.getWidth(), jogo2.getHeight());
                    desc4.setBounds(desc2.getX(), desc2.getY(), desc2.getWidth(), desc2.getHeight());
                }
                jogo1.setVisible(true);
                jogo4.setVisible(true);
                desc1.setVisible(true);
                desc4.setVisible(true);
                jogo2.setBounds(jogo1.getX()+jogo1.getWidth()+150, 200, 200, 200);
                desc2.setBounds(jogo2.getX(), jogo1.getY()+jogo1.getHeight(), 100, 50);
                jogo3.setBounds(jogo2.getX()+jogo1.getWidth()+150, 200, 200, 200);
                desc3.setBounds(jogo3.getX(), jogo1.getY()+jogo1.getHeight(), 100, 50);
                recomendadosButton.setBackground(Color.lightGray);
            }
        });
        recomendadosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selecionado = 2;
                if (selecionado == 2) {
                    recomendadosButton.setBackground(Color.gray);
                    MenuPainel.setBackground(Color.darkGray);
                    jogo1.setVisible(false);
                    jogo4.setVisible(false);
                    desc1.setVisible(false);
                    desc4.setVisible(false);
                    jogo3.setBounds(jogo2.getX(), jogo2.getY(), jogo2.getWidth(), jogo2.getHeight());
                    desc3.setBounds(desc2.getX(), desc2.getY(), desc2.getWidth(), desc2.getHeight());
                    jogo2.setBounds(jogo1.getX(), jogo1.getY(), jogo1.getWidth(), jogo1.getHeight());
                    desc2.setBounds(desc1.getX(), desc1.getY(), desc1.getWidth(), desc1.getHeight());
                }
                jogo2.setVisible(true);
                jogo3.setVisible(true);
                desc2.setVisible(true);
                desc3.setVisible(true);
                jogo1.setBounds(50, 200, 200, 200);
                desc1.setBounds(jogo1.getX(), jogo1.getY()+jogo1.getHeight(), 100, 50);
                jogo4.setBounds(jogo1.getX()+jogo1.getWidth()+150, 200, 200, 200);
                desc4.setBounds(jogo4.getX(), jogo1.getY()+jogo1.getHeight(), 100, 50);
                todosButton.setBackground(Color.lightGray);
            }
        });

    }

    public static void main(String[] args) {
        new Menu();
    }

}
