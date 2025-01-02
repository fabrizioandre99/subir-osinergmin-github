package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

/**
 * Sectores Típicos por Procedimiento de Supervisión
 */
public class AdmProcSectorTipico {
    private String codSectorTipico;

    private String codProcSupervision;

    private String observaciones;

    private String estadoRegistro;

    private String usuarioCreacion;

    private String terminalCreacion;

    private Date fechaCreacion;

    private String usuarioActualizacion;

    private String terminalActualizacion;

    private Date fechaActualizacion;

    public String getCodSectorTipico() {
        return codSectorTipico;
    }

    public void setCodSectorTipico(String codSectorTipico) {
        this.codSectorTipico = codSectorTipico;
    }

    public String getCodProcSupervision() {
        return codProcSupervision;
    }

    public void setCodProcSupervision(String codProcSupervision) {
        this.codProcSupervision = codProcSupervision;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
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