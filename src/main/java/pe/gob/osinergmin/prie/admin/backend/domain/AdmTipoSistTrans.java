package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

/**
 * Tipo Sistema de Transmisión
 */
public class AdmTipoSistTrans {
    private Integer idTipoSisTrans;

    private String codTipoSisTrans;

    private String tipSisTrans;

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

    public Integer getIdTipoSisTrans() {
        return idTipoSisTrans;
    }

    public void setIdTipoSisTrans(Integer idTipoSisTrans) {
        this.idTipoSisTrans = idTipoSisTrans;
    }

    public String getCodTipoSisTrans() {
        return codTipoSisTrans;
    }

    public void setCodTipoSisTrans(String codTipoSisTrans) {
        this.codTipoSisTrans = codTipoSisTrans;
    }

    public String getTipSisTrans() {
        return tipSisTrans;
    }

    public void setTipSisTrans(String tipSisTrans) {
        this.tipSisTrans = tipSisTrans;
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