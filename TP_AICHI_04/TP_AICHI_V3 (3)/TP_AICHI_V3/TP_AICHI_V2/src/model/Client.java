package model;

public class Client {
    private int id;
    private String nom ;
    private String prenom  ;
    private String  numero;
    private String mail;


    // Constructeur par défaut
    public Client( int id, String nom, String prenom, String numero, String mail) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.mail = mail;
    }

    public Client(  String nom, String prenom, String numero, String mail) {

        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
        this.mail = mail;
    }

    // Getter et setter pour le nom
    public String getNom() {
        return this.nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    // Getter et setter pour le mot de prenom
    public String getPrenom() {
        return this.prenom;
    }

    public void setNom(String prenom) {
        this.prenom = prenom;
    }


    // Getter et setter pour le numero
    public String getNumero() {

        return this.numero;
    }

    public void setNunero(String Numero) {

        this.numero = numero ;
    }


    // Getter et setter pour le mail
    public String getMail() {

        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getEmail() {
        return this.mail;
    }

    public int getId() {
        return this.id;
    }
}

