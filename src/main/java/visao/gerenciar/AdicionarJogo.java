package visao.gerenciar;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controle.ControleGenero;
import controle.ControleJogo;
import modelo.Genero;
import modelo.Jogo;
import modelo.Nota;
import visao.login_registro.MultiplaEscolha;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdicionarJogo extends JPanel {
    private JTextField nomeJogoText;
    private JFormattedTextField dataLancamentoField;
    private MultiplaEscolha generoBox;
    private JTextArea descricaoArea;
    private JTextField classificacaoEtariaField;
    private boolean darkTheme;
    private Integer idUsuario;

    private ControleJogo controleJogo = new ControleJogo();
    private ControleGenero controleGenero = new ControleGenero();

    public AdicionarJogo(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap, gapy 4, al center center", "[fill,300]"));
        add(new JLabel(new FlatSVGIcon("login/icon/logo.svg", 0.5f)));

        JLabel adicionarJogoLabel = new JLabel("Adicionar Jogo", JLabel.CENTER);
        adicionarJogoLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(adicionarJogoLabel);

        add(criarSeparador(), "gapy 5");

        // Nome do jogo
        JLabel nomeJogoLabel = new JLabel(" Nome do Jogo");
        nomeJogoLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(nomeJogoLabel, "gapy 5");

        nomeJogoText = new JTextField();
        nomeJogoText.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Insira o nome do jogo");
        add(nomeJogoText, "gapy 5");

        // Data de lançamento
        JLabel dataLancamentoLabel = new JLabel(" Data de Lançamento");
        dataLancamentoLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(dataLancamentoLabel, "gapy 10 2");

        dataLancamentoField = new JFormattedTextField();
        DatePicker seletorData = new DatePicker();
        seletorData.setEditor(dataLancamentoField);
        add(dataLancamentoField);

        // Classificação etária
        JLabel classificacaoLabel = new JLabel(" Classificação Etária");
        classificacaoLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(classificacaoLabel, "gapy 10 2");

        classificacaoEtariaField = new JTextField();
        classificacaoEtariaField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ex: Livre, +12, +18");
        add(classificacaoEtariaField, "gapy 5");

        // Gêneros
        JLabel generoLabel = new JLabel(" Gêneros");
        generoLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(generoLabel, "gapy 10 2");

        List<Genero> generos = controleGenero.getEntidades();
        List<Genero> generosSelecionados = new ArrayList<>();
        generoBox = new MultiplaEscolha(generos, generosSelecionados);
        add(generoBox, "gapx 23 23, width 300, align center");

        // Descrição
        JLabel descricaoLabel = new JLabel(" Descrição");
        descricaoLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(descricaoLabel, "gapy 10 2");

        descricaoArea = new JTextArea(5, 20);
        descricaoArea.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Insira uma descrição para o jogo");
        add(new JScrollPane(descricaoArea), "width 300, height 100, gapy 5");

        // Botão Adicionar
        JButton adicionarButton = new JButton("Adicionar") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        adicionarButton.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
        add(adicionarButton, "gapy 10 5");

        adicionarButton.addActionListener(e -> adicionarJogo());

        add(criarSeparador(), "gapy 5 10");

        // Botão Voltar
        JButton voltarButton = criarBotaoSemBorda("Voltar");
        add(voltarButton, "gapx n push");
        voltarButton.addActionListener(e -> voltar());
    }

    private void adicionarJogo() {
        String nomeJogo = nomeJogoText.getText().trim();
        String dataLancamento = dataLancamentoField.getText().trim();
        List<Genero> generosSelecionados = generoBox.getSelecionados();
        String descricao = descricaoArea.getText().trim();
        String classificacaoEtaria = classificacaoEtariaField.getText().trim();

        if (nomeJogo.isEmpty() || dataLancamento.isEmpty() || descricao.isEmpty() || classificacaoEtaria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos obrigatórios.", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            List<Nota> notas = new ArrayList<>();
            Jogo jogo = new Jogo(nomeJogo, dataLancamento, descricao, classificacaoEtaria, notas, generosSelecionados);
            controleJogo.salvar(jogo);
            JOptionPane.showMessageDialog(this, "Jogo adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltar() {
        GerenciarJogos menu = new GerenciarJogos(darkTheme, idUsuario);
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
