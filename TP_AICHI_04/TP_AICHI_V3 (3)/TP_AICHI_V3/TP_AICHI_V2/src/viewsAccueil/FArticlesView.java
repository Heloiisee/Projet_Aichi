package viewsAccueil;

import controller.FArticlesController;
import model.Article;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class FArticlesView extends JDialog {

    private static final long serialVersionUID = 1L;
    private static FArticlesView instance;
    private JPanel contentPane;
    private JTable table;
    private JTextField txtLibelle;
    private JTextField txtDescription;
    private JFormattedTextField txtPrix;
    private JSlider sliderStock;

    private ActionListener ajouterListener;
    private ActionListener modifierListener;
    private ActionListener supprimerListener;
    private ActionListener effacerListener;

    private final Action actionAccueil = new ActionAccueil();
    private JTextField txtRecherche;
    private JToolBar toolBar;
    private ActionListener afficherListener;

    public FArticlesView() {
        setTitle("Gestion des Articles");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        setLocationRelativeTo(null);
        createContents();
    }

    public static FArticlesView getInstance() { if (instance == null) { instance = new FArticlesView(); } return instance;
    }

    private void createContents() {
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0x99, 0xCC, 0x00));
        contentPane.setBorder(null);
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel_menu = new JPanel();
        panel_menu.setBackground(new Color(0x66, 0x99, 0x00));
        contentPane.add(panel_menu, BorderLayout.WEST);
        panel_menu.setBorder(new CompoundBorder(null, new EmptyBorder(5, 5, 5, 5)));
        GridBagLayout gbl_panel_menu = new GridBagLayout();
        panel_menu.setLayout(gbl_panel_menu);

        JLabel lblTitre = new JLabel("Gestion des Articles");
        lblTitre.setFont(new Font("Tahoma", Font.BOLD, 25));
        GridBagConstraints gbc_lblTitre = new GridBagConstraints();
        gbc_lblTitre.gridx = 0;
        gbc_lblTitre.gridy = 0;
        gbc_lblTitre.anchor = GridBagConstraints.CENTER;
        gbc_lblTitre.insets = new Insets(10, 0, 20, 0); // Ajout de marges
        panel_menu.add(lblTitre, gbc_lblTitre);

        JButton btnAccueil = new JButton("Accueil");
        btnAccueil.setAction(actionAccueil);
        btnAccueil.setForeground(Color.WHITE);
        GridBagConstraints gbc_btnAccueil = new GridBagConstraints();
        gbc_btnAccueil.gridx = 0;
        gbc_btnAccueil.gridy = 1;
        gbc_btnAccueil.anchor = GridBagConstraints.CENTER;
        gbc_btnAccueil.insets = new Insets(20, 0, 10, 0); // Ajout de marges
        panel_menu.add(btnAccueil, gbc_btnAccueil);

        JPanel panel_principal = new JPanel();
        panel_principal.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel_principal.setBackground(new Color(0xE2, 0xF0, 0xB6));
        contentPane.add(panel_principal, BorderLayout.CENTER);
        panel_principal.setLayout(new GridBagLayout());

        JPanel panel_formulaire = new JPanel();
        panel_formulaire.setOpaque(false);
        panel_formulaire.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2, true), new EmptyBorder(5, 5, 5, 5)));
        GridBagConstraints gbc_panel_formulaire = new GridBagConstraints();
        gbc_panel_formulaire.gridx = 0;
        gbc_panel_formulaire.gridy = 0;
        gbc_panel_formulaire.gridwidth = GridBagConstraints.REMAINDER;
        gbc_panel_formulaire.fill = GridBagConstraints.HORIZONTAL;
        gbc_panel_formulaire.insets = new Insets(5, 5, 5, 5);
        panel_principal.add(panel_formulaire, gbc_panel_formulaire);

        GridBagLayout gbl_panel_formulaire = new GridBagLayout();
        panel_formulaire.setLayout(gbl_panel_formulaire);

        JLabel lblLibelle = new JLabel("LibellÃ©");
        GridBagConstraints gbc_lblLibelle = new GridBagConstraints();
        gbc_lblLibelle.anchor = GridBagConstraints.WEST;
        gbc_lblLibelle.insets = new Insets(5, 5, 5, 5);
        panel_formulaire.add(lblLibelle, gbc_lblLibelle);

        txtLibelle = new JTextField();
        txtLibelle.setPreferredSize(new Dimension(200, 25)); // Largeur de 200 pixels, hauteur de 25 pixels
        GridBagConstraints gbc_txtLibelle = new GridBagConstraints();
        gbc_txtLibelle.gridwidth = GridBagConstraints.REMAINDER;
        gbc_txtLibelle.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtLibelle.insets = new Insets(5, 5, 5, 5);
        panel_formulaire.add(txtLibelle, gbc_txtLibelle);

        JLabel lblDescription = new JLabel("Description");
        GridBagConstraints gbc_lblDescription = new GridBagConstraints();
        gbc_lblDescription.anchor = GridBagConstraints.WEST;
        gbc_lblDescription.insets = new Insets(5, 5, 5, 5);
        panel_formulaire.add(lblDescription, gbc_lblDescription);

        txtDescription = new JTextField();
        txtDescription.setPreferredSize(new Dimension(200, 25)); // Largeur de 200 pixels, hauteur de 25 pixels
        GridBagConstraints gbc_txtDescription = new GridBagConstraints();
        gbc_txtDescription.gridwidth = GridBagConstraints.REMAINDER;
        gbc_txtDescription.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtDescription.insets = new Insets(5, 5, 5, 5);
        panel_formulaire.add(txtDescription, gbc_txtDescription);

        JLabel lblPrix = new JLabel("Prix");
        GridBagConstraints gbc_lblPrix = new GridBagConstraints();
        gbc_lblPrix.anchor = GridBagConstraints.WEST;
        gbc_lblPrix.insets = new Insets(5, 5, 5, 5);
        panel_formulaire.add(lblPrix, gbc_lblPrix);

        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        txtPrix = new JFormattedTextField(format);
        txtPrix.setText("0,00");
        txtPrix.setHorizontalAlignment(SwingConstants.RIGHT);
        txtPrix.setPreferredSize(new Dimension(100, 25)); // Largeur de 100 pixels, hauteur de 25 pixels
        GridBagConstraints gbc_txtPrix = new GridBagConstraints();
        gbc_txtPrix.gridwidth = GridBagConstraints.REMAINDER;
        gbc_txtPrix.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtPrix.insets = new Insets(5, 5, 5, 5);
        panel_formulaire.add(txtPrix, gbc_txtPrix);

        JLabel lblStock = new JLabel("Stock");
        GridBagConstraints gbc_lblStock = new GridBagConstraints();
        gbc_lblStock.anchor = GridBagConstraints.WEST;
        gbc_lblStock.insets = new Insets(5, 5, 5, 5);
        panel_formulaire.add(lblStock, gbc_lblStock);

        sliderStock = new JSlider(0, 100, 10);
        GridBagConstraints gbc_sliderStock = new GridBagConstraints();
        gbc_sliderStock.gridwidth = GridBagConstraints.REMAINDER;
        gbc_sliderStock.fill = GridBagConstraints.HORIZONTAL;
        gbc_sliderStock.insets = new Insets(5, 5, 5, 5);
        panel_formulaire.add(sliderStock, gbc_sliderStock);

        toolBar = new JToolBar();
        toolBar.setOpaque(false);
        GridBagConstraints gbc_toolBar = new GridBagConstraints();
        gbc_toolBar.gridwidth = GridBagConstraints.REMAINDER;
        gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
        gbc_toolBar.insets = new Insets(5, 5, 5, 5);
        panel_formulaire.add(toolBar, gbc_toolBar);

        JButton btnAjouter = new JButton("Ajouter");
        toolBar.add(btnAjouter);
        btnAjouter.setHorizontalAlignment(SwingConstants.LEFT);
        btnAjouter.setForeground(Color.BLACK);

        JButton btnModifier = new JButton("Modifier");
        toolBar.add(btnModifier);
        btnModifier.setHorizontalAlignment(SwingConstants.LEFT);
        btnModifier.setForeground(Color.BLACK);

        JButton btnSupprimer = new JButton("Supprimer");
        toolBar.add(btnSupprimer);
        btnSupprimer.setHorizontalAlignment(SwingConstants.LEFT);
        btnSupprimer.setForeground(Color.BLACK);

        JButton btnEffacer = new JButton("Effacer");
        toolBar.add(btnEffacer);
        btnEffacer.setHorizontalAlignment(SwingConstants.LEFT);
        btnEffacer.setForeground(Color.BLACK);

        JButton btnAfficher = new JButton("Afficher");
        toolBar.add(btnAfficher);
        btnAfficher.setHorizontalAlignment(SwingConstants.LEFT);
        btnAfficher.setForeground(Color.BLACK);

        JButton btnRechercher = new JButton("Rechercher");
        toolBar.add(btnRechercher);
        btnRechercher.setHorizontalAlignment(SwingConstants.LEFT);
        btnRechercher.setForeground(Color.BLACK);

        // Ajouter les listeners aux boutons
        btnAjouter.addActionListener(e -> {
            if (ajouterListener != null) {
                ajouterListener.actionPerformed(e);
            }
        });

        btnModifier.addActionListener(e -> {
            if (modifierListener != null) {
                modifierListener.actionPerformed(e);
            }
        });

        btnSupprimer.addActionListener(e -> {
            if (supprimerListener != null) {
                supprimerListener.actionPerformed(e);
            }
        });

        btnEffacer.addActionListener(e -> {
            if (effacerListener != null) {
                effacerListener.actionPerformed(e);
            }
        });

        btnAfficher.addActionListener(e -> {
            if (afficherListener != null) {
                afficherListener.actionPerformed(e);
            }
        });

        btnRechercher.addActionListener(e -> {
            String texteRecherche = txtRecherche.getText().trim();
            if (texteRecherche.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez saisir un critère de recherche.");
            } else {
                List<Article> articlesTrouves = model.UserDAO.rechercherArticlesParNom(texteRecherche);
                afficherArticlesDansTable(articlesTrouves);
            }
        });


        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;
        gbc_scrollPane.gridwidth = GridBagConstraints.REMAINDER;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.weightx = 1.0;
        gbc_scrollPane.weighty = 1.0;
        gbc_scrollPane.insets = new Insets(5, 5, 5, 5);
        panel_principal.add(scrollPane, gbc_scrollPane);

        JLabel lblRecherche = new JLabel("Recherche");
        GridBagConstraints gbc_lblRecherche = new GridBagConstraints();
        gbc_lblRecherche.gridx = 0;
        gbc_lblRecherche.gridy = 2;
        gbc_lblRecherche.anchor = GridBagConstraints.WEST;
        gbc_lblRecherche.insets = new Insets(5, 5, 5, 5);
        panel_principal.add(lblRecherche, gbc_lblRecherche);

        txtRecherche = new JTextField();
        txtRecherche.setPreferredSize(new Dimension(200, 25)); // Largeur de 200 pixels, hauteur de 25 pixels
        GridBagConstraints gbc_txtRecherche = new GridBagConstraints();
        gbc_txtRecherche.gridx = 1;
        gbc_txtRecherche.gridy = 2;
        gbc_txtRecherche.gridwidth = GridBagConstraints.REMAINDER;
        gbc_txtRecherche.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtRecherche.insets = new Insets(5, 5, 5, 5);
        panel_principal.add(txtRecherche, gbc_txtRecherche);

        bouton_mode_ajout_ou_edition(true);
        SwingUtilities.invokeLater(() -> btnAccueil.requestFocusInWindow());
    }

    public void addAjouterListener(ActionListener listener) {
        this.ajouterListener = listener;
    }

    public void addModifierListener(ActionListener listener) {
        this.modifierListener = listener;
    }

    public void addSupprimerListener(ActionListener listener) {
        this.supprimerListener = listener;
    }

    public void addEffacerListener(ActionListener listener) {
        this.effacerListener = listener;
    }

    public JTextField getTxtLibelle() {
        return txtLibelle;
    }

    public JTextField getTxtDescription() {
        return txtDescription;
    }

    public JFormattedTextField getTxtPrix() {
        return txtPrix;
    }

    public JSlider getSliderStock() {
        return sliderStock;
    }

    public void addAfficherListener(ActionListener listener) {
        this.afficherListener = listener;

    }

    public void updateArticle(Article article) {
        txtLibelle.setText(article.getLibelle());
        txtDescription.setText(article.getDescription());
        txtPrix.setValue(article.getPrix());
        sliderStock.setValue(article.getStock());
    }

    public void afficherArticlesDansTable(List<Article> articles) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Libellé");
        model.addColumn("Description");
        model.addColumn("Prix");
        model.addColumn("Stock");

        for (Article article : articles) {
            model.addRow(new Object[]{article.getId(), article.getLibelle(), article.getDescription(), article.getPrix(), article.getStock()});
        }

        table.setModel(model);
    }

    public void updateArticleList(List<Article> articles) {
        DefaultListModel<Article> model = new DefaultListModel<>();
        for (Article article : articles) {
            model.addElement(article);
        }
    }

    public JTable getTableArticles() {
        return table;
    }

    public void supprimerArticleDeTable(int selectedRow) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(selectedRow);
    }

    private class ActionAccueil extends AbstractAction {
        private static final long serialVersionUID = 1L;

        public ActionAccueil() {
            putValue(NAME, "Accueil");
            putValue(SHORT_DESCRIPTION, "Retourner sur l'Ã©cran d'accueil");
        }

        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    public void effacerSaisie() {
        txtLibelle.setText("");
        txtDescription.setText("");
        txtPrix.setValue(0.00);
        sliderStock.setValue(10);
        txtLibelle.requestFocus();
    }

    private void bouton_mode_ajout_ou_edition(boolean ajout) {
        // RÃ©cupÃ©rer les boutons de la toolbar
        Component[] components = toolBar.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals("Ajouter")) {
                    button.setEnabled(ajout);
                } else if (button.getText().equals("Modifier")) {
                    button.setEnabled(ajout);
                } else if (button.getText().equals("Supprimer")) {
                    button.setEnabled(ajout);
                } else if (button.getText().equals("Effacer")) {
                    button.setEnabled(true);
                }
                else if (button.getText().equals("Afficher")) {
                    button.setEnabled(true);
                }
            }
        }
    }


}
