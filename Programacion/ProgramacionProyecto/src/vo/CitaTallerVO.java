package vo;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaTallerVO {
    private int idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;
    private VehiculoVO vehiculo;
    private MecanicoVO mecanico;
    public CitaTallerVO(int idCita, LocalDate fecha, LocalTime hora, String descripcion, VehiculoVO vehiculo,
            MecanicoVO mecanico) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.vehiculo = vehiculo;
        this.mecanico = mecanico;
    }
    public int getIdCita() {
        return idCita;
    }
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public VehiculoVO getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(VehiculoVO vehiculo) {
        this.vehiculo = vehiculo;
    }
    public MecanicoVO getMecanico() {
        return mecanico;
    }
    public void setMecanico(MecanicoVO mecanico) {
        this.mecanico = mecanico;
    }
    @Override
    public String toString() {
        return "CitaTallerVO [idCita=" + idCita + ", fecha=" + fecha + ", hora=" + hora + ", descripcion=" + descripcion
                + ", vehiculo=" + vehiculo + ", mecanico=" + mecanico + "]";
    }
    
    
}