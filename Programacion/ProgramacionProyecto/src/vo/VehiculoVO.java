package vo;

public class VehiculoVO {
    private int idVehiculo;
    private String tipo;
    private String marca;
    private String modelo;
    private int anio;
    private double precio;
    private String estado;
    private String matricula;
    
   
    public VehiculoVO(int idVehiculo, String tipo, String marca, String modelo, int anio, double precio, String estado,
            String matricula) {
        this.idVehiculo = idVehiculo;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.precio = precio;
        this.estado = estado;
        this.matricula = matricula;
    }
    public int getIdVehiculo() {
        return idVehiculo;
    }
    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    @Override
    public String toString() {
        return "VehiculoVO [idVehiculo=" + idVehiculo + ", tipo=" + tipo + ", marca=" + marca + ", modelo=" + modelo
                + ", anio=" + anio + ", precio=" + precio + ", estado=" + estado + ", matricula=" + matricula + "]";
    }
    
 
}