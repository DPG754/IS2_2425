package es.unican.is2.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.unican.is2.java.Turismo;
import es.unican.is2.java.TipoMotor;

public class VehiculoIT {

    LocalDate hoy = LocalDate.now();

    // Casos válidos - Clases de equivalencia por potencia

    @Test
    @DisplayName("Potencia < 8, sin bonificación")
    public void testPotenciaMenor8() {
        Turismo t = new Turismo(1L, "1234UABC", hoy.minusYears(10), TipoMotor.GASOLINA, 7.9);
        assertEquals(25.0, t.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Potencia entre 8 y 12")
    public void testPotencia8a12() {
        Turismo t = new Turismo(2L, "0000AAA", hoy.minusYears(10), TipoMotor.GASOLINA, 10);
        assertEquals(67.0, t.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Potencia entre 12 y 16")
    public void testPotencia12a16() {
        Turismo t = new Turismo(3L, "9999ZZZ", hoy.minusYears(10), TipoMotor.GASOLINA, 14);
        assertEquals(143.0, t.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Potencia entre 16 y 20")
    public void testPotencia16a20() {
        Turismo t = new Turismo(4L, "1111ABC", hoy.minusYears(10), TipoMotor.GASOLINA, 18);
        assertEquals(178.0, t.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Potencia >= 20")
    public void testPotenciaMayorIgual20() {
        Turismo t = new Turismo(5L, "2222ABC", hoy.minusYears(10), TipoMotor.GASOLINA, 25);
        assertEquals(223.0, t.precioImpuesto(), 0.01);
    }

    // Casos válidos - Bonificaciones

    @Test
    @DisplayName("Bonificación 100% por antigüedad > 25 años")
    public void testBonificacion100PorAntiguedad() {
        Turismo t = new Turismo(6L, "3333ABC", hoy.minusYears(26), TipoMotor.GASOLINA, 25);
        assertEquals(0.0, t.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Bonificación 75% por ser eléctrico")
    public void testBonificacion75Electrico() {
        Turismo t = new Turismo(7L, "4444ABC", hoy.minusYears(5), TipoMotor.ELECTRICO, 14);
        assertEquals(143.0 * 0.25, t.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Bonificación 75% por ser híbrido < 4 años")
    public void testBonificacion75Hibrido() {
        Turismo t = new Turismo(8L, "5555ABC", hoy.minusYears(2), TipoMotor.HIBRIDO, 14);
        assertEquals(143.0 * 0.25, t.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Bonificación 50% por ser gas < 1 año")
    public void testBonificacion50Gas() {
        Turismo t = new Turismo(9L, "6666ABC", hoy.minusMonths(6), TipoMotor.GAS, 14);
        assertEquals(143.0 * 0.50, t.precioImpuesto(), 0.01);
    }

    // Casos no válidos

    @Test
    @DisplayName("Potencia negativa - sin validación")
    public void testPotenciaNegativa() {
        Turismo t = new Turismo(10L, "7777ABC", hoy.minusYears(5), TipoMotor.GASOLINA, -5);
        assertEquals(25.0, t.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Fecha futura - sin validación")
    public void testFechaFutura() {
        Turismo t = new Turismo(11L, "8888ABC", hoy.plusDays(5), TipoMotor.GASOLINA, 14);
        assertTrue(t.precioImpuesto() >= 0);
    }

    @Test
    @DisplayName("Motor null - lanza NullPointerException")
    public void testMotorNull() {
    	TipoMotor motor = null;
        assertThrows(NullPointerException.class, () -> {
            new Turismo(12L, "9999ABC", hoy, motor, 14).precioImpuesto();
        });
    }

    @Test
    @DisplayName("Id negativo - sin validación")
    public void testIdNegativo() {
        Turismo t = new Turismo(-1L, "0001AAA", hoy, TipoMotor.ELECTRICO, 14);
        assertTrue(t.precioImpuesto() >= 0);
    }

    @Test
    @DisplayName("Matrícula incorrecta - sin validación")
    public void testMatriculaInvalida() {
        Turismo t = new Turismo(13L, "ABC1234", hoy, TipoMotor.ELECTRICO, 14);
        assertTrue(t.precioImpuesto() >= 0);
    }
}
