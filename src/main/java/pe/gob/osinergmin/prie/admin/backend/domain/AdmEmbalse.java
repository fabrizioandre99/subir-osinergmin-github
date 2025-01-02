package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

/**
 * Embalse (SEIN)
 */
public class AdmEmbalse {
    private String codEmbalse;

    private String nomEmbalse;

    private String codCoes;

    private String codEmbalseAnt;

    private String estadoRegistro;

    private String usuarioCreacion;

    private String terminalCreacion;

    private Date fechaCreacion;

    private String usuarioActualizacion;

    private String terminalActualizacion;

    private Date fechaActualizacion;

    public String getCodEmbalse() {
        return codEmbalse;
    }

    public void setCodEmbalse(String codEmbalse) {
        this.codEmbalse = codEmbalse;
    }

    public String getNomEmbalse() {
        return nomEmbalse;
    }

    public void setNomEmbalse(String nomEmbalse) {
        this.nomEmbalse = nomEmbalse;
    }

    public String getCodCoes() {
        return codCoes;
    }

    public void setCodCoes(String codCoes) {
        this.codCoes = codCoes;
    }

    public String getCodEmbalseAnt() {
        return codEmbalseAnt;
    }

    public void setCodEmbalseAnt(String codEmbalseAnt) {
        this.codEmbalseAnt = codEmbalseAnt;
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