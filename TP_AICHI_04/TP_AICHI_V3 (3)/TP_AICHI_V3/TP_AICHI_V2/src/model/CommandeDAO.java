package model;

/**
 * @Author Melissa
 * Modèle de la classe CommandeDAO
 * @version 1.0
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe CommandeDAO
 */

public class CommandeDAO {
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
            String query = "INSERT INTO commande (id_client, date_Commande, statut) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, commande.getIdClient());
            preparedStatement.setDate(2, new java.sql.Date(commande.getDate_Commande().getTime()));
            preparedStatement.setString(3, commande.getStatut());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                commande.setNCommande(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modifierCommande(Commande commande) {
        try {
            String query = "UPDATE commande SET id_client = ?, date_Commande = ?, statut = ? WHERE nCommande = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, commande.getIdClient());
            preparedStatement.setDate(2, new java.sql.Date(commande.getDate_Commande().getTime()));
            preparedStatement.setString(3, commande.getStatut());
            preparedStatement.setInt(4, commande.getNCommande());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void supprimerCommande(int nCommande) {
        try {
            String query = "DELETE FROM commande WHERE nCommande = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, nCommande);
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
                int nCommande = resultSet.getInt("nCommande");
                int id_client = resultSet.getInt("id_client");
                Date dateCommande = resultSet.getDate("date_Commande");
                String statut = resultSet.getString("statut");

                Commande commande = new Commande(nCommande, id_client, dateCommande, statut);
                commandes.add(commande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commandes;
    }

    public Commande getCommandeById(int nCommande) {
        try {
            String query = "SELECT * FROM commande WHERE nCommande = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, nCommande);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id_client = resultSet.getInt("id_client");
                Date dateCommande = resultSet.getDate("date_Commande");
                String statut = resultSet.getString("statut");

                return new Commande(nCommande, id_client, dateCommande, statut);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
