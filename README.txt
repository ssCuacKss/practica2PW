PW Práctica 2:
	-Carlos Lucena Robles.
	-Miguel Raigón Jiménez.
	-Pablo Roldán Puebla.
	-Paloma Romero Delgado.
	-Kamal Abdelkader Haddu.

EJECUCION
-----------------------
Para la ejecución del programa abrimos una terminal en la carpeta donde se encuentra el .jar y lo movemos hacia la carpeta "ProyectoPW":
	mv p2.jar ProyectoPW/ 
	
Nos movemos a la carpeta
	cd ProyectoPW
	
Una vez movido el .jar, para ejecutar el programa escribimos en una terminal:
	java -jar p2.jar
	
-----------------------


EXPLICACION PROGRAMA
------------------------

Por consola se muestra un menú en el que elegiremos el tipo de gestor.
Una vez elegido uno de ellos, se muestra otro menú con las funcionalidades pertinentes del gestor escogido.
Por tanto, tendremos tres menús diferentes según el tipo de gestor seleccionado, Ademas de un menu adicional
para la modificación de usuarios

Los gestores accederan a una base de datos para realizar sus funciones

Menú principal:

	|/////////////////////////////////////|
	            MENU PRINCIPAL
	|/////////////////////////////////////|
		1 - Gestion de Usuarios
		2 - Gestion de Pistas
		3 - Gestion de Reservas
        0 - Salir
    |/////////////////////////////////////|
    Introduce una opción (0-3): 


Menú Gestor Usuarios:

	|/////////////////////////////////////|
	           MENU DE USUARIOS
	|/////////////////////////////////////|
		1 - Alta de un usuario
		2 - Modificar un usuario
		3 - Eliminar un usuario
        4 - Buscar usuario por correo
        5 - Lista de usuarios
        0 - Volver al menu principal
    |/////////////////////////////////////|
    Introduce una opción (0-5): 

Menú Modificar Usuario:

	|/////////////////////////////////////|
	   MENU DE MODIFICACION DE USUARIOS
	|/////////////////////////////////////|
		1 - Modificar usuario completo
		2 - Modificar nombre
		3 - Modificar apellidos
        4 - Modificar fecha de nacimiento
        5 - Modificar fecha de inscripcion
        0 - Volver al menu de gestion de usuarios
    |/////////////////////////////////////|
    Introduce una opción (0-5): 

Menú Gestor Pistas:

	|/////////////////////////////////////|
	            MENU DE PISTAS
	|/////////////////////////////////////|
		1 - Crear karts
		2 - Crear pistas
		3 - Asociar kart a pista
        4 - Listar pistas en mantenimiento
        5 - Listar pistas disponibles
        6 - Listar karts
        0 - Volver al menu principal
    |/////////////////////////////////////|
    Introduce una opción (0-6): 

Menú Gestor Reservas:

	|/////////////////////////////////////|
	            MENU DE RESERVAS
	|/////////////////////////////////////|
		1 - Reserva individual
		2 - Reserva bono
		3 - Listar reservas futuras
        4 - Listar reservas de pistas por fecha
        5 - Cancelar reserva
        6 - Cancelar bono
        0 - Volver al menu principal
    |/////////////////////////////////////|
    Introduce una opción (0-6): 


