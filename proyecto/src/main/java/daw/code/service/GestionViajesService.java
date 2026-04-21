package daw.code.service;

import daw.code.model.GestionViajes;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona los viajes de la aplicación.
 *
 * @author Erika Stancicu
 * @version 1.0
 */

public class GestionViajesService {

    private static final List<GestionViajes> listaViajes = new ArrayList<>();
    private static GestionViajes viajeAEditar = null;

    /**
     * Guarda un viaje en la lista de viajes.
     *
     * @param viaje viaje que se desea guardar
     */
    public static void guardarViaje(GestionViajes viaje) {
        listaViajes.add(viaje);
    }

    /**
     * Devuelve la lista completa de viajes.
     *
     * @return lista de viajes guardados
     */
    public static List<GestionViajes> obtenerViajes() {
        return listaViajes;
    }

    /**
     * Filtra los viajes por estado.
     *
     * @param estado estado por el que se filtran los viajes
     * @return lista de viajes filtrados
     */
    public static List<GestionViajes> filtrarPorEstado(String estado) {
        if (estado == null || estado.equals("Todos")) {
            return listaViajes;
        }

        List<GestionViajes> viajesFiltrados = new ArrayList<>();

        for (GestionViajes viaje : listaViajes) {
            if (viaje.getEstado().equalsIgnoreCase(estado)) {
                viajesFiltrados.add(viaje);
            }
        }
        return viajesFiltrados;
    }

    /**
     * Elimina un viaje de la lista.
     *
     * @param viaje viaje que se desea eliminar
     */
    public static void eliminarViaje(GestionViajes viaje) {
        listaViajes.remove(viaje);
    }

    /**
     * Devuelve el viaje que se va a editar.
     *
     * @return viaje seleccionado para editar
     */
    public static GestionViajes getViajeAEditar() {
        return viajeAEditar;
    }

    /**
     * Guarda el viaje que se va a editar.
     *
     * @param viaje viaje seleccionado para editar
     */
    public static void setViajeAEditar(GestionViajes viaje) {
        viajeAEditar = viaje;
    }

    /**
     * Limpia el viaje que se estaba editando.
     */
    public static void limpiarViajeAEditar() {
        viajeAEditar = null;
    }
}