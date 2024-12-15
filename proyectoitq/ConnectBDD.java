/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoitq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectBDD {

    public ConnectBDD() {
        //Definición de Url de conexión el nombre del usuario y la password
        String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=WOFSTORE;"
                + "encrypt=true;trustServerCertificate=true;"
                + "integratedSecurity=true";
      
        try {
            //Cargar el controlador jdbc de sql server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //Establecer conexión con la ddbb
            Connection connection = DriverManager.getConnection(jdbcURL);

            //Control de conexión existosa
            if (connection != null) {
                System.out.println("Conexión exitosa....\n");
                connection.close();
            } 
        }
        catch (ClassNotFoundException e){
           System.out.println("Error al cargar el controlador jdbc: " + e.getMessage());
       }
        
      catch (SQLException e){
          System.out.println("Error de SQL: " + e.getMessage());
      }
    }
    public static void main (String[] args ){
        new ConnectBDD();
    }
}
