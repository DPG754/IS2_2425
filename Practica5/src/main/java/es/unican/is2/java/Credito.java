package es.unican.is2.java;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Credito extends Tarjeta {
	
	private double credito;
	private List<Movimiento> MovimientosMensuales;
	private List<Movimiento> historicoMovimientos;

	public Credito(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada, double credito) {		////WMC=1 //Ccog=0
		super(numero, titular, cvc, cuentaAsociada);
		this.credito = credito;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comisi�n del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { //WMC=+ //Ccog=0
		Movimiento m = crearMovimiento(x, "Retirada en cajero");
		
		MovimientosMensuales.add(m);
	}


	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {//WMC=+  //Ccog=0
		Movimiento m = crearMovimiento(x, "Compra a credito en: "+ datos);
		MovimientosMensuales.add(m);
	}

	private Movimiento crearMovimiento(double x, String mensaje) {
		if (x<0)																		//WMC=+1 //Ccog=+1
			throw new datoErroneoException("No se puede retirar una cantidad negativa");


		if (getGastosAcumulados()+x > credito) {									//WMC=+1 //Ccog=+1
			throw new saldoInsuficienteException("Credito insuficiente");
		}
		LocalDateTime now = LocalDateTime.now();
		return (new Movimiento(mensaje, now, -x));

	}

    private double getGastosAcumulados() {	//WMC= 2 //Ccog=1
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) { //WMC=+1 //Ccog=+1
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		return r;
	}
	
	
	public LocalDate getCaducidadCredito() {//WMC=1	//Ccog=0
		return this.cuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	public void liquidar() {//WMC=3	//Ccog=2
		
		double r = 0.0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) {//WMC=+1	//Ccog=+1
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			r += m.getI();
		}
		LocalDateTime now = LocalDateTime.now();
		Movimiento liq = new Movimiento("Liquidacion de operaciones tarjeta credito", now, -r);
		if (r != 0)													//WMC=+1	//Ccog=+1
			cuentaAsociada.addMovimiento(liq);
		
		historicoMovimientos.addAll(MovimientosMensuales);
		MovimientosMensuales.clear();
	}

	public List<Movimiento> getMovimientosMensuales() { //WMC=1	//Ccog=0
		return MovimientosMensuales;
	}
	
	public CuentaAhorro getCuentaAsociada() { //WMC=1 //Ccog=0
		return cuentaAsociada;
	}
	
	public List<Movimiento> getMovimientos() { //WMC=1	//Ccog=0
		return historicoMovimientos;
	}
	
	

}