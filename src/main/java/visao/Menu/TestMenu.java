package visao.Menu;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class  TestMenu extends JFrame {
    public TestMenu() {
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