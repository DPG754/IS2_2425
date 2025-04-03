package es.unican.is2.java;


/**
 * Clase que representa un valor en bolsa (paquete de acciones). 
 * Cada valor contiene un n�mero de acciones 
 * de una de las entidades del IBEX 35
 */
public class Valor {
	
	private String entidad;	
	private int numAcciones;
	private double cotizacion;
	
	public Valor(String entidad, int numAcciones, double cotizacionActual) { //WMC ++ //Ccog +0
		this.entidad = entidad;
		this.numAcciones = numAcciones;
		this.cotizacion = cotizacionActual; 
	}
	
	public int getNumValores() { //WMC ++ //Ccog +0
		return numAcciones;
	}

	public void setNumValores(int numValores) {//WMC ++ //Ccog +0
		this.numAcciones = numValores;
	}

	public double getCotizacion() { //WMC ++ //Ccog +0
		return cotizacion;
	}
	
	public void setCotizacion(double cotizacion) { //WMC ++ //Ccog +0
		this.cotizacion = cotizacion;
	}

	public String getEntidad() { //WMC ++ //Ccog +0
		return entidad;
	}
	
	@Override
	public boolean equals(Object obj) { //WMC +2 //Ccog ++
		Valor other = (Valor)obj;
		return (entidad.equals(other.entidad) && numAcciones==other.numAcciones);

	}

}