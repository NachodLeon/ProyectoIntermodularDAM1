#  Proyecto Intermodular – Aplicación de Gestión de Concesionario

##  Módulo: Programación (0485) y MPO Ampliación de Programación

---

## 1. DESCRIPCIÓN DE LA APLICACIÓN

Esta aplicación es un sistema de gestión para un **concesionario de vehículos** que permite:

- Gestionar el inventario de vehículos (coches y motos)
- Registrar ventas y clientes
- Administrar citas del taller
- Consultar información en tiempo real desde una base de datos MySQL

La aplicación está desarrollada en **Java** utilizando **JDBC** para la conexión con la base de datos y sigue los principios de la **Programación Orientada a Objetos (POO)**.

---

## 2. TECNOLOGÍAS UTILIZADAS

| Tecnología | Versión | Uso |
|------------|---------|-----|
| Java | 17+ | Lenguaje de programación |
| JDBC | 4.2+ | Conexión a base de datos |
| MySQL | 8.0 | Base de datos relacional |
| MySQL Connector/J | 9.6.0 | Driver JDBC |

---

## 3. ESTRUCTURA DEL PROYECTO (ARQUITECTURA)

El proyecto sigue una **arquitectura por capas** para separar responsabilidades:

### Capas explicadas:

| Capa | Paquete | Responsabilidad |
|------|---------|-----------------|
| **UI** | `app` | Interfaz de usuario (menú por consola) |
| **Service** | `service` | Lógica de negocio y validaciones |
| **DAO** | `dao` | Acceso a la base de datos (CRUD) |
| **Model** | `vo` | Representación de los datos |

---

## 4. FUNCIONALIDADES IMPLEMENTADAS

### CRUD Completo de Vehículos
| Operación | Método | Descripción |
|-----------|--------|-------------|
| **Create** | `insertar()` | Añadir nuevos vehículos (coches/motos) |
| **Read** | `listarTodos()` | Mostrar todos los vehículos |
| **Read** | `buscarPorId()` | Buscar un vehículo por ID |
| **Update** | `actualizar()` | Modificar datos de un vehículo |
| **Delete** | `eliminar()` | Eliminar un vehículo |

### Consultas Específicas
- Listar vehículos disponibles (no vendidos)
- Ver ventas con detalles (cliente + vehículo)
- Ver citas pendientes del taller
- Contar coches y motos por tipo

### Conexión con Base de Datos (JDBC)
- Conexión a MySQL mediante `DriverManager`
- Ejecución de consultas `SELECT`, `INSERT`, `UPDATE`, `DELETE`
- Uso de `PreparedStatement` para evitar SQL Injection

---

## 5. REQUISITOS PREVIOS

Antes de ejecutar la aplicación, asegúrate de tener:

- [ ] **Java JDK 17 o superior** instalado
- [ ] **MySQL Server 8.0** instalado y en ejecución
- [ ] **Base de datos `concesionario`** creada con las tablas necesarias
- [ ] **MySQL Connector/J** (archivo `.jar`) descargado

---

