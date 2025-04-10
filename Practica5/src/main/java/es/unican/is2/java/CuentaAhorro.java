package es.unican.is2.java;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CuentaAhorro extends Cuenta {

	private List<Movimiento> Movimientos;
	private LocalDate caducidadDebito;
	private LocalDate caducidadCredito;
	private double limiteDebito;

	public CuentaAhorro(String numCuenta)  throws datoErroneoException { //WMC +1 //Ccog +0
		super(numCuenta);
		Movimientos = new LinkedList<Movimiento>();
		limiteDebito = 1000;
	}
	
	public void ingresar(String concepto, double x) throws datoErroneoException { //WMC +2 //Ccog +1
		if (x <= 0) //WMC ++ //Ccog ++
			throw new datoErroneoException("No se puede ingresar una cantidad negativa");

		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento(concepto, now, x);
		this.Movimientos.add(m);
	}

	public void retirar(String concepto, double x) throws saldoInsuficienteException, datoErroneoException { //WMC +3 //Ccog +2
		if (getSaldo() < x) //WMC ++ //Ccog ++
			throw new saldoInsuficienteException("Saldo insuficiente");
		if (x <= 0) //WMC ++ //Ccog ++
			throw new datoErroneoException("No se puede retirar una cantidad negativa");
		
		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento(concepto, now, x);
		this.Movimientos.add(m);
	}

	public double getSaldo() { //WMC +2 //Ccog ++
		double r = 0.0;
		for (int i = 0; i < this.Movimientos.size(); i++) { //WMC ++ //Ccog ++
			Movimiento m = (Movimiento) Movimientos.get(i);
			r += m.getI();
		}
		return r;
	}

	public void addMovimiento(Movimiento m) { //WMC +1 //Ccog +0
		Movimientos.add(m);
	}

	public List<Movimiento> getMovimientos() { //WMC +1 //Ccog +0
		return Movimientos;
	}

	public LocalDate getCaducidadDebito() { //WMC +1 //Ccog +0
		return caducidadDebito;
	}

	public void setCaducidadDebito(LocalDate caducidadDebito) { //WMC +1 //Ccog +0
		this.caducidadDebito = caducidadDebito;
	}

	public LocalDate getCaducidadCredito() { //WMC +1 //Ccog +0
		return caducidadCredito;
	}

	public void setCaducidadCredito(LocalDate caducidadCredito) { //WMC +1 //Ccog +0
		this.caducidadCredito = caducidadCredito;
	}

	public double getLimiteDebito() { //WMC +1 //Ccog +0
		return limiteDebito;
	}

}