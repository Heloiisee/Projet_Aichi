package controller;

/**
 * @Author Melissa
 * Contrôleur de la classe Commande
 * @version 1.0
 */

import model.Commande;
import model.CommandeDAO;
import view.CommandeView;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe CommandeController
 */
public class CommandeController {
    private final CommandeView view;
    private List<Commande> commandes;
    private CommandeDAO commandeDAO;

    /**
     * Constructeur par défaut
     * @param view
     */
    public CommandeController(CommandeView view) {
        this.view = view;
        this.commandeDAO = new CommandeDAO();
        this.commandes = new ArrayList<>(); // Initialisation de la liste
        initController();
    }

    /**
     * Initialisation du contrôleur
     */
    public void initController() {
        view.setValiderActionListener(e -> ajouterCommande());
        view.setEffacerActionListener(e -> effacerSaisie());
        view.setAfficherActionListener(e -> afficherCommandes());
        view.setRetourActionListener(e -> retourAccueil());
        view.setSupprimerActionListener(e -> supprimerCommande());
        view.setModifierActionListener(e -> modifierCommande());
        view.setDetailsActionListener(e -> afficherDetailCommande());
    }



    private void ajouterCommande() {
        String message;
        try {
            int id_client = Integer.parseInt(view.getTxtIdClient());
            String date = view.getTxtDateCommande();
            String statut = view.getTxtStatut();

            if (date.isEmpty() || statut.isEmpty()) {
                showMessage("Veuillez remplir tous les champs.");
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCommande = dateFormat.parse(date);

            Commande commande = new Commande(id_client, dateCommande, statut);
            commandeDAO.ajouterCommande(commande);

            commandes.add(commande);
            List<Commande> updatedCommandes = commandeDAO.getAllCommandes();
            view.afficherCommandesDansTable(updatedCommandes);

            message = "Commande ajoutée avec succès !";
            effacerSaisie();
        } catch (NumberFormatException ex) {
            message = "Veuillez entrer des valeurs valides (ex. ID).";
        } catch (ParseException ex) {
            message = "Format de date invalide. Utilisez yyyy-MM-dd.";
        } catch (Exception e) {
            message = "Erreur lors de l'ajout de la commande : " + e.getMessage();
        }
        showMessage(message);
    }

    private void effacerSaisie() {
        view.clearFields();
    }

    private void afficherCommandes() {
        try {
            commandes = commandeDAO.getAllCommandes();
            view.afficherCommandesDansTable(commandes);
        } catch (Exception e) {
            showMessage("Erreur lors de l'affichage des commandes : " + e.getMessage());
        }
    }

    private void retourAccueil() {
        view.setVisible(false);
    }

    private void supprimerCommande() {
        try {
            int selectedRow = view.getTableCommande().getSelectedRow();
            if (selectedRow == -1) {
                showMessage("Veuillez sélectionner une commande à supprimer.");
                return;
            }
            int nCommande = (int) view.getTableCommande().getValueAt(selectedRow, 0);
            commandeDAO.supprimerCommande(nCommande);

            commandes.removeIf(commande -> commande.getNCommande() == nCommande);
            view.afficherCommandesDansTable(commandes);
            showMessage("Commande supprimée avec succès.");
        } catch (Exception e) {
            showMessage("Erreur lors de la suppression de la commande : " + e.getMessage());
        }
    }

    private void modifierCommande() {
        String message;
        try {
            int selectedRow = view.getTableCommande().getSelectedRow();
            if (selectedRow == -1) {
                showMessage("Veuillez sélectionner une commande à modifier.");
                return;
            }
            int nCommande = (int) view.getTableCommande().getValueAt(selectedRow, 0);
            int id_client = Integer.parseInt(view.getTxtIdClient());
            String date = view.getTxtDateCommande();
            String statut = view.getTxtStatut();

            if (date.isEmpty() || statut.isEmpty()) {
                showMessage("Veuillez remplir tous les champs.");
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCommande = dateFormat.parse(date);

            Commande commande = new Commande(nCommande, id_client, dateCommande, statut);
            commandeDAO.modifierCommande(commande);

            commandes.removeIf(c -> c.getNCommande() == nCommande);
            commandes.add(commande);
            view.afficherCommandesDansTable(commandes);

            message = "Commande modifiée avec succès.";
            effacerSaisie();
        } catch (NumberFormatException ex) {
            message = "Veuillez entrer des valeurs valides (ex. ID).";
        } catch (ParseException ex) {
            message = "Format de date invalide. Utilisez yyyy-MM-dd.";
        } catch (Exception e) {
            message = "Erreur lors de la modification de la commande : " + e.getMessage();
        }
        showMessage(message);
    }

    private void afficherDetailCommande() {
        try {
            int selectedRow = view.getTableCommande().getSelectedRow();
            if (selectedRow == -1) {
                showMessage("Veuillez sélectionner une commande pour afficher les détails.");
                return;
            }
            int nCommande = (int) view.getTableCommande().getValueAt(selectedRow, 0);
            Commande commande = commandeDAO.getCommandeById(nCommande);
            showMessage("Détails de la commande : \n" +
                    "ID Client : " + commande.getIdClient() + "\n" +
                    "Date Commande : " + commande.getDate_Commande() + "\n" +
                    "Statut : " + commande.getStatut());
        } catch (Exception e) {
            showMessage("Erreur lors de l'affichage des détails de la commande : " + e.getMessage());
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(view, message);
    }
}
