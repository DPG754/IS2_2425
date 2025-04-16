package es.unican.is2.java;


import java.time.LocalDate;

public class Debito extends Tarjeta {
	
	public Debito(String numero, String titular, String cvc, CuentaAhorro cuentaAsociada) {	//WMC=1 //Ccog=0
		super(numero, titular, cvc, cuentaAsociada);
		saldoDisponible = cuentaAsociada.getLimiteDebito();
	}

	@Override
	public void retirar(double x) throws saldoInsuficienteException, datoErroneoException {	//WMC ++ //Ccog= 0
		OperacionDebito("Retirada en cajero", x);
	}
	
	@Override
	public void pagoEnEstablecimiento(String datos, double x) throws saldoInsuficienteException, datoErroneoException {//WMC++ //Ccog=0
		OperacionDebito("Compra en : " + datos, x);
	}
	
	public LocalDate getCaducidadDebito() {  //WMC=1 //Ccog=0
		return this.cuentaAsociada.getCaducidadDebito();
	}
	
	/**
	 * Metodo invocado automaticamente a las 00:00 de cada dia
	 */
	public void restableceSaldo() {		//WMC=1 //Ccog=0
		saldoDisponible = cuentaAsociada.getLimiteDebito();
	}
	
	public CuentaAhorro getCuentaAsociada() {		//WMC=1 //Ccog=0
		return cuentaAsociada;
	}
	
	//metodos auxiliares
	private void OperacionDebito(String mensaje, double x) { //WMC = +2 // Ccog ++
		if (saldoDisponible<x) {	//WMC=+1 //Ccog=+1
			throw new saldoInsuficienteException("Saldo insuficiente");
		}
		this.cuentaAsociada.retirar(mensaje, x);
		saldoDisponible-=x;
	}

}