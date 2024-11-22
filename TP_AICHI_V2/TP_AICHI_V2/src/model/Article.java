package model;

import java.util.ArrayList;
import java.util.List;

public class Article {

    private int id;
    private String libelle;
    private String description;
    private Double prix;

    // Liste statique pour stocker les articles
    private static List<Article> articles = new ArrayList<>();

    // Constructeur principal
    public Article(int id, String libelle, String description, Double prix) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        setPrix(prix); // Utilise le setter pour inclure la validation
    }

    // Constructeur par défaut
    public Article() {
        this.id = 0;
        this.libelle = "Non défini";
        this.description = "Non défini";
        this.prix = 0.0;
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

    // Méthodes pour la gestion des articles


    public static void ajouterArticle(Article article) {
        articles.add(article);
        System.out.println("Article ajouté : " + article);
    }


    public static void modifierArticle(int id, String libelle, String description, Double prix) {
        for (Article article : articles) {
            if (article.getId() == id) {
                article.setLibelle(libelle);
                article.setDescription(description);
                article.setPrix(prix);
                System.out.println("Article modifié : " + article);
                return;
            }
        }
        System.out.println("Article avec l'ID " + id + " non trouvé.");
    }


    public static void supprimerArticle(int id) {
        articles.removeIf(article -> article.getId() == id);
        System.out.println("Article avec l'ID " + id + " supprimé.");
    }


    public static void afficherArticles() {
        if (articles.isEmpty()) {
            System.out.println("Aucun article disponible.");
        } else {
            for (Article article : articles) {
                System.out.println(article);
            }
        }
    }
}
