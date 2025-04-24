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
	
	public void anhadeTarjeta(Tarjeta t) { //WMC ++ //Ccog +0
		tarjetas.add(t);
		t.getCuentaAsociada().setCaducidad(t.getCaducidad());

	}
	
	public double getSaldoTotal() { //WMC +2 //Ccog ++
		double total = 0.0;
		for (Cuenta c: Cuentas) {  //WMC ++ //Ccog ++
			total += c.getSaldo();
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