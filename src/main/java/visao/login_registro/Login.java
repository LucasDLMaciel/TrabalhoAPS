package visao.login_registro;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
<<<<<<< HEAD
<<<<<<< HEAD:GDB/src/main/java/com/gdb/visao/login_registro/Login.java
=======
import controle.UsuarioControle;
>>>>>>> e528347 (finalizar):src/main/java/visao/login_registro/Login.java
=======
import controle.UsuarioControle;
>>>>>>> e528347 (finalizar)
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
<<<<<<< HEAD:GDB/src/main/java/com/gdb/visao/login_registro/Login.java
import com.gdb.visao.Menu.Menu;
import com.gdb.controle.UsuarioControle;
=======
import visao.Menu.Menu;
>>>>>>> e528347 (finalizar):src/main/java/visao/login_registro/Login.java
=======
import visao.Menu.Menu;
>>>>>>> e528347 (finalizar)


public class Login extends JPanel {
    private boolean darkTheme;
    public Login(boolean darkTheme) {
        this.darkTheme = darkTheme;
        init();
    }

    private void init(){

        setLayout(new MigLayout("wrap, gapy 4, al center center", "[fill,300]"));
        add(new JLabel(new FlatSVGIcon("login/icon/logo.svg", 0.5f)));


        JLabel loginLabel = new JLabel("Login", JLabel.CENTER);
        loginLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(loginLabel);

        JLabel usuarioLabel = new JLabel(" Usuário");
        usuarioLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +2");
        add(criarSeparador(), "gapy 5");
        add(usuarioLabel, "gapy 5");

        JTextField usuarioText = new JTextField();
        usuarioText.putClientProperty(FlatClientProperties.STYLE,"iconTextGap: 10");
        usuarioText.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Insira o nome de Usuário");
        usuarioText.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,new FlatSVGIcon("login/icon/usuario.svg", 0.04f));
        add(usuarioText, "gapy 5");

        JLabel senhaLabel = new JLabel(" Senha");
        senhaLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +2");
        add(senhaLabel, "gapy 10 2");

        JPasswordField senhaText = new JPasswordField();
        botaoRevelarSenha(senhaText);
        senhaText.putClientProperty(FlatClientProperties.STYLE,"iconTextGap: 10");
        senhaText.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Insira sua senha");
        senhaText.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,new FlatSVGIcon("login/icon/senha.svg", 0.04f));
        add(senhaText);

        JButton loginButton = new JButton("Entrar"){
            @Override
            public boolean isDefaultButton(){
                return true;
            }
        };

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField campoUsuario = new JTextField();
                JPasswordField campoSenha = new JPasswordField();

                // Pega o nome de usuário do campo de texto
                String nome_de_usuario = campoUsuario.getText(); // campoUsuario é o JTextField onde o usuário digita o nome

                // Pega a senha do campo de senha (converte para String)
                String senha = new String(campoSenha.getPassword()); // campoSenha é o JPasswordField onde o usuário digita a senha

                boolean loginValido = UsuarioControle.validarLogin(nome_de_usuario, senha);

                if (loginValido) {
                    // Se o login for válido, realizar a ação de login (por exemplo, abrir um novo painel)
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    // Aqui você pode redirecionar para outra tela ou painel
                } else {
                    // Se o login falhar, exibir mensagem de erro
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        //FAZER LOGICA DE BUSCAR USUARIO NOS DADOS
        loginButton.putClientProperty(FlatClientProperties.STYLE,"foreground:#FFFFFF");
        add(loginButton, "gapy 10 5");

        add(criarSeparador(), "gapy 5 10");

        JLabel semConta = new JLabel(" Não tem conta?");
        semConta.putClientProperty(FlatClientProperties.STYLE,"foreground:$Label.disabledForeground;");
        add(semConta, "split 2, gapx push n");

        JButton criarConta = criarBotaoSemBorda("Criar Conta");
        add(criarConta, "gapx n push");


        // Botão para voltar ao menu
        JButton voltarButton = new JButton("Voltar");
        add(voltarButton, "gapy 10 5");

        // ActionListener para voltar ao menu
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Substitui o painel de login pelo painel do menu
                Menu menu = new Menu(darkTheme);
                Container container = getParent();
                container.removeAll();
                container.add(menu);
                container.revalidate();
                container.repaint();
            }
        });


        // Dentro da classe Login
        criarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Substitui o painel de login pelo painel de registro
                Registro registro = new Registro(false, darkTheme);
                Container container = getParent();
                container.removeAll();
                container.add(registro);
                container.revalidate();
                container.repaint();
            }
        });

    }



    private JSeparator criarSeparador() {
        JSeparator separador = new JSeparator();

        return separador;
    }

    private JButton criarBotaoSemBorda(String text){
        JButton botao = new JButton(text);
        botao.putClientProperty(FlatClientProperties.STYLE,"foreground:$Component.accentColor;" + "margin:1,5,1,5;" + "borderWidth:0;" + "focusWidth:0;" + "innerFocusWidth:0;" + "background:null;");
        return botao;
    }

    private void botaoRevelarSenha(JPasswordField txt) {
        FlatSVGIcon iconeOlho = new FlatSVGIcon("login/icon/olho.svg", 0.03f);
        FlatSVGIcon iconOlhoFechado = new FlatSVGIcon("login/icon/olhoFechado.svg", 0.03f);

        JToolBar toolBar = new JToolBar();
        toolBar.putClientProperty(FlatClientProperties.STYLE,"margin:0,0,0,5;");
        JButton button = new JButton(iconeOlho);

        button.addActionListener(new ActionListener() {

            private char echoSenha = txt.getEchoChar();
            private boolean show;

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                show = !show;
                if (show) {
                    button.setIcon(iconOlhoFechado);
                    txt.setEchoChar((char) 0);
                } else {
                    button.setIcon(iconeOlho);
                    txt.setEchoChar(echoSenha);
                }
            }
        });
        toolBar.add(button);
        txt.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_COMPONENT, toolBar);
    }
}
