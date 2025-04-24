package es.unican.is2.java;


public class Cuenta {
	
	private String numCuenta;
	
	public Cuenta(String numCuenta) { //WMC=1	//Ccog=0
		this.numCuenta = numCuenta;
	}
	
	public String getNumCuenta() {	//WMC=1 //Ccog=0
		return numCuenta;
	}
	
	public double getSaldo() { //WMC ++ //CCog +0
		return 0;
	}
}
