package daw.code.controller;

import daw.code.model.GestionViajes;
import daw.code.service.GestionViajesService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class CrearViajeController {

    @FXML private TextField txtDestino;
    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private TextField txtPresupuesto;
    @FXML private ComboBox<String> cbEstado;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;

    @FXML
    public void initialize() {
        cbEstado.getItems().addAll("Pendiente", "En curso", "Realizado");
    }

    @FXML
    private void guardarViaje() throws IOException {
        String destino = txtDestino.getText();
        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFin = dpFechaFin.getValue();
        String estado = cbEstado.getValue();

        if (destino.isEmpty() || fechaInicio == null || fechaFin == null || estado == null || txtPresupuesto.getText().isEmpty()) {
            mostrarAlerta("Todos los campos son obligatorios");
            return;
        }

        double presupuesto;
        try {
            presupuesto = Double.parseDouble(txtPresupuesto.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("El presupuesto debe ser un numero");
            return;
        }

        if (presupuesto < 0) {
            mostrarAlerta("El presupuesto no puede ser negativo");
            return;
        }

        if (fechaFin.isBefore(fechaInicio)) {
            mostrarAlerta("La fecha fin no puede ser anterior a la fecha inicio");
            return;
        }

        GestionViajes viaje = new GestionViajes(destino, fechaInicio, fechaFin, presupuesto, estado);
        GestionViajesService.guardarViaje(viaje);

        volverVistaPrincipal();
    }

    @FXML
    private void cancelar() throws IOException {
        volverVistaPrincipal();
    }

    private void volverVistaPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vista_principal.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}