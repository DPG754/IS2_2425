package es.unican.is2.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.java.Contribuyente;
import es.unican.is2.java.Turismo;
import es.unican.is2.java.TipoMotor;
import es.unican.is2.java.IInfoImpuestoCirculacion;
import es.unican.is2.java.Vehiculo;
import es.unican.is2.java.VistaFuncionario;

public class VistaFuncionarioIT {

    private FrameFixture window;
    private DummyInfo infoMock;

    @BeforeEach
    public void setUp() {
        infoMock = new DummyInfo();
        VistaFuncionario frame = GuiActionRunner.execute(() -> new VistaFuncionario(infoMock));
        window = new FrameFixture(frame);
        window.show();
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    @GUITest
    public void testBuscarContribuyente() {
        window.textBox("txtDniContribuyente").setText("12345678A");
        window.button(JButtonMatcher.withText("Buscar")).click();

        assertThat(window.textBox("txtNombreContribuyente").text()).isEqualTo("Pepe Pérez");
        assertThat(window.textBox("txtTotalContribuyente").text()).isEqualTo("143.0");
        assertThat(window.list("listMatriculasVehiculos").contents()).contains("0000ABC");
    }

    private class DummyInfo implements IInfoImpuestoCirculacion {
        private Contribuyente dummyContribuyente;

        public DummyInfo() {
            dummyContribuyente = new Contribuyente("Pepe", "Pérez", "García", "12345678A");
            dummyContribuyente.getVehiculos().add(new Turismo(1L, "0000ABC", LocalDate.now().minusYears(3), TipoMotor.GASOLINA, 12.0));
        }

        @Override
        public Contribuyente contribuyente(String dni) {
            return dni.equals(dummyContribuyente.getDni()) ? dummyContribuyente : null;
        }

        @Override
        public Vehiculo vehiculo(String matricula) {
            return dummyContribuyente.getVehiculos().stream()
                    .filter(v -> v.getMatricula().equals(matricula))
                    .findFirst()
                    .orElse(null);
        }
    }
}