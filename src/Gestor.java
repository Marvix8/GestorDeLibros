import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Gestor extends Util {

	private PrintStream out;
	private Scanner teclado;
	private Scanner entrada;
	private Vector<Libro> vector;
	private Libro libro;
	private Libro dato;
	private String[] campos;
	
	public Gestor() {
		out = null;
		teclado = new Scanner(System.in, "CP850");
		vector = new Vector<Libro>();
		
		try {
			entrada = new Scanner(new FileReader("./database/database.txt"));
			while (entrada.hasNextLine()) {
				campos = entrada.nextLine().split("\t"); 
				libro = new Libro();
				libro.setISBN(campos[0]);
				libro.setTitulo(campos[1]);
				libro.setAutor(campos[2]);
				libro.setEditorial(campos[3]);
				libro.setEdicion(Integer.parseInt(campos[4]));
				libro.setAnioPublicacion(Integer.parseInt(campos[5]));
				vector.add(libro);
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void ejecutar() {
		if(!validarSistemaOperativo()) {
			return;
		}
				
		int opcion, 
			subopcion = 0;
		
		int[] contador = {0};
		
		libro = new Libro();
		
		do {
			menuPrincipal();
			opcion = seleccionarOpcionValida();
			out.println(); 
			
			if (vector.isEmpty() && opcion != 1	&& opcion != 7) {
				pausar("No hay registros.\n");
				continue;
			}
			
			if (opcion < 5) {
				buscarISBN(contador);
			}
			
			if (opcion == 1 && dato != null)
				out.println("El registro ya existe.");
			else if (opcion >= 2 && opcion <= 4 && dato == null)
				out.println("\nRegistro no encontrado.");
			else
				seleccionMenuPrincipal(contador, opcion, subopcion);
			if (opcion < 7 && opcion >= 1)
				pausar("");
		} while (opcion != 7);
		
		teclado.close();
		procesarCambios();
	}

	private int seleccionarOpcionValida() {
		int opcion;
		do {
			opcion = leerEntero("Seleccione una opci\u00F3n");
			if (opcion < 1 || opcion > 7)
				out.println("Opci\u00F3nn no v\u00E1lida.");
		} while (opcion < 1 || opcion > 7);
		return opcion;
	}

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
	
	private void seleccionMenuPrincipal(int[] contador, int opcion, int subopcion) {
		int i;
		int n;
		
		switch (opcion) {
			case 1:
				nuevoLibro();
				break;
			case 3:
				menuModificarLibro(subopcion);
				modificarLibro(subopcion);
				break;
			case 4:
				vector.remove(dato);
				out.println("Registro borrado correctamente.");
				break;
			case 5:
				Collections.sort(vector);
				out.println("Registros ordenados correctamente.");
				break;
			case 6:
				n = vector.size();
				contador[0] = 0;
				for (i = 0; i < n; i++)
					imprimir(vector.get(i), contador);
				out.println("Total de registros: " + contador[0] + ".");
				break;
		}
	}
	
	private void buscarISBN(int[] contador) {
		int i;
		libro.setISBN(leerCadena ("Ingrese el ISBN del libro"));
		i = vector.indexOf(libro);
		if (i < 0)
			dato = null;
		else
			dato = vector.get(i);
		if (dato != null) {
			out.println();
			imprimir(dato, contador);
		}
	}
	
	private void nuevoLibro() {
		libro.setTitulo(leerCadena ("Ingrese el titulo"));
		libro.setAutor(leerCadena ("Ingrese el autor"));
		libro.setEditorial(leerCadena ("Ingrese el editorial"));
		libro.setEdicion(leerEntero ("Ingrese el edicion"));
		libro.setAnioPublicacion(leerEntero ("Ingrese el año de publicacion"));
		vector.add(libro);
		libro = new Libro();
		out.println("\nRegistro agregado correctamente.");
	}

	private void menuModificarLibro(int subopcion) {
		out.println("Men\u00FA de modificaci\u00F3n de campos");
		out.println("1.- titulo");
		out.println("2.- autor");
		out.println("3.- editorial");
		out.println("4.- edicion");
		out.println("5.- anno de publicacion");
		
		do {
			subopcion = leerEntero ("Seleccione un n\u00FAmero de campo a modificar");
			if (subopcion < 1 || subopcion > 5)
				out.println("Opci\u00F3n no v\u00E1lida.");
		} while (subopcion < 1 || subopcion > 5);
	}
	
	private void modificarLibro(int subopcion) {
		switch (subopcion) {
		case 1:
			dato.setTitulo(leerCadena ("Ingrese el nuevo titulo"));
			break;
		case 2:
			dato.setAutor(leerCadena ("Ingrese el nuevo autor"));
			break;
		case 3:
			dato.setEditorial(leerCadena ("Ingrese el nuevo editorial"));
			break;
		case 4:
			dato.setEdicion(leerEntero ("Ingrese el nuevo edicion"));
			break;
		case 5:
			dato.setAnioPublicacion(leerEntero ("Ingrese el nuevo año publicacion"));
			break;
		}
		out.println("\nRegistro actualizado correctamente.");
	}
	
	private boolean validarSistemaOperativo() {
		if (!System.getProperties().get("os.name").equals("Linux") // (1)
				&& System.console() == null
			) {
			try {
				out = new PrintStream(System.out, true, "CP850");
				return true;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} // (3)			
		}
		return false;
	}
	
	private void procesarCambios() {
		int i,
			n;
		
		PrintStream salida;
		try {
			salida = new PrintStream("./database/database.txt");
			n = vector.size();
			for (i = 0; i < n; i++)
				imprimirEnArchivo(vector.get(i), salida);
			salida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Integer leerEntero(String mensaje) {
		System.out.println(mensaje);
		Scanner teclado = new Scanner(System.in);
		return teclado.nextInt();
	}

	@Override
	public String leerCadena(String mensaje) {
		System.out.println(mensaje);
		Scanner teclado = new Scanner(System.in);
		return teclado.nextLine();
	}

	@Override
	public void pausar(String mensaje) {
		System.out.println(mensaje);
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void imprimir(Libro libro, int[] contador) {
		contador[0]++;
		System.out.println(libro.getISBN() + "\t" +
							libro.getTitulo() + "\t" +
							libro.getAutor() + "\t" +
							libro.getEditorial() + "\t" +
							libro.getEdicion() + "\t" +
							libro.getAnioPublicacion());
	}

	@Override
	public void imprimirEnArchivo(Libro libro, PrintStream salida) {
		salida.println(libro.getISBN() + "\t" +
				libro.getTitulo() + "\t" +
				libro.getAutor() + "\t" +
				libro.getEditorial() + "\t" +
				libro.getEdicion() + "\t" +
				libro.getAnioPublicacion()
			);
	}

}
