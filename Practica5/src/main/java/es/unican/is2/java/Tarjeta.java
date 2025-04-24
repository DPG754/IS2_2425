package es.unican.is2.java;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Tarjeta {
	
	protected String numero, titular, cvc;		
	protected CuentaAhorro cuentaAsociada;
	protected double saldoDisponible;
	
	public Tarjeta(String numero, String titular, String cvc, 
			CuentaAhorro cuentaAsociada) {// WMC ++ // CCog +0
		this.numero = numero;
		this.titular = titular;
		this.cvc = cvc;
		this.cuentaAsociada = cuentaAsociada;
	} 

	/**
	 * Retirada de dinero en cajero con la tarjeta
	 * @param x Cantidad a retirar. 
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */	
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException;

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
	public abstract void pagoEnEstablecimiento(String datos, double x)
			throws saldoInsuficienteException, datoErroneoException;

	
	public CuentaAhorro getCuentaAsociada() {		//WMC=1 //Ccog=0
		return cuentaAsociada;
	}

	public LocalDate getCaducidad() {//WMC=1	//Ccog=0
		return cuentaAsociada.getCaducidad();
	}


}