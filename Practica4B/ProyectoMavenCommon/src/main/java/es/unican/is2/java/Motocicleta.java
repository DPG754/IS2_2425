package es.unican.is2.java;


import java.time.LocalDate;

/**
 * Clase que representa un vehiculo de tipo motocicleta
 */
public class Motocicleta extends Vehiculo {

	private int cilindrada;

	public Motocicleta(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, int cilindrada) {
		super(id, matricula, fechaMatriculacion, motor);
		//TO-DO
		this.cilindrada = cilindrada;
	}

	/**
	 * Retorna la cilindrada en CC de la motocicleta.
	 */
	public int getCilindrada() {
		return cilindrada;
	}

	@Override
	public double precioImpuesto() {
		//TO-DO
		double pImpuesto = 0;
		
		if (LocalDate.now().getYear() - this.getFechaMatriculacion().getYear() > 25) {
			return pImpuesto;
		}
		
		if (this.cilindrada <= 125) {
			pImpuesto = 8;
		} else if (this.cilindrada <= 250) {
			pImpuesto = 15;
		} else if (this.cilindrada <= 500) {
			pImpuesto = 30;
		} else if (this.cilindrada <= 1000) {
			pImpuesto = 60; 
		} else {
			pImpuesto = 120;
		}
		
		if (this.getMotor() == TipoMotor.ELECTRICO || 
				(this.getMotor() == TipoMotor.HIBRIDO &&
				LocalDate.now().getYear() - this.getFechaMatriculacion().getYear() <= 4 )) {
			pImpuesto -= pImpuesto * 0.75;
		} else if (this.getMotor() == TipoMotor.GAS &&
				LocalDate.now().getYear() - this.getFechaMatriculacion().getYear() <= 1 ) {
			pImpuesto -= pImpuesto * 0.5;
		}
		

		
		return pImpuesto;
	}

}
