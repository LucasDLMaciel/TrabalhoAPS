package visao.gerenciar;

import com.formdev.flatlaf.FlatClientProperties;
<<<<<<< HEAD
<<<<<<< HEAD:GDB/src/main/java/com/gdb/visao/gerenciar/GerenciarGenero.java
import com.gdb.visao.Menu.Menu;
=======
import modelo.Genero;
import controle.GeneroControle;
import visao.Menu.Menu;
>>>>>>> e528347 (finalizar):src/main/java/visao/gerenciar/GerenciarGenero.java
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
=======
import modelo.Genero;
import controle.GeneroControle;
import visao.Menu.Menu;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
>>>>>>> e528347 (finalizar)
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD

public class GerenciarGenero extends JPanel {
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
    private boolean darkTheme;

    public GerenciarGenero(boolean darkTheme) {
        this.darkTheme = darkTheme;
=======
import java.util.List;

public class GerenciarGenero extends JPanel {
    private JTable tabelaGeneros;
    private DefaultTableModel modeloTabela;
    private boolean darkTheme;
    private Integer idUsuario = 0;
    private GeneroControle generoControle;

    public GerenciarGenero(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        this.generoControle = new GeneroControle(); // Inicializa o controle de gêneros
>>>>>>> e528347 (finalizar)
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
<<<<<<< HEAD
                Menu menu = new Menu(darkTheme);
=======
                Menu menu = new Menu(darkTheme, idUsuario);
>>>>>>> e528347 (finalizar)
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
<<<<<<< HEAD
        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        add(scrollPane, "");

        JButton botaoEditar = new JButton("Editar Usuário");
        JButton botaoRemover = new JButton("Remover Usuário");


        JButton botaoAdicionar = new JButton("Adicionar Usuário"){
            @Override
            public boolean isDefaultButton(){
                return true;
            }
        };

        botaoRemover.putClientProperty(FlatClientProperties.STYLE,"foreground:#FFFFFF;" + "background:#CC0000");

        botaoAdicionar.putClientProperty(FlatClientProperties.STYLE,"foreground:#FFFFFF");


=======

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

>>>>>>> e528347 (finalizar)
        JPanel panelBotoes = new JPanel(new MigLayout("insets 0", "[grow][grow][grow]"));
        panelBotoes.add(botaoAdicionar, "grow");
        panelBotoes.add(botaoEditar, "grow");
        panelBotoes.add(botaoRemover, "grow");
        add(panelBotoes, "grow");

<<<<<<< HEAD
//        botaoAdicionar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                adicionarUsuario();
//            }
//        });
//
//        botaoEditar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                editarUsuario();
//            }
//        });
=======
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
>>>>>>> e528347 (finalizar)

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
                removerUsuario();
            }
        });
    }

//    private void adicionarUsuario() {
//        Registro registro = new Registro();
//        int resultado = JOptionPane.showConfirmDialog(this, registro, "Adicionar Usuário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//        if (resultado == JOptionPane.OK_OPTION) {
//            String usuario = registro.getUsuario();
//            String senha = registro.getSenha();
//            String dataNascimento = registro.getDataNascimento();
//            String generos = String.join(", ", registro.getGenerosSelecionados());
//            boolean isAdmin = registro.isAdmin();
//
//            modeloTabela.addRow(new Object[]{usuario, senha, dataNascimento, generos, isAdmin});
//            // Aqui você pode salvar as informações no banco de dados ou em outro armazenamento persistente
//        }
//    }

//    private void editarUsuario() {
//        int linhaSelecionada = tabelaUsuarios.getSelectedRow();
//        if (linhaSelecionada == -1) {
//            JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        String usuario = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
//        String senha = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
//        String dataNascimento = (String) modeloTabela.getValueAt(linhaSelecionada, 2);
//        String generos = (String) modeloTabela.getValueAt(linhaSelecionada, 3);
//        boolean isAdmin = (boolean) modeloTabela.getValueAt(linhaSelecionada, 4);
//
//        Registro registro = new Registro();
//        registro.setUsuario(usuario);
//        registro.setSenha(senha);
//        registro.setDataNascimento(dataNascimento);
//        registro.setGenerosSelecionados(generos.split(", "));
//        registro.setAdmin(isAdmin);
//
//        int resultado = JOptionPane.showConfirmDialog(this, registro, "Editar Usuário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//        if (resultado == JOptionPane.OK_OPTION) {
//            modeloTabela.setValueAt(registro.getUsuario(), linhaSelecionada, 0);
//            modeloTabela.setValueAt(registro.getSenha(), linhaSelecionada, 1);
//            modeloTabela.setValueAt(registro.getDataNascimento(), linhaSelecionada, 2);
//            modeloTabela.setValueAt(String.join(", ", registro.getGenerosSelecionados()), linhaSelecionada, 3);
//            modeloTabela.setValueAt(registro.isAdmin(), linhaSelecionada, 4);
//            // Aqui você pode atualizar as informações no banco de dados ou em outro armazenamento persistente
//        }
//    }

    private void removerUsuario() {
        int linhaSelecionada = tabelaUsuarios.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este usuário?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            modeloTabela.removeRow(linhaSelecionada);
            // Aqui você pode remover o usuário do banco de dados ou de outro armazenamento persistente
        }
    }
}
=======
                removerGenero();
            }
        });

        // Carregar os gêneros na tabela ao inicializar
        carregarGeneros();
    }


    private void carregarGeneros() {
        List<Genero> generos = generoControle.lerRegistros();
        modeloTabela.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados
        for (Genero genero : generos) {
            modeloTabela.addRow(new Object[]{genero.getId(), genero.getGenero()});
        }
    }

    private void adicionarGenero() {
        String genero = JOptionPane.showInputDialog(this, "Digite o nome do novo gênero:");
        if (genero != null && !genero.trim().isEmpty()) {
            try {
                generoControle.salvarGenero(genero);
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
                generoControle.atualizarGenero(idGenero, novoGenero);
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
            generoControle.excluirGeneroPorId(idGenero);
            carregarGeneros(); // Recarrega a tabela após excluir o gênero
        }
    }
}
>>>>>>> e528347 (finalizar)
