package visao.Menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import controle.JogoControle;
import modelo.Jogo;
import modelo.Nota;
import visao.gerenciar.GerenciarNotas;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdicionarNotaTela extends JPanel {
    private JComboBox<String> jogoComboBox;
    private JSpinner notaTrilhaSonoraSpinner;
    private JSpinner notaGraficosSpinner;
    private JSpinner notaHistoriaSpinner;
    private JSpinner notaJogabilidadeSpinner;
    private JTextArea comentarioArea;

    private boolean darkTheme;
    private Integer idUsuario;
    Jogo jogo;

    private JogoControle jogoControle = new JogoControle();

    public AdicionarNotaTela(boolean darkTheme, Integer idUsuario, Jogo jogo) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        this.jogo = jogo;
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap, gapy 4, al center center", "[fill,300]"));
        add(new JLabel(new FlatSVGIcon("login/icon/logo.svg", 0.5f)));

        JLabel adicionarNotaLabel = new JLabel("Adicionar Nota", JLabel.CENTER);
        adicionarNotaLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(adicionarNotaLabel);

        add(criarSeparador(), "gapy 5");

        // Jogo

        JLabel jogoLabel = new JLabel(jogo.getTitulo());
        jogoLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(jogoLabel, "gapy 5");



        // Notas
        JLabel notasLabel = new JLabel(" Notas");
        notasLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(notasLabel, "gapy 10 2");

        notaTrilhaSonoraSpinner = criarSpinner();
        notaGraficosSpinner = criarSpinner();
        notaHistoriaSpinner = criarSpinner();
        notaJogabilidadeSpinner = criarSpinner();

        add(criarPainelNota("Trilha Sonora", notaTrilhaSonoraSpinner));
        add(criarPainelNota("Gráficos", notaGraficosSpinner));
        add(criarPainelNota("História", notaHistoriaSpinner));
        add(criarPainelNota("Jogabilidade", notaJogabilidadeSpinner));

        // Comentário
        JLabel comentarioLabel = new JLabel(" Comentário");
        comentarioLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(comentarioLabel, "gapy 10 2");

        comentarioArea = new JTextArea(5, 20);
        comentarioArea.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Insira um comentário sobre o jogo");
        add(new JScrollPane(comentarioArea), "width 300, height 100, gapy 5");

        // Botão Adicionar
        JButton adicionarButton = new JButton("Adicionar") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        adicionarButton.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
        add(adicionarButton, "gapy 10 5");

        adicionarButton.addActionListener(e -> adicionarNota());

        add(criarSeparador(), "gapy 5 10");

        // Botão Voltar
        JButton voltarButton = criarBotaoSemBorda("Voltar");
        add(voltarButton, "gapx n push");
        voltarButton.addActionListener(e -> voltar());
    }

    private void carregarJogos() {
        List<Jogo> jogos = jogoControle.carregarJogos();
        for (Jogo jogo : jogos) {
            jogoComboBox.addItem(jogo.getTitulo());
        }
    }

    private JSpinner criarSpinner() {
        SpinnerModel model = new SpinnerNumberModel(0, 0, 10, 1); // Notas de 0 a 10
        JSpinner spinner = new JSpinner(model);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setColumns(2);
        return spinner;
    }

    private JPanel criarPainelNota(String label, JSpinner spinner) {
        JPanel painel = new JPanel(new MigLayout("insets 0", "[grow][fill]"));
        JLabel notaLabel = new JLabel(label);
        painel.add(notaLabel, "grow");
        painel.add(spinner, "gapx 5");
        return painel;
    }

    private void adicionarNota() {
        String jogoTitulo = jogo.getTitulo();
        if (jogoTitulo == null || jogoTitulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um jogo.", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int trilhaSonora = (int) notaTrilhaSonoraSpinner.getValue();
        int graficos = (int) notaGraficosSpinner.getValue();
        int historia = (int) notaHistoriaSpinner.getValue();
        int jogabilidade = (int) notaJogabilidadeSpinner.getValue();
        String comentario = comentarioArea.getText().trim();

        try {
            Jogo jogo = jogoControle.buscarJogoPorTitulo(jogoTitulo);
            if (jogo == null) {
                JOptionPane.showMessageDialog(this, "Jogo não encontrado.", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Nota novaNota = new Nota(idUsuario, trilhaSonora, graficos, historia, jogabilidade, comentario);
            jogoControle.salvarNota(jogo.getId(), novaNota);

            JOptionPane.showMessageDialog(this, "Nota adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltar() {
        jogo = jogoControle.buscarJogoPorId(jogo.getId());
        DetalhesJogoPanel detalhesJogoPanel = new DetalhesJogoPanel(jogo, idUsuario, darkTheme);
        Container container = getParent();
        container.removeAll();
        container.add(detalhesJogoPanel);
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