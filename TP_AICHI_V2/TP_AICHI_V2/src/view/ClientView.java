package view;

import model.Client;
import viewsAccueil.FAccueilController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientView extends JFrame {
    private JTextField nomClient;
    private JTextField prenomClient;
    private JTextField numeroClient;
    private JTextField mailClient;
    private JButton actionAjouter;
    private JButton actionModifier;
    private JButton actionSupprimer;
    private JButton actionEffacer;
    private JButton actionQuitter;
    private JList<Client> clientList;
    private DefaultListModel<Client> listModel;

    public ClientView(FAccueilController fAccueilController) {
        setTitle("Gestion des Clients");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nomLabel = new JLabel("Nom:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nomLabel, gbc);

        nomClient = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nomClient, gbc);

        JLabel prenomLabel = new JLabel("Prénom:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(prenomLabel, gbc);

        prenomClient = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(prenomClient, gbc);

        JLabel numeroLabel = new JLabel("Numéro:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(numeroLabel, gbc);

        numeroClient = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(numeroClient, gbc);

        JLabel mailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(mailLabel, gbc);

        mailClient = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(mailClient, gbc);

        actionAjouter = new JButton("Ajouter");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(actionAjouter, gbc);

        actionModifier = new JButton("Modifier");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(actionModifier, gbc);

        actionSupprimer = new JButton("Supprimer");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(actionSupprimer, gbc);

        actionEffacer = new JButton("Effacer");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(actionEffacer, gbc);

        listModel = new DefaultListModel<>();
        clientList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(clientList);
        scrollPane.setPreferredSize(new Dimension(300, 100));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);
        actionQuitter = new JButton("Quitter");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(actionQuitter, gbc);


        add(panel);
    }

    public JTextField getNomClient() {
        return nomClient;
    }

    public JTextField getPrenomClient() {
        return prenomClient;
    }

    public JTextField getNumeroClient() {
        return numeroClient;
    }

    public JTextField getMailClient() {
        return mailClient;
    }

    public JButton getActionAjouter() {
        return actionAjouter;
    }

    public JButton getActionModifier() {
        return actionModifier;
    }

    public JButton getActionSupprimer() {
        return actionSupprimer;
    }

    public JButton getActionEffacer() {
        return actionEffacer;
    }

    public JButton getActionQuitter() {
        return actionQuitter;
    }

    public JList<Client> getClientList() {
        return clientList;
    }

    public void updateClientList(List<Client> clients) {
        listModel.clear();
        for (Client client : clients) {
            listModel.addElement(client);
        }
    }
}
