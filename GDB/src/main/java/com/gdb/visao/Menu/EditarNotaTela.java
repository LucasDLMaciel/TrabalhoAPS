package com.gdb.visao.Menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.gdb.controle.JogoControle;
import com.gdb.modelo.Jogo;
import com.gdb.modelo.Nota;
import com.gdb.visao.gerenciar.GerenciarNotas;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class EditarNotaTela extends JPanel {
    private JSpinner notaTrilhaSonoraSpinner;
    private JSpinner notaGraficosSpinner;
    private JSpinner notaHistoriaSpinner;
    private JSpinner notaJogabilidadeSpinner;
    private JTextArea comentarioArea;

    private boolean darkTheme;
    private Integer idUsuario;
    private Jogo jogo;
    private Nota notaExistente;

    private JogoControle jogoControle = new JogoControle();

    public EditarNotaTela(boolean darkTheme, Integer idUsuario, Jogo jogo, Nota nota) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        this.jogo = jogo;
        this.notaExistente = nota;
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap, gapy 4, al center center", "[fill,300]"));
        add(new JLabel(new FlatSVGIcon("login/icon/logo.svg", 0.5f)));

        JLabel editarNotaLabel = new JLabel("Editar Nota", JLabel.CENTER);
        editarNotaLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(editarNotaLabel);

        add(criarSeparador(), "gapy 5");

        // Jogo
        JLabel jogoLabel = new JLabel(jogo.getTitulo());
        jogoLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(jogoLabel, "gapy 5");

        // Notas
        JLabel notasLabel = new JLabel(" Notas");
        notasLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(notasLabel, "gapy 10 2");

        notaTrilhaSonoraSpinner = criarSpinner(notaExistente.getNotaTrilhaSonora());
        notaGraficosSpinner = criarSpinner(notaExistente.getNotaGraficos());
        notaHistoriaSpinner = criarSpinner(notaExistente.getNotaHistoria());
        notaJogabilidadeSpinner = criarSpinner(notaExistente.getNotaJogabilidade());

        add(criarPainelNota("Trilha Sonora", notaTrilhaSonoraSpinner));
        add(criarPainelNota("Gráficos", notaGraficosSpinner));
        add(criarPainelNota("História", notaHistoriaSpinner));
        add(criarPainelNota("Jogabilidade", notaJogabilidadeSpinner));

        // Comentário
        JLabel comentarioLabel = new JLabel(" Comentário");
        comentarioLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(comentarioLabel, "gapy 10 2");

        comentarioArea = new JTextArea(5, 20);
        comentarioArea.setText(notaExistente.getComentario());
        comentarioArea.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Editar seu comentário sobre o jogo");
        add(new JScrollPane(comentarioArea), "width 300, height 100, gapy 5");

        // Botão Editar
        JButton editarButton = new JButton("Editar") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        editarButton.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
        add(editarButton, "gapy 10 5");

        editarButton.addActionListener(e -> editarNota());

        add(criarSeparador(), "gapy 5 10");

        // Botão Voltar
        JButton voltarButton = criarBotaoSemBorda("Voltar");
        add(voltarButton, "gapx n push");
        voltarButton.addActionListener(e -> voltar());
    }

    private JSpinner criarSpinner(int valorAtual) {
        SpinnerModel model = new SpinnerNumberModel(valorAtual, 0, 10, 1); // Notas de 0 a 10
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

    private void editarNota() {
        // Recupera os valores dos campos preenchidos
        int trilhaSonora = (int) notaTrilhaSonoraSpinner.getValue();
        int graficos = (int) notaGraficosSpinner.getValue();
        int historia = (int) notaHistoriaSpinner.getValue();
        int jogabilidade = (int) notaJogabilidadeSpinner.getValue();
        String comentario = comentarioArea.getText().trim();

        try {
            // Chama o método editarNota no controlador
            jogoControle.editarNota(
                    jogo.getId(),                      // ID do jogo
                    notaExistente.getId(),             // ID da nota
                    trilhaSonora,                      // Nova nota para trilha sonora
                    graficos,                          // Nova nota para gráficos
                    historia,                          // Nova nota para história
                    jogabilidade,                      // Nova nota para jogabilidade
                    comentario                         // Novo comentário
            );

            JOptionPane.showMessageDialog(this, "Nota editada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Atualiza a tela de detalhes do jogo
            voltar();
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
