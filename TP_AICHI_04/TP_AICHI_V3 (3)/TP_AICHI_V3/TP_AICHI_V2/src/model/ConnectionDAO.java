package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
    private Connection connection;
    public ConnectionDAO() {
        try {
            // Connection à MySQL
            String url = "jdbc:mysql://mysql-momo.alwaysdata.net/momo_tpaichi";
            String username = "momo_utlisateur";
            String password = "Je123pas?";

            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url,
                    username, password);
        } catch (SQLException e) { e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
