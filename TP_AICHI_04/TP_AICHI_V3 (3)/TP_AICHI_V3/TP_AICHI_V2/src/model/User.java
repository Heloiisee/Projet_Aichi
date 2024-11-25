package model;

public class User {
    private int id;
    private String login;
    private String mdp;

    // Constructeur par défaut
    public User() {
    }

    // Constructeur avec paramètres
    public User(String login, String mdp) {
        this.login = login;
        this.mdp = mdp;
    }

    // Getter et setter pour l'identifiant
    public int getId() {

        return this.id;
    }

    public void setId(int id) {

        this.id = id;
    }

    // Getter et setter pour le login
    public String getLogin() {

        return this.login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    // Getter et setter pour le mot de passe
    public String getMdp() {

        return this.mdp;
    }

    public void setMdp(String mdp) {

        this.mdp = mdp;
    }
}
