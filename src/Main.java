import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream out = null;
		
		if (!System.getProperties().get("os.name").equals("Linux") // (1)
				&& System.console() == null
			) {
			try {
				out = new PrintStream(System.out, true, "CP850");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} // (3)			
		}
		
		Scanner teclado = new Scanner(System.in, "CP850");	
		Vector<Libro> vector = new Vector<Libro>(); //(4)
		int i, n;
		Libro 	dato = null, 
				libro;
		int[] contador = {0};
		int opcion, subopcion;
		String[] campos;
		Scanner entrada = new Scanner(new FileReader("./database/database.txt"));
		
		while (entrada.hasNextLine()) {// (5)
			campos = entrada.nextLine().split("\t"); // (6)
			libro = new Libro();
			libro.setISBN(campos[0]);
			libro.setTitulo(campos[1]);
			libro.setAutor(campos[2]);
			libro.setEditorial(campos[3]);
			libro.setEdicion(Integer.parseInt(campos[4]));
			libro.setAnioPublicacion(Integer.parseInt(campos[5]));
			vector.add(libro);
		}
		entrada.close();// (7)
		libro = new Libro();
		do {// (8)
			out.println("MEN\u00DA");// (9)
			out.println("1.- Altas");
			out.println("2.- Consultas");
			out.println("3.- Actualizaciones");
			out.println("4.- Bajas");
			out.println("5.- Ordenar registros");
			out.println("6.- Listar registros");
			out.println("7.- Salir");
			do {// (10)
				opcion = leer_entero("Seleccione una opci\u00F3n");// (11)
				if (opcion < 1 // (12)
					|| opcion > 7) // (13)
					out.println("Opci\u00F3nn no v\u00E1lida."); // (14)
			} while (opcion < 1 // (15)
					|| opcion > 7); // (16)
				out.println(); // (17)
				if (vector.isEmpty() // (18)
					&& opcion != 1 //(19)
					&& opcion != 7) { // (20)
				pausar("No hay registros.\n"); // (21)
				continue;
				}
			if (opcion < 5) { // (22)
				libro.setISBN(leer_cadena ("Ingrese el ISBN del libro")); // (23)
				i = vector.indexOf(libro);
				if (i < 0)
					dato = null;
				else
					dato = vector.get(i);
				if (dato != null) { // (24)
						out.println(); // (25)
						imprimir(dato, contador);
				}
			}
			if (opcion == 1 // (26)
					&& dato != null) // (27)
				out.println("El registro ya existe."); // (28)
			else if (opcion >= 2 // (29)
					&& opcion <= 4 // (30)
					&& dato == null) // (31)
				out.println("\nRegistro no encontrado."); // (32)
			else switch (opcion) { // (33)
				case 1: // (34)
					libro.setTitulo(leer_cadena ("Ingrese el titulo"));
					libro.setAutor(leer_cadena ("Ingrese el autor"));
					libro.setEditorial(leer_cadena ("Ingrese el editorial"));
					libro.setEdicion(leer_entero ("Ingrese el edicion"));
					libro.setAnioPublicacion(leer_entero ("Ingrese el año de publicacion"));
					vector.add(libro);
					libro = new Libro();
					out.println("\nRegistro agregado correctamente.");
					break;
				case 3: // (35)
					out.println("Men\u00FA de modificaci\u00F3n de campos");
					out.println("1.- titulo");
					out.println("2.- autor");
					out.println("3.- editorial");
					out.println("4.- edicion");
					out.println("5.- anno de publicacion");
			do { // (36)
			subopcion = leer_entero ("Seleccione un n\u00FAmero de campo a modificar");
			if (subopcion < 1 // (37)
					|| subopcion > 5) // (38)
				out.println("Opci\u00F3n no v\u00E1lida."); // (39)
			} while (subopcion < 1 // (40)
					|| subopcion > 5); // (41)
				switch (subopcion) { // (42)
				case 1: // (43)
					dato.setTitulo(leer_cadena ("Ingrese el nuevo titulo"));
					break;
				case 2: // (44)
					dato.setAutor(leer_cadena ("Ingrese el nuevo autor"));
					break;
				case 3: // (45)
					dato.setEditorial(leer_cadena ("Ingrese el nuevo editorial"));
					break;
				case 4: // (46)
					dato.setEdicion(leer_entero ("Ingrese el nuevo edicion"));
					break;
				case 5: // (47)
					dato.setAnioPublicacion(leer_entero ("Ingrese el nuevo año publicacion"));
					break;
				}
				out.println("\nRegistro actualizado correctamente."); // (48)
				break;
				case 4: // (49)
					vector.remove(dato);
					out.println("Registro borrado correctamente.");
					break;
				case 5: // (50)
					Collections.sort(vector);
					out.println("Registros ordenados correctamente.");
					break;
				case 6: // (51)
					n = vector.size();
					contador[0] = 0;
					for (i = 0; i < n; i++)// (52)
						imprimir(vector.get(i), contador);
					out.println("Total de registros: " + contador[0] + ".");// (53)
					break;
				}
				if (opcion < 7 // (54)
						&& opcion >= 1) // (55)
					pausar(""); // (56)
		} while (opcion != 7); // (57)
		
		teclado.close();
		PrintStream salida;
		try {
			salida = new PrintStream("./database/database.txt");
			n = vector.size();
			for (i = 0; i < n; i++) // (59)
				imprimirEnArchivo(vector.get(i), salida); // (60)
				salida.close();// (61)
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // (58)

		} // (62)
	
	private static Integer leer_entero(String mensaje) {
		System.out.println(mensaje);
		Scanner teclado = new Scanner(System.in);
		return teclado.nextInt();
	}

	private static String leer_cadena(String mensaje) {
		System.out.println(mensaje);
		Scanner teclado = new Scanner(System.in);
		return teclado.nextLine();
	}
	
	private static void pausar(String mensaje) {
		System.out.println(mensaje);
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	private static void imprimir(Libro libro, int[] contador) {
		contador[0]++;
		System.out.println(libro.getISBN() + "\t" +
							libro.getTitulo() + "\t" +
							libro.getAutor() + "\t" +
							libro.getEditorial() + "\t" +
							libro.getEdicion() + "\t" +
							libro.getAnioPublicacion());
	}
	
	private static void imprimirEnArchivo(Libro libro, PrintStream salida) {
		salida.println(libro.getISBN() + "\t" +
						libro.getTitulo() + "\t" +
						libro.getAutor() + "\t" +
						libro.getEditorial() + "\t" +
						libro.getEdicion() + "\t" +
						libro.getAnioPublicacion()
					);
	}
	
}
