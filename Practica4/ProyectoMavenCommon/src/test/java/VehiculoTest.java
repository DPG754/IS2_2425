import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class VehiculoTest {

	@Test
	public void testVehiculo() {
		
		List<Turismo> vehiculosPrueba = new ArrayList<Turismo>();
		vehiculosPrueba.add(new Turismo((long) 1, "0001AAA", LocalDate.now(), TipoMotor.ELECTRICO, 15.5));
		vehiculosPrueba.getFirst().getMatricula();
		assertEquals("0001AAA" ,vehiculosPrueba.getFirst().getMatricula());;
	}

}
