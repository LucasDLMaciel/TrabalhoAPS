package visao.gerenciar;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controle.ControleUsuario;
import controle.ControleGenero;
import modelo.Genero;
import modelo.Usuario;
import visao.Menu.Menu;
import visao.login_registro.Login;
import visao.login_registro.MultiplaEscolha;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DatePicker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GerenciarUsuario extends JPanel {
    private JTextField usuarioText;
    private JPasswordField senhaText;
    private JFormattedTextField dataNascimentoField;
    private MultiplaEscolha generoBox;
    private JCheckBox adminCheckBox;

    private boolean darkTheme;
    private Integer idUsuario = 0;

    private ControleUsuario controleUsuario;
    private ControleGenero controleGenero;

    public GerenciarUsuario(boolean darkTheme, Integer idUsuario) {
        this.idUsuario = idUsuario;
        this.darkTheme = darkTheme;
        controleUsuario = new ControleUsuario();
        controleGenero = new ControleGenero();
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap, gapy 4, al center center", "[fill,300]"));
        add(new JLabel(new FlatSVGIcon("login/icon/logo.svg", 0.5f)));

        JLabel registroLabel = new JLabel("Gerenciar Conta", JLabel.CENTER);
        registroLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(registroLabel);

        // Inicialize os campos de texto antes de usá-los
        usuarioText = new JTextField();
        senhaText = new JPasswordField();
        dataNascimentoField = new JFormattedTextField();
        generoBox = new MultiplaEscolha(new ArrayList<>(), new ArrayList<>());
        adminCheckBox = new JCheckBox("Administrador");

        JLabel usuarioLabel = new JLabel(" Usuário");
        usuarioLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +2");
        add(criarSeparador(), "gapy 5");
        add(usuarioLabel, "gapy 5");

        usuarioText.putClientProperty(FlatClientProperties.STYLE,"iconTextGap: 10");
        usuarioText.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Insira o nome de Usuário");
        usuarioText.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,new FlatSVGIcon("login/icon/usuario.svg", 0.04f));
        add(usuarioText, "gapy 5");

        JLabel senhaLabel = new JLabel(" Senha");
        senhaLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +2");
        add(senhaLabel, "gapy 10 2");

        senhaText.putClientProperty(FlatClientProperties.STYLE,"iconTextGap: 10");
        senhaText.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Insira sua senha");
        senhaText.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,new FlatSVGIcon("login/icon/senha.svg", 0.04f));
        add(senhaText);

        JLabel dataNascimentoLabel = new JLabel(" Data de Nascimento");
        dataNascimentoLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +2");
        add(dataNascimentoLabel, "gapy 10 2");

        DatePicker seletorData = new DatePicker();
        seletorData.setEditor(dataNascimentoField);
        dataNascimentoField.setBorder(BorderFactory.createCompoundBorder(
                dataNascimentoField.getBorder(),
                BorderFactory.createEmptyBorder(0, 8, 0, 0)));
        add(dataNascimentoField);

        JLabel generoLabel = new JLabel(" Gêneros favoritos", JLabel.CENTER);
        generoLabel.putClientProperty(FlatClientProperties.STYLE,"font:bold +2");
        add(generoLabel, "gapy 10 2");

        List<Genero> generos = controleGenero.getEntidades();

        Usuario usuario1 = (Usuario) controleUsuario.buscarPorId(idUsuario);
        List<Genero> generosFav = usuario1.getGenerosFavoritos();

        generoBox = new MultiplaEscolha(generos, generosFav);
        generoBox.putClientProperty(FlatClientProperties.STYLE,"");
        add(generoBox, "gapx 23 23, width 300, align center");

        adminCheckBox.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");

        if (usuario1 != null) {
            usuarioText.setText(usuario1.getUsuario());
            senhaText.setText(usuario1.getSenha());
            dataNascimentoField.setText(usuario1.getDataNascimento().toString());
            adminCheckBox.setSelected(usuario1.isAdministrador());
        }

        // Botão para salvar alterações
        JButton salvarButton = new JButton("Salvar") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };

        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuarioText.getText().isEmpty() || senhaText.getPassword().length == 0 || dataNascimentoField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.");
                    return;
                }

                String usuario = usuarioText.getText();
                String senha = new String(senhaText.getPassword());

                // Converter dataNascimento para LocalDate
                String dataNascimentoString = dataNascimentoField.getText();
                LocalDate dataNascimento = null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // define a regra que deve ser seguida

                try {
                    dataNascimento = LocalDate.parse(dataNascimentoString, formatter); // converte de string para LocalDate
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de data inválido. Use dd/MM/yyyy.");
                    return; // Interrompe a execução se houver erro
                }

                // Obter gêneros favoritos selecionados
                List<Genero> generosFavoritos = generoBox.getSelecionados();

                // Verifica se o usuário é administrador
                boolean isAdmin = adminCheckBox.isSelected();

                // Atualizar usuário no banco de dados
                Usuario usuarioAux = new Usuario(usuario, senha, dataNascimentoString, generosFavoritos, isAdmin);
                usuarioAux.setId(idUsuario);
                controleUsuario.atualizar(usuarioAux);
                JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso!");
            }
        });

        // Botão Voltar
        JButton voltarButton = new JButton("Voltar");
        add(voltarButton, "split 2, gapy 10 5");

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu menu = new Menu(darkTheme, idUsuario);  // Passando o idUsuario para a próxima tela
                Container container = getParent();
                container.removeAll();
                container.add(menu);
                container.revalidate();
                container.repaint();
            }
        });

        salvarButton.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
        add(salvarButton, "");

        add(criarSeparador(), "gapy 5 5");



        // Botão Excluir Conta
        JButton excluirConta = criarBotaoSemBorda("Excluir Conta");
        excluirConta.setForeground(Color.RED);
        add(excluirConta, "split 2, gapx push n");

        JButton sairButton = criarBotaoSemBorda("Sair da Conta");

        add(sairButton, "gapx n push");

        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja sair?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (resposta == JOptionPane.YES_OPTION) {
                    // Redireciona para a tela de login após exclusão
                    Menu menu = new Menu(darkTheme, 0);
                    Container container = getParent();
                    container.removeAll();
                    container.add(menu);
                    container.revalidate();
                    container.repaint();
                }
            }
        });

        excluirConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja excluir sua conta?",
                        "Confirmar Exclusão",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (resposta == JOptionPane.YES_OPTION) {
                    controleUsuario.deletar(idUsuario);
                    JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!");
                    // Redireciona para a tela de login após exclusão
                    Login login = new Login(darkTheme, 0);
                    Container container = getParent();
                    container.removeAll();
                    container.add(login);
                    container.revalidate();
                    container.repaint();
                }
            }
        });
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
