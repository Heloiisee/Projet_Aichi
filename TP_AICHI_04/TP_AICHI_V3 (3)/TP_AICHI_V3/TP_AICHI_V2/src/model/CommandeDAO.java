package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class  CommandeDAO {
    public static Connection connection;

    public CommandeDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://mysql-momo.alwaysdata.net/momo_tpaichi";
            String username = "momo_utlisateur";
            String password = "Je123pas?";

            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public void ajouterCommande(Commande commande) {
        try {
            String query = "INSERT INTO commande (id_client, id_article, nom_commande, date, statut, prix) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, commande.getIdClient());
            preparedStatement.setInt(2, commande.getIdArticle());
            preparedStatement.setString(3, commande.getNomCommande());
            preparedStatement.setDate(4, new java.sql.Date(commande.getDateCommande().getTime()));
            preparedStatement.setString(5, commande.getStatut());
            preparedStatement.setInt(6, commande.getPrixTotal());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void afficherCommandes() {
        try {
            String query = "SELECT * FROM commande";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Commande{" +
                        "id=" + resultSet.getInt("id") +
                        ", idclient=" + resultSet.getInt("id_client") +
                        ", idArticle=" + resultSet.getInt("id_article") +
                        ", nomCommande='" + resultSet.getString("nom_commande") + '\'' +
                        ", date='" + resultSet.getDate("date") + '\'' +
                        ", statut='" + resultSet.getString("statut") + '\'' +
                        ", prix=" + resultSet.getInt("prix") +
                        '}');
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modifierCommande(Commande commande) {
        try {
            String query = "UPDATE commande SET id_client = ?, id_article = ?, nom_commande = ?, date = ?, statut = ?, prix = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, commande.getIdClient());
            preparedStatement.setInt(2, commande.getIdArticle());
            preparedStatement.setString(3, commande.getNomCommande());
            preparedStatement.setDate(4, new java.sql.Date(commande.getDateCommande().getTime()));
            preparedStatement.setString(5, commande.getStatut());
            preparedStatement.setInt(6, commande.getPrixTotal());
            preparedStatement.setInt(7, commande.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void supprimerCommande(int id) {
        try {
            String query = "DELETE FROM commande WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Commande> getAllCommandes() {
        List<Commande> commandes = new ArrayList<>();
        try {
            String query = "SELECT * FROM commande";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int id_client = resultSet.getInt("id_client");
                int id_article = resultSet.getInt("id_article");
                String nom_commande = resultSet.getString("nom_commande");
                Date date = resultSet.getDate("date");
                String statut = resultSet.getString("statut");
                int prix = resultSet.getInt("prix");

                Commande commande = new Commande(id, id_client, id_article, nom_commande, date, statut, prix);
                commandes.add(commande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commandes;
    }


}
