package daw.code.model;

import java.time.LocalDate;

/**
 * Clase que representa un viaje de la aplicación.
 *
 * @author Erika Stancicu
 * @version 1.0
 */

public class GestionViajes {

    private String destino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double presupuesto;
    private String estado;

    /**
     * Constructor de la clase GestionViajes.
     *
     * @param destino destino del viaje
     * @param fechaInicio fecha de inicio del viaje
     * @param fechaFin fecha de fin del viaje
     * @param presupuesto presupuesto total del viaje
     * @param estado estado del viaje
     */
    public GestionViajes(String destino, LocalDate fechaInicio, LocalDate fechaFin, double presupuesto, String estado) {
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.presupuesto = presupuesto;
        this.estado = estado;
    }

    /**
     * Devuelve el destino del viaje.
     *
     * @return destino del viaje
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Modifica el destino del viaje.
     *
     * @param destino nuevo destino del viaje
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * Devuelve la fecha de inicio del viaje.
     *
     * @return fecha de inicio
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Modifica la fecha de inicio del viaje.
     *
     * @param fechaInicio nueva fecha de inicio
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Devuelve la fecha de fin del viaje.
     *
     * @return fecha de fin
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Modifica la fecha de fin del viaje.
     *
     * @param fechaFin nueva fecha de fin
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Devuelve el presupuesto del viaje.
     *
     * @return presupuesto del viaje
     */
    public double getPresupuesto() {
        return presupuesto;
    }

    /**
     * Modifica el presupuesto del viaje.
     *
     * @param presupuesto nuevo presupuesto del viaje
     */
    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    /**
     * Devuelve el estado del viaje.
     *
     * @return estado del viaje
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Modifica el estado del viaje.
     *
     * @param estado nuevo estado del viaje
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}