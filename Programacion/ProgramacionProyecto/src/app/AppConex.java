package app;

import dao.ConsultasDAO;
import dao.ConexionBD;
import vo.VehiculoVO;
import vo.VentaVO;
import vo.CitaTallerVO;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;



public class AppConex {

    private static ConsultasDAO consultasDAO = new ConsultasDAO();

    public static void mostrarMenu(){
        System.out.println("MENÚ PRINCIPAL.");
        System.out.println("1. Ver vehículos disponibles.");
        System.out.println("2. Ver ventas con detalles.");
        System.out.println("3. Modificar cita del taller");
        System.out.println("4. Contar coches y motos.");
        System.out.println("5. Actualizar datos.");
        System.out.println("6. Salir.");
        System.out.println("Seleccione una opción: ");
    }

    public static void mostrarVehiculosDisponibles(){
        System.out.println("VEHÍCULOS DISPONIBLES.");
        
        try (Connection conex = ConexionBD.getConnection()) {
            System.out.println("Conexión realizada con éxito.");
            
            List<VehiculoVO> vehiculos = consultasDAO.vehiculosDisponibles(conex);
            
            if (vehiculos.isEmpty()) {
                System.out.println("No hay vehículos disponibles.");
            } else {
                
                for (VehiculoVO v : vehiculos) {
                    System.out.println("Id: " + v.getIdVehiculo() + " " + 
                                       "Tipo: " + v.getTipo() + " " +
                                      "Marca: " + v.getMarca() + " " +
                                      "Modelo: " + v.getModelo() + " " +
                                       "Año: " + v.getAnio() + " " +
                                       "Precio: " + v.getPrecio() + " " +
                                       "Estado: " + v.getEstado() + " " +
                                       "Matricula: " + v.getMatricula());
                }
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void mostrarVentasConDetalles(){
        System.out.println("VENTAS REALIZADAS.");
        
        try (Connection conex = ConexionBD.getConnection()) {
            System.out.println("Conexión realizada con éxito.");
            
            List<VentaVO> ventas = consultasDAO.listarVentasConDetalles(conex);
            
            if (ventas.isEmpty()) {
                System.out.println("No hay ventas registradas.");
            } else {
                for (VentaVO venta : ventas) {
                    System.out.println("VENTA " + venta.getId() + "");
                    System.out.println("Fecha: " + venta.getFecha());
                    System.out.println("Total: " + venta.getTotal() + "€");
                    System.out.println("Cliente: " + venta.getCliente().getNombre());
                    System.out.println("Teléfono: " + venta.getCliente().getTelefono());
                    System.out.println("Email: " + venta.getCliente().getEmail());
                    System.out.println("Dirección: " + venta.getCliente().getDireccion());
                    System.out.println("Vehículo: " + venta.getVehiculo().getMarca() + "" + 
                                     venta.getVehiculo().getModelo() + "(" + venta.getVehiculo().getMatricula() + ")");
                    System.out.println("Precio: " + venta.getVehiculo().getPrecio() + "€");
                }
            }
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    public static void insertarCita() {
        // En App.java - Añadir esta opción en el menú
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n=== INSERTAR NUEVA CITA ===");
        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        
        System.out.print("Hora (HH:MM:SS): ");
        String hora = sc.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        
        System.out.print("ID del vehículo: ");
        int idVehiculo = sc.nextInt();
        
        System.out.print("ID del mecánico: ");
        int idMecanico = sc.nextInt();
        
        ConsultasDAO.insertarCita(fecha, hora, descripcion, idVehiculo, idMecanico);
    }
    

    public static void contarVehiculosPorTipo() {
        System.out.println("CONTEO DE VEHÍCULOS.");
        
        try (Connection conex = ConexionBD.getConnection()) {
            System.out.println("Conexión realizada con éxito.");
            consultasDAO.contarVehiculosPorTipo(conex);
        } catch (Exception e) {
            System.out.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void actualizarDatos() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== ACTUALIZAR HORA DE CITA ===");
        
        // Primero mostrar las citas existentes
        ConsultasDAO.actualizarDatos(0, null);
        
        System.out.print("\nIngrese el ID de la cita a modificar: ");
        int idCita = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        
        System.out.print("Ingrese la nueva hora (HH:MM:SS): ");
        String nuevaHora = sc.nextLine();
        
        // Confirmar la actualización
        System.out.print("¿Está seguro de que desea actualizar la hora? (S/N): ");
        String confirmacion = sc.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            boolean resultado = ConsultasDAO.actualizarDatos(idCita, nuevaHora);
            if (resultado) {
                System.out.println("La cita ha sido actualizada correctamente.");
            } else {
                System.out.println("No se pudo actualizar la cita. Verifique el ID.");
            }
        } else {
            System.out.println("Actualización cancelada.");
        }
        sc.close();
}
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    mostrarVehiculosDisponibles();
                    break;
                case 2:
                    mostrarVentasConDetalles();
                    break;
                case 3:
                    insertarCita();
                    break;
                case 4:
                    contarVehiculosPorTipo();
                    break;
                case 5:
                    actualizarDatos();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intentelo de nuevo.");
            }
            
        } while (opcion != 5);
        
        sc.close();
    }
}