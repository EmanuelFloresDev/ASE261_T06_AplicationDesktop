
package vallegrande.edu.pe.misistema.controller;

import javafx.scene.control.Alert;
import vallegrande.edu.pe.misistema.model.Usuario;
import vallegrande.edu.pe.misistema.model.UsuarioDAO;
import vallegrande.edu.pe.misistema.view.MainView;

import java.util.List;

public class MainController {

    private final MainView view;
    private final UsuarioDAO usuarioDAO;

    private Usuario usuarioSeleccionado;

    public MainController(MainView view) {
        this.view = view;
        this.usuarioDAO = new UsuarioDAO();
        configurarEventos();
    }

    public void configurarEventos() {

        view.getBtnInicio().setOnAction(e -> {
            view.mostrarInicio();
        });

        view.getBtnUsuarios().setOnAction(e -> {
            view.mostrarUsuarios();
            cargarUsuarios();
        });

        // El botón Actualizar cambia el estado de la consulta.
        view.getBtnActualizar().setOnAction(e -> {
            actualizarEstado();
        });

        view.getTablaUsuarios()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, anterior, seleccionado) -> {

                    usuarioSeleccionado = seleccionado;

                    if (seleccionado != null) {
                        cargarUsuarioSeleccionado();
                    }
                });
    }

    // Consultar los registros de la tabla consulta.
    private void cargarUsuarios() {

        List<Usuario> consultas = usuarioDAO.listar();

        view.mostrarDatosUsuarios(consultas);
    }

    // Mostrar los datos de la consulta seleccionada.
    private void cargarUsuarioSeleccionado() {

        if (usuarioSeleccionado == null) {
            return;
        }

        view.setNombreOrganizacion(
                usuarioSeleccionado.getNombreOrganizacion()
        );

        view.setIdentificacionFiscal(
                usuarioSeleccionado.getIdentificacionFiscal()
        );

        view.setTelefono(
                usuarioSeleccionado.getTelefono()
        );

        view.setDireccion(
                usuarioSeleccionado.getDireccion()
        );

        view.setResponsable(
                usuarioSeleccionado.getResponsable()
        );

        view.setCorreo(
                usuarioSeleccionado.getCorreo()
        );

        view.setDescripcion(
                usuarioSeleccionado.getDescripcion()
        );
    }

    // Cambiar el estado de la consulta seleccionada.
    private void actualizarEstado() {

        if (usuarioSeleccionado == null) {
            mostrarMensaje(
                    Alert.AlertType.WARNING,
                    "Consulta no seleccionada",
                    "Selecciona una consulta de la tabla."
            );
            return;
        }

        String nuevoEstado =
                "Pendiente".equals(usuarioSeleccionado.getEstado())
                        ? "Atendido"
                        : "Pendiente";

        boolean actualizado = usuarioDAO.actualizarEstado(
                usuarioSeleccionado.getId(),
                nuevoEstado
        );

        if (actualizado) {

            cargarUsuarios();

            // Volver a seleccionar la consulta actualizada.
            for (Usuario consulta : view.getTablaUsuarios().getItems()) {
                if (consulta.getId() == usuarioSeleccionado.getId()) {
                    view.getTablaUsuarios()
                            .getSelectionModel()
                            .select(consulta);
                    break;
                }
            }

            mostrarMensaje(
                    Alert.AlertType.INFORMATION,
                    "Estado actualizado",
                    "La consulta ahora está: " + nuevoEstado
            );

        } else {
            mostrarMensaje(
                    Alert.AlertType.ERROR,
                    "No se pudo actualizar",
                    "Verifica la conexión y que la consulta exista."
            );
        }
    }

    private void mostrarMensaje(
            Alert.AlertType tipo,
            String titulo,
            String mensaje
    ) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}