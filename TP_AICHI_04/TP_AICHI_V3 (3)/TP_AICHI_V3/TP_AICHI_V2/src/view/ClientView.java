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
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Haut : Formulaire
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(new TitledBorder("Détails du Client"));

        txtNomClient = createLabeledField(formPanel, "Nom");
        txtPrenomClient = createLabeledField(formPanel, "Prénom");
        txtNumeroClient = createLabeledField(formPanel, "Numéro");
        txtMailClient = createLabeledField(formPanel, "Mail");

        contentPane.add(formPanel, BorderLayout.NORTH);

        // Centre : Table
        tableModel = new DefaultTableModel(new String[]{"Nom", "Prénom", "Numéro", "Mail"}, 0);
        tableClient = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(tableClient);
        tableScroll.setBorder(new TitledBorder("Liste des Clients"));
        contentPane.add(tableScroll, BorderLayout.CENTER);

        // Bas : Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAjouter = createButton(buttonPanel, "Ajouter");
        btnModifier = createButton(buttonPanel, "Modifier");
        btnSupprimer = createButton(buttonPanel, "Supprimer");
        btnEffacer = createButton(buttonPanel, "Effacer");
        btnAfficher = createButton(buttonPanel, "Afficher");
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
}
