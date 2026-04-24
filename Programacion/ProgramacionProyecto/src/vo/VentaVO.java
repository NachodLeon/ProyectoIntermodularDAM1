package vo;

import java.time.LocalDate;

public class VentaVO {
    private int id;
    private LocalDate fecha;
    private double total;
    private VehiculoVO Vehiculo;
    private ClienteVO cliente;
    
    public VentaVO(int id, LocalDate fecha, double total, VehiculoVO vehiculo, ClienteVO cliente) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        Vehiculo = vehiculo;
        this.cliente = cliente;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public VehiculoVO getVehiculo() {
        return Vehiculo;
    }
    public void setVehiculo(VehiculoVO vehiculo) {
        Vehiculo = vehiculo;
    }
    public ClienteVO getCliente() {
        return cliente;
    }
    public void setCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    
    @Override
    public String toString() {
        return "VentaVO [id=" + id + ", fecha=" + fecha + ", total=" + total + ", Vehiculo=" + Vehiculo + ", cliente="
                + cliente + "]";
    }
    
} 