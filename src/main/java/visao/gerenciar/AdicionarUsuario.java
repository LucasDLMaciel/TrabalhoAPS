package visao.gerenciar;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controle.GeneroControle;
import controle.UsuarioControle;
import modelo.Genero;
import visao.login_registro.MultiplaEscolha;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdicionarUsuario extends JPanel {
    private JTextField usuarioText;
    private JPasswordField senhaText;
    private JFormattedTextField dataNascimentoField;
    private MultiplaEscolha generoBox;
    private JCheckBox adminCheckBox;
    private boolean darkTheme;
    private Integer idUsuario;

    private GeneroControle generoControle = new GeneroControle();

    public AdicionarUsuario(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap, gapy 4, al center center", "[fill,300]"));
        add(new JLabel(new FlatSVGIcon("login/icon/logo.svg", 0.5f)));

        JLabel adicionarUsuarioLabel = new JLabel("Adicionar Usuário", JLabel.CENTER);
        adicionarUsuarioLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(adicionarUsuarioLabel);

        add(criarSeparador(), "gapy 5");

        JLabel usuarioLabel = new JLabel(" Usuário");
        usuarioLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(usuarioLabel, "gapy 5");

        usuarioText = new JTextField();
        usuarioText.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Insira o nome de Usuário");
        add(usuarioText, "gapy 5");

        JLabel senhaLabel = new JLabel(" Senha");
        senhaLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(senhaLabel, "gapy 10 2");

        senhaText = new JPasswordField();
        senhaText.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Insira a senha");
        add(senhaText);

        JLabel dataNascimentoLabel = new JLabel(" Data de Nascimento");
        dataNascimentoLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(dataNascimentoLabel, "gapy 10 2");

        dataNascimentoField = new JFormattedTextField();
        DatePicker seletorData = new DatePicker();
        seletorData.setEditor(dataNascimentoField);
        add(dataNascimentoField);

        JLabel generoLabel = new JLabel(" Gêneros favoritos");
        generoLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(generoLabel, "gapy 10 2");

        List<Genero> generos = generoControle.getGeneros();

        List<Genero> generosFav = new ArrayList<>();

        generoBox = new MultiplaEscolha(generos, generosFav);
        add(generoBox, "gapx 23 23, width 300, align center");

        adminCheckBox = new JCheckBox("Administrador");
        adminCheckBox.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(adminCheckBox, "gapy 10");

        JButton adicionarButton = new JButton("Adicionar") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        adicionarButton.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
        add(adicionarButton, "gapy 10 5");

        adicionarButton.addActionListener(e -> adicionarUsuario());

        add(criarSeparador(), "gapy 5 10");

        JButton voltarButton = criarBotaoSemBorda("Voltar");
        add(voltarButton, "gapx n push");
        voltarButton.addActionListener(e -> voltar());

    }

    private void adicionarUsuario() {
        String usuario = usuarioText.getText().trim();
        String senha = new String(senhaText.getPassword()).trim();
        String dataNascimento = dataNascimentoField.getText().trim();
        List<Genero> generosSelecionados = generoBox.getSelecionados();

        boolean isAdmin = adminCheckBox.isSelected();

        if (usuario.isEmpty() || senha.isEmpty() || dataNascimento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            UsuarioControle service = new UsuarioControle();

            service.salvarRegistro(usuario, senha, dataNascimento, generosSelecionados, isAdmin);
            JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltar() {
        // Substitui o painel de login pelo painel do menu
        GerenciarUsuarios menu = new GerenciarUsuarios(darkTheme, idUsuario);
        Container container = getParent();
        container.removeAll();
        container.add(menu);
        container.revalidate();
        container.repaint();
    }

    private JSeparator criarSeparador() {
        return new JSeparator();
    }

    private JButton criarBotaoSemBorda(String text) {
        JButton botao = new JButton(text);
        botao.putClientProperty(FlatClientProperties.STYLE, "foreground:$Component.accentColor;" +
                "margin:1,5,1,5;" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0;" +
                "background:null;");
        return botao;
    }
}
