package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

/**
 * Tabla consumidor Independiente
 */
public class AdmConsumidorIndependiente {
    private String codConsumidorInde;

    private String razonSocial;

    private String direccion;

    private String telefono;

    private String nombreRepLegal;

    private String cargoRepLeg;

    private String telefonoRepLeg;

    private String correoRepLeg;

    private String usuarioCreacion;

    private String terminalCreacion;

    private Date fechaCreacion;

    private String usuarioActualizacion;

    private String terminalActualizacion;

    private Date fechaActualizacion;

    public String getCodConsumidorInde() {
        return codConsumidorInde;
    }

    public void setCodConsumidorInde(String codConsumidorInde) {
        this.codConsumidorInde = codConsumidorInde;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreRepLegal() {
        return nombreRepLegal;
    }

    public void setNombreRepLegal(String nombreRepLegal) {
        this.nombreRepLegal = nombreRepLegal;
    }

    public String getCargoRepLeg() {
        return cargoRepLeg;
    }

    public void setCargoRepLeg(String cargoRepLeg) {
        this.cargoRepLeg = cargoRepLeg;
    }

    public String getTelefonoRepLeg() {
        return telefonoRepLeg;
    }

    public void setTelefonoRepLeg(String telefonoRepLeg) {
        this.telefonoRepLeg = telefonoRepLeg;
    }

    public String getCorreoRepLeg() {
        return correoRepLeg;
    }

    public void setCorreoRepLeg(String correoRepLeg) {
        this.correoRepLeg = correoRepLeg;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getTerminalCreacion() {
        return terminalCreacion;
    }

    public void setTerminalCreacion(String terminalCreacion) {
        this.terminalCreacion = terminalCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    public void setUsuarioActualizacion(String usuarioActualizacion) {
        this.usuarioActualizacion = usuarioActualizacion;
    }

    public String getTerminalActualizacion() {
        return terminalActualizacion;
    }

    public void setTerminalActualizacion(String terminalActualizacion) {
        this.terminalActualizacion = terminalActualizacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}