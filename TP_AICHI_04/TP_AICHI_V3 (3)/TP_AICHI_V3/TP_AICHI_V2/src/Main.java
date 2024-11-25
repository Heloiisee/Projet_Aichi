import controller.ConnexionController;
import model.UserDAO;
import view.FConnexionView;

public class Main {
    public static void main(String[] args) {
        FConnexionView view = new FConnexionView();
        UserDAO userDAO = new UserDAO();
        ConnexionController controller = new ConnexionController(view, userDAO);
        view.setVisible(true);
        controller.addListeners();




    }
}