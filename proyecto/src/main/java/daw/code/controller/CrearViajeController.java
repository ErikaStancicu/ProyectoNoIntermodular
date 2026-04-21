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

/**
 * Controlador de la vista de crear viaje.
 *
 * @author Erika Stancicu
 * @version 1.0
 */
public class CrearViajeController {

    @FXML private TextField txtDestino;
    @FXML private DatePicker dpFechaInicio;
    @FXML private DatePicker dpFechaFin;
    @FXML private TextField txtPresupuesto;
    @FXML private ComboBox<String> cbEstado;
    @FXML private Button btnGuardar;
    @FXML private Button btnCancelar;

    private GestionViajes viajeAEditar;

    /**
     * Inicializa la vista de crear viaje.
     */
    @FXML
    public void initialize() {
        cbEstado.getItems().addAll("Pendiente", "En curso", "Realizado");
        cbEstado.setValue("Pendiente");

        viajeAEditar = GestionViajesService.getViajeAEditar();

        if (viajeAEditar != null) {
            txtDestino.setText(viajeAEditar.getDestino());
            dpFechaInicio.setValue(viajeAEditar.getFechaInicio());
            dpFechaFin.setValue(viajeAEditar.getFechaFin());
            txtPresupuesto.setText(String.valueOf(viajeAEditar.getPresupuesto()));
            cbEstado.setValue(viajeAEditar.getEstado());
        }
    }

    /**
     * Guarda un viaje nuevo o actualiza uno existente.
     *
     * @throws IOException si ocurre un error al cargar la vista principal
     */
    @FXML
    private void guardarViaje() throws IOException {
        String destino = txtDestino.getText().trim();
        LocalDate fechaInicio = dpFechaInicio.getValue();
        LocalDate fechaFin = dpFechaFin.getValue();
        String estado = cbEstado.getValue();

        if (destino.isEmpty() || fechaInicio == null || fechaFin == null || estado == null || txtPresupuesto.getText().trim().isEmpty()) {
            mostrarAlerta("Todos los campos son obligatorios");
            return;
        }

        if (!destino.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarAlerta("El destino no puede contener numeros");
            return;
        }

        double presupuesto;
        try {
            presupuesto = Double.parseDouble(txtPresupuesto.getText().trim());
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

        if (viajeAEditar == null) {
            GestionViajes viaje = new GestionViajes(destino, fechaInicio, fechaFin, presupuesto, estado);
            GestionViajesService.guardarViaje(viaje);
        } else {
            viajeAEditar.setDestino(destino);
            viajeAEditar.setFechaInicio(fechaInicio);
            viajeAEditar.setFechaFin(fechaFin);
            viajeAEditar.setPresupuesto(presupuesto);
            viajeAEditar.setEstado(estado);
        }

        GestionViajesService.limpiarViajeAEditar();
        volverVistaPrincipal();
    }

    /**
     * Cancela la operación y vuelve a la vista principal.
     *
     * @throws IOException si ocurre un error al cargar la vista principal
     */
    @FXML
    private void cancelar() throws IOException {
        GestionViajesService.limpiarViajeAEditar();
        volverVistaPrincipal();
    }

    /**
     * Vuelve a la vista principal.
     *
     * @throws IOException si ocurre un error al cargar la vista
     */
    private void volverVistaPrincipal() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vista_principal.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Muestra un mensaje de aviso.
     *
     * @param mensaje texto que se muestra en la alerta
     */
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}