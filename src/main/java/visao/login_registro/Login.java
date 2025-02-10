package visao.login_registro;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controle.ControleUsuario;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.Menu.Menu;


public class Login extends JPanel {
    private boolean darkTheme;
    private Integer idUsuario = 0;
    ControleUsuario controleUsuario;

    public Login(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        this.controleUsuario = new ControleUsuario();
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
                String nome_de_usuario = usuarioText.getText();
                String senha = new String(senhaText.getPassword());


                idUsuario = controleUsuario.validarLogin(nome_de_usuario, senha);

                if (idUsuario != 0) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    // Substitui o painel de login pelo painel do menu
                    Menu menu = new Menu(darkTheme, idUsuario);
                    Container container = getParent();
                    container.removeAll();
                    container.add(menu);
                    container.revalidate();
                    container.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        // Botão para voltar ao menu
        JButton voltarButton = new JButton("Voltar");
        add(voltarButton, "split 2, gapy 10 5");

        loginButton.putClientProperty(FlatClientProperties.STYLE,"foreground:#FFFFFF");
        add(loginButton, "");


        add(criarSeparador(), "gapy 5 10");

        JLabel semConta = new JLabel(" Não tem conta?");
        semConta.putClientProperty(FlatClientProperties.STYLE,"foreground:$Label.disabledForeground;");
        add(semConta, "split 2, gapx push n");

        JButton criarConta = criarBotaoSemBorda("Criar Conta");
        add(criarConta, "gapx n push");




        // ActionListener para voltar ao menu
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Substitui o painel de login pelo painel do menu
                Menu menu = new Menu(darkTheme, idUsuario);
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
                RegistrarUsuario registro = new RegistrarUsuario(darkTheme, idUsuario);
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
