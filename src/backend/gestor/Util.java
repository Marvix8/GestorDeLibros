package gestor;
import java.io.PrintStream;

public abstract class Util {
	
	public abstract Integer leerEntero(String mensaje);
	
	public abstract String leerCadena(String mensaje);
	
	public abstract void pausar(String mensaje);
	
	public abstract void imprimir(Libro libro, int[] contador);
	
	public abstract void imprimirEnArchivo(Libro libro, PrintStream salida);
}
