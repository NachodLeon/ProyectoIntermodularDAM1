package dao; 

import java.sql.Connection;      
import java.sql.DriverManager;  
import java.sql.SQLException;   

public class ConexionBD {

    //URL de conexión a la base de datos.
    private static final String URL = "jdbc:mysql://localhost:3306/concesionario";

    //Usuario
    private static final String USER = "root";
    
    //Contraseña
    private static final String PASSWORD = "mysql"; 

    //Método estático para obtener una conexión.
    public static Connection getConnection() throws SQLException {

        try {
            //Carga el driver de MySQL en memoria.
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Establece la conexión usando la URL, usuario y contraseña. Devuelve un objeto Connection listo para usar.
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            //Si no encuentra el driver, lanza una excepción de tipo SQL.
            throw new SQLException("Driver JDBC no encontrado", e);
        }
    }
}