package vallegrande.edu.pe.misistema.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3308/nutrilacteos";

    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }

    public static void main(String[] args) {
        try (Connection conn = conectar()) {
            System.out.println("Conexión exitosa");
            System.out.println("Base de datos: "
                    + conn.getCatalog());
        } catch (SQLException e) {
            System.err.println("Error de conexión: "
                    + e.getMessage());
        }
    }
}
