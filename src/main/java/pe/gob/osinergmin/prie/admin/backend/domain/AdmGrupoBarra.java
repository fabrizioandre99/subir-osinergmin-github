package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmGrupoBarra {
    private String codBarraGrupo;

    private String codBarra;

    private String estado;

    private String usuarioCreacion;

    private String terminalCreacion;

    private Date fechaCreacion;

    private String usuarioActualizacion;

    private String terminalActualizacion;

    private Date fechaActualizacion;

    public String getCodBarraGrupo() {
        return codBarraGrupo;
    }

    public void setCodBarraGrupo(String codBarraGrupo) {
        this.codBarraGrupo = codBarraGrupo;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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