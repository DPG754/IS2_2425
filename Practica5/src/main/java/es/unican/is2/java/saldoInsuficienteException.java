package es.unican.is2.java;


@SuppressWarnings("serial")
public class saldoInsuficienteException extends RuntimeException {

	public saldoInsuficienteException (String mensaje) { //WMC ++ //Ccog +0
		super(mensaje);
	}
}
