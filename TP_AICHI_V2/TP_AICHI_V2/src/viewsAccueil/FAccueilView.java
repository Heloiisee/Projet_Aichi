package viewsAccueil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class FAccueilView extends JFrame {
    private static final long serialVersionUID = 1L;

    // Menu items and buttons
    private JMenuItem mntmQuitter, mntmClients, mntmArticles, mntmCommandes, mntmParametres;
    private JButton btnQuitter, btnClients, btnArticles, btnStats, btnCommandes, btnParametres;
    private JLabel lblInfos;

    public FAccueilView() {
        // Configuration de la fenêtre principale
        setTitle("Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 926, 686);
        setLocationRelativeTo(null);

        // Barre de menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Menu "Fichier"
        JMenu mnFichier = new JMenu("Fichier");
        menuBar.add(mnFichier);

        mntmParametres = new JMenuItem("Paramètres");
        mnFichier.add(mntmParametres);

        JSeparator separator = new JSeparator();
        mnFichier.add(separator);

        mntmQuitter = new JMenuItem("Quitter");
        mnFichier.add(mntmQuitter);

        // Menu "Vues"
        JMenu mnVues = new JMenu("Vues");
        menuBar.add(mnVues);

        mntmClients = new JMenuItem("Clients");
        mnVues.add(mntmClients);

        mntmArticles = new JMenuItem("Articles");
        mnVues.add(mntmArticles);

        mntmCommandes = new JMenuItem("Commandes");
        mnVues.add(mntmCommandes);

        // Panneau gauche (menu)
        JPanel panel_menu = new JPanel();
        panel_menu.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel_menu.setBackground(SystemColor.controlShadow);
        add(panel_menu, BorderLayout.WEST);

        JLabel lblTitre = new JLabel("Cas AICHI");
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setFont(lblTitre.getFont().deriveFont(33f));

        lblInfos = new JLabel("Affichage option");
        lblInfos.setForeground(SystemColor.control);
        lblInfos.setBackground(SystemColor.controlShadow);
        lblInfos.setOpaque(true);
        lblInfos.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfos.setFont(lblInfos.getFont().deriveFont(21f));

        btnQuitter = new JButton("Quitter");

        GroupLayout gl_panel_menu = new GroupLayout(panel_menu);
        gl_panel_menu.setHorizontalGroup(
                gl_panel_menu.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblTitre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblInfos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuitter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        gl_panel_menu.setVerticalGroup(
                gl_panel_menu.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(gl_panel_menu.createSequentialGroup()
                                .addGap(10)
                                .addComponent(lblTitre)
                                .addGap(30)
                                .addComponent(lblInfos)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
                                .addComponent(btnQuitter)
                                .addGap(10))
        );
        panel_menu.setLayout(gl_panel_menu);

        // Panneau central (boutons principaux)
        JPanel panel_principal = new JPanel();
        panel_principal.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(panel_principal, BorderLayout.CENTER);
        panel_principal.setLayout(new GridLayout(3, 2, 10, 10)); // 3 lignes, 2 colonnes, espaces de 10px

        btnArticles = new JButton("Articles");
        btnClients = new JButton("Clients");
        btnStats = new JButton("Statistiques");
        btnCommandes = new JButton("Commandes");
        btnParametres = new JButton("Paramètres");

        // Ajouter les boutons au panneau principal
        panel_principal.add(btnArticles);
        panel_principal.add(btnClients);
        panel_principal.add(btnStats);
        panel_principal.add(btnCommandes);
        panel_principal.add(btnParametres);
    }

    // Ajout d'écouteurs aux boutons et aux éléments de menu
    public void addQuitterListener(ActionListener listener) {
        mntmQuitter.addActionListener(listener);
        btnQuitter.addActionListener(listener);
    }

    public void addArticlesListener(ActionListener listener) {
        mntmArticles.addActionListener(listener);
        btnArticles.addActionListener(listener);
    }

    public void addClientsListener(ActionListener listener) {
        mntmClients.addActionListener(listener);
        btnClients.addActionListener(listener);
    }

    public void addCommandesListener(ActionListener listener) {
        mntmCommandes.addActionListener(listener);
        btnCommandes.addActionListener(listener);
    }

    // Mise à jour du texte d'information
    public void setInfoText(String text) {
        lblInfos.setText(text);
    }
}
