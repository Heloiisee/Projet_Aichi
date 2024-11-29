package model;

import java.util.Date;

public class Commande {
    private int id;
    private int id_client;
    private int id_article;
    private String nom_commande;
    private Date date;
    private String statut;
    private int prix;

    public Commande(int id, int id_client, int id_article, String nom_commande, Date date, String statut, int prix) {
        this.id = id;
        this.id_client = id_client;
        this.id_article = id_article;
        this.nom_commande = nom_commande;
        this.date = date;
        this.statut = statut;
        this.prix = prix;
    }



    public int getId() {
        return id;
    }

    public int getIdClient() {
        return id_client;
    }

    public int getIdArticle() {
        return id_article;
    }

    public String getNomCommande() {
        return nom_commande;
    }

    public Date getDateCommande() {
        return date;
    }

    public String getStatut() {
        return statut;
    }

    public int getPrixTotal() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdClient(int id_client) {
        this.id_client = id_client;
    }

    public void setIdArticle(int id_article) {
        this.id_article = id_article;
    }

    public void setNomCommande(String nom_commande) {
        this.nom_commande = nom_commande;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setPrixTotal(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", idClient=" + id_client +
                ", idArticle=" + id_article +
                ", nomCommande='" + nom_commande + '\'' +
                ", dateCommande=" + date +
                ", statut='" + statut + '\'' +
                ", prixTotal=" + prix +
                '}';
    }
}
