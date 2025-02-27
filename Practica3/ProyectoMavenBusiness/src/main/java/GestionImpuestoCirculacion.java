
public class GestionImpuestoCirculacion implements IInfoImpuestoCirculacion, IGestionContribuyentes, IGestionVehiculos{
	public GestionImpuestoCirculacion(ContribuyentesDAO c, VehiculosDAO v) {
		
	}

	@Override
	public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo)
			throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contribuyente bajaContribuyente(String dni) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehiculo vehiculo(String matricula) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contribuyente contribuyente(String dni) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
