package com.gdb.visao.gerenciar;

import com.formdev.flatlaf.FlatClientProperties;
import com.gdb.controle.JogoControle;
import com.gdb.controle.UsuarioControle;
import com.gdb.modelo.Jogo;
import com.gdb.modelo.Nota;
import com.gdb.modelo.Usuario;
import com.gdb.visao.Menu.Menu;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GerenciarNotas extends JPanel {
    private JTable tabelaNotas;
    private DefaultTableModel modeloTabela;

    private boolean darkTheme;
    private Integer idUsuario;
    private JogoControle jogoControle = new JogoControle();

    public GerenciarNotas(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        init();
        listarNotas();
    }

    private void init() {
        setLayout(new MigLayout("wrap, fill, insets 20, al center center"));

        JLabel tituloLabel = new JLabel("Tabela de Notas", JLabel.CENTER);
        tituloLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(tituloLabel, "span, align center");

        // Modificando as colunas, removendo "Data" e adicionando "Usuário"
        String[] colunas = {"ID Nota", "Jogo", "Usuário", "Trilha Sonora", "Gráficos", "História", "Jogabilidade", "Comentário"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };

        tabelaNotas = new JTable(modeloTabela);
        tabelaNotas.setRowHeight(30);
        tabelaNotas.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centralRenderer = new DefaultTableCellRenderer();
        centralRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelaNotas.getColumnCount(); i++) {
            tabelaNotas.getColumnModel().getColumn(i).setCellRenderer(centralRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tabelaNotas);
        add(scrollPane, "grow, wrap");

        JButton botaoAdicionar = new JButton("Adicionar Nota");
        JButton botaoEditar = new JButton("Salvar Alterações");
        JButton botaoRemover = new JButton("Remover Nota");

        botaoRemover.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF;" + "background:#CC0000");
        botaoAdicionar.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");

        JPanel panelBotoes = new JPanel(new MigLayout("insets 0", "[grow][grow][grow]"));
        panelBotoes.add(botaoAdicionar, "grow");
        panelBotoes.add(botaoEditar, "grow");
        panelBotoes.add(botaoRemover, "grow");
        add(panelBotoes, "span, align center");

        botaoAdicionar.addActionListener(e -> adicionarNota());
        botaoEditar.addActionListener(e -> editarNota());
        botaoRemover.addActionListener(e -> removerNota());

        JButton voltarButton = new JButton("Voltar");
        add(voltarButton, "gap left push");
        voltarButton.addActionListener(e -> voltarAoMenu());
    }

    private void adicionarNota() {
        // Abre o painel para adicionar uma nova nota
        AdicionarNota adicionarNota = new AdicionarNota(darkTheme, idUsuario);
        Container container = getParent();
        container.removeAll();
        container.add(adicionarNota);
        container.revalidate();
        container.repaint();
    }

    private void removerNota() {
        int selectedRow = tabelaNotas.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma nota para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int notaId = Integer.parseInt(modeloTabela.getValueAt(selectedRow, 0).toString());
        String jogoTitulo = modeloTabela.getValueAt(selectedRow, 1).toString();

        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir esta nota?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            Jogo jogo = jogoControle.buscarJogoPorTitulo(jogoTitulo);
            if (jogo != null) {
                List<Nota> notas = jogo.getNotas();
                notas.removeIf(nota -> nota.getId().equals(notaId));
                jogoControle.atualizarJogo(jogo.getId(), jogo.getTitulo(), jogo.getDescricao(),
                        jogo.getClassificacaoEtaria(), jogo.getDataLancamento(), jogo.getGeneros(), notas);
                modeloTabela.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Nota removida com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void editarNota() {
        for (int i = 0; i < tabelaNotas.getRowCount(); i++) {
            int notaId = Integer.parseInt(tabelaNotas.getValueAt(i, 0).toString());
            String jogoTitulo = tabelaNotas.getValueAt(i, 1).toString();
            Integer trilhaSonora = Integer.parseInt(tabelaNotas.getValueAt(i, 3).toString());
            Integer graficos = Integer.parseInt(tabelaNotas.getValueAt(i, 4).toString());
            Integer historia = Integer.parseInt(tabelaNotas.getValueAt(i, 5).toString());
            Integer jogabilidade = Integer.parseInt(tabelaNotas.getValueAt(i, 6).toString());
            String comentario = tabelaNotas.getValueAt(i, 7).toString();

            Jogo jogo = jogoControle.buscarJogoPorTitulo(jogoTitulo);
            if (jogo != null) {
                List<Nota> notas = jogo.getNotas();
                for (Nota nota : notas) {
                    if (nota.getId().equals(notaId)) {
                        nota.setNotaTrilhaSonora(trilhaSonora);
                        nota.setNotaGraficos(graficos);
                        nota.setNotaHistoria(historia);
                        nota.setNotaJogabilidade(jogabilidade);
                        nota.setComentario(comentario);
                        break;
                    }
                }
                jogoControle.atualizarJogo(jogo.getId(), jogo.getTitulo(), jogo.getDescricao(),
                        jogo.getClassificacaoEtaria(), jogo.getDataLancamento(), jogo.getGeneros(), notas);
            }
        }
        JOptionPane.showMessageDialog(this, "Notas editadas com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarNotas() {
        List<Jogo> jogos = jogoControle.carregarJogos();
        UsuarioControle usuarioControle = new UsuarioControle();
        for (Jogo jogo : jogos) {
            for (Nota nota : jogo.getNotas()) {
                // Obtendo o nome do usuário baseado no idUsuario
                Usuario usuario = usuarioControle.buscarUsuarioPorId(nota.getIdUsuario());
                String nomeUsuario = (usuario != null) ? usuario.getUsuario() : "Desconhecido";

                modeloTabela.addRow(new Object[]{
                        nota.getId(),
                        jogo.getTitulo(),
                        nomeUsuario,  // Colocando o nome do usuário
                        nota.getNotaTrilhaSonora(),
                        nota.getNotaGraficos(),
                        nota.getNotaHistoria(),
                        nota.getNotaJogabilidade(),
                        nota.getComentario()
                });
            }
        }
    }

    private void voltarAoMenu() {
        Menu menu = new Menu(darkTheme, idUsuario);
        Container container = getParent();
        container.removeAll();
        container.add(menu);
        container.revalidate();
        container.repaint();
    }
}
