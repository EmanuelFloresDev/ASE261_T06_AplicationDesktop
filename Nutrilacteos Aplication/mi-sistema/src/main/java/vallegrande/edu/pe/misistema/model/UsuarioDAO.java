
package vallegrande.edu.pe.misistema.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Listar todas las consultas registradas desde la web
    public List<Usuario> listar() {

        List<Usuario> lista = new ArrayList<>();

        String sql = """
                SELECT
                    id_consulta,
                    nombre_organizacion,
                    tipo_documento,
                    identificacion_fiscal,
                    telefono,
                    direccion_empresa,
                    nombre_responsable,
                    correo_electronico,
                    descripcion,
                    fecha_registro,
                    estado
                FROM consulta
                ORDER BY fecha_registro DESC
                """;

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setId(rs.getInt("id_consulta"));
                u.setNombreOrganizacion(
                        rs.getString("nombre_organizacion"));
                u.setTipoDocumento(
                        rs.getString("tipo_documento"));
                u.setIdentificacionFiscal(
                        rs.getString("identificacion_fiscal"));
                u.setTelefono(
                        rs.getString("telefono"));
                u.setDireccion(
                        rs.getString("direccion_empresa"));
                u.setResponsable(
                        rs.getString("nombre_responsable"));
                u.setCorreo(
                        rs.getString("correo_electronico"));
                u.setDescripcion(
                        rs.getString("descripcion"));
                u.setFechaRegistro(
                        rs.getTimestamp("fecha_registro"));
                u.setEstado(
                        rs.getString("estado"));

                lista.add(u);
            }

        } catch (SQLException e) {
            System.err.println(
                    "Error al listar las consultas: "
                            + e.getMessage());
        }

        return lista;
    }

    // Actualizar únicamente el estado de una consulta
    public boolean actualizarEstado(int id, String estado) {

        if (!"Pendiente".equals(estado)
                && !"Atendido".equals(estado)) {
            System.err.println("Estado no válido.");
            return false;
        }

        String sql = """
                UPDATE consulta
                SET estado = ?
                WHERE id_consulta = ?
                """;

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estado);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println(
                    "Error al actualizar el estado: "
                            + e.getMessage());
            return false;
        }
    }

    // Buscar una consulta por su ID
    public Usuario buscarPorId(int id) {

        String sql = """
                SELECT
                    id_consulta,
                    nombre_organizacion,
                    tipo_documento,
                    identificacion_fiscal,
                    telefono,
                    direccion_empresa,
                    nombre_responsable,
                    correo_electronico,
                    descripcion,
                    fecha_registro,
                    estado
                FROM consulta
                WHERE id_consulta = ?
                """;

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Usuario u = new Usuario();

                    u.setId(rs.getInt("id_consulta"));
                    u.setNombreOrganizacion(
                            rs.getString("nombre_organizacion"));
                    u.setTipoDocumento(
                            rs.getString("tipo_documento"));
                    u.setIdentificacionFiscal(
                            rs.getString("identificacion_fiscal"));
                    u.setTelefono(
                            rs.getString("telefono"));
                    u.setDireccion(
                            rs.getString("direccion_empresa"));
                    u.setResponsable(
                            rs.getString("nombre_responsable"));
                    u.setCorreo(
                            rs.getString("correo_electronico"));
                    u.setDescripcion(
                            rs.getString("descripcion"));
                    u.setFechaRegistro(
                            rs.getTimestamp("fecha_registro"));
                    u.setEstado(
                            rs.getString("estado"));

                    return u;
                }
            }

        } catch (SQLException e) {
            System.err.println(
                    "Error al buscar la consulta: "
                            + e.getMessage());
        }

        return null;
    }
}