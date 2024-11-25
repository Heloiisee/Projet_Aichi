package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Article {

    private int id;
    private String libelle;
    private String description;
    private Double prix;
    private int stock;

    // Liste statique pour stocker les articles
    private static List<Article> articles = new ArrayList<>();

    // Constructeur principal
    public Article(int id, String libelle, String description, Double prix, int stock) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        setPrix(prix); // Utilise le setter pour inclure la validation
        this.stock = stock;
    }

    // Constructeur par défaut
    public Article() {
        this.id = 0;
        this.libelle = "Non défini";
        this.description = "Non défini";
        this.prix = 0.0;
        this.stock = 0;
    }

    public Article(String libelle, String description, double prix, int stock) {
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
    }

    public Article(int id) {
        this.id = id;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        if (libelle == null || libelle.trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé ne peut pas être vide.");
        }
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        if (prix == null || prix < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif ou nul.");
        }
        this.prix = prix;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Article {" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }

}
