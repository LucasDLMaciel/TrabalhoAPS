package visao.gerenciar;

import com.formdev.flatlaf.FlatClientProperties;
import controle.Controle;
import controle.Controle;
import modelo.Genero;
import modelo.Jogo;
import visao.Menu.Menu;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GerenciarJogos extends JPanel {
    private JTable tabelaJogos;
    private DefaultTableModel modeloTabela;

    private boolean darkTheme;
    private Integer idUsuario;
    private Controle controle = new Controle();

    public GerenciarJogos(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        init(); // inicializa a janela
        listarJogos(); // Carrega os dados dos jogos na tabela
    }

    private void init() {
        setLayout(new MigLayout("wrap, fill, insets 20, al center center"));

        JLabel tituloLabel = new JLabel("Tabela de Jogos", JLabel.CENTER);
        tituloLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(tituloLabel, "span, align center");

        String[] colunas = {"ID", "Título", "Descrição", "Classificação Etária", "Data de Lançamento", "Gêneros"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class; // Todas as colunas são tratadas como String
            }
        };

        tabelaJogos = new JTable(modeloTabela);
        tabelaJogos.setRowHeight(30);
        tabelaJogos.getTableHeader().setReorderingAllowed(false);

        // Centralizando o conteúdo das células de texto
        DefaultTableCellRenderer centralRenderer = new DefaultTableCellRenderer();
        centralRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelaJogos.getColumnCount(); i++) {
            tabelaJogos.getColumnModel().getColumn(i).setCellRenderer(centralRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(tabelaJogos);
        add(scrollPane, "grow, wrap");

        JButton botaoEditar = new JButton("Salvar Alterações");
        JButton botaoRemover = new JButton("Remover Jogo");
        JButton botaoAdicionar = new JButton("Adicionar Jogo") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };

        botaoRemover.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF;" + "background:#CC0000");
        botaoAdicionar.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");

        JPanel panelBotoes = new JPanel(new MigLayout("insets 0", "[grow][grow][grow]"));
        panelBotoes.add(botaoAdicionar, "grow");
        panelBotoes.add(botaoEditar, "grow");
        panelBotoes.add(botaoRemover, "grow");
        add(panelBotoes, "span, align center");

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarJogo();
            }
        });

        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarJogo();
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerJogo();
            }
        });

        JButton voltarButton = new JButton("Voltar");
        add(voltarButton, "gap left push");

        // ActionListener para voltar ao menu
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Substitui o painel atual pelo painel do menu
                Menu menu = new Menu(darkTheme, idUsuario);
                Container container = getParent();
                container.removeAll();
                container.add(menu);
                container.revalidate();
                container.repaint();
            }
        });
    }

    private void adicionarJogo() {
        AdicionarJogo adicionarJogo = new AdicionarJogo(darkTheme, idUsuario);
        Container container = getParent();
        container.removeAll();
        container.add(adicionarJogo);
        container.revalidate();
        container.repaint();
    }

    private void removerJogo() {
        int selectedRow = tabelaJogos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um jogo para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer jogoId = Integer.parseInt(modeloTabela.getValueAt(selectedRow, 0).toString());

        // Caixa de diálogo para confirmar a exclusão
        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir este jogo?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            controle.deletar("jogo",jogoId);
            modeloTabela.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Jogo excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Exclusão cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editarJogo() {
        for (int i = 0; i < tabelaJogos.getRowCount(); i++) {
            Integer idJogo = Integer.parseInt(tabelaJogos.getValueAt(i, 0).toString());
            String titulo = tabelaJogos.getValueAt(i, 1).toString();
            String descricao = tabelaJogos.getValueAt(i, 2).toString();
            String classificacaoEtaria = tabelaJogos.getValueAt(i, 3).toString();
            String dataLancamento = tabelaJogos.getValueAt(i, 4).toString();
            String generosString = tabelaJogos.getValueAt(i, 5).toString();
            Controle controle = new Controle();
            List<String> selecionado = List.of(generosString.split(";"));
            List<Genero> generosAtualizados = new ArrayList<>();
            List<Genero> generos = controle.daoGenero.getGeneros();
            List<Genero> genFav = new ArrayList<>();
            Jogo jogo = controle.daoJogo.buscarPorId(idJogo);

            // Verificar quais opções estão selecionadas
            for (String a : selecionado) {
                for (Genero genero : generos) {
                    if(genero.getGenero().equals(a)){
                        genFav.add(genero);
                    }
                }
            }

            jogo.setTitulo(titulo);
            jogo.setDescricao(descricao);
            jogo.setClassificacaoEtaria(classificacaoEtaria);
            jogo.setDataLancamento(dataLancamento);
            jogo.setGeneros(genFav);

            controle.atualizar("jogo",jogo);
        }
        JOptionPane.showMessageDialog(this, "Jogos editados com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarJogos() {
        List<Jogo> jogos = controle.daoJogo.getJogos();
        for (Jogo jogo : jogos) {
            List<String> generos = new ArrayList<>();
            for (Genero genero : jogo.getGeneros()) {
                generos.add(genero.getGenero());
            }

            modeloTabela.addRow(new Object[]{
                    jogo.getId(),
                    jogo.getTitulo(),
                    jogo.getDescricao(),
                    jogo.getClassificacaoEtaria(),
                    jogo.getDataLancamento(),
                    String.join(";", generos)
            });
        }
    }
}
