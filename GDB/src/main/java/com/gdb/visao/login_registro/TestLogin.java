//package com.gdb.visao.login_registro;
//import com.formdev.flatlaf.FlatLaf;
//import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
//import com.formdev.flatlaf.themes.FlatMacLightLaf;
//import net.miginfocom.swing.MigLayout;
//import javax.swing.*;
//import java.awt.*;
//
//public class TestLogin extends JFrame {
//
//    public TestLogin() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(1280, 720);
//        setLocationRelativeTo(null);
//
//        add(new Login());
//
//        usuarioText = new JTextField();
//        senhaText = new JPasswordField();
//
//        //pedro: aqui cria o botao de login, no caso o segundo botao de entrar (Branco)
//        JButton loginButton = new JButton("Entrar");
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String usuario = usuarioText.getText();
//                String senha = new String(senhaText.getPassword());
//
//                boolean loginValido = loginController.autenticarUsuario(usuario, senha);
//                //pedro: la na funcao de controlador de login no modulo de controle, vai retornar se
//                //o usuario e valido ou nao
//
//                if (loginValido) {
//                    JOptionPane.showMessageDialog(TestLogin.this, "Login bem-sucedido!");
//                    // pedro: aqui precisamos do banco CSV, para aprovar certos logins de usuários!!!
//                } else {
//                    JOptionPane.showMessageDialog(TestLogin.this, "Usuário ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
//                }  //pedro: por enquanto nenhum usuário está validando
//            }
//        });
//
//        add(loginButton);
//    }
//
//    public static void main(String[] args) {
//        FlatRobotoFont.install();
//        FlatLaf.registerCustomDefaultsSource("login.themes");
//        FlatMacDarkLaf.setup();
//        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
//        EventQueue.invokeLater(() -> new TestLoginRegistro().setVisible(true));
//    }
//}
