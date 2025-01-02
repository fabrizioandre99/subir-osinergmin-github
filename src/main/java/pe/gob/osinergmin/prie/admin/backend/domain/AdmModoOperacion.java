package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

/**
 * Modo de Operacion (COES - SEIN)
 */
public class AdmModoOperacion {
    private String codModoOperacion;

    private String dscModoOperacion;

    private String codCoes;

    private String codModoOperacionAnt;

    private String estadoRegistro;

    private String usuarioCreacion;

    private String terminalCreacion;

    private Date fechaCreacion;

    private String usuarioActualizacion;

    private String terminalActualizacion;

    private Date fechaActualizacion;

    public String getCodModoOperacion() {
        return codModoOperacion;
    }

    public void setCodModoOperacion(String codModoOperacion) {
        this.codModoOperacion = codModoOperacion;
    }

    public String getDscModoOperacion() {
        return dscModoOperacion;
    }

    public void setDscModoOperacion(String dscModoOperacion) {
        this.dscModoOperacion = dscModoOperacion;
    }

    public String getCodCoes() {
        return codCoes;
    }

    public void setCodCoes(String codCoes) {
        this.codCoes = codCoes;
    }

    public String getCodModoOperacionAnt() {
        return codModoOperacionAnt;
    }

    public void setCodModoOperacionAnt(String codModoOperacionAnt) {
        this.codModoOperacionAnt = codModoOperacionAnt;
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