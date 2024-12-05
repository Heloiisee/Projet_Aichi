package controller;

import model.Client;
import model.UserDAO;
import view.ClientView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Clientcontroller {

    private final ClientView view;
    private List<Client> clients;
    private UserDAO userDAO;
    private boolean areListenersAdded = false; // Variable pour vérifier si les listeners sont ajoutés
    private boolean messageShowing = false;

    public Clientcontroller(ClientView view) {
        this.view = view;
        this.userDAO = new UserDAO();
        this.clients = new ArrayList<>(); // Initialisation de la liste
        initController();
    }

    public void initController() {
        if (!areListenersAdded) {
            view.setAjouterActionListener(e -> ajouterClient());
            view.setModifierActionListener(e -> modifierClient());
            view.setSupprimerActionListener(e -> supprimerClient());
            view.setEffacerActionListener(e -> effacerSaisie());
            view.setAfficherActionListener(e -> afficherClients());
            view.setRetourActionListener(e -> retourAccueil());
            areListenersAdded = true; // Marquer les listeners comme ajoutés
        }
    }

    private void ajouterClient() {
        try {
            Client client = extraireClientDeVue();
            if (client == null) {
                showMessage("Veuillez remplir tous les champs.");
                return;
            }

            userDAO.ajouterClient(client);
            clients.add(client);
            view.afficherClientsDansTable(clients);

            showMessage("Client ajouté avec succès !");
            effacerSaisie();
        } catch (Exception e) {
            handleException(e, "Erreur lors de l'ajout du client : ");
        }
    }

    private void effacerSaisie() {
        view.clearFields();
    }

    private void afficherClients() {
        try {
            clients = userDAO.getClientList();
            view.afficherClientsDansTable(clients);
        } catch (SQLException e) {
            handleException(e, "Erreur lors de l'affichage des clients : ");
        }
    }

    private void retourAccueil() {
        view.setVisible(false);
    }

    private void supprimerClient() {
        try {
            int selectedRow = view.getTableClient().getSelectedRow();
            if (selectedRow == -1) {
                showMessage("Veuillez sélectionner un client à supprimer.");
                return;
            }
            Client client = clients.get(selectedRow);
            userDAO.supprimerClient(client.getId());
            clients.remove(selectedRow);
            view.afficherClientsDansTable(clients);
            showMessage("Client supprimé avec succès.");
            effacerSaisie();
        } catch (Exception e) {
            handleException(e, "Erreur lors de la suppression du client : ");
        }
    }

    private void modifierClient() {
        try {
            int selectedRow = view.getTableClient().getSelectedRow();
            if (selectedRow == -1) {
                showMessage("Veuillez sélectionner un client à modifier.");
                return;
            }
            Client client = clients.get(selectedRow);
            client.setNom(view.getTxtNomClient());
            client.setPrenom(view.getTxtPrenomClient());
            client.setNumero(view.getTxtNumeroClient());
            client.setMail(view.getTxtMailClient());

            userDAO.modifierClient(client);
            view.afficherClientsDansTable(clients);
            showMessage("Client modifié avec succès.");
            effacerSaisie();
        } catch (SQLException e) {
            handleException(e, "Erreur lors de la modification du client : ");
        }
    }

    private Client extraireClientDeVue() {
        try {
            String nom = view.getTxtNomClient();
            String prenom = view.getTxtPrenomClient();
            String numero = view.getTxtNumeroClient();
            String mail = view.getTxtMailClient();

            if (nom.isEmpty() || prenom.isEmpty() || numero.isEmpty() || mail.isEmpty()) {
                return null;
            }

            return new Client(nom, prenom, numero, mail);
        } catch (NumberFormatException e) {
            showMessage("Veuillez entrer des valeurs valides.");
            return null;
        }
    }

    private void showMessage(String message) {
        System.out.println("Appel de showMessage avec message : " + message); // Ajouté pour débogage
        SwingUtilities.invokeLater(() -> {
            if (!messageShowing) {
                messageShowing = true;
                JOptionPane.showMessageDialog(view, message);
                messageShowing = false;
            }
        });
    }

    private void handleException(Exception e, String message) {
        showMessage(message + e.getMessage());
    }
}
