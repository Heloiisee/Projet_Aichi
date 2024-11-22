package controller;
import model.Client;
import view.ClientView;
import view.ClientView;

import java.util.List;
import javax.swing.JOptionPane;

public class Clientcontroller {

    private final ClientView view;
    private List<Client> clients;

    public Clientcontroller(ClientView view, List<Client> clients) {
        this.view = view;
        this.clients = clients;
        initController();
    }

    public Clientcontroller(ClientView view) {
        this.view = view;
    }

    private void initController() {
        view.getActionAjouter().addActionListener(e -> ajouterClient());
        view.getActionModifier().addActionListener(e -> modifierClient());
        view.getActionSupprimer().addActionListener(e -> supprimerClient());
        view.getActionEffacer().addActionListener(e -> effacerSaisie());
        view.getActionQuitter().addActionListener(e -> quitter());
    }

    private void ajouterClient() {
        String nom = view.getNomClient().getText();
        String prenom = view.getPrenomClient().getText();
        String numero = view.getNumeroClient().getText();
        String mail = view.getMailClient().getText();

        if (!nom.isEmpty() && !prenom.isEmpty() && !numero.isEmpty() && !mail.isEmpty()) {
            int id = clients.size() + 1; // Simple ID generation
            clients.add(new Client(id, nom, prenom, numero, mail));
            view.updateClientList(clients);
            effacerSaisie();
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.");
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
            view.updateClientList(clients);
            effacerSaisie();
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
    private void quitter() {
        int confirmation = JOptionPane.showConfirmDialog(view, "Êtes-vous sûr de vouloir quitter la fenêtre ?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}
