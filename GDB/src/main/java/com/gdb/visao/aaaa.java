package com.gdb.visao;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class aaaa extends JFrame {
    private JButton botaoSair;
    private JButton botaoCadastro;
    private JTextField nome;
    private JPanel painel;
    private Clip clip;


    public aaaa() {
        //setContentPane(painel);
        setTitle("Menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        BackgroundPanel backgroundPanel = new BackgroundPanel("C:/Users/Eduardo/Downloads/xdd2.jpg");
        playMusic("C:/Users/Eduardo/Downloads/Wii.wav");
        botaoSair = new JButton("Sair");
        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(aaaa.this, "Sair do programa");
            }
        });


        // Set the layout and add the background panel
        setContentPane(backgroundPanel);


        // Set layout and add the background label
        setLayout(new BorderLayout());
        setVisible(true);
    }

    public void playMusic(String filePath) {
        try {
            // Open the audio file
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start(); // Start playing the clip

                // Loop the music continuously
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("The specified audio file was not found.");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new aaaa();
    }

}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        // Load the background image
        backgroundImage = new ImageIcon(imagePath).getImage();

        // Listen for window resize events to repaint with a scaled image
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();  // Repaint the panel when it resizes
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image scaled to the size of the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}