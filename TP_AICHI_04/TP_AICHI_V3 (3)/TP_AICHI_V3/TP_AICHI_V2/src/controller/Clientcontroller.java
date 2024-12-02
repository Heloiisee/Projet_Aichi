package controller;

import model.Client;
import model.UserDAO;
import view.ClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class Clientcontroller {

    private final ClientView view;
    private List<Client> clients;
    private UserDAO userDAO;

    public Clientcontroller(ClientView view, List<Client> clients) {
        this.view = view;
        this.clients = clients;
        this.userDAO = new UserDAO();
        initController();
    }

    public Clientcontroller(ClientView view) {
        this.view = view;
        this.userDAO = new UserDAO();
        initController();
    }

    public void initController() {
        view.setAjouterActionListener(e -> ajouterClient());
        view.setModifierActionListener(e -> modifierClient());
        view.setSupprimerActionListener(e -> supprimerClient());
        view.setEffacerActionListener(e -> effacerSaisie());
        view.setAfficherActionListener(e -> afficherClients());
        view.setRetourActionListener(e -> retourAccueil());
        view.setRechercherActionListener(new RechercherClientListener()); // Ajout du Listener pour la recherche
    }

    public void ajouterClient() {
        String nom = view.getTxtNomClient();
        String prenom = view.getTxtPrenomClient();
        String numero = view.getTxtNumeroClient();
        String mail = view.getTxtMailClient();

        if (nom.isEmpty() || prenom.isEmpty() || numero.isEmpty() || mail.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.");
            return;
        }

        Client client = new Client(nom, prenom, numero, mail);
        try {
            userDAO.ajouterClient(client);
            view.showMessage("Client ajouté avec succès !");
            clients.add(client);
            view.afficherClientsDansTable(clients);
            effacerSaisie();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de l'ajout du client: " + e.getMessage());
        }
    }

    public void modifierClient() {
        int selectedRow = view.getTableClient().getSelectedRow();
        if (selectedRow != -1) {
            Client client = clients.get(selectedRow);
            client.setNom(view.getTxtNomClient());
            client.setPrenom(view.getTxtPrenomClient());
            client.setNumero(view.getTxtNumeroClient());
            client.setMail(view.getTxtMailClient());
            try {
                userDAO.modifierClient(client);
                view.showMessage("Client modifié avec succès !");
                view.afficherClientsDansTable(clients);
                effacerSaisie();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, "Erreur lors de la modification du client: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un client à modifier.");
        }
    }

    public void supprimerClient() {
        int selectedRow = view.getTableClient().getSelectedRow();
        if (selectedRow != -1) {
            Client client = clients.get(selectedRow);
            try {
                userDAO.supprimerClient(client.getId());
                clients.remove(selectedRow);
                view.afficherClientsDansTable(clients);
                effacerSaisie();
                view.showMessage("Client supprimé avec succès !");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, "Erreur lors de la suppression du client: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un client à supprimer.");
        }
    }

    public void effacerSaisie() {
        view.clearFields();
    }

    public void afficherClients() {
        try {
            clients = userDAO.getClientList();
            view.afficherClientsDansTable(clients);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de l'affichage des clients: " + e.getMessage());
        }
    }

    public void retourAccueil() {
        view.setVisible(false);
    }

    class RechercherClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nomRecherche = view.getTxtRechercheClient();
            if (nomRecherche.isEmpty()) {
                view.showMessage("Veuillez entrer un nom pour la recherche.");
                return;
            }

            List<Client> resultats = userDAO.rechercherClientsParNom(nomRecherche);
            if (resultats.isEmpty()) {
                view.showMessage("Aucun client trouvé.");
            } else {
                view.afficherClientsDansTable(resultats);
            }
        }
    }
}
