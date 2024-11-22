package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private String serveur;
    private String bdd;
    private String user;
    private String mdp;
    private Connection maConnexion;


    public Connexion(String serveur, String bdd, String user, String mdp) {
        this.serveur = serveur;
        this.bdd = "momo_tpaichi";
        this.user = "momo_utlisateur";
        this.mdp = "Je123pas?";
        this.maConnexion = null;
    }


    public void chargerPilote() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur de chargement du pilote JDBC : " + e.getMessage());
        }
    }


    public void seConnecter() {
        this.chargerPilote();
        String url = "jdbc:mysql://" + this.serveur + "/" + this.bdd;
        try {
            this.maConnexion = DriverManager.getConnection(url, this.user, this.mdp);
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à : " + url + " - " + e.getMessage());
        }
    }


    public void seDeconnecter() {
        try {
            if (this.maConnexion != null) {
                this.maConnexion.close();
                System.out.println("Connexion fermée !");
            }
        } catch (SQLException e) {
            System.out.println("Erreur de fermeture de la connexion : " + e.getMessage());
        }
    }

    public Connection getMaConnexion() {

        return this.maConnexion;
    }

    public static void main(String[] args) {
        Connexion conn = new Connexion("mysql-momo.alwaysdata.net", "momo_tpaichi", "momo_deymi", "Je123pas?");
        conn.seConnecter();
        conn.seDeconnecter();
    }
}
