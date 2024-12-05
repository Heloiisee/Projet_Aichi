package model;

/**
 * @Author Melissa
 * Modèle de la classe Commande
 * @version 1.0
 */

import java.util.Date;

/**
 * Classe Commande
 */

public class Commande {
    private int nCommande;
    private int idClient;
    private Date date_Commande;
    private String statut;

    /**
     * Constructeur pour la création d'une nouvelle commande avec un nCommande auto-incrémenté.
     */
    public Commande(int idClient, Date date_Commande, String statut) {
        this.idClient = idClient;
        this.date_Commande = date_Commande;
        this.statut = statut;
    }

    /**
     * Constructeur complet pour l'initialisation de toutes les variables de la commande.
     */
    public Commande(int nCommande, int idClient, Date date_Commande, String statut) {
        this.nCommande = nCommande;
        this.idClient = idClient;
        this.date_Commande = date_Commande;
        this.statut = statut;
    }

    public int getNCommande() {
        return nCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public Date getDate_Commande() {
        return date_Commande;
    }

    public String getStatut() {
        return statut;
    }

    public void setNCommande(int nCommande) {
        this.nCommande = nCommande;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setDate_Commande(Date date_Commande) {
        this.date_Commande = date_Commande;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Commande{ nCommande=" + nCommande + ", idClient=" + idClient + ", date_Commande=" + date_Commande + ", statut=" + statut + '}';
    }
}
