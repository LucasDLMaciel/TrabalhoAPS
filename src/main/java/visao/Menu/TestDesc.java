<<<<<<< HEAD
<<<<<<< HEAD:GDB/src/main/java/com/gdb/visao/Menu/TestDesc.java
package com.gdb.visao.Menu;
import com.formdev.flatlaf.FlatClientProperties;
=======
package visao.Menu;
>>>>>>> e528347 (finalizar):src/main/java/visao/Menu/TestDesc.java
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
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

public class TestDesc extends JFrame {
    public TestDesc(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1280, 720));
        setTitle("Descrição - Game Database");
        setLocationRelativeTo(null);
        add(new Desc());
    };

    public static void main(String[] args) {
        TestDesc t = new TestDesc();
        FlatLaf.registerCustomDefaultsSource("login.themes");
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font("Roboto", Font.PLAIN, 13));
        EventQueue.invokeLater(() -> new TestDesc().setVisible(true));
    }
}
=======
<<<<<<<< HEAD:src/main/java/Main.java
========
package visao.Menu;
>>>>>>>> e528347 (finalizar):src/main/java/visao/Menu/TestDesc.java
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import visao.Menu.Menu;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main extends JFrame {
    public Main() {
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
        EventQueue.invokeLater(() -> new Main().setVisible(true));
    }
}
>>>>>>> e528347 (finalizar)
