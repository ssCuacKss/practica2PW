/*
 * SCRIPT INSERTAR VALORES FICTIOS
 */

-- BORRADO DE TABLAS INICIAL
TRUNCATE KART;
TRUNCATE PISTA;
TRUNCATE USUARIO;

-- TABLA PISTAS
INSERT INTO PISTA(nombre, estado, dificultad, max_karts, asoc_karts) VALUES ('P1', true, 'INFANTIL', 10, 5);
INSERT INTO PISTA(nombre, estado, dificultad, max_karts, asoc_karts) VALUES ('P2', true, 'FAMILIAR', 20, 8);
INSERT INTO PISTA(nombre, estado, dificultad, max_karts, asoc_karts) VALUES ('P3', true, 'ADULTOS', 15, 4);


-- TABLA KARTS

INSERT INTO KART(tipo, estado, pista_asociada) VALUES (true,'DISPONIBLE', 'P1');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (true,'DISPONIBLE', 'P1');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (true,'DISPONIBLE', 'P1');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (true,'DISPONIBLE', 'P1');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (true,'DISPONIBLE', 'P1');

INSERT INTO KART(tipo, estado, pista_asociada) VALUES (false,'DISPONIBLE', 'P2');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (false,'DISPONIBLE', 'P2');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (true,'DISPONIBLE', 'P2');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (true,'DISPONIBLE', 'P2');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (true,'DISPONIBLE', 'P2');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (false,'DISPONIBLE', 'P2');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (true,'DISPONIBLE', 'P2');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (false,'DISPONIBLE', 'P2');

INSERT INTO KART(tipo, estado, pista_asociada) VALUES (false,'DISPONIBLE', 'P3');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (false,'DISPONIBLE', 'P3');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (false,'DISPONIBLE', 'P3');
INSERT INTO KART(tipo, estado, pista_asociada) VALUES (false,'DISPONIBLE', 'P3');

INSERT INTO KART(tipo, estado) VALUES (true,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (true,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (true,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (true,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (true,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (true,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (true,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (true,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (true,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (true,'DISPONIBLE');

INSERT INTO KART(tipo, estado) VALUES (false,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (false,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (false,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (false,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (false,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (false,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (false,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (false,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (false,'DISPONIBLE');
INSERT INTO KART(tipo, estado) VALUES (false,'DISPONIBLE');


INSERT INTO KART(tipo, estado) VALUES (true,'MANTENIMIENTO');
INSERT INTO KART(tipo, estado) VALUES (true,'MANTENIMIENTO');
INSERT INTO KART(tipo, estado) VALUES (true,'MANTENIMIENTO');
INSERT INTO KART(tipo, estado) VALUES (true,'MANTENIMIENTO');
INSERT INTO KART(tipo, estado) VALUES (true,'MANTENIMIENTO');

INSERT INTO KART(tipo, estado) VALUES (false,'MANTENIMIENTO');
INSERT INTO KART(tipo, estado) VALUES (false,'MANTENIMIENTO');
INSERT INTO KART(tipo, estado) VALUES (false,'MANTENIMIENTO');
INSERT INTO KART(tipo, estado) VALUES (false,'MANTENIMIENTO');
INSERT INTO KART(tipo, estado) VALUES (false,'MANTENIMIENTO');


-- TABLA USUARIOS

INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('jpp@gmail.com', 'Jose', 'Perez Perez', '23/02/1982');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('lavafe@gmail.com', 'Laura', 'Valenzuela Ferrer', '09/09/1975');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('jimsanped@yahoo.es', 'Pedro', 'Jimenez Santos', '12/09/1997');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('martita00@hotmail.com', 'Marta', 'Sanchez Rodriguez', '28/01/2000');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('expositoeam@yahoo.es', 'Ana María', 'Expósito Escudero', '30/07/1992');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('anfernandez@outlook.com', 'Alberto', 'Nuñez Fernandez', '14/05/1990');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('karolop90@hotmail.com', 'Carolina', 'Lopez Muriel', '19/11/1988');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('morapa@gmail.es', 'Pablo', 'Morales Raigan', '25/03/1969');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('manuelmedinalv@gmail.com', 'Manuel', 'Medina Alvarez', '01/01/1992');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('louisecave@yahoo.es', 'Luisa', 'Cano Vega', '05/06/1981');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('alvadigo@outlook.es', 'Alvaro', 'Diaz Gomez', '29/08/1999');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('esomen@gmail.com', 'Estrella', 'Ortiz Mengual', '23/01/1953');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('he2men@yahoo.es', 'Hector', 'Mendoza Hernandez', '14/10/1986');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('andrescaracruz@yahoo.es', 'Andres', 'Carrasco Cruz', '11/09/1992');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('hoyosmalu@hotmail.com', 'Lucia', 'Hoyos Martin', '31/03/1998');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('hoyosmama@hotmail.es', 'Martin', 'Hoyos Martin', '01/03/1996');
INSERT INTO USUARIO (correo, nombre, apellidos, fecha_Nacimiento) VALUES ('hoyosmaj@hotmail.com', 'Jose', 'Hoyos Martin',  '22/01/1993');  


