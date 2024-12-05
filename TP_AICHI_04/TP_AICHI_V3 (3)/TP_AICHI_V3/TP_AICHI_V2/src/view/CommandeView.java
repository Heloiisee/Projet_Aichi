package view;

import model.Commande;
import viewsAccueil.FAccueilController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class CommandeView extends JFrame {

    private static CommandeView instance;

    private JPanel contentPane;
    private JTextField txtIdClient, txtDateCommande, txtStatut;
    private JButton btnValider, btnEffacer, btnAfficher, btnRetour, btnSupprimer, btnModifier, btnDetails;
    private JTable tableCommande;
    private DefaultTableModel tableModel;

    private CommandeView() {  // Private constructor for Singleton
        setTitle("Gestion des Commandes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initUI();
    }

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

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(new TitledBorder("Détails de la Commande"));

        txtIdClient = createLabeledField(formPanel, "ID Client");
        txtDateCommande = createLabeledField(formPanel, "Date Commande");
        txtStatut = createLabeledField(formPanel, "Statut");

        contentPane.add(formPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Numéro de Commande", "ID Client", "Date", "Statut"}, 0);
        tableCommande = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(tableCommande);
        tableScroll.setBorder(new TitledBorder("Liste des Commandes"));
        contentPane.add(tableScroll, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnValider = createButton(buttonPanel, "Valider");
        btnEffacer = createButton(buttonPanel, "Effacer");
        btnAfficher = createButton(buttonPanel, "Afficher");
        btnSupprimer = createButton(buttonPanel, "Supprimer");
        btnModifier = createButton(buttonPanel, "Modifier");
        btnRetour = createButton(buttonPanel, "Retour");
        btnDetails = createButton(buttonPanel, "Détails");

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

    public void setDetailsActionListener(ActionListener listener) {
        btnDetails.addActionListener(listener);
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public String getTxtIdClient() {
        return txtIdClient.getText();
    }

    public String getTxtDateCommande() {
        return txtDateCommande.getText();
    }

    public String getTxtStatut() {
        return txtStatut.getText();
    }

    public void afficherCommandesDansTable(List<Commande> commandes) {
        DefaultTableModel model = (DefaultTableModel) tableCommande.getModel();
        model.setRowCount(0);
        for (Commande commande : commandes) {
            model.addRow(new Object[]{
                    commande.getNCommande(),
                    commande.getIdClient(),
                    commande.getDate_Commande(),
                    commande.getStatut()
            });
        }
    }

    public void clearFields() {
        txtIdClient.setText("");
        txtDateCommande.setText("");
        txtStatut.setText("");
    }

    public JTable getTableCommande() {
        return tableCommande;
    }

    public void addRow(Vector<String> row) {
        tableModel.addRow(row);
    }

    public String getTxtNCommande() {
        int selectedRow = tableCommande.getSelectedRow();
        if (selectedRow == -1) {
            return "";
        }
        return (String) tableModel.getValueAt(selectedRow, 0);
    }
}
