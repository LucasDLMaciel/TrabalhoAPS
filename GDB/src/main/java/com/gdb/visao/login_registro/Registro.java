package com.gdb.visao.login_registro;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Registro extends JPanel {
    private JTextField usuarioText;
    private JPasswordField senhaText;
    private JFormattedTextField dataNascimentoField;
    private MultiplaEscolha generoBox;
    private JCheckBox adminCheckBox;

    public Registro(boolean isAdminLogado) {
        init(isAdminLogado);
    }

    private void init(boolean isAdminLogado) {
        setLayout(new MigLayout("wrap, gapy 4, al center center", "[fill,300]"));
        add(new JLabel(new FlatSVGIcon("login/icon/logo.svg", 0.5f)));

        JLabel registroLabel = new JLabel("Registro", JLabel.CENTER);
        registroLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(registroLabel);

        JLabel usuarioLabel = new JLabel(" Usuário");
        usuarioLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +2");
        add(criarSeparador(), "gapy 5");
        add(usuarioLabel, "gapy 5");

        usuarioText = new JTextField();
        usuarioText.putClientProperty(FlatClientProperties.STYLE,"iconTextGap: 10");
        usuarioText.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Insira o nome de Usuário");
        usuarioText.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,new FlatSVGIcon("login/icon/usuario.svg", 0.04f));
        add(usuarioText, "gapy 5");

        JLabel senhaLabel = new JLabel(" Senha");
        senhaLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +2");
        add(senhaLabel, "gapy 10 2");

        senhaText = new JPasswordField();
        botaoRevelarSenha(senhaText);

        senhaText.putClientProperty(FlatClientProperties.STYLE,"iconTextGap: 10");
        senhaText.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Insira sua senha");
        senhaText.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,new FlatSVGIcon("login/icon/senha.svg", 0.04f));
        add(senhaText);

        JLabel dataNascimentoLabel = new JLabel(" Data de Nascimento");
        dataNascimentoLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +2");
        add(dataNascimentoLabel, "gapy 10 2");

        dataNascimentoField = new JFormattedTextField();
        DatePicker seletorData = new DatePicker();
        seletorData.setEditor(dataNascimentoField);
        dataNascimentoField.setBorder(BorderFactory.createCompoundBorder(
                dataNascimentoField.getBorder(),
                BorderFactory.createEmptyBorder(0, 8, 0, 0)));
        add(dataNascimentoField);

        JLabel generoLabel = new JLabel(" Gêneros favoritos", JLabel.CENTER);
        generoLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +2");
        add(generoLabel, "gapy 10 2");

        List<String> generos = new ArrayList<>();
        generos.add("Terror");
        generos.add("Aventura");
        generos.add("Survival");
        generos.add("História");
        generos.add("Plataforma");

        List<String> generosFav = new ArrayList<>();

        generoBox = new MultiplaEscolha(generos, generosFav);
        generoBox.putClientProperty(FlatClientProperties.STYLE,"");
        add(generoBox, "gapx 23 23, width 300, align center");

        adminCheckBox = new JCheckBox("Administrador");
        adminCheckBox.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        adminCheckBox.setVisible(isAdminLogado);
        if(isAdminLogado) {
            add(adminCheckBox, "gapy 10");
        }

        JButton registerButton = new JButton("Registrar") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        registerButton.putClientProperty(FlatClientProperties.STYLE,"foreground:#FFFFFF");
        add(registerButton, "gapy 10 5");

        // FAZER LOGICA PRA SALVAR USUARIO
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        add(criarSeparador(), "gapy 5 10");

        JLabel semConta = new JLabel(" Já tem uma conta?");
        semConta.putClientProperty(FlatClientProperties.STYLE,"foreground:$Label.disabledForeground;");
        add(semConta, "split 2, gapx push n");

        JButton criarConta = criarBotaoSemBorda("Fazer login");
        add(criarConta, "gapx n push");

        criarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Substitui o painel de registro pelo painel de login
                Login login = new Login();
                Container container = getParent();
                container.removeAll();
                container.add(login);
                container.revalidate();
                container.repaint();
            }
        });
    }

    private void registrarUsuario() {
        String usuario = usuarioText.getText().trim();
        String senha = new String(senhaText.getPassword()).trim();
        String dataNascimento = dataNascimentoField.getText().trim();
        List<String> generosSelecionados = generoBox.getSelecionados();
        // Verifica se todos os campos obrigatórios estão preenchidos
        if (usuario.isEmpty() || senha.isEmpty() || dataNascimento.contains("-")) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Exibe as informações se todos os campos estão preenchidos
        JOptionPane.showMessageDialog(this,
                "Usuário: " + usuario + "\n" +
                        "Senha: " + senha + "\n" +
                        "Data de Nascimento: " + dataNascimento + "\n" +
                        "Gêneros favoritos: " + generosSelecionados + "\n" +
                        "Administrador: " + (adminCheckBox.isSelected() ? "Sim" : "Não"),
                "Dados de Registro",
                JOptionPane.INFORMATION_MESSAGE);
        //FAZER LOGICA DE SALVAR INFORMAÇÕES
    }


    private JSeparator criarSeparador() {
        return new JSeparator();
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
