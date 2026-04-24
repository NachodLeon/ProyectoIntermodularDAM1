package dao;

import vo.VehiculoVO;
import vo.ClienteVO;
import vo.VentaVO;
import vo.MecanicoVO;
import vo.CitaTallerVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultasDAO {

    // 1. Vehículos disponibles (no vendidos)
    public List<VehiculoVO> vehiculosDisponibles(Connection conn) {
        List<VehiculoVO> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM Vehiculo WHERE id_vehiculo NOT IN (SELECT id_vehiculo FROM Venta)";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                VehiculoVO vehiculo = new VehiculoVO(
                    rs.getInt("id_vehiculo"),
                    rs.getString("tipo"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("anio"),
                    rs.getDouble("precio"),
                    rs.getString("estado"),
                    rs.getString("matricula")
                );
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    // 2. Ventas con datos de cliente y vehículo
    public List<VentaVO> listarVentasConDetalles(Connection conn) {
        List<VentaVO> ventas = new ArrayList<>();
        String sql = "SELECT v.id_venta, v.fecha, v.total, " +
                     "c.id_cliente, c.nombre, c.telefono, c.email, c.direccion, " +
                     "veh.id_vehiculo, veh.tipo, veh.marca, veh.modelo, veh.anio, veh.precio, veh.estado, veh.matricula " +
                     "FROM Venta v " +
                     "JOIN Cliente c ON v.id_cliente = c.id_cliente " +
                     "JOIN Vehiculo veh ON v.id_vehiculo = veh.id_vehiculo";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                ClienteVO cliente = new ClienteVO(
                    rs.getInt("id_cliente"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion")
                );
                
                VehiculoVO vehiculo = new VehiculoVO(
                    rs.getInt("id_vehiculo"),
                    rs.getString("tipo"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("anio"),
                    rs.getDouble("precio"),
                    rs.getString("estado"),
                    rs.getString("matricula")
                );
                
                VentaVO venta = new VentaVO(
                    rs.getInt("id_venta"),
                    rs.getDate("fecha").toLocalDate(),
                    rs.getDouble("total"),
                    vehiculo,
                    cliente
                );
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    // 3. Insertar cita 
    public static boolean insertarCita(String fecha, String hora, String descripcion, int idVehiculo, int idMecanico) {
    String sql = "INSERT INTO CitaTaller (fecha, hora, descripcion, id_vehiculo, id_mecanico) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = ConexionBD.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, fecha); 
        pstmt.setString(2, hora);   
        pstmt.setString(3, descripcion);
        pstmt.setInt(4, idVehiculo);
        pstmt.setInt(5, idMecanico);
        
        int filasInsertadas = pstmt.executeUpdate();
        
        if (filasInsertadas > 0) {
            System.out.println("Cita insertada correctamente.");
            return true;
        } else {
            System.out.println("No se pudo insertar la cita.");
            return false;
        }
        
    } catch (SQLException e) {
        System.out.println("Error al insertar la cita: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

    // 4. Contar coches y motos
    public int contarVehiculosPorTipo(Connection conn) {
        String sqlCoches = "SELECT COUNT(*) AS total_coches FROM Vehiculo WHERE tipo='coche'";
        String sqlMotos = "SELECT COUNT(*) AS total_motos FROM Vehiculo WHERE tipo='moto'";
        
        try (Statement stmt1 = conn.createStatement();
             ResultSet rs1 = stmt1.executeQuery(sqlCoches)) {
            
            if (rs1.next()) {
                System.out.println("Total coches: " + rs1.getInt("total_coches"));
            }
            
            try (Statement stmt2 = conn.createStatement();
                 ResultSet rs2 = stmt2.executeQuery(sqlMotos)) {
                if (rs2.next()) {
                    System.out.println("Total motos: " + rs2.getInt("total_motos"));
                }

                return rs1.getInt("total_coches")+ rs2.getInt("total_motos");
            }
            catch(SQLException e){
                e.printStackTrace();
                return rs1.getInt("total_coches");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    // 5. Actualizar datos

    public static boolean actualizarDatos(int idCita, String nuevaHora) {
    String sql = "UPDATE CitaTaller SET hora = ? WHERE id_cita = ?";
    
    try (Connection conn = ConexionBD.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, nuevaHora);
        pstmt.setInt(2, idCita);
        
        int filasActualizadas = pstmt.executeUpdate();
        
        if (filasActualizadas > 0) {
            System.out.println("Hora de la cita actualizada correctamente.");
            return true;
        } else {
            System.out.println("No se encontró ninguna cita con el ID: " + idCita);
            return false;
        }
        
    } catch (SQLException e) {
        System.out.println("Error al actualizar la hora: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
}