package com.gdb.visao.Menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.gdb.controle.UsuarioControle;
import com.gdb.modelo.Jogo;
import com.gdb.modelo.Nota;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DetalhesJogoPanel extends JPanel {
    private Jogo jogo;
    private Integer idUsuario;
    private boolean darkTheme;

    public DetalhesJogoPanel(Jogo jogo, Integer idUsuario, boolean darkTheme) {
        this.jogo = jogo;
        this.idUsuario = idUsuario;
        this.darkTheme = darkTheme;
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap, gapy 4", "[fill,1280]"));

        JLabel tituloLabel = new JLabel(jogo.getTitulo(), JLabel.CENTER);
        tituloLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(tituloLabel);

        // Informações do jogo
        JLabel descricaoLabel = new JLabel("Descrição", JLabel.LEFT);
        descricaoLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(descricaoLabel, "gapy 5");

        JTextArea descricaoArea = new JTextArea(jogo.getDescricao());
        descricaoArea.setEditable(false);
        descricaoArea.setFocusable(false);
        descricaoArea.setLineWrap(true);
        descricaoArea.setWrapStyleWord(true);

        JScrollPane scrollPane2 = new JScrollPane(descricaoArea);
        scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane2, "width 300, height 100, gapy 5");

        // Notas do jogo
        JLabel notasLabel = new JLabel("Média de Notas", JLabel.LEFT);
        notasLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(notasLabel, "gapy 10");

        JPanel notasPanel = new JPanel(new MigLayout("wrap, gapy 4", "[grow,fill]"));
        notasPanel.setOpaque(false);

        notasPanel.add(criarBlocoNota("Média Geral", jogo.calcularMediaJogo()), "split 5");
        notasPanel.add(criarBlocoNota("Trilha Sonora", jogo.calcularMediaTrilhaSonora()));
        notasPanel.add(criarBlocoNota("Gráficos", jogo.calcularMediaGraficos()));
        notasPanel.add(criarBlocoNota("História", jogo.calcularMediaHistoria()));
        notasPanel.add(criarBlocoNota("Jogabilidade", jogo.calcularMediaJogabilidade()));

        add(notasPanel, "gapy 5");

        // Listar todas as notas
        JLabel todasNotasLabel = new JLabel("Notas dos Usuários", JLabel.LEFT);
        todasNotasLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        add(todasNotasLabel, "gapy 10");

        JPanel todasNotasPanel = new JPanel(new MigLayout("wrap, gapy 4", "[grow,fill]"));
        todasNotasPanel.setOpaque(false);

        for (Nota nota : jogo.getNotas()) {
            todasNotasPanel.add(criarBlocoNotaUsuario(nota));
        }

        JScrollPane scrollPane = new JScrollPane(todasNotasPanel);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        add(scrollPane, "height 300, gapy 5");

        // Verifica se o usuário já tem uma nota para o jogo
        Nota notaExistente = buscarNotaUsuario(idUsuario);
        JButton adicionarNotaButton;
        if(idUsuario > 0){
            if (notaExistente != null) {
                adicionarNotaButton = new JButton("Editar Nota");
                adicionarNotaButton.addActionListener(e -> editarNota(notaExistente));
            } else {
                adicionarNotaButton = new JButton("Adicionar Nota");
                adicionarNotaButton.addActionListener(e -> adicionarNota());
            }
            adicionarNotaButton.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
            add(adicionarNotaButton, "split 2, gapy 10, gapx push n");
            // Botão Voltar
            JButton voltarButton = new JButton("Voltar");
            voltarButton.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
            add(voltarButton, "gapx n push");
            voltarButton.addActionListener(e -> voltar());
        } else {
            // Botão Voltar
            JButton voltarButton = new JButton("Voltar");
            voltarButton.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
            add(voltarButton, "gapx push n");
            voltarButton.addActionListener(e -> voltar());
        }



    }

    private Nota buscarNotaUsuario(Integer idUsuario) {
        for (Nota nota : jogo.getNotas()) {
            if (nota.getIdUsuario().equals(idUsuario)) {
                return nota;
            }
        }
        return null;
    }

    private void editarNota(Nota notaExistente) {
        // Inicia a tela de edição da nota, passando a nota existente
        EditarNotaTela editarNotaTela = new EditarNotaTela(darkTheme, idUsuario, jogo, notaExistente);
        Container container = getParent();
        container.removeAll();
        container.add(editarNotaTela);
        container.revalidate();
        container.repaint();
    }

    private void adicionarNota() {
        AdicionarNotaTela adicionarNotaTela = new AdicionarNotaTela(darkTheme, idUsuario, jogo);
        Container container = getParent();
        container.removeAll();
        container.add(adicionarNotaTela);
        container.revalidate();
        container.repaint();
    }

    private JPanel criarBlocoNota(String titulo, double notaMedia) {
        JPanel blocoPanel = new JPanel(new MigLayout("insets 5", "[grow,fill]"));
        blocoPanel.setBorder(new RoundedBorder(10, Color.GRAY));

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        blocoPanel.add(tituloLabel);

        JLabel notaLabel = new JLabel(String.format("%.1f", notaMedia), JLabel.CENTER);
        notaLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +4; foreground:$Component.accentColor;");
        blocoPanel.add(notaLabel, "wrap");

        return blocoPanel;
    }

    private JPanel criarBlocoNotaUsuario(Nota nota) {
        JPanel blocoNota = new JPanel(new MigLayout("wrap, insets 10", "[grow,fill]"));
        UsuarioControle usuarioControle = new UsuarioControle();
        blocoNota.setBorder(new RoundedBorder(10, Color.LIGHT_GRAY));

        JLabel usuarioLabel = new JLabel("Usuário: " + usuarioControle.buscarUsuarioPorId(nota.getIdUsuario()).getUsuario());
        usuarioLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +2");
        blocoNota.add(usuarioLabel);

        JLabel notasDetalhes = new JLabel(String.format("Trilha Sonora: %d, Gráficos: %d, História: %d, Jogabilidade: %d",
                nota.getNotaTrilhaSonora(), nota.getNotaGraficos(), nota.getNotaHistoria(), nota.getNotaJogabilidade()));
        blocoNota.add(notasDetalhes);

        JTextArea comentarioArea = new JTextArea(nota.getComentario());
        comentarioArea.setEditable(false);
        comentarioArea.setLineWrap(true);
        comentarioArea.setWrapStyleWord(true);
        comentarioArea.setFocusable(false);
        JScrollPane comentarioScroll = new JScrollPane(comentarioArea);
        comentarioScroll.setPreferredSize(new Dimension(250, 100));
        blocoNota.add(comentarioScroll);

        return blocoNota;
    }

    private JSeparator criarSeparador() {
        return new JSeparator();
    }

    private JButton criarBotaoSemBorda(String text) {
        JButton botao = new JButton(text);
        botao.putClientProperty(FlatClientProperties.STYLE, "foreground:$Component.accentColor; margin:1,5,1,5; borderWidth:0; focusWidth:0; innerFocusWidth:0; background:null;");
        return botao;
    }

    private void voltar() {
        Menu menu = new Menu(darkTheme, idUsuario);
        Container container = getParent();
        container.removeAll();
        container.add(menu);
        container.revalidate();
        container.repaint();
    }

    public class RoundedBorder implements Border {
        private int radius;
        private Color color;

        public RoundedBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

}