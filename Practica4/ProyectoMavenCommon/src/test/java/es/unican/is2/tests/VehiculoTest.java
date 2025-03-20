package es.unican.is2.tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.java.*;


public class VehiculoTest {

    private Turismo turismo;
    private Motocicleta moto;
    private LocalDate hoy = LocalDate.now();

    @Test
    public void testTurismoId() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusYears((long) 20), TipoMotor.GASOLINA, 7.9);
        assertTrue("El precio impuesto no es correcto", 25.0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoMinMatricula() {
    	turismo = new Turismo((long) 16,"0000AAA", hoy.minusYears((long) 20), TipoMotor.GASOLINA, 8.0);
        assertTrue("El precio impuesto no es correcto", 67.0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoMaxMatricula() {
    	turismo = new Turismo((long) 16,"9999ZZZ", hoy.minusYears((long) 20), TipoMotor.GASOLINA, 10.0);
        assertTrue("El precio impuesto no es correcto", 67.0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoFechMatrhoy() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy, TipoMotor.GASOLINA, 11.9);
        assertTrue("El precio impuesto no es correcto", 67.0 == turismo.precioImpuesto());
    }
    @Test
    public void testTurismoFechMatrEntreHoyY1año() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusMonths((long) 6), TipoMotor.GASOLINA, 12.0);
        assertTrue("El precio impuesto no es correcto", 143.0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoFechMatrMenor1año() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusYears((long) 1).plusDays(1), TipoMotor.GASOLINA, 14.0);
        assertTrue("El precio impuesto no es correcto", 143.0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoFechMatrIgual1año() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusYears((long) 1), TipoMotor.GASOLINA, 15.9);
        assertTrue("El precio impuesto no es correcto", 143.0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoFechMatrEntre1y4años() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusYears((long) 3), TipoMotor.GASOLINA, 16.0);
        assertTrue("El precio impuesto no es correcto", 178.0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoFechMatrMenor4años() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusYears((long) 4).plusDays(1), TipoMotor.GASOLINA, 18.0);
        assertTrue("El precio impuesto no es correcto", 178.0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoFechMatrIgual25años() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusYears((long) 25), TipoMotor.GASOLINA, 19.9);
        assertTrue("El precio impuesto no es correcto", 0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoFechMatrMayor25años() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusYears((long) 30), TipoMotor.GASOLINA, 20.0);
        assertTrue("El precio impuesto no es correcto", 0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoGasolina() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusYears((long) 20), TipoMotor.GASOLINA, 25.0);
        assertTrue("El precio impuesto no es correcto", 223.0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoDiesel() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusYears((long) 20), TipoMotor.DIESEL, 35.0);
        assertTrue("El precio impuesto no es correcto", 223.0 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoHibrido() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusYears((long) 3), TipoMotor.HIBRIDO, 80.0);
        assertTrue("El precio impuesto no es correcto", 55.75 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoGas() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusMonths((long) 6), TipoMotor.GAS, 100.0);
        assertTrue("El precio impuesto no es correcto", 111.5 == turismo.precioImpuesto());
        
    }
    @Test
    public void testTurismoElectrico() {
    	turismo = new Turismo((long) 16,"1111AAA", hoy.minusYears((long) 20), TipoMotor.ELECTRICO, 200.0);
        assertTrue("El precio impuesto no es correcto", 55.75 == turismo.precioImpuesto());
        
    }
//
//    @Test
//    public void testX() {}
//    Motocicleta vehiculoPrueba;
//    Long id = (long) 1;
//    
//    @Test
//    public void testMotoInfMat() {
//    	//limite inferior matricula
//    	vehiculoPrueba = new Motocicleta(id, "0000AAA", LocalDate.now(), TipoMotor.GASOLINA, 100);
//    	assertTrue("El precio impuesto no es correcto", 8 == vehiculoPrueba.precioImpuesto());
//    }
//
//
//	//limite superior matricula
//	vehiculoPrueba = new Motocicleta(id, "9999ZZZ", LocalDate.now(), TipoMotor.GASOLINA, 100);
//	//fecha hoy - 6 meses
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", LocalDate.now().minusMonths((long) 6), TipoMotor.GASOLINA, 100);
//	//fecha hoy - 1 año + 1 dia
//	fecha = LocalDate.now().minusYears((long) 1);
//	fecha = fecha.plusDays((long) 1);
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GASOLINA, 100);
//	//fecha hoy -1 año
//	fecha = LocalDate.now().minusYears((long) 1);
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GASOLINA, 100);
//	//fecha hoy -3 años
//	fecha = LocalDate.now().minusYears((long) 3);
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GASOLINA, 100);
//	//fecha hoy -4 años + 1 dia
//	fecha = LocalDate.now().minusYears((long) 4);
//	fecha = LocalDate.now().plusDays((long) 1);
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GASOLINA, 100);
//	//fecha hoy -25 años
//	fecha = LocalDate.now().minusYears((long) 25);
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GASOLINA, 100);
//	//fecha hoy -30 años
//	fecha = LocalDate.now().minusYears((long) 30);
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GASOLINA, 100);
//	//tipo motor = Diesel
//	fecha = LocalDate.now();
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.DIESEL, 100);
//	//tipo motor Electrico
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.ELECTRICO, 100);
//	//tipo motor Gas
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GAS, 100);
//	//tipo motor hibrido
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.HIBRIDO, 100);
//	//cilindrada 125
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GASOLINA, 125);
//	//cilindrada 250
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GAS, 250);
//	//cilindrada 500
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GAS, 500);
//	//cilindrada 1000
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GAS, 1000);
//	//cilindrada >1000
//	vehiculoPrueba = new Motocicleta(id, "0001AAA", fecha, TipoMotor.GAS, 1015);

//    @Test
//    public void testCalculoImpuestoMoto() {
//        assertEquals(75.0, moto.calcularImpuesto(), 0.01);
//    }
//
//    @Test
//    public void testVehiculoInvalido() {
//        try {
//            new Turismo("", 1800, "Gasolina", -5);
//            fail("Se esperaba una excepción");
//        } catch (IllegalArgumentException e) {
//            assertTrue(true);
//        }
//    }
}
//public class VehiculoTest {
//
//	@Test
//	public void mainTest() {
//		
//		//se crean los vehiculos
//		ArrayList<Turismo> vehiculosPrueba = new ArrayList<Turismo>();
//		Long id;
//		LocalDate fecha = LocalDate.now();
//		//Turismo valido
//		id = (long) 1;
//		vehiculosPrueba.add(new Turismo(id, "0001AAA", fecha, TipoMotor.ELECTRICO, 15.5));
//		//Turismo no valido por id negativo
//		id = (long) -1;
//		//vehiculosPrueba.add(new Turismo(id, "0001AAB", fecha, TipoMotor.GASOLINA, 20.5));
//		//Turismo no valido por id nulo
//		id = null;
//		//vehiculosPrueba.add(new Turismo(id, "0001AAC", fecha, TipoMotor.DIESEL, 40.8));
//		//Turismo no valido por matricula nula
//		String matricula = null;
//		id = (long) 2;
//		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, TipoMotor.HIBRIDO, 23.1));
//		//Turismo no valido por matricula vacia;
//		matricula = " ";
//		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, TipoMotor.HIBRIDO, 14.6));
//		//Turismo no valido por fecha de matriculacion
//		matricula = "0001AAD";
//		//vehiculosPrueba.add(new Turismo(id, matricula, fecha.plusDays((long) 3), TipoMotor.ELECTRICO, 42.32));
//		//turismo no valido por fecha nula
//		fecha = null;
//		id = (long) 4;
//		matricula = "0001AAE";
//		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, TipoMotor.ELECTRICO, 7.1));
//		//Turismo no valido por fecha con fomato incorrecto
//		fecha = LocalDate.now().withYear(31);
//		id = (long) 5;
//		matricula = "0001AAF";
//		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, TipoMotor.DIESEL, 7.1));
//		//Turismo no valido por motor no valido
//		fecha = LocalDate.now();
//		id = (long) 6;
//		matricula = "0001AAG";
//		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, TipoMotor.GAS, 7.1));
//		//Turismo no valido por motor nulo
//		id = (long) 7;
//		matricula = "0001AAH";
//		TipoMotor motor = null;
//		//vehiculosPrueba.add(new Turismo(id, matricula, fecha, motor, 19.3));
//		
//	}
//	
//	@Test
//	public void testTurismoId() {
//		Turismo vehiculoPrueba;
//		Long id;
//		LocalDate fecha = LocalDate.now();
//		//Turismo valido
//		id = (long) 1;
//		vehiculoPrueba = new Turismo(id, "0001AAA", fecha, TipoMotor.ELECTRICO, 15.5);
//		
//		id = vehiculoPrueba.getId();
//		//Comprobamos id no negativo
//		assertTrue("Id no valido por negativo", id >= 0);
//		//Comprobamos id no nulo
//		assertTrue("Id no valido por nulo", id != null);
//		
//
//		
//	}
//	
//	@Test
//	public void testTurismoMatricula() {
//		Turismo vehiculoPrueba;
//		Long id = (long) 1;
//		LocalDate fecha = LocalDate.now();
//		vehiculoPrueba = new Turismo(id, "0001AAA", fecha, TipoMotor.ELECTRICO, 15.5);
//		String matricula = vehiculoPrueba.getMatricula();
//		//comprobamos el valor de la matricula
//		assertTrue("Matricula no valida por nula", matricula != null);
//		
//	}
//	
//	@Test
//	public void testTurismoFechaMatriculacion() {
//		Turismo vehiculoPrueba;
//		long id = (long) 2;
//		LocalDate fecha = LocalDate.now();
//		vehiculoPrueba = new Turismo(id, "0001AAA", fecha, TipoMotor.ELECTRICO, 15.5);
//		
//		//comprobamos que la fecha de matriculacion no sea valida.
//		assertTrue("Fecha de matriculacion no valida", vehiculoPrueba.getFechaMatriculacion() != null);
//		//comprobamos que la fecha de matriculacion no sea superior a la fecha actual.
//		assertTrue("Fecha de matriculacion supera la fecha actual", vehiculoPrueba.getFechaMatriculacion().compareTo(LocalDate.now()) < 1 );
//		
//	}
//	
//	
//	@Test
//	public void testTurismoTipoMotor() {
//		Turismo vehiculoPrueba;
//		long id = (long) 3;
//		LocalDate fecha = LocalDate.now();
//		vehiculoPrueba = new Turismo(id, "0001AAA", fecha, TipoMotor.DIESEL, 12.3);
//		TipoMotor motor = vehiculoPrueba.getMotor();
//		//comprobamos que el vehiculo tiene un motor no nulo
//		assertTrue("Tipo motor nulo", motor != null);
//		//comprobamos que el tipo de motor es uno de los enumerados
//		
//		assertTrue("Tipo de motor no valido", motor == TipoMotor.DIESEL || motor == TipoMotor.ELECTRICO ||
//											  motor == TipoMotor.GASOLINA || motor == TipoMotor.HIBRIDO);
//	}
//	
//	
//
//}
