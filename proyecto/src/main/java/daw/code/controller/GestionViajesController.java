package daw.code.controller;

import daw.code.model.GestionViajes;
import daw.code.service.GestionViajesService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;

public class GestionViajesController {

    @FXML private Button btnCrearViaje;
    @FXML private ComboBox<String> filtrarPorEstado;

    @FXML private TableView<GestionViajes> tablaViajes;
    @FXML private TableColumn<GestionViajes, String> colDestino;
    @FXML private TableColumn<GestionViajes, LocalDate> colFechaInicio;
    @FXML private TableColumn<GestionViajes, LocalDate> colFechaFin;
    @FXML private TableColumn<GestionViajes, Double> colPresupuesto;
    @FXML private TableColumn<GestionViajes, String> colEstado;

    @FXML
    public void initialize() {
        filtrarPorEstado.getItems().addAll("Todos", "Pendiente", "En curso", "Realizado");
        filtrarPorEstado.setValue("Todos");

        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colPresupuesto.setCellValueFactory(new PropertyValueFactory<>("presupuesto"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        cargarTabla();

        filtrarPorEstado.setOnAction(e -> aplicarFiltroEstado());
    }

    private void cargarTabla() {
        tablaViajes.setItems(FXCollections.observableArrayList(GestionViajesService.obtenerViajes()));
    }

    @FXML
    private void aplicarFiltroEstado() {
        String estado = filtrarPorEstado.getValue();
        tablaViajes.setItems(FXCollections.observableArrayList(GestionViajesService.filtrarPorEstado(estado)));
    }

    @FXML
    private void abrirCrearViaje() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vista_crear_viaje.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnCrearViaje.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}