package es.unican.is2.java;


import java.time.LocalDateTime;

public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public Movimiento(String concepto, LocalDateTime date, double importe) {
		this.concepto = concepto;
		this.fecha = date;
		this.importe = importe;
	}

	public double getI() {	//WMC=1 //Ccog=0
		return importe;
	}
	
	public String getC() {	//WMC=1 //Ccog=0
		return concepto;
	}

	public LocalDateTime getF() {	//WMC=1 //Ccog=0
		return fecha;
	}

	
	@Override
	public boolean equals(Object obj) {		//WMC=1 //Ccog=1
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha)&& importe==other.importe); //Ccog=+1
	}
	
}