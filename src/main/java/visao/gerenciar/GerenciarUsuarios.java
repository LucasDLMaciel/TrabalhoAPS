package visao.gerenciar;

import com.formdev.flatlaf.FlatClientProperties;
<<<<<<< HEAD
<<<<<<< HEAD:GDB/src/main/java/com/gdb/visao/gerenciar/GerenciarUsuarios.java
import com.gdb.controle.UsuarioControle;
import com.gdb.visao.Menu.Menu;
=======
=======
>>>>>>> e528347 (finalizar)
import controle.GeneroControle;
import controle.UsuarioControle;
import modelo.Genero;
import modelo.Usuario;
import visao.Menu.Menu;
<<<<<<< HEAD
>>>>>>> e528347 (finalizar):src/main/java/visao/gerenciar/GerenciarUsuarios.java
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
=======
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
>>>>>>> e528347 (finalizar)
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
<<<<<<< HEAD
import com.gdb.modelo.Usuario;
import java.util.List;
import java.util.ArrayList;
=======
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
>>>>>>> e528347 (finalizar)

public class GerenciarUsuarios extends JPanel {
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
<<<<<<< HEAD
    private boolean darkTheme;

    public GerenciarUsuarios(boolean darkTheme) {
        this.darkTheme = darkTheme;
=======

    private boolean darkTheme;
    private Integer idUsuario = 0;
    private UsuarioControle usuarioControle = new UsuarioControle();

    public GerenciarUsuarios(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
>>>>>>> e528347 (finalizar)
        init(); // inicializa a janela
        listarUsuarios(); // pedro: mostra os dados salvos de usuarios na tela.
    }

    private void init() {
        setLayout(new MigLayout("wrap, fill, insets 20, al center center"));

        JLabel tituloLabel = new JLabel("Tabela de Usuários", JLabel.CENTER);
        tituloLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
<<<<<<< HEAD
        add(tituloLabel, "split 2");
        JButton voltarButton = new JButton("Voltar");
        add(voltarButton, "gap left push");

        // ActionListener para voltar ao menu
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Substitui o painel de login pelo painel do menu
                Menu menu = new Menu(darkTheme);
                Container container = getParent();
                container.removeAll();
                container.add(menu);
                container.revalidate();
                container.repaint();
            }
        });
=======
        add(tituloLabel, "span, align center"); // Alinhamento centralizado com a opção "span"
>>>>>>> e528347 (finalizar)

        String[] colunas = {"ID", "Usuário", "Senha", "Data de Nascimento", "Gêneros Favoritos", "Administrador"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Integer.class; // ID como inteiro
<<<<<<< HEAD
                    case 4:
=======
                    case 5:
>>>>>>> e528347 (finalizar)
                        return Boolean.class; // Administrador como booleano (checkbox)
                    default:
                        return String.class; // Outras colunas como String
                }
            }
        };
        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setRowHeight(30);

<<<<<<< HEAD
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        add(scrollPane, "grow, wrap");

        JButton botaoEditar = new JButton("Editar Usuário");
        JButton botaoRemover = new JButton("Remover Usuário");


        JButton botaoAdicionar = new JButton("Adicionar Usuário"){
            @Override
            public boolean isDefaultButton(){
=======
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
>>>>>>> e528347 (finalizar)
                return true;
            }
        };

<<<<<<< HEAD
        botaoRemover.putClientProperty(FlatClientProperties.STYLE,"foreground:#FFFFFF;" + "background:#CC0000");

        botaoAdicionar.putClientProperty(FlatClientProperties.STYLE,"foreground:#FFFFFF");

=======
        botaoRemover.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF;" + "background:#CC0000");
        botaoAdicionar.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
>>>>>>> e528347 (finalizar)

        JPanel panelBotoes = new JPanel(new MigLayout("insets 0", "[grow][grow][grow]"));
        panelBotoes.add(botaoAdicionar, "grow");
        panelBotoes.add(botaoEditar, "grow");
        panelBotoes.add(botaoRemover, "grow");
<<<<<<< HEAD
        add(panelBotoes, "grow");

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
>>>>>>> e528347 (finalizar)

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerUsuario();
            }
        });
<<<<<<< HEAD
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
=======

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
>>>>>>> e528347 (finalizar)
            JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

<<<<<<< HEAD
        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este usuário?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            modeloTabela.removeRow(linhaSelecionada);
            // Aqui você pode remover o usuário do banco de dados ou de outro armazenamento persistente
        }
    }

    private void listarUsuarios() {
        //chama o controle pra trazer os dados
        List<Usuario> usuarios = UsuarioControle.ListaUsuariosForAdmin();

        // limpa a tabela antes de adicionar novos dados
        //modeloTabela.setRowCount(0);

        // adiciona cada usuario na tabela
        for (Usuario usuario : usuarios) {
            modeloTabela.addRow(new Object[]{
                    modeloTabela.getRowCount() + 1,  // ID (auto incrementado)
                    usuario.getUsuario(),            // Nome do usuário
                    usuario.getSenha(),              // Senha do usuário
                    usuario.getDataNascimento(),    // Data de nascimento
                    usuario.getAdministradorFlag()     // Se é administrador (true/false)
            });
        }
    }
}
=======
        int userId = (int) modeloTabela.getValueAt(selectedRow, 0);

        // Caixa de diálogo para confirmar a exclusão
        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja excluir este usuário?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        // Verifica se a resposta foi "Sim"
        if (resposta == JOptionPane.YES_OPTION) {
            usuarioControle.excluirUsuarioPorId(userId);
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
            GeneroControle generoControle = new GeneroControle();
            List<Genero> generos = generoControle.getGeneros();
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
            usuarioControle.atualizarUsuario(idUsuario, usuario, senha, dataNascimento, genFav, isAdmin);
        }
        JOptionPane.showMessageDialog(this, "Usuários editados com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioControle.lerRegistros();
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
>>>>>>> e528347 (finalizar)
