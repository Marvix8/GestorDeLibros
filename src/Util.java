import java.io.PrintStream;

public abstract class Util {
	
	public abstract Integer leer_entero(String mensaje);
	
	public abstract String leer_cadena(String mensaje);
	
	public abstract void pausar(String mensaje);
	
	public abstract void imprimir(Libro libro, int[] contador);
	
	public abstract void imprimirEnArchivo(Libro libro, PrintStream salida);
}
