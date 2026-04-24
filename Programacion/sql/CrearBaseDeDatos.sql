-- Creamos la base de datos concesionario
CREATE DATABASE concesionario;
USE concesionario;

--Creamos todas las tablas
CREATE TABLE Cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    email VARCHAR(100) UNIQUE,
    direccion VARCHAR(200)
);

CREATE TABLE Vehiculo (
    id_vehiculo INT PRIMARY KEY AUTO_INCREMENT,
    Tipo VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT CHECK (anio BETWEEN 1900 AND 2026),
    precio DECIMAL(10,2) CHECK (precio > 0),
    estado ENUM('nuevo', 'usado') DEFAULT 'usado',
    matricula VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE Venta (
    id_venta INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE DEFAULT (CURRENT_DATE),
    total DECIMAL(10,2),
    id_vehiculo INT NOT NULL,
    id_cliente INT NOT NULL,
    FOREIGN KEY (id_vehiculo) REFERENCES vehiculo(id_vehiculo),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

CREATE TABLE Mecanico (
    id_mecanico INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100)
);

CREATE TABLE CitaTaller (
    id_cita INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    descripcion TEXT,
    id_vehiculo INT NOT NULL,
    id_mecanico INT NOT NULL,
    FOREIGN KEY (id_vehiculo) REFERENCES vehiculo(id_vehiculo),
    FOREIGN KEY (id_mecanico) REFERENCES Mecanico(id_mecanico)
);

--Hacemos los insterts para rellenar las tablas con informacion inventada
INSERT INTO Cliente (nombre, telefono, email, direccion) VALUES
('Juan Pérez', '600111222', 'juan@email.com', 'Calle Mayor 1, Madrid'),
('María Friki ', '600333444', 'maria@email.com', 'Avenida Sol 2, Barcelona'),
('Jairo Jairea', '600555666', 'jairo@email.com', 'Calle Bolonchos 4, Almeria'),
('Candela Chimenea', '600555666', 'candela@email.com', 'Plaza Venezuela 5, Granada'),
('Gonzalo Carnets', '600555666', 'gonzalo@email.com', 'Calle Pa 6, Palencia');

INSERT INTO vehiculo (tipo, marca, modelo, anio, precio, estado, matricula) VALUES
('coche', 'BMW', 'e46', 2014, 12000.00, 'usado', '1234ABC'),
('coche', 'Audi', 'RS3 SportBack', 2023, 57000.00, 'nuevo', '5678DEF'),
('coche', 'Mercedes', 'Clase G', 2022, 66500.00, 'nuevo', '9101GHI'),
('coche', 'Mustang', 'Shelby Cobra', 2017, 45000.00, 'nuevo', '1122JKL'),
('coche', 'Mclaren', '720s', 2024, 145000.00, 'nuevo', '2104MYI'),
('coche', 'Nissan', 'Skyline r34', 2000, 75000.00, 'usado', '9874GFY'),
('coche', 'Mazda', 'RX7 Turbo', 2008, 12000.00, 'usado', '1345SFJ'),
('moto', 'BMW', 'e250', 2021, 9000.00, 'nuevo', '8668KLK'),
('moto', 'Harley', 'Davidson', 2012, 8000.00, 'usado', '1023GHP'),
('moto', 'Kawasaki', 'Ninja H2', 2020, 8350.00, 'usado', '3592LOL');


INSERT INTO Mecanico (nombre, especialidad) VALUES
('jose Luis Fernandez', 'Motor'),
('Midas Alonso', 'Electricidad'),
('David Contreras', 'Carrocería');

INSERT INTO Venta (fecha, total, id_vehiculo, id_cliente) VALUES
('2025-04-01', 18500.00, 1, 1),
('2025-04-05', 22000.00, 2, 2); 

INSERT INTO CitaTaller (fecha, hora, descripcion, id_vehiculo, id_mecanico) VALUES
('2026-05-10', '10:00:00', 'Revisión de frenos', 1, 1),
('2026-05-12', '16:30:00', 'Cambio de aceite', 3, 2);
