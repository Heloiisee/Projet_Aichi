package view;

import model.Client;
import viewsAccueil.FAccueilController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientView extends JFrame {

    private static ClientView instance; // Instance unique de ClientView
    private JTextField txtRechercheClient;
    private JButton btnRechercherClient;


    private JPanel contentPane;
    private JTextField txtNomClient, txtPrenomClient, txtNumeroClient, txtMailClient;
    private JButton btnAjouter, btnModifier, btnSupprimer, btnEffacer, btnAfficher, btnRetour;
    private JTable tableClient;
    private DefaultTableModel tableModel;

    // Constructeur privé pour empêcher l'instanciation directe
    public ClientView() {
        setTitle("Gestion des Clients");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        initUI();
    }


    // Méthode pour obtenir l'instance unique de ClientView
    public static ClientView getInstance() {
        if (instance == null) {
            instance = new ClientView();
        }
        return instance;
    }

    private void initUI() {
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        // Ajout du champ de recherche et du bouton
        JPanel recherchePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        txtRechercheClient = new JTextField(20);
        btnRechercherClient = new JButton("Rechercher");
        recherchePanel.add(new JLabel("Rechercher un client:"));
        recherchePanel.add(txtRechercheClient);
        recherchePanel.add(btnRechercherClient);
        contentPane.add(recherchePanel, BorderLayout.NORTH);

        // Formulaire (placé à gauche)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new TitledBorder("Détails du Client"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        txtNomClient = createLabeledField(formPanel, "Nom", gbc);
        gbc.gridy++;
        txtPrenomClient = createLabeledField(formPanel, "Prénom", gbc);
        gbc.gridy++;
        txtNumeroClient = createLabeledField(formPanel, "Numéro", gbc);
        gbc.gridy++;
        txtMailClient = createLabeledField(formPanel, "Mail", gbc);

        contentPane.add(formPanel, BorderLayout.WEST);

        // Table (au centre)
        tableModel = new DefaultTableModel(new String[]{"Nom", "Prénom", "Numéro", "Mail"}, 0);
        tableClient = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(tableClient);
        tableScroll.setBorder(new TitledBorder("Liste des Clients"));
        contentPane.add(tableScroll, BorderLayout.CENTER);

        // Boutons (en bas)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAjouter = createButton(buttonPanel, "Ajouter");
        btnModifier = createButton(buttonPanel, "Modifier");
        btnSupprimer = createButton(buttonPanel, "Supprimer");
        btnEffacer = createButton(buttonPanel, "Effacer");
        btnAfficher = createButton(buttonPanel, "Afficher");
        btnRetour = createButton(buttonPanel, "Retour");
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }



    private JTextField createLabeledField(JPanel panel, String label, GridBagConstraints gbc) {
        JLabel lbl = new JLabel(label + ":");
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        panel.add(lbl, gbc);

        JTextField txt = new JTextField(20);
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        panel.add(txt, gbc);

        return txt;
    }


    private JButton createButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(button);
        return button;
    }

    // Méthodes pour gérer les actions des boutons
    public void setAjouterActionListener(ActionListener listener) {
        btnAjouter.addActionListener(listener);
    }

    public void setModifierActionListener(ActionListener listener) {
        btnModifier.addActionListener(listener);
    }

    public void setSupprimerActionListener(ActionListener listener) {
        btnSupprimer.addActionListener(listener);
    }

    public void setEffacerActionListener(ActionListener listener) {
        btnEffacer.addActionListener(listener);
    }

    public void setAfficherActionListener(ActionListener listener) {
        btnAfficher.addActionListener(listener);
    }

    public void setRetourActionListener(ActionListener listener) {
        btnRetour.addActionListener(listener);
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    // Getters pour récupérer les valeurs saisies
    public String getTxtNomClient() {
        return txtNomClient.getText();
    }

    public String getTxtPrenomClient() {
        return txtPrenomClient.getText();
    }

    public String getTxtNumeroClient() {
        return txtNumeroClient.getText();
    }

    public String getTxtMailClient() {
        return txtMailClient.getText();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void afficherClientsDansTable(List<Client> clients) {
        DefaultTableModel model = (DefaultTableModel) tableClient.getModel();
        model.setRowCount(0); // Effacer le tableau
        for (Client client : clients) {
            model.addRow(new Object[]{
                    client.getNom(),
                    client.getPrenom(),
                    client.getNumero(),
                    client.getMail()
            });
        }
    }

    public void clearFields() {
        txtNomClient.setText("");
        txtPrenomClient.setText("");
        txtNumeroClient.setText("");
        txtMailClient.setText("");
    }

    public JTable getTableClient() {
        return tableClient;
    }

    public String getTxtRechercheClient() {
        return txtRechercheClient.getText();
    }

    public void setRechercherActionListener(ActionListener listener) {
        btnRechercherClient.addActionListener(listener);
    }



}
