package visao.Menu;

import com.formdev.flatlaf.FlatClientProperties;
<<<<<<< HEAD
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.gdb.visao.login_registro.Login;
import com.gdb.visao.login_registro.Registro;
import com.gdb.visao.login_registro.TestLoginRegistro;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ColorModel;
import javax.swing.*;

public class Desc extends JPanel {
    public Desc() {
=======

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Desc extends JPanel {
    private boolean darkTheme;
    private int idUsuario;
    public Desc(boolean darkTheme, int idUsuario) {
        this.idUsuario = idUsuario;
        this.darkTheme = darkTheme;
>>>>>>> e528347 (finalizar)
        init();
    }

    private void init() {
<<<<<<< HEAD
        JButton Voltar = new JButton("Voltar");
        Voltar.putClientProperty(FlatClientProperties.STYLE, "");
        Voltar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //logica de voltar pro menu
=======
        this.setLayout(null);
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
>>>>>>> e528347 (finalizar)
            }
        });
        JTextPane text = new JTextPane();
        text.setEditable(false);
        text.setBackground(Color.BLACK);
        add(text);
    }
}
