package es.unican.is2.java;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Credito extends Tarjeta {
	

	private List<Movimiento> MovimientosMensuales;
	private List<Movimiento> historicoMovimientos;

	public Credito(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada, double credito) {		////WMC=1 //Ccog=0
		super(numero, titular, cvc, cuentaAsociada);
		this.saldoDisponible = credito;
	}

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. Se aplica una comision del 5%.
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException { //WMC=+ //Ccog=0
		crearMovimiento("Retirada en cajero", x);

	}


	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {//WMC=+  //Ccog=0
		crearMovimiento("Compra a credito en: "+ datos, x);

	}


	public double getGastosAcumulados() {	//WMC ++ //Ccog = 0
		double r = 0.0;
		r = getImporteTotal();
		return r;
	}
	
	
	public LocalDate getCaducidadCredito() {//WMC=1	//Ccog=0
		return this.cuentaAsociada.getCaducidadCredito();
	}

	/**
	 * Metodo que se invoca automaticamente el dia 1 de cada mes
	 */
	public void liquidar() {//WMC +2	//Ccog ++
		
		double importeLiquidacion = 0.0;
		importeLiquidacion = getImporteTotal();
		LocalDateTime now = LocalDateTime.now();
		Movimiento liq = new Movimiento("Liquidacion de operaciones tarjeta credito", now, -importeLiquidacion);
		if (importeLiquidacion != 0)													//WMC=+1	//Ccog=+1
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
	
	//Metodos auxiliares
	private double getImporteTotal() { //WMC +2 //Ccog ++
		double importeTotal = 0;
		for (int i = 0; i < this.MovimientosMensuales.size(); i++) {//WMC=+1	//Ccog=+1
			Movimiento m = (Movimiento) MovimientosMensuales.get(i);
			importeTotal += m.getI();
		}
		return importeTotal;
	}

	
	private void crearMovimiento(String mensaje, double x) {//WMC=+3  //Ccog=2
		
		if (getGastosAcumulados()+x > saldoDisponible) {									//WMC=+1 //Ccog=+1
			throw new saldoInsuficienteException("Credito insuficiente");
		}
		if (x<0)																		//WMC=+1 //Ccog=+1
			throw new datoErroneoException("No se puede operar con una cantidad negativa");

		LocalDateTime now = LocalDateTime.now();
		Movimiento m = new Movimiento(mensaje, now, -x);
		MovimientosMensuales.add(m);
	
	} 
}