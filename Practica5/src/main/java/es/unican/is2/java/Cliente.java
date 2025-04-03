package es.unican.is2.java;



import java.util.LinkedList;
import java.util.List;

public class Cliente { 
	
	public String nombre;
	public String calle;
	public String zip;
	public String localidad;
	public String telefono;
	public String dni;
	
    private List<Cuenta> Cuentas = new LinkedList<Cuenta>();
    
    private List<Tarjeta> tarjetas = new LinkedList<Tarjeta>();

 	public Cliente(String titular, String calle, String zip, String localidad, 
 			String telefono, String dni) {  //WMC ++ //Ccog +0
		this.nombre = titular;
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}
	
	public void cambiaDireccion(String calle, String zip, String localidad) { //WMC ++ //Ccog +0
		this.calle = calle;
		this.zip = zip;
		this.localidad = localidad;
	}
	
	public void anhadeCuenta(Cuenta c) { //WMC ++ //Ccog +0
		Cuentas.add(c);
	}
	
	public void anhadeTarjeta(Tarjeta t) { //WMC +3 //Ccog +2
		tarjetas.add(t);
		if (t instanceof Debito) { //WMC ++ //Ccog++
			Debito td = (Debito)t;
			td.getCuentaAsociada().setCaducidadDebito(td.getCaducidadDebito());
		} else { //WMC ++ //Ccog ++
			Credito tc = (Credito) t;
			tc.getCuentaAsociada().setCaducidadCredito(tc.getCaducidadCredito());
		}
	}
	
	public double getSaldoTotal() { //WMC +9 //Ccog +8
		double total = 0.0;
		for (Cuenta c: Cuentas) {  //WMC ++ //Ccog ++
			if (c instanceof CuentaAhorro) { //WMC +2 //Ccog +2
				total += ((CuentaAhorro) c).getSaldo();
			} else if (c instanceof CuentaValores)  { //WMC +2 //Ccog +2
				for (Valor v: ((CuentaValores) c).getValores()) { //WMC +3 //Ccog +3
					total += v.getCotizacion()*v.getNumValores();
				}
			}
		}
		return total;
	}
	
	public String getNombre() { //WMC ++ //Ccog +0
		return nombre;
	}

	public String getCalle() {//WMC ++ //Ccog +0
		return calle;
	}

	public String getZip() {//WMC ++ //Ccog +0
		return zip;
	}

	public String getLocalidad() {//WMC ++ //Ccog +0
		return localidad;
	}

	public String getTelefono() {//WMC ++ //Ccog +0
		return telefono;
	}

	public String getDni() {//WMC ++ //Ccog +0
		return dni;
	}
	
	
	
}