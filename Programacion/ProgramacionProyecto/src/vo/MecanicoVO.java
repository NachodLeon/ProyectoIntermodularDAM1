// MecanicoVO.java
package vo;

public class MecanicoVO {
    private int idMecanico;
    private String nombre;
    private String especialidad;
    public MecanicoVO(int idMecanico, String nombre, String especialidad) {
        this.idMecanico = idMecanico;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
    public int getIdMecanico() {
        return idMecanico;
    }
    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    @Override
    public String toString() {
        return "MecanicoVO [idMecanico=" + idMecanico + ", nombre=" + nombre + ", especialidad=" + especialidad + "]";
    }
    
    
}