package TrabalhoAPS.Programa.GDB.Visao;

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
    private Image imagem_redimensionada;
    private ImageIcon icon_jogo1;

    public Menu() {
        icon_jogo1 = new ImageIcon("Gow.jpg");
        imagem_redimensionada = icon_jogo1.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        icon_jogo1.setImage(imagem_redimensionada);
        jogo1 = new JButton(icon_jogo1);
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
        jogo1.setBounds(200, 200, 90, 90);
        Sair_Botao.setBounds(1285, 0, 60, 30);
        Buscar.setBounds(0, 0, 0, 30);
        Login_Botao.setBounds(Sair_Botao.getX()-70, 0, 70, 30);
        todosButton.setBounds(Buscar.getX(), Buscar.getHeight(), 70, 30);
        recomendadosButton.setBounds(todosButton.getX()+todosButton.getWidth(), todosButton.getY(), todosButton.getWidth()+60, 30);
        Buscar.setSize(todosButton.getWidth()+recomendadosButton.getWidth(), Buscar.getHeight());
        Buscar.setText("Buscar");
        jogo1.setBorderPainted(false);
        jogo1.setContentAreaFilled(false);
        jogo1.setFocusPainted(false);
        Login_Botao.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        todosButton.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        recomendadosButton.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        Sair_Botao.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        Sair_Botao.setContentAreaFilled(false);
        Login_Botao.setContentAreaFilled(false);
        recomendadosButton.setContentAreaFilled(false);
        todosButton.setContentAreaFilled(false);
        MenuPainel.add(Buscar);
        MenuPainel.add(Sair_Botao);
        MenuPainel.add(Login_Botao);
        MenuPainel.add(todosButton);
        MenuPainel.add(recomendadosButton);
        MenuPainel.add(jogo1);
        Sair_Botao.setBackground(Color.gray);
        Login_Botao.setBackground(Color.gray);
        todosButton.setBackground(Color.gray);
        recomendadosButton.setBackground(Color.gray);
        setContentPane(MenuPainel);
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
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
                Login_Botao.setBackground(Color.green);
                Login_Botao.setBorder(BorderFactory.createLineBorder(Color.white, 3, false));
            }
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Login_Botao.setBackground(Color.gray);
                Login_Botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            }
        });
        Sair_Botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Sair_Botao.setBackground(Color.darkGray);
                Sair_Botao.setBorder(BorderFactory.createLineBorder(Color.white, 3, false));
            }
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Sair_Botao.setBackground(Color.gray);
                Sair_Botao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            }
        });
        recomendadosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                recomendadosButton.setBackground(Color.darkGray);
                recomendadosButton.setBorder(BorderFactory.createLineBorder(Color.white, 3, false));
            }
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                recomendadosButton.setBackground(Color.gray);
                recomendadosButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            }
        });
        todosButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                todosButton.setBackground(Color.darkGray);
                todosButton.setBorder(BorderFactory.createLineBorder(Color.white, 3, false));
            }
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                todosButton.setBackground(Color.gray);
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
                } else Sair_Botao.setBackground(Color.white);
            }
        });
        Login_Botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login_Botao.setBackground(Color.gray);
                JOptionPane.showMessageDialog(Menu.this, "Você está sendo redirecionado para a tela de login");
                Login_Botao.setBackground(Color.white);
            }
        });
        todosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    todosButton.setBackground(Color.gray);
                    MenuPainel.setBackground(Color.gray);
                    recomendadosButton.setBackground(Color.white);
            }
        });
        recomendadosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                recomendadosButton.setBackground(Color.black);
                MenuPainel.setBackground(Color.black);
                todosButton.setBackground(Color.white);
            }
        });

    }

    public static void main(String[] args) {
        new Menu();
    }

}

