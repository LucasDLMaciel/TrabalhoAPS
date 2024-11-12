package com.gdb.visao.login_registro;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MultiplaEscolha extends JPanel {

    private JCheckBox[] checkboxes;
    private JPopupMenu popupMenu;

    // Construtor que recebe uma lista de strings para as opções e uma lista de gêneros favoritos para marcar
    public MultiplaEscolha(List<String> options, List<String> generosFavoritos) {
        // Definir layout para empilhar os componentes verticalmente
        setLayout(new MigLayout("wrap, al center center", "[fill,300]"));

        // Criar o botão que irá exibir o menu de múltipla escolha
        JButton showOptionsButton = new JButton("Escolher opções");
        add(showOptionsButton);

        // Criar o JPopupMenu
        popupMenu = new JPopupMenu();

        // Criar o painel para armazenar os checkboxes
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("wrap 1")); // Usar MigLayout para otimizar o layout

        // Criar as opções de múltipla escolha com JCheckBox
        checkboxes = new JCheckBox[options.size()];
        for (int i = 0; i < options.size(); i++) {
            checkboxes[i] = new JCheckBox(options.get(i));
            // Apenas marcar as opções que estão na lista de gêneros favoritos
            if (generosFavoritos.contains(options.get(i))) {
                checkboxes[i].setSelected(true);
            }
            panel.add(checkboxes[i]); // Adicionar os checkboxes ao painel
        }

        // Envolver o painel com um JScrollPane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new java.awt.Dimension(244, 165)); // Definir um tamanho para o JScrollPane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Habilitar rolagem vertical

        // Melhorar o desempenho de rolagem (otimização)
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Velocidade de rolagem mais rápida
        scrollPane.setWheelScrollingEnabled(true); // Habilitar rolagem com roda do mouse
        popupMenu.add(scrollPane, "align center");

        // Adicionar o comportamento do botão de exibir o menu
        showOptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.show(showOptionsButton, 0, showOptionsButton.getHeight());
            }
        });
    }

    private void mostrarSelecao() {
        List<String> selecionados = getSelecionados();
        StringBuilder selectedOptions = new StringBuilder("Opções selecionadas: ");

        // Verificar quais opções estão selecionadas e adicionar ao StringBuilder
        for (String option : selecionados) {
            selectedOptions.append(option).append(" ");
        }

        // Exibir as opções selecionadas em uma caixa de diálogo
        JOptionPane.showMessageDialog(this, selectedOptions.toString());
    }

    // Função que retorna as opções selecionadas
    public List<String> getSelecionados() {
        List<String> selecionados = new ArrayList<>();

        // Verificar quais opções estão selecionadas
        for (JCheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                selecionados.add(checkbox.getText());
            }
        }

        return selecionados;
    }

    public void clearSelection() {
        for (JCheckBox checkBox : checkboxes) {
            checkBox.setSelected(false);
        }
    }

}
