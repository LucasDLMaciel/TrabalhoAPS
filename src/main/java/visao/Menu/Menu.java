package visao.Menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import controle.ControleJogo;
import controle.ControleUsuario;
import modelo.Genero;
import modelo.Jogo;
import modelo.Usuario;
import visao.gerenciar.*;
import visao.login_registro.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

public class Menu extends JPanel {
    private JTextField Buscar;
    private JButton Sair_Botao;
    private JButton Login_Botao;
    private JButton todosButton;
    private JButton recomendadosButton;
    JButton toggleThemeButton;
    private JButton database;
    private JButton gerenciarConta;
    private JButton gerenciarGenero;
    private JButton gerenciarUsuarios;
    private JButton gerenciarJogos;
    private JButton gerenciarNotas;
    // Defina os objetos adicionais necessários
    private JPanel jogosPanel;  // Painel para os botões de jogos
    private JScrollPane jogosScrollPane;  // Para permitir rolagem caso haja muitos jogos;

    private Integer idUsuario;
    private boolean admin = true;
    private boolean selecionado;
    private boolean darkTheme;
    private ControleJogo controleJogo;
    private ControleUsuario controleUsuario;
    private Usuario usuario;



    public Menu(boolean darkTheme, Integer idUsuario) {
        this.darkTheme = darkTheme;
        this.idUsuario = idUsuario;
        this.controleJogo = new ControleJogo();
        this.controleUsuario = new ControleUsuario();
        usuario = (Usuario) controleUsuario.buscarPorId(idUsuario);
        if(usuario != null){
            this.admin = usuario.isAdministrador();
        }
        this.init();
    }

    private void toggleTheme() {
        // Alterna o tema e redefine o estilo
        if (darkTheme) {
            FlatMacLightLaf.setup();
        } else {
            FlatMacDarkLaf.setup();
        }
        darkTheme = !darkTheme;

        // Atualiza o tema para todos os componentes
        SwingUtilities.updateComponentTreeUI(SwingUtilities.getWindowAncestor(this));
        this.jogosScrollPane.setBorder(null);
        this.jogosScrollPane.getVerticalScrollBar().setUnitIncrement(30);
        this.jogosScrollPane.getVerticalScrollBar().setBlockIncrement(30);
    }

    private void init() {
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLayout(null);  // Layout nulo para controle manual de posição dos componentes

        // Inicializar os componentes do menu
        this.jogosPanel = new JPanel();

        // Layout GridBagLayout
        this.jogosPanel.setLayout(new GridBagLayout());

        // Definindo o JScrollPane
        this.jogosScrollPane = new JScrollPane(this.jogosPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.jogosScrollPane.setBounds(70, 150, 1125, 500);  // Define a posição e o tamanho do painel de rolagem
        this.jogosScrollPane.setBorder(null);
        this.jogosScrollPane.getVerticalScrollBar().setUnitIncrement(30);
        this.jogosScrollPane.getVerticalScrollBar().setBlockIncrement(30);
        add(this.jogosScrollPane);

        // Adicionar um botão para cada jogo
        adicionarBotoesJogos();

        this.setPreferredSize(new Dimension(1280, 720));

        // Adicionar um botão para cada jogo
        adicionarBotoesJogos();
        this.setPreferredSize(new Dimension(1280, 720));
        this.selecionado = true;
        this.gerenciarConta = new JButton(new FlatSVGIcon("login/icon/usuario.svg", 0.035f)){
            @Override
            public boolean isDefaultButton(){
                return true;
            }
        };
        this.gerenciarUsuarios = new JButton();
        this.gerenciarGenero = new JButton();
        this.gerenciarJogos = new JButton();
        this.gerenciarNotas = new JButton();

        this.database = new JButton(new FlatSVGIcon("Menu/logo.svg", 0.5F));

        gerenciarGenero.setText("Gerenciar gêneros");
        gerenciarUsuarios.setText("Gerenciar usuarios");
        gerenciarJogos.setText("Gerenciar jogos");
        gerenciarNotas.setText("Gerenciar notas");
        gerenciarConta.setText("Conta");
        this.Buscar = new JTextField();
        this.Sair_Botao = new JButton("Sair");
        this.setLayout(null);  // Define layout como null para posicionamento absoluto
        this.Login_Botao = new JButton("Login"){
            @Override
            public boolean isDefaultButton(){
                return true;
            }
        };
        this.todosButton = new JButton("Todos");
        this.recomendadosButton = new JButton("Recomendados");
        this.toggleThemeButton = new JButton(new FlatSVGIcon("Menu/luaSol.svg", 1f));

        this.atulizarPosObjetos();
        Login_Botao.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
        gerenciarUsuarios.setBackground(new Color(109, 1, 235));
        gerenciarUsuarios.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
        gerenciarGenero.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
        gerenciarJogos.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
        gerenciarNotas.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
        Buscar.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("Menu/icon-buscar.svg", 0.009f));
        this.gerenciarConta.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, null);
        gerenciarConta.putClientProperty(FlatClientProperties.STYLE, "foreground:#FFFFFF");
        gerenciarGenero.setBackground(new Color(109, 1, 235));
        gerenciarJogos.setBackground(new Color(109, 1, 235));
        gerenciarNotas.setBackground(new Color(109, 1, 235));
        gerenciarUsuarios.setFocusable(false);
        gerenciarGenero.setFocusable(false);
        gerenciarJogos.setFocusable(false);
        gerenciarNotas.setFocusable(false);
        todosButton.setFocusable(true);
        Sair_Botao.setFocusable(false);
        Login_Botao.setFocusable(false);
        gerenciarConta.setFocusable(false);
        this.Buscar.setText("Buscar");
        this.Buscar.setFocusable(false);
        this.database.setFocusPainted(false);
        this.database.setBorderPainted(false);
        this.database.setContentAreaFilled(false);

        // Adiciona componentes ao painel
        if (idUsuario > 0) {
          add(this.gerenciarConta);
        } else this.add(this.Login_Botao);
        if (idUsuario > 0 && admin){
            add(this.gerenciarUsuarios);
            add(this.gerenciarGenero);
            add(this.gerenciarJogos);
            add(this.gerenciarNotas);
        }
        this.add(this.Buscar);
        this.add(this.Sair_Botao);
        if(idUsuario > 0){
            this.add(this.recomendadosButton);
            this.add(this.todosButton);
        }
        this.add(this.toggleThemeButton);
        this.add(this.database);

        toggleThemeButton.addActionListener(e -> toggleTheme());


        // Configurações dos botões
        this.Sair_Botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.Login_Botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.todosButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.recomendadosButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.gerenciarConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gerenciarUsuarios.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gerenciarGenero.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gerenciarJogos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gerenciarNotas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        database.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Menu.this.atulizarPosObjetos();
            }
        });

        this.Buscar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Menu.this.Buscar.setFocusable(true);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Menu.this.Buscar.setFocusable(false);
            }
        });

        this.Buscar.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (Menu.this.Buscar.getText().equals("Buscar")) {
                    Menu.this.Buscar.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (Menu.this.Buscar.getText().isEmpty()) {
                    Menu.this.Buscar.setText("Buscar");
                    adicionarBotoesJogos();
                }
            }
        });

        this.database.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Desc desc = new Desc(darkTheme, idUsuario);
                Container container = getParent();
                container.removeAll();
                container.add(desc);
                container.revalidate();
                container.repaint();
            }
        });


        this.Sair_Botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(Menu.this, "Você tem certeza que quer sair da aplicação?", "Sair", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }

            }
        });

        this.Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoFiltro = Buscar.getText().toLowerCase(); // Converte o texto para minúsculas
                List<Jogo> jogosFiltrados;

                if (textoFiltro == null || textoFiltro.isEmpty()) {
                    // Se não houver filtro, exibe todos os jogos
                    jogosFiltrados = controleJogo.getEntidades();
                } else {
                    // Filtra os jogos cujo título contém o texto digitado
                    jogosFiltrados = controleJogo.getEntidades().stream()
                            .filter(jogo -> jogo.getTitulo().toLowerCase().contains(textoFiltro))
                            .collect(Collectors.toList());
                }
                // Atualiza a interface com os jogos filtrados
                adicionarBotoesJogosBuscados(jogosFiltrados);
            }
        });

        gerenciarGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciarGenero gerenciarGenero1 = new GerenciarGenero(darkTheme, idUsuario);
                Container container = Menu.this.getParent();
                container.removeAll();
                container.add(gerenciarGenero1);
                container.revalidate();
                container.repaint();
            }
        });

        gerenciarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciarUsuarios gerenciarUsuarios1 = new GerenciarUsuarios(darkTheme, idUsuario);
                Container container = Menu.this.getParent();
                container.removeAll();
                container.add(gerenciarUsuarios1);
                container.revalidate();
                container.repaint();
            }
        });

        gerenciarJogos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciarJogos gerenciarJogos1 = new GerenciarJogos(darkTheme, idUsuario);
                Container container = Menu.this.getParent();
                container.removeAll();
                container.add(gerenciarJogos1);
                container.revalidate();
                container.repaint();
            }
        });

        gerenciarNotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciarNotas gerenciarNotas1 = new GerenciarNotas(darkTheme, idUsuario);
                Container container = Menu.this.getParent();
                container.removeAll();
                container.add(gerenciarNotas1);
                container.revalidate();
                container.repaint();
            }
        });

        gerenciarConta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GerenciarUsuario conta = new GerenciarUsuario(darkTheme, idUsuario);
                Container container = Menu.this.getParent();
                container.removeAll();
                container.add(conta);
                container.revalidate();
                container.repaint();
            }
        });

        Login_Botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Substitui o painel do menu pelo painel de login
                Login login = new Login(darkTheme, idUsuario);
                Container container = Menu.this.getParent();
                container.removeAll();
                container.add(login);
                container.revalidate();
                container.repaint();
            }
        });

        // Função dos botões de categoria
        Color corTodos = todosButton.getBackground();
        Color corRecomendado = recomendadosButton.getBackground();
        todosButton.setBackground(new Color(149, 149, 149));
        this.todosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selecionado = true;
                recomendadosButton.setBackground(corRecomendado);
                todosButton.setBackground(new Color(149, 149, 149));
                Menu.this.selecionado = true;
                Menu.this.atulizarPosObjetos();
                adicionarBotoesJogos();
            }
        });

        this.recomendadosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                todosButton.setBackground(corTodos);
                recomendadosButton.setBackground(new Color(149, 149, 149));
                adicionarBotoesJogosRecomendados();
            }
        });
    }

    private void atulizarPosObjetos() {
        this.toggleThemeButton.setBounds(this.getWidth() - 40, this.getHeight() - 40, 40, 40);
        this.Sair_Botao.setSize(60, 30);
        this.database.setBounds(this.getWidth() / 2 - 45, 50, 90, 90);
        this.Sair_Botao.setBounds(this.getWidth() - this.Sair_Botao.getWidth(), 0, 60, 30);
        this.gerenciarConta.setBounds(this.Sair_Botao.getX() - 85,0,85,30);
        this.Buscar.setBounds(0, 0, 0, 30);
        this.Login_Botao.setBounds(this.Sair_Botao.getX() - 70, 0, 70, 30);
        this.todosButton.setBounds(this.Buscar.getX(), this.Buscar.getHeight(), 70, 30);
        gerenciarUsuarios.setBounds(recomendadosButton.getWidth()+recomendadosButton.getX(), 0, 140, 30);
        gerenciarGenero.setBounds(gerenciarUsuarios.getWidth()+gerenciarUsuarios.getX(), 0, 140, 30);
        gerenciarJogos.setBounds(gerenciarGenero.getWidth()+gerenciarGenero.getX(), 0, 140, 30);
        gerenciarNotas.setBounds(gerenciarJogos.getWidth()+gerenciarJogos.getX(), 0, 140, 30);
        this.recomendadosButton.setBounds(this.todosButton.getX() + this.todosButton.getWidth(), this.todosButton.getY(), this.todosButton.getWidth() + 60, 30);
        this.Buscar.setSize(this.todosButton.getWidth() + this.recomendadosButton.getWidth(), this.Buscar.getHeight());
    }

    private void adicionarBotoesJogos() {
        // Obtenha os jogos do sistema
        List<Jogo> jogos = controleJogo.getEntidades();  // Assume que há um método em JogoControle para buscar todos os jogos

        // Limpar o painel de jogos antes de adicionar os botões
        this.jogosPanel.removeAll();

        // Inicializar um GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;  // Inicia na primeira coluna
        gbc.gridy = 0;  // Inicia na primeira linha
        gbc.insets = new Insets(2, 2, 2, 2);  // Espaçamento entre os botões

        // Adicionar um botão para cada jogo
        for (Jogo jogo : jogos) {
            JButton jogoButton = new JButton(jogo.getTitulo());
            jogoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Alterar o tamanho do botão
            jogoButton.setPreferredSize(new Dimension(217, 217)); // Largura: 217, Altura: 217

            jogoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ação ao clicar no botão do jogo, exemplo: abrir detalhes do jogo
                    exibirDetalhesJogo(jogo);
                }
            });

            // Adiciona o botão ao painel de jogos com a configuração do GridBagLayout
            this.jogosPanel.add(jogoButton, gbc);

            // Mover para a próxima coluna
            gbc.gridx++;

            // Se a coluna ultrapassar um limite (por exemplo, 5 colunas), vamos para a próxima linha
            if (gbc.gridx > 4) {  // Define que há 5 colunas
                gbc.gridx = 0;   // Volta para a primeira coluna
                gbc.gridy++;     // Muda para a próxima linha
            }
        }

        // Revalidar e repintar o painel após a adição dos botões
        this.jogosPanel.revalidate();
        this.jogosPanel.repaint();
    }

    private void adicionarBotoesJogosBuscados(List<Jogo> jogos) {

        // Limpar o painel de jogos antes de adicionar os botões
        this.jogosPanel.removeAll();

        // Inicializar um GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;  // Inicia na primeira coluna
        gbc.gridy = 0;  // Inicia na primeira linha
        gbc.insets = new Insets(2, 2, 2, 2);  // Espaçamento entre os botões

        // Adicionar um botão para cada jogo
        for (Jogo jogo : jogos) {
            JButton jogoButton = new JButton(jogo.getTitulo());
            jogoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Alterar o tamanho do botão
            jogoButton.setPreferredSize(new Dimension(217, 217)); // Largura: 217, Altura: 217

            jogoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ação ao clicar no botão do jogo, exemplo: abrir detalhes do jogo
                    exibirDetalhesJogo(jogo);
                }
            });

            // Adiciona o botão ao painel de jogos com a configuração do GridBagLayout
            this.jogosPanel.add(jogoButton, gbc);

            // Mover para a próxima coluna
            gbc.gridx++;

            // Se a coluna ultrapassar um limite (por exemplo, 5 colunas), vamos para a próxima linha
            if (gbc.gridx > 4) {  // Define que há 5 colunas
                gbc.gridx = 0;   // Volta para a primeira coluna
                gbc.gridy++;     // Muda para a próxima linha
            }
        }

        // Revalidar e repintar o painel após a adição dos botões
        this.jogosPanel.revalidate();
        this.jogosPanel.repaint();
    }

    private void adicionarBotoesJogosRecomendados() {
        // Obtenha os jogos do sistema
        List<Jogo> jogos = controleJogo.getEntidades();
        List<Jogo> jogosRecomendados = new ArrayList<>();

        // Obter gêneros favoritos do usuário logado (caso esteja logado)
        if (idUsuario > 0) {
            List<String> generosFavoritos = new ArrayList<>(); // Método fictício
            List<Genero> generos = ((Usuario)controleUsuario.buscarPorId(idUsuario)).getGenerosFavoritos();
            for(Genero genero : generos){
                generosFavoritos.add(genero.getGenero());
            }
            if (generosFavoritos != null && !generosFavoritos.isEmpty()) {
                // Filtrar jogos pelos gêneros favoritos
                jogosRecomendados = jogos.stream()
                        .filter(jogo -> jogo.getGeneros().stream()
                                .anyMatch(genero -> generosFavoritos.contains(genero.getGenero())))
                        .collect(Collectors.toList());
            }
        }

        // Limpar o painel de jogos antes de adicionar os botões
        this.jogosPanel.removeAll();

        // Inicializar um GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Inicia na primeira coluna
        gbc.gridy = 0; // Inicia na primeira linha
        gbc.insets = new Insets(2, 2, 2, 2); // Espaçamento entre os botões

        // Determinar a lista de jogos a exibir (se o usuário está logado, exibe jogos recomendados; caso contrário, todos os jogos)
        List<Jogo> jogosParaExibir = jogosRecomendados.isEmpty() ? jogos : jogosRecomendados;

        // Adicionar um botão para cada jogo na lista a exibir
        for (Jogo jogo : jogosParaExibir) {
            JButton jogoButton = new JButton(jogo.getTitulo());
            jogoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Alterar o tamanho do botão
            jogoButton.setPreferredSize(new Dimension(217, 217));

            jogoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Ação ao clicar no botão do jogo, exemplo: abrir detalhes do jogo
                    exibirDetalhesJogo(jogo);
                }
            });

            // Adiciona o botão ao painel de jogos com a configuração do GridBagLayout
            this.jogosPanel.add(jogoButton, gbc);

            // Mover para a próxima coluna
            gbc.gridx++;

            // Se a coluna ultrapassar um limite (por exemplo, 5 colunas), vamos para a próxima linha
            if (gbc.gridx > 4) { // Define que há 5 colunas
                gbc.gridx = 0;   // Volta para a primeira coluna
                gbc.gridy++;     // Muda para a próxima linha
            }
        }

        // Revalidar e repintar o painel após a adição dos botões
        this.jogosPanel.revalidate();
        this.jogosPanel.repaint();
    }

    public void exibirDetalhesJogo(Jogo jogo) {
        // Abre o painel para adicionar uma nova nota
        DetalhesJogoPanel detalhesJogoPanel = new DetalhesJogoPanel(jogo, idUsuario, darkTheme);
        Container container = getParent();
        container.removeAll();
        container.add(detalhesJogoPanel);
        container.revalidate();
        container.repaint();
    }


}
