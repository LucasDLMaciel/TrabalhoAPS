package visao.Menu;

import com.formdev.flatlaf.FlatClientProperties;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Desc extends JPanel {
    private boolean darkTheme;
    private int idUsuario;
    private Image backgroundImage; // Variável para armazenar a imagem de fundo

    public Desc(boolean darkTheme, int idUsuario) {
        this.idUsuario = idUsuario;
        this.darkTheme = darkTheme;
        init();
    }

    private void init() {
        this.setLayout(null); // Usando layout nulo para posicionamento manual

        // Carregar a imagem de fundo
        try {
            // Usando getResource para carregar a imagem de dentro do projeto
            backgroundImage = ImageIO.read(getClass().getResource("/Menu/wesley.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Botão "voltar"
        JButton voltar = new JButton("voltar");
        voltar.setBounds(0, 0, 60, 30);
        voltar.putClientProperty(FlatClientProperties.STYLE, "");
        add(voltar);
        voltar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Menu menu = new Menu(darkTheme, idUsuario);
                Container container = getParent();
                container.removeAll();
                container.add(menu);
                container.revalidate();
                container.repaint();
            }
        });

        // JTextPane com fundo transparente
        JTextPane text = new JTextPane();
        text.setEditable(false);
        text.setOpaque(false); // Fundo transparente
        text.setBounds(50, 50, 200, 100); // Ajuste a posição e o tamanho conforme necessário
        add(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenhar a imagem de fundo
        if (backgroundImage != null) {
            // Redimensionar a imagem para cobrir todo o painel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}