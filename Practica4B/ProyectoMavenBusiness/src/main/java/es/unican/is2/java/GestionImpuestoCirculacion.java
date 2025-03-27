package es.unican.is2.java;

import es.unican.is2.java.*;

public class GestionImpuestoCirculacion implements IInfoImpuestoCirculacion, IGestionContribuyentes, IGestionVehiculos {

    private IContribuyentesDAO contribuyentesDAO;
    private IVehiculosDAO vehiculosDAO;

    public GestionImpuestoCirculacion(IContribuyentesDAO c, IVehiculosDAO v) {
        this.contribuyentesDAO = c;
        this.vehiculosDAO = v;
    }

    @Override
    public Vehiculo altaVehiculo(Vehiculo v, String dni) throws OperacionNoValidaException, DataAccessException {
        Contribuyente c = contribuyentesDAO.contribuyente(dni);
        if (c == null) {
            return null;
        }
        if (vehiculosDAO.vehiculoPorMatricula(v.getMatricula()) != null) {
            throw new OperacionNoValidaException("El vehículo ya está registrado.");
        }
        c.getVehiculos().add(v);
        contribuyentesDAO.actualizaContribuyente(c); // ✅ Se guarda el cambio en la BD
        return vehiculosDAO.creaVehiculo(v);
    }

    @Override
    public Vehiculo bajaVehiculo(String matricula, String dni) throws OperacionNoValidaException, DataAccessException {
        Vehiculo v = vehiculosDAO.vehiculoPorMatricula(matricula);
        Contribuyente c = contribuyentesDAO.contribuyente(dni);
        
        if (v == null || c == null) {
            return null;
        }
        if (!c.getVehiculos().remove(v)) {  // ✅ Mejor eliminación directa
            throw new OperacionNoValidaException("El vehículo no pertenece al contribuyente.");
        }
        contribuyentesDAO.actualizaContribuyente(c); // ✅ Se actualiza la BD
        return vehiculosDAO.eliminaVehiculo(matricula);
    }

    @Override
    public boolean cambiaTitularVehiculo(String matricula, String dniActual, String dniNuevo) throws OperacionNoValidaException, DataAccessException {
        Vehiculo v = vehiculosDAO.vehiculoPorMatricula(matricula);
        Contribuyente actual = contribuyentesDAO.contribuyente(dniActual);
        Contribuyente nuevo = contribuyentesDAO.contribuyente(dniNuevo);
        
        if (v == null || actual == null || nuevo == null) {
            return false;
        }
        if (!actual.getVehiculos().contains(v)) {
            throw new OperacionNoValidaException("El vehículo no pertenece al contribuyente actual.");
        }
        actual.getVehiculos().remove(v); // ✅ Eliminamos correctamente
        nuevo.getVehiculos().add(v); // ✅ Se añade al nuevo titular
        contribuyentesDAO.actualizaContribuyente(actual); // ✅ Guardamos cambios en la BD
        contribuyentesDAO.actualizaContribuyente(nuevo);
        vehiculosDAO.actualizaVehiculo(v);
        return true;
    }

    @Override
    public Contribuyente altaContribuyente(Contribuyente c) throws DataAccessException {
        if (contribuyentesDAO.contribuyente(c.getDni()) != null) {
            return null;
        }
        return contribuyentesDAO.creaContribuyente(c);
    }

    @Override
    public Contribuyente bajaContribuyente(String dni) throws OperacionNoValidaException, DataAccessException {
        Contribuyente c = contribuyentesDAO.contribuyente(dni);
        if (c == null) {
            return null;
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