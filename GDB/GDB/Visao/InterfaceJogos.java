import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InterfaceJogos {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfaceJogos().criarInterface());
    }

    private void criarInterface() {
        JFrame frame = new JFrame("Loja de Jogos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Painel superior com barra de pesquisa e botões de filtro
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Barra de pesquisa
        JTextField barraPesquisa = new JTextField("Pesquisar");
        barraPesquisa.setPreferredSize(new Dimension(200, 30));
        painelSuperior.add(barraPesquisa, BorderLayout.WEST);

        // Painel para os botões de filtro
        JPanel painelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnTodosJogos = new JButton("Todos os jogos");
        JButton btnJogosRecomendados = new JButton("Jogos recomendados");
        painelFiltros.add(btnTodosJogos);
        painelFiltros.add(btnJogosRecomendados);

        painelSuperior.add(painelFiltros, BorderLayout.CENTER);

        // Painel para botões "Gerenciar conta" e "Sair"
        JPanel painelUsuario = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGerenciarConta = new JButton("Gerenciar conta");
        JButton btnSair = new JButton("Sair");
        painelUsuario.add(btnGerenciarConta);
        painelUsuario.add(btnSair);

        painelSuperior.add(painelUsuario, BorderLayout.EAST);

        frame.add(painelSuperior, BorderLayout.NORTH);

        // Painel principal com a grade de jogos
        JPanel painelJogos = new JPanel(new GridLayout(3, 4, 20, 20));
        painelJogos.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Adiciona os jogos (ícones fictícios)
        for (int i = 0; i < 12; i++) {
            JPanel painelJogo = new JPanel(new BorderLayout());
            painelJogo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            JLabel iconJogo = new JLabel();
            iconJogo.setPreferredSize(new Dimension(100, 100));
            iconJogo.setHorizontalAlignment(JLabel.CENTER);
            iconJogo.setVerticalAlignment(JLabel.CENTER);
            iconJogo.setText("GDB"); // Colocando texto como marcador do ícone
            iconJogo.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

            JLabel nomeJogo = new JLabel("Nome do Jogo", JLabel.CENTER);
            nomeJogo.setForeground(Color.GRAY);

            painelJogo.add(iconJogo, BorderLayout.CENTER);
            painelJogo.add(nomeJogo, BorderLayout.SOUTH);

            painelJogos.add(painelJogo);
        }

        frame.add(painelJogos, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null); // Centraliza a janela
        frame.setVisible(true);
    }
}
