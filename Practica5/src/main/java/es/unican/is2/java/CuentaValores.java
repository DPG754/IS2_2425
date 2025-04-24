package es.unican.is2.java;


import java.util.LinkedList;
import java.util.List;

public class CuentaValores extends Cuenta {

	private List<Valor> valores;
	
	public CuentaValores(String numCuenta) { //WMC ++ //Ccog +0
		super(numCuenta); 
		valores = new LinkedList<Valor>();
	}
	
	public List<Valor> getValores() { //WMC ++ //Ccog +0
		return valores;
	}
	
	@Override
	public double getSaldo() { //WMC +2 //Ccog ++
		double total = 0;
		for (Valor v: this.valores) { //WMC ++ //Ccog ++
			total += v.getCotizacion()*v.getNumValores();
		}
		return total;
	}
	
	public boolean anhadeValor(Valor valor) { //WMC +4 //Ccog +3
		for (Valor v:valores) { //WMC ++ //Ccog +1
			if (v.getEntidad().equals(valor.getEntidad())) //WMC +2 //Ccog +2
				return false;
			
		}
		valores.add(valor);
		return true;
	}
	
}
