import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class VehiculoTest {

	@Test
	public void mainTest() {
		
		//se crean los vehiculos
		ArrayList<Turismo> vehiculosPrueba = new ArrayList<Turismo>();
		Long id;
		LocalDate fecha = LocalDate.now();
		//Turismo valido
		id = (long) 1;
		vehiculosPrueba.add(new Turismo(id, "0001AAA", fecha, TipoMotor.ELECTRICO, 15.5));
		//Turismo no valido por id negativo
		id = (long) -1;
		//vehiculosPrueba.add(new Turismo(id, "0001AAB", fecha, TipoMotor.GASOLINA, 20.5));
		//Turismo no valido por id nulo
		id = null;
		//vehiculosPrueba.add(new Turismo(id, "0001AAC", fecha, TipoMotor.DIESEL, 40.8));
		//Turismo no valido por matricula nula
		String matricula = null;
		id = (long) 2;
		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, TipoMotor.HIBRIDO, 23.1));
		//Turismo no valido por matricula vacia;
		matricula = " ";
		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, TipoMotor.HIBRIDO, 14.6));
		//Turismo no valido por fecha de matriculacion
		matricula = "0001AAD";
		//vehiculosPrueba.add(new Turismo(id, matricula, fecha.plusDays((long) 3), TipoMotor.ELECTRICO, 42.32));
		//turismo no valido por fecha nula
		fecha = null;
		id = (long) 4;
		matricula = "0001AAE";
		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, TipoMotor.ELECTRICO, 7.1));
		//Turismo no valido por fecha con fomato incorrecto
		fecha = LocalDate.now().withYear(31);
		id = (long) 5;
		matricula = "0001AAF";
		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, TipoMotor.DIESEL, 7.1));
		//Turismo no valido por motor no valido
		fecha = LocalDate.now();
		id = (long) 6;
		matricula = "0001AAG";
		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, TipoMotor.GAS, 7.1));
		//Turismo no valido por motor nulo
		id = (long) 7;
		matricula = "0001AAH";
		TipoMotor motor = null;
		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, motor, 19.3));
		
	}
	
	@Test
	public void testTurismoId() {
		Turismo vehiculoPrueba;
		Long id;
		LocalDate fecha = LocalDate.now();
		//Turismo valido
		id = (long) 1;
		vehiculoPrueba = new Turismo(id, "0001AAA", fecha, TipoMotor.ELECTRICO, 15.5);
		
		id = vehiculoPrueba.getId();
		//Comprobamos id no negativo
		assertTrue("Id no valido por negativo", id >= 0);
		//Comprobamos id no nulo
		assertTrue("Id no valido por nulo", id != null);
		

		
	}
	
	@Test
	public void testTurismoMatricula() {
		Turismo vehiculoPrueba;
		Long id = (long) 1;
		LocalDate fecha = LocalDate.now();
		vehiculoPrueba = new Turismo(id, "0001AAA", fecha, TipoMotor.ELECTRICO, 15.5);
		String matricula = vehiculoPrueba.getMatricula();
		//comprobamos el valor de la matricula
		assertTrue("Matricula no valida por nula", matricula != null);
		
	}
	
	@Test
	public void testTurismoFechaMatriculacion() {
		Turismo vehiculoPrueba;
		long id = (long) 2;
		LocalDate fecha = LocalDate.now();
		vehiculoPrueba = new Turismo(id, "0001AAA", fecha, TipoMotor.ELECTRICO, 15.5);
		
		//comprobamos que la fecha de matriculacion no sea valida.
		assertTrue("Fecha de matriculacion no valida", vehiculoPrueba.getFechaMatriculacion() != null);
		//comprobamos que la fecha de matriculacion no sea superior a la fecha actual.
		assertTrue("Fecha de matriculacion supera la fecha actual", vehiculoPrueba.getFechaMatriculacion().compareTo(LocalDate.now()) < 1 );
		
	}
	
	
	@Test
	public void testTurismoTipoMotor() {
		Turismo vehiculoPrueba;
		long id = (long) 3;
		LocalDate fecha = LocalDate.now();
		vehiculoPrueba = new Turismo(id, "0001AAA", fecha, TipoMotor.DIESEL, 12.3);
		TipoMotor motor = vehiculoPrueba.getMotor();
		//comprobamos que el vehiculo tiene un motor no nulo
		assertTrue("Tipo motor nulo", motor != null);
		//comprobamos que el tipo de motor es uno de los enumerados
		
		assertTrue("Tipo de motor no valido", motor == TipoMotor.DIESEL || motor == TipoMotor.ELECTRICO ||
											  motor == TipoMotor.GASOLINA || motor == TipoMotor.HIBRIDO);
	}
	
	

}
