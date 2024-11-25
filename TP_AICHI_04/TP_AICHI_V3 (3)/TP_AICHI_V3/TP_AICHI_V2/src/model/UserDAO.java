package model;

import java.sql.*;

public class UserDAO {
    private static Connection connection;

    public UserDAO() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            String url = "jdbc:mariadb://mysql-momo.alwaysdata.net/momo_tpaichi";
            String username = "momo_utlisateur";
            String password = "Je123pas?";
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void ajoutUser(User user) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established or is closed.");
        }
        String query = "INSERT INTO users (login, mdp) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getMdp());
            preparedStatement.executeUpdate();
        }
    }

    public boolean verifierUser(User user) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established or is closed.");
        }
        String query = "SELECT mdp FROM users WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("mdp").equals(user.getMdp());
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void ajouterClient(Client client) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established or is closed.");
        }
        String query = "INSERT INTO clients (nom, prenom, numero, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setString(3, client.getNumero());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.executeUpdate();
        }
    }

    public void supprimerClient(int clientId) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established or is closed.");
        }
        String query = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientId);
            preparedStatement.executeUpdate();
        }
    }

    public void modifierClient(Client client) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established or is closed.");
        }
        String query = "UPDATE clients SET nom = ?, prenom = ?, numero = ?, email = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setString(3, client.getNumero());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setInt(5, client.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void afficherClients() throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Connection is not established or is closed.");
        }
        String query = "SELECT * FROM clients";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Nom: " + resultSet.getString("nom"));
                System.out.println("Prénom: " + resultSet.getString("prenom"));
                System.out.println("Numéro: " + resultSet.getString("numero"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println();
            }
        }
    }

    public void ajouterArticle(Article article) {
        String sql = "INSERT INTO articles (libellé, description, prix, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection()) {

            if (conn == null || connection.isClosed()) {
                System.err.println("Impossible d'ajouter l'article : connexion à la base de données échouée.");
                return;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, article.getLibelle());
                stmt.setString(2, article.getDescription());
                stmt.setDouble(3, article.getPrix());
                stmt.setInt(4, article.getStock());
                stmt.executeUpdate();
                System.out.println("Article ajouté avec succès.");
                System.out.println(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerArticle(Article article) {
        String sql = "DELETE FROM articles WHERE id = ?";
        try (Connection conn = getConnection()) {
            if (conn == null && connection == null || connection.isClosed()) {
                System.err.println("Impossible de supprimer l'article : connexion à la base de données échouée.");
                return;
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, article.getId());
                stmt.executeUpdate();
                System.out.println("Article supprimé avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherArticles() {
        String sql = "SELECT * FROM articles";
        /*try (Connection conn = getConnection()) {
            System.out.println(connection);
            if (conn == null ) {
                System.err.println("Impossible d'afficher les articles : connexion à la base de données échouée.");
                return;
            }*/
        ConnectionDAO connection = new ConnectionDAO();
        System.out.println(connection.getConnection());
            try (Statement stmt = connection.getConnection().createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String libelle = rs.getString("libellé");
                    String description = rs.getString("description");
                    double prix = rs.getDouble("prix");
                    int stock = rs.getInt("stock");

                    System.out.println("ID: " + id + ", Libellé: " + libelle + ", Description: " + description + ", Prix: " + prix + ", Stock: " + stock);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    /*} catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public void modifierArticle(Article article) {
        String sql = "UPDATE articles SET libellé = ?, description = ?, prix = ?, stock = ? WHERE id = ?";
        try (Connection conn = getConnection()) {
            if (conn == null) {
                System.err.println("Impossible de modifier l'article : connexion à la base de données échouée.");
                return;
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, article.getLibelle());
                stmt.setString(2, article.getDescription());
                stmt.setDouble(3, article.getPrix());
                stmt.setInt(4, article.getStock());
                stmt.setInt(5, article.getId());

                stmt.executeUpdate();
                System.out.println("Article modifié avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
