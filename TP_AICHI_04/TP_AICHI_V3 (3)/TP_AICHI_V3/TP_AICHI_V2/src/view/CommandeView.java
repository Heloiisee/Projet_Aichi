package view;

import model.Commande;
import viewsAccueil.FAccueilController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CommandeView extends JFrame {

    private static CommandeView instance; // Instance unique de CommandeView

    private JPanel contentPane;
    private JTextField txtIdClient, txtIdArticle, txtDateCommande, txtNomCommande, txtStatut, txtPrixTotal;
    private JButton btnValider, btnEffacer, btnAfficher, btnRetour, btnSupprimer, btnModifier;
    private JTable tableCommande;
    private DefaultTableModel tableModel;

    // Constructeur privé pour empêcher l'instanciation directe
    public CommandeView() {
        setTitle("Gestion des Commandes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initUI();
    }
    public CommandeView(FAccueilController fAccueilController) {
        setTitle("Gestion des Commandes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initUI();
    }

    // Méthode pour obtenir l'instance unique de CommandeView
    public static CommandeView getInstance() {
        if (instance == null) {
            instance = new CommandeView();
        }
        return instance;
    }

    private void initUI() {
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Haut : Formulaire
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(new TitledBorder("Détails de la Commande"));

        txtIdClient = createLabeledField(formPanel, "ID Client");
        txtIdArticle = createLabeledField(formPanel, "ID Article");
        txtDateCommande = createLabeledField(formPanel, "Date Commande");
        txtNomCommande = createLabeledField(formPanel, "Nom Commande");
        txtStatut = createLabeledField(formPanel, "Statut");
        txtPrixTotal = createLabeledField(formPanel, "Prix Total");

        contentPane.add(formPanel, BorderLayout.NORTH);

        // Centre : Table
        tableModel = new DefaultTableModel(new String[]{"ID", "ID Client", "ID Article", "Date", "Nom", "Statut", "Prix Total"}, 0);
        tableCommande = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(tableCommande);
        tableScroll.setBorder(new TitledBorder("Liste des Commandes"));
        contentPane.add(tableScroll, BorderLayout.CENTER);

        // Bas : Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnValider = createButton(buttonPanel, "Valider");
        btnEffacer = createButton(buttonPanel, "Effacer");
        btnAfficher = createButton(buttonPanel, "Afficher");
        btnSupprimer = createButton(buttonPanel, "Supprimer");
        btnModifier = createButton(buttonPanel, "Modifier");
        btnRetour = createButton(buttonPanel, "Retour");

        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextField createLabeledField(JPanel panel, String label) {
        JLabel lbl = new JLabel(label + ":");
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField txt = new JTextField();
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lbl);
        panel.add(txt);
        return txt;
    }

    private JButton createButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(button);
        return button;
    }

    // Méthodes pour gérer les actions des boutons
    public void setValiderActionListener(ActionListener listener) {
        btnValider.addActionListener(listener);
    }

    public void setModifierActionListener(ActionListener listener) {
        btnModifier.addActionListener(listener);
    }

    public void setEffacerActionListener(ActionListener listener) {
        btnEffacer.addActionListener(listener);
    }

    public void setAfficherActionListener(ActionListener listener) {
        btnAfficher.addActionListener(listener);
    }

    public void setSupprimerActionListener(ActionListener listener) {
        btnSupprimer.addActionListener(listener);
    }

    public void setRetourActionListener(ActionListener listener) {
        btnRetour.addActionListener(listener);
    }

    public void addCommandeToTable(Commande commande) {
        tableModel.addRow(new Object[]{
                commande.getId(),
                commande.getIdClient(),
                commande.getIdArticle(),
                commande.getDateCommande(),
                commande.getNomCommande(),
                commande.getStatut(),
                commande.getPrixTotal()
        });
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    // Getters pour récupérer les valeurs saisies
    public String getTxtIdClient() {
        return txtIdClient.getText();
    }

    public String getTxtIdArticle() {
        return txtIdArticle.getText();
    }

    public String getTxtDateCommande() {
        return txtDateCommande.getText();
    }

    public String getTxtNomCommande() {
        return txtNomCommande.getText();
    }

    public String getTxtStatut() {
        return txtStatut.getText();
    }

    public String getTxtPrixTotal() {
        return txtPrixTotal.getText();
    }

    public void showMessage(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    public void afficherCommandesDansTable(List<Commande> commandes) {
        DefaultTableModel model = (DefaultTableModel) tableCommande.getModel();
        model.setRowCount(0); // Effacer le tableau
        for (Commande commande : commandes) {
            model.addRow(new Object[]{
                    commande.getId(),
                    commande.getIdClient(),
                    commande.getIdArticle(),
                    commande.getDateCommande(),
                    commande.getNomCommande(),
                    commande.getStatut(),
                    commande.getPrixTotal()
            });
        }
    }

    public void clearFields() {
        txtIdClient.setText("");
        txtIdArticle.setText("");
        txtDateCommande.setText("");
        txtNomCommande.setText("");
        txtStatut.setText("");
        txtPrixTotal.setText("");
    }

    public JTable getTableCommande() {
        return tableCommande;
    }
}
