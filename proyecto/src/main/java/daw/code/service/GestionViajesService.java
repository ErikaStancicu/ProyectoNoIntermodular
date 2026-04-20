package daw.code.service;

import daw.code.model.GestionViajes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestionViajesService {

    private static final List<GestionViajes> listaViajes = new ArrayList<>();

    public static void guardarViaje(GestionViajes viaje) {
        listaViajes.add(viaje);
    }

    public static List<GestionViajes> obtenerViajes() {
        return listaViajes;
    }

    public static List<GestionViajes> filtrarPorEstado(String estado) {
        if (estado == null || estado.equals("Todos")) {
            return listaViajes;
        }

        return listaViajes.stream()
                .filter(v -> v.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }
}
