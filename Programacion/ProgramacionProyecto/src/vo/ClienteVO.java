package vo;

public class ClienteVO {
    private int idCliente;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;

    

    public ClienteVO(int idCliente, String nombre, String telefono, String email, String direccion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }
    // Getters y Setters
    public int getId() { return idCliente; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }

    @Override
    public String toString() {
        return String.format("%d - %s (%s)", idCliente, nombre, email);
    }
} 