package com.gdb.visao.login_registro;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenciarUsuarios extends JPanel {
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;

    public GerenciarUsuarios() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap, fill, insets 20, al center center"));

        JLabel tituloLabel = new JLabel("Tabela de Usuários", JLabel.CENTER);
        tituloLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +15");
        add(tituloLabel, "split 2");

        JButton voltarButton = new JButton("Voltar");
        voltarButton.putClientProperty(FlatClientProperties.STYLE,"foreground:#FFFFFF");

        add(voltarButton, "gapleft push");

        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        String[] colunas = {"ID", "Usuário", "Senha", "Data de Nascimento", "Gêneros Favoritos", "Administrador"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 4 ? Boolean.class : String.class;
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
}