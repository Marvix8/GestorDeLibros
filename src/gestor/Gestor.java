package gestor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Gestor extends Util {
	/**
	 * Nombre y ubicación de la base de datos. <br>
	 */
	private static final String NOMBRE_BASE = "./database/database.txt";
	private PrintStream out;
	private Scanner teclado;
	private Vector<Libro> vector;
	private Libro libro;
	private Libro dato;
	private String[] campos;

	/**
	 * Inicializa el gestor de libros. <br>
	 * CC: 2
	 */
	public Gestor() {
		this.out = null;
		this.teclado = new Scanner(System.in, "CP850");
		this.vector = new Vector<Libro>();
		try {
			Scanner entrada = new Scanner(new FileReader(NOMBRE_BASE));
			while (entrada.hasNextLine()) {
				this.campos = entrada.nextLine().split("\t");
				this.libro = new Libro();
				this.libro.setISBN(campos[0]);
				this.libro.setTitulo(campos[1]);
				this.libro.setAutor(campos[2]);
				this.libro.setEditorial(campos[3]);
				this.libro.setEdicion(Integer.parseInt(campos[4]));
				this.libro.setAnioPublicacion(Integer.parseInt(campos[5]));
				this.vector.add(this.libro);
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ejecuta el gestor de libros. <br>
	 */
	public void ejecutar() {
		if (!validarSistemaOperativo()) {
			return;
		}
		int opcion, subopcion = 0;
		int[] contador = { 0 };
		libro = new Libro();
		do {
			menuPrincipal();
			opcion = seleccionarOpcionValida();
			out.println();
			if (vector.isEmpty() && opcion != 1 && opcion != 7) {
				pausar("No hay registros.\n");
				continue;
			}
			if (opcion < 5) {
				this.buscarISBN(contador);
			}
			if (opcion == 1 && this.dato != null) {
				out.println("El registro ya existe.");
			} else {
				if (opcion >= 2 && opcion <= 4 && this.dato == null) {
					out.println("\nRegistro no encontrado.");
				} else {
					this.seleccionMenuPrincipal(contador, opcion, subopcion);
				}
			}
			if (opcion < 7 && opcion >= 1) {
				pausar("");
			}
		} while (opcion != 7);
		teclado.close();
		this.procesarCambios();
	}

	/**
	 * Indica si una opción seleccionada es válida. <br>
	 * CC: 3
	 * 
	 * @return Número de opción. <br>
	 */
	private int seleccionarOpcionValida() {
		int opcion;
		do {
			opcion = leerEntero("Seleccione una opci\u00F3n");
			if (opcion < 1 || opcion > 7)
				out.println("Opci\u00F3nn no v\u00E1lida.");
		} while (opcion < 1 || opcion > 7);
		return opcion;
	}

	/**
	 * Muestra el menú principal del gestor. <br>
	 * CC: 1
	 */
	private void menuPrincipal() {
		out.println("MEN\u00DA");// (9)
		out.println("1.- Altas");
		out.println("2.- Consultas");
		out.println("3.- Actualizaciones");
		out.println("4.- Bajas");
		out.println("5.- Ordenar registros");
		out.println("6.- Listar registros");
		out.println("7.- Salir");
	}

	/**
	 * Selecciona una opción del menú principal. <br>
	 * CC:
	 * 
	 * @param contador
	 * @param opcion
	 * @param subopcion
	 */
	private void seleccionMenuPrincipal(int[] contador, int opcion, int subopcion) {
		switch (opcion) {
		case 1:
			this.nuevoLibro();
			break;
		case 3:
			this.menuModificarLibro(subopcion);
			this.modificarLibro(subopcion);
			break;
		case 4:
			this.vector.remove(dato);
			this.out.println("Registro borrado correctamente.");
			break;
		case 5:
			Collections.sort(vector);
			this.out.println("Registros ordenados correctamente.");
			break;
		case 6:
			contador[0] = 0;
			for (int i = 0; i < this.vector.size(); i++) {
				this.imprimir(vector.get(i), contador);
			}
			this.out.println("Total de registros: " + contador[0] + ".");
			break;
		}
	}

	/**
	 * Busca un libro por su ISBN. <br>
	 * CC: 3 //4
	 * 
	 * @param contador
	 */
	private void buscarISBN(int[] contador) {
		int i;
		this.libro.setISBN(leerCadena("Ingrese el ISBN del libro"));
		i = this.vector.indexOf(this.libro);
		// if (i < 0) {
		// this.dato = null;
		// } else {
		// this.dato = this.vector.get(i);
		// }
		// if (this.dato != null) {
		// this.out.println();
		// this.imprimir(this.dato, contador);
		// }
		if (i < 0) {
			this.dato = null;
			return;
		} else {
			this.dato = this.vector.get(i);
			this.out.println();
			this.imprimir(this.dato, contador);
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
		this.vector.add(this.libro);
		this.libro = new Libro();
		out.println("\nRegistro agregado correctamente.");
	}

	/**
	 * Carga el menú para modificar información sobre un libro. <br>
	 * CC: 3
	 * 
	 * @param subopcion
	 */
	private void menuModificarLibro(int subopcion) {
		this.out.println("Men\u00FA de modificaci\u00F3n de campos");
		this.out.println("1.- titulo");
		this.out.println("2.- autor");
		this.out.println("3.- editorial");
		this.out.println("4.- edicion");
		this.out.println("5.- anno de publicacion");

		do {
			subopcion = this.leerEntero("Seleccione un n\u00FAmero de campo a modificar");
			if (subopcion < 1 || subopcion > 5) {
				this.out.println("Opci\u00F3n no v\u00E1lida.");
			}
		} while (subopcion < 1 || subopcion > 5);
	}

	/**
	 * Modifica información sobre un libro. <br>
	 * CC: 5
	 * 
	 * @param subopcion
	 *            Posición de la modificación a realizar. <br>
	 */
	private void modificarLibro(int subopcion) {
		switch (subopcion) {
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
	 * Valida el sistema operativo. <br>
	 * CC: 2
	 * 
	 * @return <b>true</b> si no es Linux o falla el encoding. <br>
	 *         <b>false</b> de lo contrario. <br>
	 */
	private boolean validarSistemaOperativo() {
		if (!System.getProperties().get("os.name").equals("Linux") && System.console() == null) {
			try {
				out = new PrintStream(System.out, true, "CP850");
				return true;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Procesa los cambios en la base de datos. <br>
	 * CC: 2
	 */
	private void procesarCambios() {
		PrintStream salida;
		try {
			salida = new PrintStream(NOMBRE_BASE);
			for (int i = 0; i < this.vector.size(); i++) {
				this.imprimirEnArchivo(vector.get(i), salida);
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
	 * Imprime los libros por pantalla. <br>
	 * CC: 1
	 */
	@Override
	public void imprimir(Libro libro, int[] contador) {
		contador[0]++;
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
