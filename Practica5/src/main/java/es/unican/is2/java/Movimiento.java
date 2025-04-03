package es.unican.is2.java;


import java.time.LocalDateTime;

public class Movimiento {
	private String concepto;
	private LocalDateTime fecha;
	private double importe;

	public double getI() {	//WMC=1 //Ccog=0
		return importe;
	}

	public void setI(double newMImporte) { 	//WMC=1 //Ccog=0
		importe = newMImporte;
	}
	
	public String getC() {	//WMC=1 //Ccog=0
		return concepto;
	}

	public void setC(String newMConcepto) {	//WMC=1 //Ccog=0
		concepto = newMConcepto;
	}

	public LocalDateTime getF() {	//WMC=1 //Ccog=0
		return fecha;
	}

	public void setF(LocalDateTime newMFecha) {	//WMC=1 //Ccog=0
		fecha = newMFecha;
	}

	
	@Override
	public boolean equals(Object obj) {		//WMC=1 //Ccog=1
		Movimiento other = (Movimiento)obj;
		return (concepto.equals(other.concepto) && fecha.equals(other.fecha)&& importe==other.importe); //Ccog=+1
	}
	
}