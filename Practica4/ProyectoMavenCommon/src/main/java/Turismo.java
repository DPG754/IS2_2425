import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Clase que representa un vehiculo de tipo turismo.
 */
public class Turismo extends Vehiculo {

	private double potencia;
	
	public Turismo(long id, String matricula, LocalDate fechaMatriculacion, TipoMotor motor, double potencia) {
		super(id, matricula, fechaMatriculacion, motor);
		this.potencia = potencia;
	}

	/**
	 * Retorna la potencia en caballos fiscales del vehiculo.
	 */
	public double getPotencia() {
		return potencia;
	}

	@Override
	public double precioImpuesto() {
        double impuestoBase = calcularTarifaBase();
        double descuento = calcularDescuento(impuestoBase);
        return impuestoBase - descuento;
    }

    /**
     * Calcula la tarifa base del impuesto según la tabla de tarifas.
     */
    private double calcularTarifaBase() {
        if (potencia < 8) {
            return 25;
        } else if (potencia < 12) {
            return 67;
        } else if (potencia < 16) {
            return 143;
        } else if (potencia < 20) {
            return 178;
        } else {
            return 223;
        }
    }
    private double calcularDescuento(double impuestoBase) {
        LocalDate hoy = LocalDate.now();
        long antigüedad = ChronoUnit.YEARS.between(getFechaMatriculacion(), hoy);
        if (antigüedad > 25) {
            return impuestoBase;
        }

        // Bonificación del 75% si es eléctrico
        if (getMotor() == TipoMotor.ELECTRICO) {
            return impuestoBase * 0.75;
        }

        // Bonificación del 75% durante los primeros 4 años si es híbrido
        if (getMotor() == TipoMotor.HIBRIDO && antigüedad <= 4) {
            return impuestoBase * 0.75;
        }

        // Bonificación del 50% durante el primer año si es de gas
        if (getMotor() == TipoMotor.GAS && antigüedad < 1) {
            return impuestoBase * 0.50;
        }

        // Si no hay bonificación aplicable, retorna 0 descuento
        return 0;
    }
}
