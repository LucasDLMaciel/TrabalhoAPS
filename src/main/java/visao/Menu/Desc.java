package visao.Menu;

import com.formdev.flatlaf.FlatClientProperties;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;

public class Desc extends JPanel {
    private boolean darkTheme;
    private int idUsuario;
    private Image backgroundImage; // Variável para armazenar a imagem de fundo
    private JButton butao = new JButton();

    public Desc(boolean darkTheme, int idUsuario) {
        this.idUsuario = idUsuario;
        this.darkTheme = darkTheme;
        init();
    }

    private void init() {
        this.setLayout(null); // Usando layout nulo para posicionamento manual

        // Botão "voltar"
        JButton voltar = new JButton("voltar");
        voltar.setBounds(0, 0, 60, 30);
        voltar.putClientProperty(FlatClientProperties.STYLE, "");
        add(voltar);
        butao.setBounds(0, 0, 1280, 720);
        butao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.butao.setFocusPainted(false);
        this.butao.setBorderPainted(false);
        this.butao.setContentAreaFilled(false);
        add(butao);
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

        butao.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Random rand = new Random();
                int numeroAleatorio = rand.nextInt(8) + 1;
                switch (numeroAleatorio) {
                    case 1:
                        try {
                            backgroundImage = ImageIO.read(getClass().getResource("/Menu/manos.png"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 2:
                        try {
                            backgroundImage = ImageIO.read(getClass().getResource("/Menu/wesley.png"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 3:
                        try {
                            backgroundImage = ImageIO.read(getClass().getResource("/Menu/kamila.png"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 4:
                        try {
                            backgroundImage = ImageIO.read(getClass().getResource("/Menu/FElipe.png"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 5:
                        try {
                            backgroundImage = ImageIO.read(getClass().getResource("/Menu/manos2.png"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 6:
                        try {
                            backgroundImage = ImageIO.read(getClass().getResource("/Menu/manos3.png"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 7:
                        try {
                            backgroundImage = ImageIO.read(getClass().getResource("/Menu/dudu.png"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 8:
                        try {
                            backgroundImage = ImageIO.read(getClass().getResource("/Menu/cursed.png"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                }
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