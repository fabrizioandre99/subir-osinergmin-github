package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

/**
 * Sistema de Transmisión
 */
public class AdmSistemaTransmision {
    private Integer idSisTrans;

    private String codSisTrans;

    private String nomSisTrans;

    private String usuarioCreacion;

    private String terminalCreacion;

    private Date fechaCreacion;

    private String usuarioActualizacion;

    private String terminalActualizacion;

    private Date fechaActualizacion;

    private String usuarioEliminacion;

    private String terminalEliminacion;

    private Date fechaEliminacion;

    private String estado;

    public Integer getIdSisTrans() {
        return idSisTrans;
    }

    public void setIdSisTrans(Integer idSisTrans) {
        this.idSisTrans = idSisTrans;
    }

    public String getCodSisTrans() {
        return codSisTrans;
    }

    public void setCodSisTrans(String codSisTrans) {
        this.codSisTrans = codSisTrans;
    }

    public String getNomSisTrans() {
        return nomSisTrans;
    }

    public void setNomSisTrans(String nomSisTrans) {
        this.nomSisTrans = nomSisTrans;
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

    public String getUsuarioEliminacion() {
        return usuarioEliminacion;
    }

    public void setUsuarioEliminacion(String usuarioEliminacion) {
        this.usuarioEliminacion = usuarioEliminacion;
    }

    public String getTerminalEliminacion() {
        return terminalEliminacion;
    }

    public void setTerminalEliminacion(String terminalEliminacion) {
        this.terminalEliminacion = terminalEliminacion;
    }

    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}