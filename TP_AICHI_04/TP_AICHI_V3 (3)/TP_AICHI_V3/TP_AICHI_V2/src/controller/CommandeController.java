package controller;

import model.Commande;
import model.CommandeDAO;
import view.CommandeView;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommandeController {

    private final CommandeView view;
    private List<Commande> commandes;
    private CommandeDAO commandeDAO;


    public CommandeController(CommandeView view, List<Commande> commandes) {
        this.view = view;
        this.commandes = commandes;
        this.commandeDAO = new CommandeDAO();
        initController();
    }

    public CommandeController(CommandeView view) {
        this.view = view;
        this.commandeDAO = new CommandeDAO();
        initController();
    }

    public void initController() {
        view.setValiderActionListener(e -> ajouterCommande());
        view.setEffacerActionListener(e -> effacerSaisie());
        view.setAfficherActionListener(e -> afficherCommandes());
        view.setRetourActionListener(e -> retourAccueil());
        view.setSupprimerActionListener(e -> supprimerCommande());
        view.setModifierActionListener(e -> modifierCommande());

    }

    private void ajouterCommande() {
        try {
            int id_client = Integer.parseInt(view.getTxtIdClient());
            int id_article = Integer.parseInt(view.getTxtIdArticle());
            String nom_commande = view.getTxtNomCommande();
            String date = view.getTxtDateCommande();
            String statut = view.getTxtStatut();
            int prix = Integer.parseInt(view.getTxtPrixTotal());

            if (nom_commande.isEmpty() || date.isEmpty() || statut.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.");
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCommande = dateFormat.parse(date);

            Commande commande = new Commande(0, id_client, id_article, nom_commande, dateCommande, statut, prix);
            commandeDAO.ajouterCommande(commande);
            view.showMessage("Commande ajoutée avec succès !");
            commandes.add(commande);
            view.afficherCommandesDansTable(commandes);
            effacerSaisie();
            List<Commande> updatedCommandes = commandeDAO.getAllCommandes();
            view.afficherCommandesDansTable(updatedCommandes);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(view, "Format de date invalide. Utilisez yyyy-MM-dd.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de l'ajout de la commande: " + e.getMessage());
        }
    }

    private void annulerCommande() {
        effacerSaisie();
    }

    private void effacerSaisie() {
        view.clearFields();
    }

    private void afficherCommandes() {
        try {
            commandes = commandeDAO.getAllCommandes();
            view.afficherCommandesDansTable(commandes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de l'affichage des commandes: " + e.getMessage());
        }
    }

    private void retourAccueil() {
        view.setVisible(false);
    }

    private void supprimerCommande() {
        try {
            int selectedRow = view.getTableCommande().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner une commande à supprimer.");
                return;
            }
            int id = (int) view.getTableCommande().getValueAt(selectedRow, 0);
            commandeDAO.supprimerCommande(id);
            view.showMessage("Commande supprimée avec succès !");
            commandes.removeIf(commande -> commande.getId() == id);
            view.afficherCommandesDansTable(commandes);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de la suppression de la commande: " + e.getMessage());
        }
    }

    private void modifierCommande() {
        try {
            int selectedRow = view.getTableCommande().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner une commande à modifier.");
                return;
            }
            int id = (int) view.getTableCommande().getValueAt(selectedRow, 0);
            int id_client = Integer.parseInt(view.getTxtIdClient());
            int id_article = Integer.parseInt(view.getTxtIdArticle());
            String nom_commande = view.getTxtNomCommande();
            String date = view.getTxtDateCommande();
            String statut = view.getTxtStatut();
            int prix = Integer.parseInt(view.getTxtPrixTotal());

            if (nom_commande.isEmpty() || date.isEmpty() || statut.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.");
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCommande = dateFormat.parse(date);

            Commande commande = new Commande(id, id_client, id_article, nom_commande, dateCommande, statut, prix);
            commandeDAO.modifierCommande(commande);
            view.showMessage("Commande modifiée avec succès !");
            commandes.removeIf(c -> c.getId() == id);
            commandes.add(commande);
            view.afficherCommandesDansTable(commandes);
            effacerSaisie();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(view, "Format de date invalide. Utilisez yyyy-MM-dd.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Erreur lors de la modification de la commande: " + e.getMessage());
        }
    }
}
