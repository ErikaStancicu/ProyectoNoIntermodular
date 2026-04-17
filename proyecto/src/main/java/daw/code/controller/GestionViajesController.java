package daw.code.controller;

import daw.code.model.GestionViajes;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;
import java.time.LocalDate;

public class GestionViajesController {

    @FXML private TableView<GestionViajes> tablaViajes;
    @FXML private TableColumn<GestionViajes, String> colDestino;
    @FXML private TableColumn<GestionViajes, LocalDate> colFechaInicio;
    @FXML private TableColumn<GestionViajes, LocalDate> colFechaFin;
    @FXML private TableColumn<GestionViajes, Double> colPresupuesto;
    @FXML private TableColumn<GestionViajes, Boolean> colEstado;

    @FXML
    private void crearViaje() {
        // código
    }

    @FXML
    private void filtrarPorEstado() {
        // código
    }

}
