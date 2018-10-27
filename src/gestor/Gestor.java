package gestor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

/**
 * Clase que administra un gestor de libros. <br>
 */
public class Gestor extends Util {
	/**
	 * Nombre y ubicación de la base de datos. <br>
	 */
	private static final String NOMBRE_BASE = "./database/database.txt";
	/**
	 * Encoding a utilizar. <br>
	 */
	private static final String ENCODING = "CP850";
	/**
	 * Salida de mensajes del gestor. <br>
	 */
	private PrintStream out;
	/**
	 * Entrada de usuario. <br>
	 */
	private Scanner teclado;
	/**
	 * Libros en la base de datos. <br>
	 */
	private Vector<Libro> libros;
	private Libro libro, dato;
	/**
	 * Actualmente no se usa, pero se deja para los métodos. <br>
	 */
	private int[] contador = new int[1];

	/**
	 * Crea un gestor de libros. <br>
	 * CC: 1
	 */
	public Gestor() {
		this.teclado = new Scanner(System.in, ENCODING);
		this.libros = new Vector<Libro>();
		this.contador[0] = 0;
	}

	/**
	 * Controla que la base no se encuentre vacia y que la opción solicitada no
	 * se vea afectado por esto. <br>
	 * 
	 * @param opcion
	 *            Opción seleccionada por el usuario. <br>
	 * @return <b>true</b> si no puede continuar. <br>
	 *         <b>false</b> si puede continuar. <br>
	 */
	private boolean controlarBaseVacia(final int opcion) {
		if (this.libros.isEmpty() && opcion != 1 && opcion != 7) {
			return true;
		}
		return false;
	}

	/**
	 * Controla que un registro exista para ciertas opciones. <br>
	 * 
	 * @param opcion
	 *            Opción seleccionada por el usuario. <br>
	 * @return <b>true</b> si no existe. <br>
	 *         <b>false</b> si existe. <br>
	 */
	private boolean registroInexitente(final int opcion) {
		if (opcion >= 2 && opcion <= 4 && this.dato == null) {
			return true;
		}
		return false;
	}

	/**
	 * Ejecuta el gestor de libros. <br>
	 * CC: 9
	 */
	public void ejecutar() {
		this.controlarSalidaPorConsola();
		this.leeerLibrosEnBase();
		int opcion;
		this.libro = new Libro();
		do {
			this.cargarMenuPrincipal();
			opcion = this.leerOpcionUsuario();
			this.out.println();
			if (this.controlarBaseVacia(opcion)) {
				pausar("No hay registros.\n");
				// Si no hay registros no se puede hacer nada más que agregar.
				continue;
			}
			// Para agregar, actualizar, consultar o dar de baja debo tener el
			// ISBN.
			if (opcion < 5) {
				this.buscarLibroPorISBN();
			}
			if (opcion == 1 && this.dato != null) {
				out.println("El registro ya existe.");
			} else {
				if (this.registroInexitente(opcion)) {
					out.println("\nRegistro no encontrado.");
				} else {
					// Si ya no tengo los casos que puede salir realizo la
					// operación.
					this.seleccionMenuPrincipal(opcion);
				}
			}
			if (opcion < 7 && opcion >= 1) {
				this.pausar("");
			}
		} while (opcion != 7);
		teclado.close();
		this.procesarCambios();
	}

	/**
	 * Lee los libros en base. <br>
	 * CC: 2
	 */
	private void leeerLibrosEnBase() {
		try {
			Libro libro;
			String[] campos;
			Scanner entrada = new Scanner(new FileReader(NOMBRE_BASE));
			while (entrada.hasNextLine()) {
				libro = new Libro();
				campos = entrada.nextLine().split("\t");
				libro.setISBN(campos[0]);
				libro.setTitulo(campos[1]);
				libro.setAutor(campos[2]);
				libro.setEditorial(campos[3]);
				libro.setEdicion(Integer.parseInt(campos[4]));
				libro.setAnioPublicacion(Integer.parseInt(campos[5]));
				this.libros.add(libro);
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Controla la salida por consola. <br>
	 * CC: 2
	 */
	private void controlarSalidaPorConsola() {
		// Si no es un SO Linux o si no posee algúna consola para mostrar define
		// la salida por consola.
		if (!System.getProperties().get("os.name").equals("Linux") && System.console() == null) {
			try {
				this.out = new PrintStream(System.out, true, ENCODING);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Lee una opción solicitada por el usuario. <br>
	 * CC: 2
	 * 
	 * @return Número de opción. <br>
	 */
	private int leerOpcionUsuario() {
		int opcion = leerEntero("Seleccione una opción");
		while (opcion < 1 || opcion > 7) {
			this.out.println("Opción no válida.");
			opcion = leerEntero("Seleccione una opción");
		}
		return opcion;
	}

	/**
	 * Muestra el menú principal del gestor. <br>
	 * CC: 1
	 */
	private void cargarMenuPrincipal() {
		this.out.println("MENÚ");
		this.out.println("1.- Altas");
		this.out.println("2.- Consultas");
		this.out.println("3.- Actualizaciones");
		this.out.println("4.- Bajas");
		this.out.println("5.- Ordenar registros");
		this.out.println("6.- Listar registros");
		this.out.println("7.- Salir");
	}

	/**
	 * Selecciona una opción del menú principal. <br>
	 * CC: 5
	 * 
	 * @param opcion
	 *            Opción seleccionada por el usuario. <br>
	 */
	@SuppressWarnings("unchecked")
	private void seleccionMenuPrincipal(int opcion) {
		switch (opcion) {
		case 1:
			this.nuevoLibro();
			break;
		case 3:
			this.modificarLibro();
			break;
		case 4:
			this.libros.remove(this.dato);
			this.out.println("Registro borrado correctamente.");
			break;
		case 5:
			Collections.sort(this.libros);
			this.out.println("Registros ordenados correctamente.");
			break;
		case 6:
			this.mostrarLibros();
			break;
		}
	}

	/**
	 * Muestra los libros en la base. <br>
	 * CC: 2
	 */
	private void mostrarLibros() {
		for (int i = 0; i < this.libros.size(); i++) {
			this.imprimir(this.libros.get(i), this.contador);
		}
		this.out.println("Total de registros: " + this.libros.size() + ".");
	}

	/**
	 * Busca un libro por su ISBN. <br>
	 * CC: 2
	 */
	private void buscarLibroPorISBN() {
		int i;
		this.libro.setISBN(leerCadena("Ingrese el ISBN del libro"));
		i = this.libros.indexOf(this.libro);
		if (i < 0) {
			this.dato = null;
		} else {
			this.dato = this.libros.get(i);
			this.out.println();
			this.imprimir(this.dato, this.contador);
		}
	}

	/**
	 * Carga un nuevo libro. <br>
	 * CC: 1
	 */
	private void nuevoLibro() {
		this.libro.setTitulo(leerCadena("Ingrese el titulo"));
		this.libro.setAutor(leerCadena("Ingrese el autor"));
		this.libro.setEditorial(leerCadena("Ingrese el editorial"));
		this.libro.setEdicion(leerEntero("Ingrese el edicion"));
		this.libro.setAnioPublicacion(leerEntero("Ingrese el año de publicacion"));
		this.libros.add(this.libro);
		this.libro = new Libro();
		this.out.println("\nRegistro agregado correctamente.");
	}

	/**
	 * Carga el menú para modificar información sobre un libro. <br>
	 * CC: 2
	 */
	private int obtenerOpcionModificacion() {
		this.out.println("Menú de modificación de campos");
		this.out.println("1.- titulo");
		this.out.println("2.- autor");
		this.out.println("3.- editorial");
		this.out.println("4.- edicion");
		this.out.println("5.- año de publicacion");
		int subopcion = this.leerEntero("Seleccione un número de campo a modificar");
		while (subopcion < 1 || subopcion > 5) {
			this.out.println("Opción no válida.");
			subopcion = this.leerEntero("Seleccione un número de campo a modificar");
		}
		return subopcion;
	}

	/**
	 * Modifica información sobre un libro. <br>
	 * CC: 5
	 */
	private void modificarLibro() {
		switch (this.obtenerOpcionModificacion()) {
		case 1:
			this.dato.setTitulo(leerCadena("Ingrese el nuevo titulo"));
			break;
		case 2:
			this.dato.setAutor(leerCadena("Ingrese el nuevo autor"));
			break;
		case 3:
			this.dato.setEditorial(leerCadena("Ingrese el nuevo editorial"));
			break;
		case 4:
			this.dato.setEdicion(leerEntero("Ingrese el nuevo edicion"));
			break;
		case 5:
			this.dato.setAnioPublicacion(leerEntero("Ingrese el nuevo año publicacion"));
			break;
		}
		this.out.println("\nRegistro actualizado correctamente.");
	}

	/**
	 * Procesa los cambios en la base de datos. <br>
	 * CC: 2
	 */
	private void procesarCambios() {
		PrintStream salida;
		try {
			salida = new PrintStream(NOMBRE_BASE);
			for (int i = 0; i < this.libros.size(); i++) {
				this.imprimirEnArchivo(libros.get(i), salida);
			}
			salida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lee un entero desde consola. <br>
	 * CC: 1
	 */
	@SuppressWarnings("resource")
	@Override
	public Integer leerEntero(String mensaje) {
		System.out.println(mensaje);
		return new Scanner(System.in).nextInt();
	}

	/**
	 * Lee una cadena desde consola. <br>
	 * CC: 1
	 */
	@SuppressWarnings("resource")
	@Override
	public String leerCadena(String mensaje) {
		System.out.println(mensaje);
		return new Scanner(System.in).nextLine();
	}

	/**
	 * Pausa la ejecucción del gestor. <br>
	 * CC: 1
	 */
	@Override
	public void pausar(String mensaje) {
		System.out.println(mensaje);
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Muestra los libros por pantalla. <br>
	 * CC: 1
	 */
	@Override
	public void imprimir(Libro libro, int[] contador) {
		System.out.println(libro.getISBN() + "\t" + libro.getTitulo() + "\t" + libro.getAutor() + "\t"
				+ libro.getEditorial() + "\t" + libro.getEdicion() + "\t" + libro.getAnioPublicacion());
	}

	/**
	 * Graba los libros en archivo. <br>
	 * CC: 1
	 */
	@Override
	public void imprimirEnArchivo(Libro libro, PrintStream salida) {
		salida.println(libro.getISBN() + "\t" + libro.getTitulo() + "\t" + libro.getAutor() + "\t"
				+ libro.getEditorial() + "\t" + libro.getEdicion() + "\t" + libro.getAnioPublicacion());
	}
}
