package controller;

import viewsAccueil.FArticlesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FArticlesController {

    private final FArticlesView view;

    public FArticlesController(FArticlesView view) {
        this.view = view;
        initController();
    }

    public void initController() {
        view.getActionAjouter().addActionListener(new AjouterArticleListener());
        view.getActionModifier().addActionListener(new ModifierArticleListener());
        view.getActionSupprimer().addActionListener(new SupprimerArticleListener());
        view.getActionEffacer().addActionListener(new EffacerSaisieListener());
    }

    class AjouterArticleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ajouterArticle();
        }
    }

    class ModifierArticleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            modifierArticle();
        }
    }

    class SupprimerArticleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            supprimerArticle();
        }
    }

    class EffacerSaisieListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            effacerSaisie();
        }
    }

    private void ajouterArticle() {
        // Logique pour ajouter un article
    }

    private void modifierArticle() {
        // Logique pour modifier un article
    }

    private void supprimerArticle() {
        // Logique pour supprimer un article
    }

    private void effacerSaisie() {
        // Logique pour effacer la saisie
    }
}