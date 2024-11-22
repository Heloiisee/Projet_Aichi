package controller;
import model.User;
import model.UserDAO;
import view.FConnexionView;
import viewsAccueil.FAccueilController;
import viewsAccueil.FAccueilView;
import viewsAccueil.FArticlesView;

import java.sql.SQLException;
public class ConnexionController {

    private final FConnexionView view;
    private final UserDAO userDAO;
    private boolean listenersAdded = false;

    public ConnexionController(FConnexionView view, UserDAO userDAO) {
        this.view = view;
        this.userDAO = userDAO;
    }

    public void addListeners() {
        if (!listenersAdded) {
            view.addValiderListener(e -> {
                try {
                    handleValider();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            view.addQuitterListener(e -> handleQuitter());
            listenersAdded = true;
        }
    }

    private void handleValider() throws SQLException {
        String login = view.getLogin();
        String motDePasse = view.getMotDePasse();
        // Ajoutez ici la logique de validation
        if (login.isEmpty() || motDePasse.isEmpty()) {
            view.showMessage("Veuillez remplir tous les champs.");
            return;
        }

        User user = new User(login, motDePasse);
        if (userDAO.verifierUser(user)==false) {
            view.showMessage("Erreur de connexion : identifiants incorrects ou problème de base de données.");
            return;
        }else{
            view.showMessage("Connexion réussie !");
            FAccueilView accueilView = new FAccueilView();
            accueilView.setVisible(true);
            FAccueilController fAccueilController = new FAccueilController(accueilView);
            fAccueilController.initController();




        }
    }

    private void handleQuitter() {
        view.fermerFenetre();
    }

}