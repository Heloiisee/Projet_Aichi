package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @Author Melissa
 * Modèle de la classe Detail_CommandeDAO
 * @version 1.0
 */

public class Detail_CommandeDAO {
    public static Connection connection;

    /**
     * Constructeur par défaut
     */

    public Detail_CommandeDAO() {
        // Connexion à la base de données
        try {
            // Chargement du driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://mysql-momo.alwaysdata.net/momo_tpaichi";
            String username = "momo_utlisateur";
            String password = "Je123pas?";

            // Connexion à la base de données
            connection = java.sql.DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            // Gestion des erreurs
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            // Gestion des erreurs
            throw new RuntimeException(e);
        }

    }

    /**
     * Getter pour la connexion
     * @return connection
     */

    // Getter pour la connexion
    public static Connection getConnection() {

        return connection;
    }

    public void AfficherCommandeDetail(){



    }
}
