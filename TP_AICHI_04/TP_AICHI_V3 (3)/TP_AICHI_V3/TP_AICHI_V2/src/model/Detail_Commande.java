package model;

/**
 * @Author Melissa
 * Modèle de la classe Detail_Commande
 * @version 1.0
 */


/**
 * Classe Detail_Commande
 */

public class Detail_Commande {

    private int id_commande;
    private int id_article;
    private int quantite;


    /**
     * Constructeur par défaut
     */

    public Detail_Commande( int id_commande, int id_article, int quantite) {
        this.id_commande = id_commande;
        this.id_article = id_article;
        this.quantite = quantite;
    }


    public int getIdCommande() {
        return id_commande;
    }

    public int getIdArticle() {
        return id_article;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setIdCommande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setIdArticle(int id_article) {
        this.id_article = id_article;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Detail_Commande{ id_commande=" + id_commande + ", id_article=" + id_article + ", quantite=" + quantite + '}';
    }
}
