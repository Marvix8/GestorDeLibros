package gestor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Vector;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import excepciones.BaseVaciaException;
import excepciones.DatoInexistenteException;

/**
 * Clase que administra un gestor de libros. <br>
 */
public class Gestor /* extends Util */ {
	/**
	 * Nombre y ubicación de la base de datos. <br>
	 */
	private static final String NOMBRE_BASE = "./database/database.txt";
	/**
	 * Encoding a utilizar. <br>
	 */
	private static final Charset ENCODING = StandardCharsets.UTF_8;
	/**
	 * Método de encriptación. <br>
	 */
	private static final String METODO_ENCRIPTACION = "AES";
	/**
	 * Salida de mensajes del gestor. <br>
	 */
	private PrintStream out;
//	/**
//	 * Entrada de usuario. <br>
//	 */
//	private Scanner teclado;
	/**
	 * Libros en la base de datos. <br>
	 */
	private Vector<Libro> libros;
	private Libro libroBuscar, dato;
	private Libro libroActual;
	/**
	 * Actualmente no se usa, pero se deja para los métodos. <br>
	 */
	private int[] contador = new int[1];
	
	private static final String KEY = "COCObASILE20182C";

	/**
	 * Crea un gestor de libros. <br>
	 */
	public Gestor() throws IOException {
		try {
			this.libros = new Vector<Libro>();
			this.contador[0] = 0;
			this.libroBuscar = new Libro();
			this.dato = new Libro();
			this.leerLibrosEnBase();
		} catch (IOException exception) {
			throw exception;
		}
	}

	/**
	 * Controla que la base no se encuentre vacia y que la opción solicitada no
	 * se vea afectado por esto. <br>
	 * 
	 * @param opcion
	 *            Opción seleccionada. <br>
	 * @throws BaseVaciaException
	 *             Si la opción a realizar necesita que haya datos en la base de
	 *             datos. <br>
	 */
	public void controlarBaseVacia(final GestorOpciones opcion) throws BaseVaciaException {
		if (this.libros.isEmpty() && opcion != GestorOpciones.ALTA && opcion != GestorOpciones.SALIR) {
			throw new BaseVaciaException();
		}
	}

	// /**
	// * Controla que un registro exista para ciertas opciones. <br>
	// *
	// * @param opcion
	// * Opción seleccionada por el usuario. <br>
	// * @return <b>true</b> si no existe. <br>
	// * <b>false</b> si existe. <br>
	// */
	// private boolean registroInexitente(final int opcion) {
	// if (opcion >= 2 && opcion <= 4 && this.dato == null) {
	// return true;
	// }
	// return false;
	// }

	// // /**
	// // * Ejecuta el gestor de libros. <br>
	// // * CC: 9
	// // */
	// public void ejecutar() {
	// this.controlarSalidaPorConsola();
	// // this.leeerLibrosEnBase();
	// int opcion;
	// this.libro = new Libro();
	// do {
	// this.cargarMenuPrincipal();
	// opcion = this.leerOpcionUsuario();
	// this.out.println();
	// // if (this.controlarBaseVacia(opcion)) {
	// // pausar("No hay registros.\n");
	// // // Si no hay registros no se puede hacer nada más que agregar.
	// // continue;
	// // }
	// // Para agregar, actualizar, consultar o dar de baja debo tener el
	// // ISBN.
	// // if (opcion < 5) {
	// // this.buscarLibroPorISBN();
	// // }
	// if (opcion == 1 && this.dato != null) {
	// out.println("El registro ya existe.");
	// } else {
	// if (this.registroInexitente(opcion)) {
	// out.println("\nRegistro no encontrado.");
	// } else {
	// // Si ya no tengo los casos que puede salir realizo la
	// // operación.
	// this.seleccionMenuPrincipal(opcion);
	// }
	// }
	// if (opcion < 7 && opcion >= 1) {
	// this.pausar("");
	// }
	// } while (opcion != 7);
	// teclado.close();
	// // this.procesarCambios();
	// }
	
	/**
	 * Lee los libros en base. <br>
	 */
	private void leerLibrosEnBase() throws IOException {
		String[] campos;
		Libro libro;
		File archivoEntrada;
		try {
			archivoEntrada = new File(NOMBRE_BASE);
			Key secretKey = new SecretKeySpec(KEY.getBytes(), METODO_ENCRIPTACION);
			Cipher cipher = Cipher.getInstance(METODO_ENCRIPTACION);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			FileInputStream inputStream = new FileInputStream(archivoEntrada);
			byte[] bytesCifrado = new byte[(int) archivoEntrada.length()];
			inputStream.read(bytesCifrado);
			inputStream.close();
			byte[] bytesDecifrados = cipher.doFinal(bytesCifrado);
			// Leemos los archivos decifrados.
			for (String entrada : new String(bytesDecifrados, ENCODING).split("\\|")) {
				libro = new Libro();
				campos = entrada.split("\t");
				libro.setISBN(campos[0]);
				libro.setTitulo(campos[1]);
				libro.setAutor(campos[2]);
				libro.setEditorial(campos[3]);
				libro.setEdicion(Integer.parseInt(campos[4]));
				libro.setAnioPublicacion(Integer.parseInt(campos[5]));
				this.libros.add(libro);
			}
		} catch (NullPointerException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
				| InvalidKeyException | BadPaddingException e) {
			throw new IOException(e.getMessage());
		}
	}

	
//	/**
//	 * Lee los libros en base. <br>
//	 */
//	private void leerLibrosEnBase2() throws IOException {
//		try {
//			Libro libro;
//			String[] campos;
//			Scanner entrada = new Scanner(new FileReader(NOMBRE_BASE));
//			String linea = null;
//			
//			while (entrada.hasNextLine()) {
//				try {
//					KeyGenerator keyGenerator = KeyGenerator.getInstance(METODO_ENCRIPTACION);
//					SecretKey clave = keyGenerator.generateKey();
////					new SecretKeySpec() //("cocoBasile", METODO_ENCRIPTACION) //
//					Cipher cipher = Cipher.getInstance(METODO_ENCRIPTACION);
//					cipher.init(Cipher.DECRYPT_MODE, clave);
//					String pepe = entrada.nextLine();
//					
//					Byte nina = Byte.valueOf(pepe);
//					System.out.println(nina.toString());
//
//					linea = new String(cipher.doFinal(pepe.getBytes(ENCODING)));
//					System.out.println("1" + linea);
//				} catch (NoSuchAlgorithmException e) {
//					System.out.println("1 " + e.getMessage());
//				} catch (NoSuchPaddingException e) {
//					System.out.println("2 " + e.getMessage());
//				} catch (InvalidKeyException e) {
//					System.out.println("3 " + e.getMessage());
//				} catch (IllegalBlockSizeException | BadPaddingException e) {
//					System.out.println("4 " + e.getMessage());
//				}
////				byte[] textoCifrado = cipher.doFinal(texto);
// 
//				libro = new Libro();
//				campos = linea.split("\t");
//				libro.setISBN(campos[0]);
//				libro.setTitulo(campos[1]);
//				libro.setAutor(campos[2]);
//				libro.setEditorial(campos[3]);
//				libro.setEdicion(Integer.parseInt(campos[4]));
//				libro.setAnioPublicacion(Integer.parseInt(campos[5]));
//				this.libros.add(libro);
//			}
//			entrada.close();
//		} catch (IOException exception) {
//			throw exception;
//		}
//	}
	
	// /**
	// * Selecciona una opción del menú principal. <br>
	// * CC: 5
	// *
	// * @param opcion
	// * Opción seleccionada por el usuario. <br>
	// */
	// @SuppressWarnings("unchecked")
	// private void seleccionMenuPrincipal(int opcion) {
	// switch (opcion) {
	// case 1:
	// this.nuevoLibro();
	// break;
	// case 3:
	// this.modificarLibro();
	// break;
	// case 5:
	// Collections.sort(this.libros);
	// this.out.println("Registros ordenados correctamente.");
	// break;
	// case 6:
	// this.mostrarLibros();
	// break;
	// }
	// }

	/**
	 * Ordena los libros en la base de datos. <br>
	 */
	public void ordenarLibros() {
		Collections.sort(this.libros);
	}

	/**
	 * Da un libro de baja. <br>
	 */
	public void darDeBajaLibro() {
		this.libros.remove(this.dato);
		this.out.println("Registro borrado correctamente.");
	}

	// /**
	// * Muestra los libros en la base. <br>
	// * CC: 2
	// */
	// private void mostrarLibros() {
	// for (int i = 0; i < this.libros.size(); i++) {
	// this.imprimir(this.libros.get(i), this.contador);
	// }
	// this.out.println("Total de registros: " + this.libros.size() + ".");
	// }

	/**
	 * Busca un libro por su ISBN. <br>
	 * 
	 * @throws DatoInexistenteException
	 *             Si el libro no se encuentra en la base de datos. <br>
	 */
	public void buscarLibroPorISBN() throws DatoInexistenteException {
		// Si no se seleccionó ningún libro, salgo.
		if (this.libroBuscar == null) {
			throw new DatoInexistenteException();
		}
		int i = this.libros.indexOf(this.libroBuscar);
		// Si no se encontró el libro es porque no existe en la base de datos.
		if (i < 0) {
			throw new DatoInexistenteException();
		}
		this.dato = this.libros.get(i);
	}

	/**
	 * Carga un libro en la base de datos. <br>
	 * 
	 * @param libro
	 *            Libro a cargar. <br>
	 */
	public void cargarLibro(final Libro libro) {
		this.libros.add(libro);
	}

	/**
	 * Carga un nuevo libro. <br>
	 */
	// private void nuevoLibro() {
	// this.libroBuscar.setTitulo(leerCadena("Ingrese el titulo"));
	// this.libroBuscar.setAutor(leerCadena("Ingrese el autor"));
	// this.libroBuscar.setEditorial(leerCadena("Ingrese el editorial"));
	// this.libroBuscar.setEdicion(leerEntero("Ingrese el edicion"));
	// this.libroBuscar.setAnioPublicacion(leerEntero("Ingrese el año de
	// publicacion"));
	// this.libros.add(this.libroBuscar);
	// this.libroBuscar = new Libro();
	// this.out.println("\nRegistro agregado correctamente.");
	// }

	/**
	 * Carga el menú para modificar información sobre un libro. <br>
	 * CC: 2
	 */
	// private int obtenerOpcionModificacion() {
	// this.out.println("Menú de modificación de campos");
	// this.out.println("1.- titulo");
	// this.out.println("2.- autor");
	// this.out.println("3.- editorial");
	// this.out.println("4.- edicion");
	// this.out.println("5.- año de publicacion");
	// int subopcion = this.leerEntero("Seleccione un número de campo a
	// modificar");
	// while (subopcion < 1 || subopcion > 5) {
	// this.out.println("Opción no válida.");
	// subopcion = this.leerEntero("Seleccione un número de campo a modificar");
	// }
	// return subopcion;
	// }

	/**
	 * Modifica información sobre un libro. <br>
	 * CC: 5
	 */
//	private void modificarLibro() {
//		switch (this.obtenerOpcionModificacion()) {
//		case 1:
//			this.dato.setTitulo(leerCadena("Ingrese el nuevo titulo"));
//			break;
//		case 2:
//			this.dato.setAutor(leerCadena("Ingrese el nuevo autor"));
//			break;
//		case 3:
//			this.dato.setEditorial(leerCadena("Ingrese el nuevo editorial"));
//			break;
//		case 4:
//			this.dato.setEdicion(leerEntero("Ingrese el nuevo edicion"));
//			break;
//		case 5:
//			this.dato.setAnioPublicacion(leerEntero("Ingrese el nuevo año publicacion"));
//			break;
//		}
//		this.out.println("\nRegistro actualizado correctamente.");
//	}

	
	
	/**
	 * Procesa los cambios en la base de datos. <br>
	 * 
	 * @throws IOException
	 *             No se pudo grabar el archivo. <br>
	 */
	public void procesarCambios() throws IOException {
		FileOutputStream salida;
		StringBuilder stringSalida = new StringBuilder();
		try {
			Key secretKey = new SecretKeySpec(KEY.getBytes(), METODO_ENCRIPTACION);
			Cipher cipher = Cipher.getInstance(METODO_ENCRIPTACION);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			salida = new FileOutputStream(NOMBRE_BASE);
			for (int i = 0; i < this.libros.size(); i++) {
				stringSalida.append(libros.get(i).getISBN()).append("\t").append(libros.get(i).getTitulo()).append("\t")
						.append(libros.get(i).getAutor()).append("\t").append(libros.get(i).getEditorial()).append("\t")
						.append(libros.get(i).getEdicion()).append("\t").append(libros.get(i).getAnioPublicacion())
						.append("|");
			}
			// Elimino el último | para que al leer no lea un libro nulo.
			stringSalida.deleteCharAt(stringSalida.length() - 1);
			salida.write(cipher.doFinal(stringSalida.toString().getBytes(ENCODING)));
			salida.close();
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * Devuelve el ISBN del libro actual. <br>
	 * 
	 * @return ISBN del libro actual. <br>
	 */
	public String getISBN() {
		return this.libroBuscar.getISBN();
	}

	/**
	 * Establece el ISBN del libro actual. <br>
	 * 
	 * @param ISBN
	 *            ISBN del libro. <br>
	 */
	public void setISBN(final String ISBN) {
		this.libroBuscar.setISBN(ISBN);
	}

	/**
	 * Devuelve los libros cargados en la base de datos. <br>
	 * 
	 * @return Libros en base. <br>
	 */
	public Vector<Libro> getLibros() {
		return this.libros;
	}

	/**
	 * Devuelve el libro actual. <br>
	 * 
	 * @return Libro actual. <br>
	 */
	public Vector<Libro> getLibroActual() {
		Vector<Libro> libro = new Vector<Libro>();
		libro.add(this.dato);
		return libro;
	}

	/**
	 * Finaliza la instancia del libro actual y empieza una nueva.
	 * <p>
	 * <b>NOTA</b>: Esto solo debe ser usado para métodos que realizan algún
	 * tipo de DML con la base de datos. <br>
	 */
	private void finalizarGestionActual() {
		this.libroBuscar = new Libro();
	}

	/**
	 * Muestra los libros por pantalla. <br>
	 */
	public void imprimir(Libro libro, int[] contador) {
		System.out.println(libro.getISBN() + "\t" + libro.getTitulo() + "\t" + libro.getAutor() + "\t"
				+ libro.getEditorial() + "\t" + libro.getEdicion() + "\t" + libro.getAnioPublicacion());
	}

	/**
	 * Graba los libros en archivo. <br>
	 */
	public void imprimirEnArchivo2(Libro libro, PrintStream salida) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(METODO_ENCRIPTACION);
			SecretKey clave = keyGenerator.generateKey();
			Cipher cipher = Cipher.getInstance(METODO_ENCRIPTACION);
			byte[] texto = new StringBuilder(libro.getISBN()).append("\t").append(libro.getTitulo()).append("\t")
					.append(libro.getAutor()).append("\t")
					.append(libro.getEditorial() + "\t" + libro.getEdicion() + "\t" + libro.getAnioPublicacion())
					.toString().getBytes(ENCODING);
			cipher.init(Cipher.ENCRYPT_MODE, clave);
			//
			// byte[] algo = "algo".getBytes(ENCODING);
			// cipher.init(Cipher.ENCRYPT_MODE, clave);
			// byte[] textoCifrado = cipher.doFinal(algo);
			// System.out.println(new String(textoCifrado));

			byte[] textoCifrado = cipher.doFinal(texto);
			salida.println(new String(textoCifrado));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void imprimirEnArchivo(Libro libro, PrintStream salida) {
		salida.println(libro.getISBN() + "\t" + libro.getTitulo() + "\t" + libro.getAutor() + "\t"
				+ libro.getEditorial() + "\t" + libro.getEdicion() + "\t" + libro.getAnioPublicacion());
	}
}
