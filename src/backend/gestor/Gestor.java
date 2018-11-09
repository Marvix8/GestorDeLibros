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
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import excepciones.BaseVaciaException;
import excepciones.DatoInexistenteException;

/**
 * Clase que administra un gestor de libros. <br>
 */
public class Gestor{
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
	/**
	 * Libros en la base de datos. <br>
	 */
	private Vector<Libro> libros;
	/**
	 * Libro pivot en el que se busca por ISBN. <br>
	 */
	private Libro libroBuscar;
	/**
	 * Información del libro actual. <br>
	 */
	private Libro libroConsultado;
	/**
	 * NO MODIFICAR, DEBE SER DE 16 CARACTERES. <br>
	 */
	private static final String KEY = "COCObASILE20182C";

	/**
	 * Crea un gestor de libros. <br>
	 */
	public Gestor() throws IOException {
		try {
			this.libros = new Vector<Libro>();
			this.libroBuscar = new Libro();
			this.libroConsultado = new Libro();
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
	 // * Ejecuta el gestor de libros. <br>
	 // * CC: 9
	 // */
	public void ejecutar() {
		this.controlarSalidaPorConsola();
		// this.leeerLibrosEnBase();
		int opcion;
		this.libro = new Libro();
		do {
			this.cargarMenuPrincipal();
			opcion = this.leerOpcionUsuario();
			this.out.println();
			// if (this.controlarBaseVacia(opcion)) {
			// pausar("No hay registros.\n");
			// // Si no hay registros no se puede hacer nada más que agregar.
			// continue;
			// }
			// Para agregar, actualizar, consultar o dar de baja debo tener el
			// ISBN.
			// if (opcion < 5) {
			// this.buscarLibroPorISBN();
			// }
			if (opcion == 1 && this.libroConsultado != null) {
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
		// this.procesarCambios();
	}
	
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
		this.libros.remove(this.libroConsultado);
		this.out.println("Registro borrado correctamente.");
		
	}

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
		this.libroConsultado = this.libros.get(i);
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
		libro.add(this.libroConsultado);
		return libro;
	}
}
