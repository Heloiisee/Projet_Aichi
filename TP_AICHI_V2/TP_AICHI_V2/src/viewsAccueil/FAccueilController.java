package viewsAccueil;

import controller.FArticlesController;
import view.ClientView;

import java.awt.event.*;

public class FAccueilController {
    private final FAccueilView view;

    public FAccueilController(FAccueilView view) {
        this.view = view;
        initController();

    }

    public void initController() {
        view.addQuitterListener(new QuitterListener());
        view.addArticlesListener(new ArticlesListener());
        view.addClientsListener(new ClientsListener());
        view.addCommandesListener(new CommandesListener());
    }

    static class QuitterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class ArticlesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setInfoText("Affichage des Articles");
            afficherArticles();
        }
    }


    class ClientsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            view.setInfoText("Affichage des Clients");
            afficherClients();
        }
    }

    class CommandesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setInfoText("Affichage des Commandes");
        }
    }

    protected void afficherArticles() {
        FArticlesView laFenetre = new FArticlesView(this);
        laFenetre.setVisible(true);
        FArticlesController controller = new FArticlesController(laFenetre);
        controller.initController();
    }

    protected void afficherClients() {
        ClientView laFenetre = new ClientView(this);
        laFenetre.setVisible(true);
    }
}
