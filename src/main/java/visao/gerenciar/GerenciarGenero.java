package visao.gerenciar;

import com.formdev.flatlaf.FlatClientProperties;
import modelo.Genero;
import controle.Controle;
import visao.Menu.Menu;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GerenciarGenero extends JPanel {
    private JTable tabelaGeneros;
    private DefaultTableModel modeloTabela;
    private boolean darkTheme;
    private Integer idUsuario = 0;
    private Controle controle;

    public GerenciarGenero(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        this.controle = new Controle(); // Inicializa o controle de gêneros
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap, gapy 4, al center center", "[fill,500]"));

        JLabel tituloLabel = new JLabel("Tabela de Gêneros", JLabel.CENTER);
        tituloLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(tituloLabel, "split 2");

        JButton voltarButton = new JButton("Voltar");
        add(voltarButton, "gapy 10 5");

        // ActionListener para voltar ao menu
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Substitui o painel de login pelo painel do menu
                Menu menu = new Menu(darkTheme, idUsuario);
                Container container = getParent();
                container.removeAll();
                container.add(menu);
                container.revalidate();
                container.repaint();
            }
        });

        String[] colunas = {"ID", "Genero"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
        };

        tabelaGeneros = new JTable(modeloTabela);
        tabelaGeneros.setRowHeight(30);

        tabelaGeneros.getTableHeader().setReorderingAllowed(false);


        // Centralizar o conteúdo das células
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);

        // Aplicar o renderizador centralizado a todas as colunas
        for (int i = 0; i < tabelaGeneros.getColumnCount(); i++) {
            tabelaGeneros.getColumnModel().getColumn(i).setCellRenderer(centralizado);
        }

        JScrollPane scrollPane = new JScrollPane(tabelaGeneros);
        add(scrollPane, "");

        JButton botaoAdicionar = new JButton("Adicionar Gênero");
        JButton botaoEditar = new JButton("Salvar Alterações");
        JButton botaoRemover = new JButton("Remover Gênero");

        botaoRemover.putClientProperty(FlatClientProperties.STYLE,"foreground:#FFFFFF;" + "background:#CC0000");

        JPanel panelBotoes = new JPanel(new MigLayout("insets 0", "[grow][grow][grow]"));
        panelBotoes.add(botaoAdicionar, "grow");
        panelBotoes.add(botaoEditar, "grow");
        panelBotoes.add(botaoRemover, "grow");
        add(panelBotoes, "grow");

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarGenero();
            }
        });

        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarAlteracoes();
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerGenero();
            }
        });

        // Carregar os gêneros na tabela ao inicializar
        carregarGeneros();
    }


    private void carregarGeneros() {
        List<Genero> generos = controle.daoGenero.getGeneros();
        modeloTabela.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados
        for (Genero genero : generos) {
            modeloTabela.addRow(new Object[]{genero.getId(), genero.getGenero()});
        }
    }

    private void adicionarGenero() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do novo gênero:");
        Genero genero = new Genero(0, nome);
        if (nome != null && !nome.trim().isEmpty()) {
            try {
                controle.salvar("genero", genero);
                carregarGeneros(); // Recarrega a tabela após adicionar o gênero
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void salvarAlteracoes() {
        try {
            int totalLinhas = modeloTabela.getRowCount();

            for (int i = 0; i < totalLinhas; i++) {
                int idGenero = (int) modeloTabela.getValueAt(i, 0);
                String novoGenero = (String) modeloTabela.getValueAt(i, 1);

                // Valida cada gênero antes de salvar
                if (novoGenero == null || novoGenero.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "O nome do gênero na linha " + (i + 1) + " não pode estar vazio.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    carregarGeneros(); // Reverte as alterações para valores válidos
                    return;
                }

                // Atualiza o gênero no controle

                Genero genero = new Genero(idGenero, novoGenero);
                controle.atualizar("genero", genero);
            }

            JOptionPane.showMessageDialog(this, "Todas as alterações foram salvas com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro ao salvar alterações", JOptionPane.ERROR_MESSAGE);
            carregarGeneros(); // Reverte alterações em caso de erro
        }
    }


    private void removerGenero() {
        int linhaSelecionada = tabelaGeneros.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um gênero para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idGenero = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este gênero?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            controle.deletar("genero", idGenero);
            carregarGeneros(); // Recarrega a tabela após excluir o gênero
        }
    }
}
