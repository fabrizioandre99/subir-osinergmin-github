package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

/**
 * Cuenca (SEIN)
 */
public class AdmCuenca {
    private String codCuenca;

    private String nomCuenca;

    private String codCoes;

    private String codCuencaAnt;

    private String estadoRegistro;

    private String usuarioCreacion;

    private String terminalCreacion;

    private Date fechaCreacion;

    private String usuarioActualizacion;

    private String terminalActualizacion;

    private Date fechaActualizacion;

    public String getCodCuenca() {
        return codCuenca;
    }

    public void setCodCuenca(String codCuenca) {
        this.codCuenca = codCuenca;
    }

    public String getNomCuenca() {
        return nomCuenca;
    }

    public void setNomCuenca(String nomCuenca) {
        this.nomCuenca = nomCuenca;
    }

    public String getCodCoes() {
        return codCoes;
    }

    public void setCodCoes(String codCoes) {
        this.codCoes = codCoes;
    }

    public String getCodCuencaAnt() {
        return codCuencaAnt;
    }

    public void setCodCuencaAnt(String codCuencaAnt) {
        this.codCuencaAnt = codCuencaAnt;
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