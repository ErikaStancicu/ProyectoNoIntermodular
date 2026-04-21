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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Controlador de la vista principal de la aplicación.
 *
 * @author Erika Stancicu
 * @version 1.0
 */

public class GestionViajesController {

    @FXML private Button btnCrearViaje;
    @FXML private ComboBox<String> filtrarPorEstado;

    @FXML private TableView<GestionViajes> tablaViajes;
    @FXML private TableColumn<GestionViajes, String> colDestino;
    @FXML private TableColumn<GestionViajes, LocalDate> colFechaInicio;
    @FXML private TableColumn<GestionViajes, LocalDate> colFechaFin;
    @FXML private TableColumn<GestionViajes, Double> colPresupuesto;
    @FXML private TableColumn<GestionViajes, String> colEstado;
    @FXML private TableColumn<GestionViajes, Void> colAcciones;

    /**
     * Inicializa la vista principal.
     */
    @FXML
    public void initialize() {
        filtrarPorEstado.getItems().addAll("Todos", "Pendiente", "En curso", "Realizado");
        filtrarPorEstado.setValue("Todos");

        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colPresupuesto.setCellValueFactory(new PropertyValueFactory<>("presupuesto"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        configurarColumnaAcciones();
        cargarTabla();

        filtrarPorEstado.setOnAction(e -> aplicarFiltroEstado());
    }

    /**
     * Carga todos los viajes almacenados en la tabla.
     */
    private void cargarTabla() {
        tablaViajes.setItems(FXCollections.observableArrayList(GestionViajesService.obtenerViajes()));
    }

    /**
     * Filtra los viajes segun el estado seleccionado.
     */
    @FXML
    private void aplicarFiltroEstado() {
        String estado = filtrarPorEstado.getValue();
        tablaViajes.setItems(FXCollections.observableArrayList(GestionViajesService.filtrarPorEstado(estado)));
    }

    /**
     * Abre la vista para crear un viaje.
     *
     * @throws IOException si ocurre un error al cargar la vista
     */
    @FXML
    private void abrirCrearViaje() throws IOException {
        GestionViajesService.limpiarViajeAEditar();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vista_crear_viaje.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnCrearViaje.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Configura la columna de acciones de la tabla.
     */
    private void configurarColumnaAcciones() {
        colAcciones.setCellFactory(param -> new TableCell<>() {
            private final Button btnEditar = new Button("Editar");
            private final Button btnEliminar = new Button("Eliminar");
            private final HBox contenedor = new HBox(8, btnEditar, btnEliminar);

            {
                btnEditar.setOnAction(event -> {
                    GestionViajes viaje = getTableView().getItems().get(getIndex());
                    GestionViajesService.setViajeAEditar(viaje);

                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/vista_crear_viaje.fxml"));
                        Parent root = loader.load();

                        Stage stage = (Stage) tablaViajes.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                btnEliminar.setOnAction(event -> {
                    GestionViajes viaje = getTableView().getItems().get(getIndex());
                    GestionViajesService.eliminarViaje(viaje);
                    aplicarFiltroEstado();
                });
            }

            /**
             * Actualiza el contenido grafico de cada celda
             * de la columna de acciones.
             *
             * @param item valor de la celda
             * @param empty indica si la celda esta vacia
             */
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(contenedor);
                }
            }
        });
    }
}