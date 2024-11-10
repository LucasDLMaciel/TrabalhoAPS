package com.gdb.visao.Login;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {
    public Login() {
        init();
    }

    private void init(){

        setLayout(new MigLayout("wrap, gapy 4", "[fill,300]"));
        setBackground(new Color(239, 239, 239));
        add(new JLabel(new FlatSVGIcon("login/icon/logo.svg", 0.065f)));


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
        loginButton.putClientProperty(FlatClientProperties.STYLE,"foreground:#FFFFFF");
        add(loginButton, "gapy 10 5");

        JLabel semConta = new JLabel(" Não tem conta?");
        semConta.putClientProperty(FlatClientProperties.STYLE,"foreground:$Label.disabledForeground;");
        add(semConta, "split 2, gapx push n");

        JButton criarConta = criarBotaoSemBorda("Criar Conta");
        add(criarConta, "gapx n push");


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
}
