package controller;

import viewsAccueil.FArticlesView;
import model.UserDAO;
import model.Article;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class FArticlesController {
    private final FArticlesView view;
    private final UserDAO userDAO;

    public FArticlesController(FArticlesView view) {
        this.view = view;
        this.userDAO = new UserDAO();
        initController();
    }

    public void initController() {
        view.addAjouterListener(new AjouterListener());
        view.addModifierListener(new ModifierListener());
        view.addSupprimerListener(new SupprimerListener());
        view.addEffacerListener(new EffacerListener());
        view.addAfficherListener(new AfficherListener());

    }

    // Listener pour l'ajout d'articles
    class AjouterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Vérifier que tous les champs sont remplis
            if (view.getTxtLibelle().getText().isEmpty() || view.getTxtDescription().getText().isEmpty() || view.getTxtPrix().getText().isEmpty() || view.getSliderStock().getValue() == 0) {
                JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.");
                return;
            }

            try {
                // Récupérer les données de la vue
                String libelle = view.getTxtLibelle().getText();
                String description = view.getTxtDescription().getText();
                double prix = Double.parseDouble(view.getTxtPrix().getText().replace(",", "."));
                int stock = view.getSliderStock().getValue();

                // Créer un objet Article
                Article article = new Article(libelle, description, prix, stock);

                // Ajouter l'article à la base de données
                userDAO.ajouterArticle(article);
                userDAO.afficherArticles();
                view.effacerSaisie();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Erreur de format de nombre : " + ex.getMessage());
            }
        }
    }
    // Listener pour la modification d'articles
    class ModifierListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Récupérer les données de la vue
            if(view.getTxtLibelle().getText().isEmpty() || view.getTxtDescription().getText().isEmpty() || view.getTxtPrix().getText().isEmpty() || view.getSliderStock().getValue() == 0) {
                JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs.");
                return;
            }
            try {
                // Récupérer les données de la vue
                int id = getSelectedArticleId(); // Méthode pour obtenir l'ID de l'article sélectionné
                String libelle = view.getTxtLibelle().getText();
                String description = view.getTxtDescription().getText();
                double prix = Double.parseDouble(view.getTxtPrix().getText().replace(",", "."));
                int stock = (int) view.getSliderStock().getValue();

                // Créer un objet Article
                Article article = new Article(id, libelle, description, prix, stock);

                // Modifier l'article dans la base de données
                userDAO.modifierArticle(article);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Erreur de format de nombre : " + ex.getMessage());
            }

        }
    }

    // Listener pour la suppression d'articles
    class SupprimerListener implements ActionListener {
        @Override

        public void actionPerformed(ActionEvent e) {

            // Récupérer l'ID de l'article sélectionné
            int id = getSelectedArticleId(); // Méthode pour obtenir l'ID de l'article sélectionné

            // Créer un objet Article
            Article article = new Article(id);

            // Supprimer l'article de la base de données
            userDAO.supprimerArticle(article);
        }
    }

    // Listener pour effacer la saisie
    class EffacerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.getTxtLibelle().getText().isEmpty() && view.getTxtDescription().getText().isEmpty() && view.getTxtPrix().getText().isEmpty() && view.getSliderStock().getValue() == 0) {
                JOptionPane.showMessageDialog(view, "Les champs sont déjà vides.");
                return;
            }
            try {
                // Effacer les champs de saisie
                view.effacerSaisie();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erreur lors de l'effacement des champs : " + ex.getMessage());
            }

        }
    }

    class AfficherListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Récupérer les articles depuis la base de données
            List<Article> articles = userDAO.getArticles();

            // Mettre à jour la table dans la vue
            view.afficherArticlesDansTable(articles);
        }
    }



    // Méthode pour obtenir l'ID de l'article sélectionné
    private int getSelectedArticleId() {

        return 0;
    }
}
