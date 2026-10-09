
package vallegrande.edu.pe.misistema.view;

import java.sql.Timestamp;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import vallegrande.edu.pe.misistema.model.Usuario;

public class MainView extends BorderPane {

    // Botones del menú
    private Button btnInicio;
    private Button btnUsuarios;

    // Campos para visualizar la consulta seleccionada
    private TextField txtNombreOrganizacion;
    private TextField txtIdentificacionFiscal;
    private TextField txtTelefono;
    private TextField txtDireccion;
    private TextField txtResponsable;
    private TextField txtCorreo;
    private TextField txtDescripcion;

    // Botones de gestión
    private Button btnRegistrar;
    private Button btnActualizar;
    private Button btnEliminar;

    // Tabla de consultas
    private TableView<Usuario> tablaUsuarios;

    public MainView() {
        crearMenu();
        crearTabla();
        crearFormulario();
        mostrarInicio();
    }

    // Menú lateral
    private void crearMenu() {
        VBox menu = new VBox(15);
        menu.setPadding(new Insets(25));
        menu.setPrefWidth(220);

        Label titulo = new Label("NUTRILÁCTEOS");
        titulo.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );

        btnInicio = crearBotonMenu("Inicio");
        btnUsuarios = crearBotonMenu("Consultas");

        menu.getChildren().addAll(
                titulo,
                btnInicio,
                btnUsuarios
        );

        menu.setStyle("-fx-background-color: #063B00;");
        setLeft(menu);
    }

    private Button crearBotonMenu(String texto) {
        Button boton = new Button(texto);
        boton.setPrefWidth(170);
        boton.setPrefHeight(40);
        boton.setStyle(
                "-fx-background-color: white;" +
                        "-fx-text-fill: #063B00;" +
                        "-fx-font-weight: bold;"
        );
        return boton;
    }

    // Pantalla de inicio
    public void mostrarInicio() {
        VBox contenido = new VBox(15);
        contenido.setAlignment(Pos.CENTER);
        contenido.setPadding(new Insets(30));

        Label titulo = new Label("SISTEMA DE GESTIÓN");
        titulo.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #063B00;"
        );

        Label texto = new Label(
                "Administración de consultas de Nutrilácteos"
        );

        Label descripcion = new Label(
                "Selecciona Consultas para visualizar los registros."
        );

        contenido.getChildren().addAll(
                titulo,
                texto,
                descripcion
        );

        setCenter(contenido);
    }

    // Pantalla principal de consultas
    public void mostrarUsuarios() {
        VBox contenido = new VBox(15);
        contenido.setPadding(new Insets(20));

        Label titulo = new Label("GESTIÓN DE CONSULTAS");
        titulo.setStyle(
                "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #063B00;"
        );

        Label subtitulo = new Label(
                "Selecciona una consulta para ver sus datos y cambiar su estado."
        );

        // Botones
        HBox botones = new HBox(10);
        botones.setAlignment(Pos.CENTER_LEFT);

        botones.getChildren().add(btnActualizar);

        // Datos de la consulta seleccionada
        Label tituloDetalle = new Label("DETALLE DE LA CONSULTA");
        tituloDetalle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        GridPane formulario = new GridPane();
        formulario.setHgap(12);
        formulario.setVgap(10);

        formulario.add(new Label("Organización:"), 0, 0);
        formulario.add(txtNombreOrganizacion, 1, 0);

        formulario.add(new Label("Identificación:"), 2, 0);
        formulario.add(txtIdentificacionFiscal, 3, 0);

        formulario.add(new Label("Teléfono:"), 0, 1);
        formulario.add(txtTelefono, 1, 1);

        formulario.add(new Label("Dirección:"), 2, 1);
        formulario.add(txtDireccion, 3, 1);

        formulario.add(new Label("Responsable:"), 0, 2);
        formulario.add(txtResponsable, 1, 2);

        formulario.add(new Label("Correo:"), 2, 2);
        formulario.add(txtCorreo, 3, 2);

        formulario.add(new Label("Descripción:"), 0, 3);
        formulario.add(txtDescripcion, 1, 3, 3, 1);

        for (int i = 0; i < 4; i++) {
            formulario.getColumnConstraints().add(
                    new javafx.scene.layout.ColumnConstraints()
            );
        }

        GridPane.setHgrow(txtNombreOrganizacion,
                javafx.scene.layout.Priority.ALWAYS);
        GridPane.setHgrow(txtIdentificacionFiscal,
                javafx.scene.layout.Priority.ALWAYS);
        GridPane.setHgrow(txtTelefono,
                javafx.scene.layout.Priority.ALWAYS);
        GridPane.setHgrow(txtDireccion,
                javafx.scene.layout.Priority.ALWAYS);
        GridPane.setHgrow(txtResponsable,
                javafx.scene.layout.Priority.ALWAYS);
        GridPane.setHgrow(txtCorreo,
                javafx.scene.layout.Priority.ALWAYS);
        GridPane.setHgrow(txtDescripcion,
                javafx.scene.layout.Priority.ALWAYS);

        contenido.getChildren().addAll(
                titulo,
                subtitulo,
                botones,
                tituloDetalle,
                formulario,
                new Label("CONSULTAS REGISTRADAS"),
                tablaUsuarios
        );

        VBox.setVgrow(tablaUsuarios, javafx.scene.layout.Priority.ALWAYS);

        setCenter(contenido);
    }

    // Crea los campos y botones
    private void crearFormulario() {
        txtNombreOrganizacion = crearCampo("Nombre de la organización");
        txtIdentificacionFiscal = crearCampo("Identificación fiscal");
        txtTelefono = crearCampo("Teléfono");
        txtDireccion = crearCampo("Dirección");
        txtResponsable = crearCampo("Responsable");
        txtCorreo = crearCampo("Correo electrónico");
        txtDescripcion = crearCampo("Descripción");

        // Campos únicamente para visualizar los datos
        txtNombreOrganizacion.setEditable(false);
        txtIdentificacionFiscal.setEditable(false);
        txtTelefono.setEditable(false);
        txtDireccion.setEditable(false);
        txtResponsable.setEditable(false);
        txtCorreo.setEditable(false);
        txtDescripcion.setEditable(false);

        // Único botón de gestión
        btnActualizar = new Button("Cambiar estado");

        btnActualizar.setStyle(
                "-fx-background-color: #063B00;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;"
        );
    }

    private TextField crearCampo(String texto) {
        TextField campo = new TextField();
        campo.setPromptText(texto);
        campo.setPrefWidth(180);
        return campo;
    }

    // Crea las columnas de la tabla
    private void crearTabla() {
        tablaUsuarios = new TableView<>();
        tablaUsuarios.setPrefHeight(350);
        tablaUsuarios.setColumnResizePolicy(
                TableView.UNCONSTRAINED_RESIZE_POLICY
        );

        TableColumn<Usuario, Integer> colId =
                new TableColumn<>("ID");

        TableColumn<Usuario, String> colOrganizacion =
                new TableColumn<>("Organización");

        TableColumn<Usuario, String> colTipoDocumento =
                new TableColumn<>("Tipo documento");

        TableColumn<Usuario, String> colIdentificacion =
                new TableColumn<>("Identificación fiscal");

        TableColumn<Usuario, String> colTelefono =
                new TableColumn<>("Teléfono");

        TableColumn<Usuario, String> colDireccion =
                new TableColumn<>("Dirección");

        TableColumn<Usuario, String> colResponsable =
                new TableColumn<>("Responsable");

        TableColumn<Usuario, String> colCorreo =
                new TableColumn<>("Correo");

        TableColumn<Usuario, String> colDescripcion =
                new TableColumn<>("Descripción");

        TableColumn<Usuario, Timestamp> colFecha =
                new TableColumn<>("Fecha de registro");

        TableColumn<Usuario, String> colEstado =
                new TableColumn<>("Estado");

        // Vincula las columnas con las propiedades de Usuario.java
        colId.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colOrganizacion.setCellValueFactory(
                new PropertyValueFactory<>("nombreOrganizacion")
        );

        colTipoDocumento.setCellValueFactory(
                new PropertyValueFactory<>("tipoDocumento")
        );

        colIdentificacion.setCellValueFactory(
                new PropertyValueFactory<>("identificacionFiscal")
        );

        colTelefono.setCellValueFactory(
                new PropertyValueFactory<>("telefono")
        );

        colDireccion.setCellValueFactory(
                new PropertyValueFactory<>("direccion")
        );

        colResponsable.setCellValueFactory(
                new PropertyValueFactory<>("responsable")
        );

        colCorreo.setCellValueFactory(
                new PropertyValueFactory<>("correo")
        );

        colDescripcion.setCellValueFactory(
                new PropertyValueFactory<>("descripcion")
        );

        colFecha.setCellValueFactory(
                new PropertyValueFactory<>("fechaRegistro")
        );

        colEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado")
        );

        // Ancho de columnas
        colId.setPrefWidth(55);
        colOrganizacion.setPrefWidth(180);
        colTipoDocumento.setPrefWidth(120);
        colIdentificacion.setPrefWidth(150);
        colTelefono.setPrefWidth(110);
        colDireccion.setPrefWidth(180);
        colResponsable.setPrefWidth(160);
        colCorreo.setPrefWidth(190);
        colDescripcion.setPrefWidth(220);
        colFecha.setPrefWidth(160);
        colEstado.setPrefWidth(110);

        tablaUsuarios.getColumns().addAll(
                colId,
                colOrganizacion,
                colTipoDocumento,
                colIdentificacion,
                colTelefono,
                colDireccion,
                colResponsable,
                colCorreo,
                colDescripcion,
                colFecha,
                colEstado
        );
    }

    // Recibe y muestra los registros obtenidos desde MySQL
    public void mostrarDatosUsuarios(List<Usuario> usuarios) {
        tablaUsuarios.setItems(
                FXCollections.observableArrayList(usuarios)
        );
    }

    // BOTONES
    public Button getBtnInicio() {
        return btnInicio;
    }

    public Button getBtnUsuarios() {
        return btnUsuarios;
    }

    public Button getBtnRegistrar() {
        return btnRegistrar;
    }

    public Button getBtnActualizar() {
        return btnActualizar;
    }

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    // TABLA
    public TableView<Usuario> getTablaUsuarios() {
        return tablaUsuarios;
    }

    // GETTERS
    public String getNombreOrganizacion() {
        return txtNombreOrganizacion.getText();
    }

    public String getIdentificacionFiscal() {
        return txtIdentificacionFiscal.getText();
    }

    public String getTelefono() {
        return txtTelefono.getText();
    }

    public String getDireccion() {
        return txtDireccion.getText();
    }

    public String getResponsable() {
        return txtResponsable.getText();
    }

    public String getCorreo() {
        return txtCorreo.getText();
    }

    public String getDescripcion() {
        return txtDescripcion.getText();
    }

    // SETTERS
    public void setNombreOrganizacion(String valor) {
        txtNombreOrganizacion.setText(valor);
    }

    public void setIdentificacionFiscal(String valor) {
        txtIdentificacionFiscal.setText(valor);
    }

    public void setTelefono(String valor) {
        txtTelefono.setText(valor);
    }

    public void setDireccion(String valor) {
        txtDireccion.setText(valor);
    }

    public void setResponsable(String valor) {
        txtResponsable.setText(valor);
    }

    public void setCorreo(String valor) {
        txtCorreo.setText(valor);
    }

    public void setDescripcion(String valor) {
        txtDescripcion.setText(valor);
    }

    // LIMPIAR FORMULARIO
    public void limpiarFormulario() {
        txtNombreOrganizacion.clear();
        txtIdentificacionFiscal.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        txtResponsable.clear();
        txtCorreo.clear();
        txtDescripcion.clear();
    }
}