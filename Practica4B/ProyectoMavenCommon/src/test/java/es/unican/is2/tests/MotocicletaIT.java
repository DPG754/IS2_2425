package es.unican.is2.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.unican.is2.java.Motocicleta;
import es.unican.is2.java.TipoMotor;

public class MotocicletaIT {

    LocalDate hoy = LocalDate.now();

    // Casos válidos - Clases de equivalencia por potencia

    @Test
    @DisplayName("Cilindrada < 125, sin bonificación")
    public void testPotenciaMenor8() {
        Motocicleta m = new Motocicleta(1L, "1234UABC", hoy.minusYears(10), TipoMotor.GASOLINA, 79);
        assertEquals(8.0, m.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Cilindrada entre 125 y 250")
    public void testPotencia8a12() {
    	Motocicleta m = new Motocicleta(2L, "0000AAA", hoy.minusYears(10), TipoMotor.GASOLINA, 150);
        assertEquals(15.0, m.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Cilindrada entre 250 y 500")
    public void testPotencia12a16() {
    	Motocicleta m = new Motocicleta(3L, "9999ZZZ", hoy.minusYears(10), TipoMotor.GASOLINA, 300);
        assertEquals(30.0, m.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Cilindrada entre 500 y 1000")
    public void testPotencia16a20() {
    	Motocicleta m = new Motocicleta(4L, "1111ABC", hoy.minusYears(10), TipoMotor.GASOLINA, 600);
        assertEquals(60.0, m.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Cilindrada >= 1000")
    public void testPotenciaMayorIgual20() {
    	Motocicleta m = new Motocicleta(5L, "2222ABC", hoy.minusYears(10), TipoMotor.GASOLINA, 2500);
        assertEquals(120.0, m.precioImpuesto(), 0.01);
    }

    // Casos válidos - Bonificaciones

    @Test
    @DisplayName("Bonificación 100% por antigüedad > 25 años")
    public void testBonificacion100PorAntiguedad() {
    	Motocicleta m = new Motocicleta(6L, "3333ABC", hoy.minusYears(26), TipoMotor.GASOLINA, 250);
        assertEquals(0.0, m.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Bonificación 75% por ser eléctrico")
    public void testBonificacion75Electrico() {
    	Motocicleta m = new Motocicleta(7L, "4444ABC", hoy.minusYears(5), TipoMotor.ELECTRICO, 300);
        assertEquals(30.0 * 0.25, m.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Bonificación 75% por ser híbrido < 4 años")
    public void testBonificacion75Hibrido() {
    	Motocicleta m = new Motocicleta(8L, "5555ABC", hoy.minusYears(2), TipoMotor.HIBRIDO, 300);
        assertEquals(30.0 * 0.25, m.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Bonificación 50% por ser gas < 1 año")
    public void testBonificacion50Gas() {
    	Motocicleta m = new Motocicleta(9L, "6666ABC", hoy.minusMonths(6), TipoMotor.GAS, 300);
        assertEquals(30.0 * 0.50, m.precioImpuesto(), 0.01);
    }

    // Casos no válidos

    @Test
    @DisplayName("Cilindrada negativa - sin validación")
    public void testPotenciaNegativa() {
    	Motocicleta m = new Motocicleta(10L, "7777ABC", hoy.minusYears(5), TipoMotor.GASOLINA, -200);
        assertEquals(8.0, m.precioImpuesto(), 0.01);
    }

    @Test
    @DisplayName("Fecha futura - sin validación")
    public void testFechaFutura() {
    	Motocicleta m = new Motocicleta(11L, "8888ABC", hoy.plusDays(5), TipoMotor.GASOLINA, 200);
        assertTrue(m.precioImpuesto() >= 0);
    }

    @Test
    @DisplayName("Motor null - lanza NullPointerException")
    public void testMotorNull() {
    	TipoMotor motor = null;
        assertThrows(NullPointerException.class, () -> {
            new Motocicleta(12L, "9999ABC", hoy, motor, 200).precioImpuesto();
        });
    }

    @Test
    @DisplayName("Id negativo - sin validación")
    public void testIdNegativo() {
    	Motocicleta m = new Motocicleta(-1L, "0001AAA", hoy, TipoMotor.ELECTRICO, 200);
        assertTrue(m.precioImpuesto() >= 0);
    }

    @Test
    @DisplayName("Matrícula incorrecta - sin validación")
    public void testMatriculaInvalida() {
    	Motocicleta m = new Motocicleta(13L, "ABC1234", hoy, TipoMotor.ELECTRICO, 200);
        assertTrue(m.precioImpuesto() >= 0);
    }

}
