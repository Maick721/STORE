/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoitq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
    
    // Cambia estos valores según tu configuración de base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

