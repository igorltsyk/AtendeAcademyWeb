package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    private static final String URL = "jdbc:mysql://localhost:3306/atendeacademy";
    private static final String USER = "gustavoizidio";
    private static final String PASSWORD = "teste123456!";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConexaoMySQL() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
