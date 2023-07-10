drop database if exists qantamail;
create database qantamail;
use qantamail;

CREATE TABLE contacto (
    id_contacto INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) DEFAULT NULL,
    email VARCHAR(300) DEFAULT NULL,
    telefono VARCHAR(10) DEFAULT NULL,
    direccion VARCHAR(300) DEFAULT NULL,
    PRIMARY KEY (id_contacto)
);

CREATE TABLE mensaje (
    id_mensaje INT NOT NULL,
    de VARCHAR(1000) DEFAULT NULL,
    para VARCHAR(1000) DEFAULT NULL,
    cc VARCHAR(1000) DEFAULT NULL,
    cco VARCHAR(1000) DEFAULT NULL,
    asunto VARCHAR(1000) DEFAULT NULL,
    texto TEXT,
    fecha DATETIME DEFAULT NULL,
    estado VARCHAR(120) DEFAULT NULL,
    caracter VARCHAR(6) DEFAULT NULL,
    PRIMARY KEY (id_mensaje)
);

CREATE TABLE usuario (
    nombre_usuario VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);
/*----------------------------------------------------------------------------*/
insert into usuario (nombre_usuario, password) values	('admin', 'admin'),	('usuario', 'usuario');    insert into contacto (id_contacto, nombre, apellido, email, telefono, direccion) values 	(1, 'Ignacio', 'Sanchez', 'sanchezih@gmail.com', '1150992726', 'Av. Siempreviva 742');
