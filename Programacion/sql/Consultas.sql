-- 1. vehiculos disponibles.
SELECT * FROM vehiculo 
WHERE id_vehiculo NOT IN (SELECT id_vehiculo FROM Venta);

-- 2. Ventas con datos de cliente y coche.
SELECT v.id_venta, v.fecha, v.total, 
       c.nombre AS cliente, veh.marca, veh.modelo
FROM Venta v
JOIN Cliente c ON v.id_cliente = c.id_cliente
JOIN vehiculo veh ON veh.id_vehiculo = veh.id_vehiculo;

-- 3. Insertar citas.
insert into citaTaller(fecha, hora, descripcion, id_vehiculo, id_mecanico) values("12-04-2023", "13:30", "Revisión de aceite", 7, 2);

-- 4. Coches o motos disponibles.
SELECT COUNT(*) AS total_coches FROM vehiculo WHERE tipo='coche';
SELECT COUNT(*) AS total_motos FROM vehiculo WHERE tipo='moto';

-- 5. Actualizar datos.
UPDATE CitaTaller SET hora = 18:00 WHERE id_cita = 1;


