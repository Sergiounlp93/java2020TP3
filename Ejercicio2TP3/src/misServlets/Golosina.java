package misServlets;

public class Golosina {
	private String nombre;
	private float precio;
	private int cantidad = 0;
	public Golosina(String nombre,float precio) {
		this.precio = precio;
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	// get de totalprecio por cada golosina
	
	public float getTotalPrecio() {
		return (float)(cantidad*precio);
	}

}
