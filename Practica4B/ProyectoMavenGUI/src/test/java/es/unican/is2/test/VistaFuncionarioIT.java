package es.unican.is2.test;
import static org.junit.jupiter.api.Assertions.*;
import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.java.Contribuyente;
import es.unican.is2.java.IInfoImpuestoCirculacion;
import es.unican.is2.java.TipoMotor;
import es.unican.is2.java.Turismo;
import es.unican.is2.java.Vehiculo;
import es.unican.is2.java.VistaFuncionario;

import javax.swing.SwingUtilities;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

class VistaFuncionarioIT {
    private FrameFixture window;

    // 🔹 Mock dentro de la misma clase
    private class MockInfoImpuestoCirculacion implements IInfoImpuestoCirculacion {
        @Override
        public Contribuyente contribuyente(String dni) {
            if ("11111111A".equals(dni)) {
                Contribuyente c = new Contribuyente("Juan", "Perez", "Lopez", "11111111A");

                // 🔹 Ajustar fecha al formato correcto
                LocalDate fecha = LocalDate.parse("2002-01-15", DateTimeFormatter.ISO_LOCAL_DATE);

                // 🔹 Crear vehículo con los datos de la tabla
                Turismo turismo = new Turismo(8L, "1111AAA", fecha, TipoMotor.GASOLINA, 15);
                c.getVehiculos().add(turismo);

                return c;
            }
            return null; // Simula que el contribuyente no existe
        }

        @Override
        public Vehiculo vehiculo(String matricula) {
            if ("1111AAA".equals(matricula)) {
                return new Turismo(8L, "1111AAA", 
                    LocalDate.parse("2002-01-15", DateTimeFormatter.ISO_LOCAL_DATE),
                    TipoMotor.GASOLINA, 15);
            }
            return null; // Simula que no existe el vehículo
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            VistaFuncionario vista = new VistaFuncionario(new MockInfoImpuestoCirculacion());
            vista.setVisible(true);
            window = new FrameFixture(vista);
        });
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }

    @Test
    void testConsultarContribuyente() {
        window.textBox("txtDniContribuyente").enterText("11111111A");
        window.button("btnBuscar").click();
        window.textBox("txtNombreContribuyente").requireText("Juan Lopez Perez");
        window.list("listMatriculasVehiculos").selectItem("1111AAA");
        window.list("listMatriculasVehiculos").requireSelectedItems("1111AAA");
    }
}
