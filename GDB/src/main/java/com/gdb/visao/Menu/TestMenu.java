package com.gdb.visao.Menu;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.gdb.controle.GeneroControle;
import com.gdb.controle.JogoControle;
import com.gdb.controle.NotaControle;
import com.gdb.modelo.Genero;
import com.gdb.modelo.Jogo;
import com.gdb.modelo.Nota;
import com.gdb.visao.login_registro.Login;
import com.gdb.visao.login_registro.TestLoginRegistro;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class  TestMenu extends JFrame {
    public TestMenu() {
        JogoControle jogoControle = new JogoControle();
        GeneroControle generoControle = new GeneroControle();
        NotaControle notaControle = new NotaControle();

// Criando uma nova nota
        Nota novaNota = new Nota(10, 9, 10, 9, "Jogo incrível, gráficos de tirar o fôlego.");
        Nota novaNota1 = new Nota(10, 9, 10, 9, "Jogo incrível, gráficos de tirar o fôlego.");
        Genero genero = new Genero(0, "teste");
        Genero genero2 = new Genero(0, "teste2");
        List<Genero> generos = new ArrayList<>();
        generos.add(genero);
        generos.add(genero2);
        generoControle.salvarGenero(genero.getGenero());
        generoControle.salvarGenero(genero2.getGenero());
        List<Nota> notas = new ArrayList<>();
        notas.add(novaNota);
        notas.add(novaNota1);
// Salvar a nota no arquivo JSON
        notaControle.salvarNota(novaNota);
        notaControle.salvarNota(novaNota1);

        Jogo novoJogo = new Jogo(
                "The Witcher 3",
                "2015-05-19",
                "Aventura épica de Geralt de Rivia.",
                "18+",
                notas, generos
        );


        try {
            // Tentando salvar o jogo
            jogoControle.salvarJogo(novoJogo);
            System.out.println("Jogo salvo com sucesso!");
        } catch (IllegalArgumentException e) {
            // Lidando com duplicidade
            System.out.println(e.getMessage());
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(new Dimension(1280, 720));
        setLocationRelativeTo(null);
        setResizable(false);
        FlatSVGIcon iconFrame = new FlatSVGIcon("login/icon/logo.svg", 0.067F);
        this.setIconImage(iconFrame.getImage());
        add(new Menu(true, 0));
    }

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("login.themes");
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font("Roboto", Font.PLAIN, 13));
        UIManager.put("Button.font", new Font("Roboto", Font.BOLD, 13));
        EventQueue.invokeLater(() -> new TestMenu().setVisible(true));
    }
}