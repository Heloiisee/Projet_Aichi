package controller;
import model.Client;
import view.ClientView;
import model.UserDAO;


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
    }

    public void initController() {
        view.getActionAjouter().addActionListener(e -> ajouterClient());
        view.getActionModifier().addActionListener(e -> modifierClient());
        view.getActionSupprimer().addActionListener(e -> supprimerClient());
        view.getActionEffacer().addActionListener(e -> effacerSaisie());
        view.getActionRetour().addActionListener(e -> retourAccueil());

    }

    private void ajouterClient() {
        String nom = view.getNomClient().getText();
        String prenom = view.getPrenomClient().getText();
        String numero = view.getNumeroClient().getText();
        String mail = view.getMailClient().getText();

        Client client = new Client(nom, prenom, numero, mail);
        if (nom.isEmpty() || prenom.isEmpty() || numero.isEmpty() || mail.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.");
            return;
        }
        try {
            userDAO.ajouterClient(client);
            view.showMessage("Client ajouté avec succès !");
            clients.add(client);
            view.updateClientList(clients);
            effacerSaisie();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de l'ajout du client." + e.getMessage());
        }


    }

    private void modifierClient() {
        int selectedIndex = view.getClientList().getSelectedIndex();
        if (selectedIndex != -1) {
            Client client = clients.get(selectedIndex);
            client.setNom(view.getNomClient().getText());
            client.setPrenom(view.getPrenomClient().getText());
            client.setNumero(view.getNumeroClient().getText());
            client.setMail(view.getMailClient().getText());
            try {
                userDAO.modifierClient(client);
                view.showMessage("Client modifié avec succès !");
                view.updateClientList(clients);
                effacerSaisie();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(view, "Erreur lors de la modification du client." + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un client à modifier.");
        }
    }

    private void supprimerClient() {
        int selectedIndex = view.getClientList().getSelectedIndex();
        if (selectedIndex != -1) {
            clients.remove(selectedIndex);
            view.updateClientList(clients);
            effacerSaisie();
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner un client à supprimer.");
        }
    }

    private void effacerSaisie() {
        view.getNomClient().setText("");
        view.getPrenomClient().setText("");
        view.getNumeroClient().setText("");
        view.getMailClient().setText("");


    }
    private void retourAccueil() {
        view.setVisible(false);

    }
    public void afficherClients() {
        try {
            userDAO.afficherClients();
            view.updateClientList(clients);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de l'affichage des clients." + e.getMessage());
        }
    }

}
