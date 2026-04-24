CLIENTE (
    id_cliente INT PK AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    direccion VARCHAR(200)
)

VEHICULO (
    id_vehiculo INT PK AUTO_INCREMENT,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT (4),
    precio DECIMAL(10,2) ,
    estado ENUM('nuevo', 'usado') DEFAULT 'usado',
    matricula VARCHAR(20) UNIQUE NOT NULL
)

VENTA (
    id_venta INT PK AUTO_INCREMENT,
    fecha DATE DEFAULT CURRENT_DATE,
    total DECIMAL(10,2),
    id_vehiculo INT NOT NULL,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_vehiculo) REFERENCES VHEICULO(id_vehiculo),
    FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente)
)

MECANICO (
    id_mecanico INT PK AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100)
)

CITA_TALLER (
    id_cita INT PK AUTO_INCREMENT,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    descripcion TEXT,
    id_vehiculo INT NOT NULL,
    id_mecanico INT NOT NULL,
    FOREIGN KEY (id_vehiculo) REFERENCES VEHICULO(id_vheiculo),
    FOREIGN KEY (id_mecanico) REFERENCES MECANICO(id_mecanico)
)
