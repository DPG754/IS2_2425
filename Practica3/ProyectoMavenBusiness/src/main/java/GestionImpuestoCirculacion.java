public class GestionImpuestoCirculacion implements IInfoImpuestoCirculacion, IGestionContribuyentes, IGestionVehiculos {

    private ContribuyentesDAO contribuyentesDAO;
    private IVehiculosDAO vehiculosDAO;

    public GestionImpuestoCirculacion(ContribuyentesDAO c, IVehiculosDAO v) {
        this.contribuyentesDAO = c;
        this.vehiculosDAO = v;
    }

    @Override
    public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValidaException, DataAccessException {
        Contribuyente c = contribuyentesDAO.contribuyente(dni);
        if (c == null) {
            throw new OperacionNoValidaException("Contribuyente no encontrado.");
        }
        if (vehiculosDAO.vehiculoPorMatricula(v.getMatricula()) != null) {
            throw new OperacionNoValidaException("El vehículo ya está registrado.");
        }
        c.getVehiculos().add(v);
        return vehiculosDAO.creaVehiculo(v);
    }

    @Override
    public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValidaException, DataAccessException {
        Vehiculo v = vehiculosDAO.vehiculoPorMatricula(matricula);
        Contribuyente c = contribuyentesDAO.contribuyente(dni);
        
        if (v == null || c == null) {
            throw new OperacionNoValidaException("Vehículo o contribuyente no encontrado.");
        }
        if (!c.getVehiculos().removeIf(vehiculo -> vehiculo.getMatricula().equals(matricula))) {
            throw new OperacionNoValidaException("El vehículo no pertenece al contribuyente.");
        }
        return vehiculosDAO.eliminaVehiculo(matricula);
    }

    @Override
    public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo) throws OperacionNoValidaException, DataAccessException {
        Vehiculo v = vehiculosDAO.vehiculoPorMatricula(matricula);
        Contribuyente actual = contribuyentesDAO.contribuyente(dniActual);
        Contribuyente nuevo = contribuyentesDAO.contribuyente(dniNuevo);
        
        if (v == null || actual == null || nuevo == null) {
            throw new OperacionNoValidaException("Datos incorrectos.");
        }
        if (!actual.getVehiculos().contains(v)) {
            throw new OperacionNoValidaException("El vehículo no pertenece al contribuyente actual.");
        }
        actual.getVehiculos().removeIf(vehiculo -> vehiculo.getMatricula().equals(matricula));
        nuevo.getVehiculos().add(v);
        vehiculosDAO.actualizaVehiculo(v);
        return true;
    }

    @Override
    public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
        if (contribuyentesDAO.contribuyente(c.getDni()) != null) {
            throw new OperacionNoValidaException("El contribuyente ya existe.");
        }
        return contribuyentesDAO.creaContribuyente(c);
    }

    @Override
    public Contribuyente bajaContribuyente(String dni) throws OperacionNoValidaException, DataAccessException {
        Contribuyente c = contribuyentesDAO.contribuyente(dni);
        if (c == null) {
            throw new OperacionNoValidaException("El contribuyente no existe.");
        }
        if (!c.getVehiculos().isEmpty()) {
            throw new OperacionNoValidaException("El contribuyente tiene vehículos registrados.");
        }
        return contribuyentesDAO.eliminaContribuyente(dni);
    }

    @Override
    public Vehiculo vehiculo(String matricula) throws DataAccessException {
        return vehiculosDAO.vehiculoPorMatricula(matricula);
    }

    @Override
    public Contribuyente contribuyente(String dni) throws DataAccessException {
        return contribuyentesDAO.contribuyente(dni);
    }
}