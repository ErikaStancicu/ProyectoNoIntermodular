package daw.code.model;

import java.time.LocalDate;

public class GestionViajes {

    private String Destino;
    private LocalDate FechaInicio;
    private LocalDate FechaFin;
    private double Presupuesto;
    private String Estado;

    public GestionViajes(String destino, LocalDate fechaInicio, LocalDate fechaFin, double presupuesto, String estado) {
        Destino = destino;
        FechaInicio = fechaInicio;
        FechaFin = fechaFin;
        Presupuesto = presupuesto;
        Estado = estado;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String destino) {
        Destino = destino;
    }

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        FechaFin = fechaFin;
    }

    public double getPresupuesto() {
        return Presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        Presupuesto = presupuesto;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
