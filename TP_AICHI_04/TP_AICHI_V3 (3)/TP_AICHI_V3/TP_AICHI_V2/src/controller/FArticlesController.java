package controller;

import viewsAccueil.FArticlesView;
import model.Article;
import model.UserDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class FArticlesController {
    private final FArticlesView view;
    private final UserDAO userDAO;
    private boolean areListenersAdded = false; // Variable pour vérifier si les listeners sont ajoutés
    private boolean messageShowing = false;

    public FArticlesController(FArticlesView view) {
        this.view = view;
        this.userDAO = new UserDAO();
        initController();
    }

    public void initController() {
        if (!areListenersAdded) {
            view.addAjouterListener(e -> ajouterArticle());
            view.addModifierListener(e -> modifierArticle());
            view.addSupprimerListener(e -> supprimerArticle());
            view.addEffacerListener(e -> effacerSaisie());
            view.addAfficherListener(e -> afficherArticles());
            areListenersAdded = true;
        }
    }

    private void ajouterArticle() {
        try {
            Article article = extraireArticleDeVue();
            if (article == null) {
                showMessage("Veuillez remplir tous les champs.");
                return;
            }

            userDAO.ajouterArticle(article);
            userDAO.afficherArticles();
            view.effacerSaisie();
            showMessage("Article ajouté avec succès !");
        } catch (NumberFormatException ex) {
            showMessage("Erreur de format de nombre : " + ex.getMessage());
        } catch (Exception e) {
            handleException(e, "Erreur lors de l'ajout de l'article : ");
        }
    }

    private void modifierArticle() {
        try {
            Article article = extraireArticleDeVue();
            if (article == null) {
                showMessage("Veuillez remplir tous les champs.");
                return;
            }

            int id = getSelectedArticleId();
            article.setId(id);

            userDAO.modifierArticle(article);
            showMessage("Article modifié avec succès !");
        } catch (NumberFormatException ex) {
            showMessage("Erreur de format de nombre : " + ex.getMessage());
        } catch (Exception e) {
            handleException(e, "Erreur lors de la modification de l'article : ");
        }
    }

    private void supprimerArticle() {
        try {
            int selectedRow = view.getTableArticles().getSelectedRow();
            if (selectedRow == -1) {
                showMessage("Veuillez sélectionner un article à supprimer.");
                return;
            }
            int id = (int) view.getTableArticles().getValueAt(selectedRow, 0);
            Article article = new Article();
            article.setId(id);
            view.supprimerArticleDeTable(selectedRow);


            userDAO.supprimerArticle(article);
            showMessage("Article supprimé avec succès !");
        } catch (Exception e) {
            handleException(e, "Erreur lors de la suppression de l'article : ");
        }
    }

    private void effacerSaisie() {
        try {
            if (view.getTxtLibelle().getText().isEmpty() && view.getTxtDescription().getText().isEmpty()
                    && view.getTxtPrix().getText().isEmpty() && view.getSliderStock().getValue() == 0) {
                showMessage("Les champs sont déjà vides.");
                return;
            }
            view.effacerSaisie();
        } catch (Exception ex) {
            showMessage("Erreur lors de l'effacement des champs : " + ex.getMessage());
        }
    }

    private void afficherArticles() {
        try {
            List<Article> articles = userDAO.getArticles();
            view.afficherArticlesDansTable(articles);
            view.updateArticleList(articles);
        } catch (Exception e) {
            handleException(e, "Erreur lors de l'affichage des articles : ");
        }
    }

    public List<Article> rechercherArticlesParNom(String nom) {
        return userDAO.rechercherArticlesParNom(nom);
    }

    private int getSelectedArticleId() {
        int selectedRow = view.getTableArticles().getSelectedRow();
        if (selectedRow == -1) {
            showMessage("Veuillez sélectionner un article.");
            return -1;
        }
        return (int) view.getTableArticles().getValueAt(selectedRow, 0);
    }

    private Article extraireArticleDeVue() {
        try {
            String libelle = view.getTxtLibelle().getText();
            String description = view.getTxtDescription().getText();
            double prix = Double.parseDouble(view.getTxtPrix().getText().replace(",", "."));
            int stock = view.getSliderStock().getValue();

            if (libelle.isEmpty() || description.isEmpty() || prix <= 0 || stock <= 0) {
                return null;
            }
            return new Article(libelle, description, prix, stock);
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
