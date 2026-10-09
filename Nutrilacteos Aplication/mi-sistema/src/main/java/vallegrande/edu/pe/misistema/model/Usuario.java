package vallegrande.edu.pe.misistema.model;

import java.sql.Timestamp;

public class Usuario {

    private int id;
    private String nombreOrganizacion;
    private String tipoDocumento;
    private String identificacionFiscal;
    private String telefono;
    private String direccion;
    private String responsable;
    private String correo;
    private String descripcion;
    private Timestamp fechaRegistro;
    private String estado;

    public Usuario() {
    }

    public Usuario(
            int id,
            String nombreOrganizacion,
            String tipoDocumento,
            String identificacionFiscal,
            String telefono,
            String direccion,
            String responsable,
            String correo,
            String descripcion,
            Timestamp fechaRegistro,
            String estado
    ) {
        this.id = id;
        this.nombreOrganizacion = nombreOrganizacion;
        this.tipoDocumento = tipoDocumento;
        this.identificacionFiscal = identificacionFiscal;
        this.telefono = telefono;
        this.direccion = direccion;
        this.responsable = responsable;
        this.correo = correo;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreOrganizacion() {
        return nombreOrganizacion;
    }

    public void setNombreOrganizacion(String nombreOrganizacion) {
        this.nombreOrganizacion = nombreOrganizacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getIdentificacionFiscal() {
        return identificacionFiscal;
    }

    public void setIdentificacionFiscal(String identificacionFiscal) {
        this.identificacionFiscal = identificacionFiscal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}