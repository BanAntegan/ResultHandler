package by.gsu.epamlab.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection cn;
    private String url = "jdbc:mysql://127.0.0.1:3306/start?autoReconnect=true&useSSL=false&serverTimezone=Europe/Minsk";
    private String user = "root";
    private String password = "An19991608";

    public DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.cn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Database connection creation failed : " + e.getMessage());
        }
    }

    public Connection getCn() {
        return cn;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getCn().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
