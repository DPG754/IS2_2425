package es.unican.is2.java;


public abstract class Tarjeta {
	
	protected String numero, titular, cvc;		
	protected CuentaAhorro cuentaAsociada;

<<<<<<< HEAD
	public Tarjeta(String numero, String titular, String cvc,
			CuentaAhorro cuentaAsociada) { //WMC=1 //Ccog=0
=======
	public Tarjeta(String numero, String titular, String cvc, 
			CuentaAhorro cuentaAsociada) {// WMC ++ // CCog +0
>>>>>>> 0e3ed0efbde65769dfa828cd6adedeb7f872249e
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
<<<<<<< HEAD
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException;		
=======
	public abstract void retirar(double x) throws saldoInsuficienteException, datoErroneoException; //WMC ++ //Ccog +0
>>>>>>> 0e3ed0efbde65769dfa828cd6adedeb7f872249e

	/**
	 * Pago en establecimiento con la tarjeta
	 * @param datos Concepto del pago
	 * @param x Cantidada a pagar
	 * @throws saldoInsuficienteException
	 * @throws datoErroneoException
	 */
<<<<<<< HEAD
	public abstract void pagoEnEstablecimiento(String datos, double x)	
			throws saldoInsuficienteException, datoErroneoException;
=======
	public abstract void pagoEnEstablecimiento(String datos, double x)
			throws saldoInsuficienteException, datoErroneoException; //WMC ++ //Ccog +0
>>>>>>> 0e3ed0efbde65769dfa828cd6adedeb7f872249e
	
}