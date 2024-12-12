package com.gdb.visao.gerenciar;

import com.formdev.flatlaf.FlatClientProperties;
import com.gdb.controle.JogoControle;
import com.gdb.modelo.Genero;
import com.gdb.modelo.Jogo;
import com.gdb.visao.Menu.Menu;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciarJogos extends JPanel {
    private JTable tabelaJogos;
    private DefaultTableModel modeloTabela;

    private boolean darkTheme;
    private Integer idUsuario = 0;
    private JogoControle jogoControle = new JogoControle();

    public GerenciarJogos(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        init(); // Inicializa a janela
        listarJogos(); // Mostra os dados salvos de jogos na tela
    }

    private void init() {
        setLayout(new MigLayout("wrap, fill, insets 20, al center center"));

        JLabel tituloLabel = new JLabel("Tabela de Jogos", JLabel.CENTER);
        tituloLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(tituloLabel, "span, align center");

        String[] colunas = {"ID", "Título", "Descrição", "Faixa Etária", "Gêneros"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Integer.class : String.class;
            }
        };

        tabelaJogos = new JTable(modeloTabela);
        tabelaJogos.setRowHeight(30);
        tabelaJogos.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centralRenderer = new DefaultTableCellRenderer();
        centralRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelaJogos.getColumnCount(); i++) {
            tabelaJogos.getColumnModel().getColumn(i).setCellRenderer(centralRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tabelaJogos);
        add(scrollPane, "grow, wrap");

        JButton botaoAdicionar = new JButton("Adicionar Jogo");
        JButton botaoEditar = new JButton("Salvar Alterações");
        JButton botaoRemover = new JButton("Remover Jogo");

        botaoRemover.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF;background:#CC0000");
        botaoAdicionar.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");

        JPanel panelBotoes = new JPanel(new MigLayout("insets 0", "[grow][grow][grow]"));
        panelBotoes.add(botaoAdicionar, "grow");
        panelBotoes.add(botaoEditar, "grow");
        panelBotoes.add(botaoRemover, "grow");
        add(panelBotoes, "span, align center");

        botaoAdicionar.addActionListener(e -> adicionarJogo());
        botaoEditar.addActionListener(e -> editarJogos());
        botaoRemover.addActionListener(e -> removerJogo());

        JButton voltarButton = new JButton("Voltar");
        add(voltarButton, "gap left push");
        voltarButton.addActionListener(e -> voltarMenu());
    }

    private void adicionarJogo() {
        String titulo = JOptionPane.showInputDialog(this, "Digite o título do jogo:");
        String descricao = JOptionPane.showInputDialog(this, "Digite a descrição do jogo:");
        String faixaEtaria = JOptionPane.showInputDialog(this, "Digite a faixa etária do jogo:");
        String dataLancamento = JOptionPane.showInputDialog(this, "Digite a data de lançamento do jogo:");

        // Selecionar gêneros
        List<Genero> todosGeneros = jogoControle.carregarGeneros();
        String generosSelecionados = JOptionPane.showInputDialog(this,
                "Escolha os gêneros (IDs separados por vírgula):\n" +
                        todosGeneros.stream()
                                .map(g -> g.getId() + " - " + g.getGenero())
                                .collect(Collectors.joining("\n")));

        List<Genero> generos = new ArrayList<>();
        if (generosSelecionados != null && !generosSelecionados.isBlank()) {
            for (String idStr : generosSelecionados.split(",")) {
                int id = Integer.parseInt(idStr.trim());
                todosGeneros.stream()
                        .filter(g -> g.getId() == id)
                        .findFirst()
                        .ifPresent(generos::add);
            }
        }

        if (titulo != null && !titulo.isBlank()) {
            Jogo novoJogo = new Jogo(titulo, dataLancamento, descricao, faixaEtaria, generos);
            try {
                jogoControle.salvarJogo(novoJogo);
                modeloTabela.addRow(new Object[]{
                        novoJogo.getId(),
                        novoJogo.getTitulo(),
                        novoJogo.getDescricao(),
                        novoJogo.getClassificacaoEtaria(),
                        generos.stream().map(Genero::getGenero).collect(Collectors.joining(", "))
                });
                JOptionPane.showMessageDialog(this, "Jogo adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removerJogo() {
        int selectedRow = tabelaJogos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um jogo para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int jogoId = (int) modeloTabela.getValueAt(selectedRow, 0);

        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir este jogo?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            jogoControle.carregarJogos().removeIf(jogo -> jogo.getId() == jogoId);
            modeloTabela.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Jogo excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editarJogos() {
        for (int i = 0; i < tabelaJogos.getRowCount(); i++) {
            int id = (int) modeloTabela.getValueAt(i, 0);
            String titulo = modeloTabela.getValueAt(i, 1).toString();
            String descricao = modeloTabela.getValueAt(i, 2).toString();
            String faixaEtaria = modeloTabela.getValueAt(i, 3).toString();
            String[] generosStr = modeloTabela.getValueAt(i, 4).toString().split(", ");

            List<Genero> generos = jogoControle.carregarGeneros().stream()
                    .filter(g -> List.of(generosStr).contains(g.getGenero()))
                    .collect(Collectors.toList());

            Jogo jogoEditado = new Jogo(id, titulo, descricao, faixaEtaria, generos);
            jogoControle.salvarJogo(jogoEditado);
        }
        JOptionPane.showMessageDialog(this, "Jogos editados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarJogos() {
        List<Jogo> jogos = jogoControle.carregarJogos();

        for (Jogo jogo : jogos) {
            modeloTabela.addRow(new Object[]{
                    jogo.getId(),
                    jogo.getTitulo(),
                    jogo.getDescricao(),
                    jogo.getClassificacaoEtaria(),
                    jogo.getGeneros().stream().map(Genero::getGenero).collect(Collectors.joining(", "))
            });
        }
    }

    private void voltarMenu() {
        Menu menu = new Menu(darkTheme, idUsuario);
        Container container = getParent();
        container.removeAll();
        container.add(menu);
        container.revalidate();
        container.repaint();
    }
}
