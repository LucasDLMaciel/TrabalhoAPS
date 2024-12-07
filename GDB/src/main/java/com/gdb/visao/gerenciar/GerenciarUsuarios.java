package com.gdb.visao.gerenciar;

import com.formdev.flatlaf.FlatClientProperties;
import com.gdb.controle.UsuarioControle;
import com.gdb.visao.Menu.Menu;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.gdb.modelo.Usuario;
import java.util.List;
import java.util.ArrayList;

public class GerenciarUsuarios extends JPanel {
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
    private boolean darkTheme;

    public GerenciarUsuarios(boolean darkTheme) {
        this.darkTheme = darkTheme;
        init(); // inicializa a janela
        listarUsuarios(); // pedro: mostra os dados salvos de usuarios na tela.
    }

    private void init() {
        setLayout(new MigLayout("wrap, fill, insets 20, al center center"));

        JLabel tituloLabel = new JLabel("Tabela de Usuários", JLabel.CENTER);
        tituloLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
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

        String[] colunas = {"ID", "Usuário", "Senha", "Data de Nascimento", "Gêneros Favoritos", "Administrador"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Integer.class; // ID como inteiro
                    case 4:
                        return Boolean.class; // Administrador como booleano (checkbox)
                    default:
                        return String.class; // Outras colunas como String
                }
            }
        };
        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        add(scrollPane, "grow, wrap");

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


        JPanel panelBotoes = new JPanel(new MigLayout("insets 0", "[grow][grow][grow]"));
        panelBotoes.add(botaoAdicionar, "grow");
        panelBotoes.add(botaoEditar, "grow");
        panelBotoes.add(botaoRemover, "grow");
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

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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