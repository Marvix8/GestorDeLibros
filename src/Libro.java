public class Libro implements Comparable {

	private String ISBN;
	private String titulo;
	private String autor;
	private String editorial;
	private int edicion;
	private int anioPublicacion;
	
	public Libro() {
		
	}
	
	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public int getEdicion() {
		return edicion;
	}


	public void setEdicion(int edicion) {
		this.edicion = edicion;
	}


	public int getAnioPublicacion() {
		return anioPublicacion;
	}


	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	@Override
	public int compareTo(Object obj) {
		if(!(obj instanceof Libro))
			throw new ClassCastException();
		
		Libro libro = (Libro) obj;
		
	    if (this.getISBN().compareTo(libro.getISBN()) > 0)
	          return 1;
	       else if (this.getISBN().compareTo(libro.getISBN()) < 0)
	          return -1;
	       else   
	          return 0;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (ISBN == null) {
			if (other.ISBN != null)
				return false;
		} else if (!ISBN.equals(other.ISBN))
			return false;
		return true;
	}
	
}
