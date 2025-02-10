package visao.gerenciar;

import com.formdev.flatlaf.FlatClientProperties;
import controle.ControleGenero;
import controle.ControleUsuario;
import modelo.Genero;
import modelo.Usuario;
import visao.Menu.Menu;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GerenciarUsuarios extends JPanel {
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;

    private boolean darkTheme;
    private Integer idUsuario = 0;
    private ControleUsuario controleUsuario = new ControleUsuario();

    public GerenciarUsuarios(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        init(); // inicializa a janela
        listarUsuarios(); // pedro: mostra os dados salvos de usuarios na tela.
    }

    private void init() {
        setLayout(new MigLayout("wrap, fill, insets 20, al center center"));

        JLabel tituloLabel = new JLabel("Tabela de Usuários", JLabel.CENTER);
        tituloLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(tituloLabel, "span, align center"); // Alinhamento centralizado com a opção "span"

        String[] colunas = {"ID", "Usuário", "Senha", "Data de Nascimento", "Gêneros Favoritos", "Administrador"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Integer.class; // ID como inteiro
                    case 5:
                        return Boolean.class; // Administrador como booleano (checkbox)
                    default:
                        return String.class; // Outras colunas como String
                }
            }
        };
        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setRowHeight(30);

        tabelaUsuarios.getTableHeader().setReorderingAllowed(false);


        // Centralizando o conteúdo das células de texto
        DefaultTableCellRenderer centralRenderer = new DefaultTableCellRenderer();
        centralRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabelaUsuarios.getColumnCount(); i++) {
            if (i != 5) {  // Não aplicar ao checkbox (coluna 5)
                tabelaUsuarios.getColumnModel().getColumn(i).setCellRenderer(centralRenderer);
            }
        }

        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        add(scrollPane, "grow, wrap");

        JButton botaoEditar = new JButton("Salvar Alterações");
        JButton botaoRemover = new JButton("Remover Usuário");
        JButton botaoAdicionar = new JButton("Adicionar Usuário") {
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
        add(panelBotoes, "span, align center"); // Alinhamento centralizado com a opção "span"

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarUsuario();
            }
        });

        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerUsuario();
            }
        });

        JButton voltarButton = new JButton("Voltar");
        add(voltarButton, "gap left push");

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
    }



    private void adicionarUsuario() {
        AdicionarUsuario adicionarUsuario = new AdicionarUsuario(darkTheme, idUsuario);
        Container container = getParent();
        container.removeAll();
        container.add(adicionarUsuario);
        container.revalidate();
        container.repaint();
    }

    private void removerUsuario() {
        int selectedRow = tabelaUsuarios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer userId = (Integer) modeloTabela.getValueAt(selectedRow, 0);

        // Caixa de diálogo para confirmar a exclusão
        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir este usuário?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        // Verifica se a resposta foi "Sim"
        if (resposta == JOptionPane.YES_OPTION) {
            controleUsuario.deletar(userId);
            modeloTabela.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Exclusão cancelada.", "Cancelado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editarUsuario() {
        // Iterar pelas linhas da tabela para capturar os dados atualizados
        for (int i = 0; i < tabelaUsuarios.getRowCount(); i++) {

            Integer idUsuario = Integer.parseInt(tabelaUsuarios.getValueAt(i, 0).toString());
            String usuario = tabelaUsuarios.getValueAt(i, 1).toString();
            String senha = tabelaUsuarios.getValueAt(i, 2).toString();
            String dataNascimento = tabelaUsuarios.getValueAt(i, 3).toString();
            String generosFavoritos = tabelaUsuarios.getValueAt(i, 4).toString();
            boolean isAdmin = Boolean.parseBoolean(tabelaUsuarios.getValueAt(i, 5).toString());

            List<String> selecionado = Arrays.asList(generosFavoritos.split(";"));

            ControleGenero controleGenero = new ControleGenero();

            List<Genero> generos = controleGenero.getEntidades();
            List<Genero> genFav = new ArrayList<>();

            // Verificar quais opções estão selecionadas
            for (String a : selecionado) {
                    for (Genero genero : generos) {
                        if(genero.getGenero().equals(a)){
                            genFav.add(genero);
                        }
                    }
            }

            // Adicionar o usuário atualizado na lista
            Usuario usuarioAux = new Usuario(usuario, senha, dataNascimento, genFav, isAdmin);
            usuarioAux.setId(idUsuario);
            controleUsuario.atualizar(usuarioAux);
        }
        JOptionPane.showMessageDialog(this, "Usuários editados com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = controleUsuario.getEntidades();
        List<Genero> generos;
        List<String> generoList = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            generos = usuario.getGenerosFavoritos();

            for(Genero registro : generos){
                generoList.add(registro.getGenero());
            }

            modeloTabela.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getUsuario(),
                    usuario.getSenha(),
                    usuario.getDataNascimento(),
                    String.join(";", generoList),
                    usuario.isAdministrador()
            });
            generoList = new ArrayList<>();
        }
    }
}
