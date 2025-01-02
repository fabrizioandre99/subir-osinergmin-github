package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmModuloSupervision {
    private String coModulo;

    private String deModulo;

    private String deCorta;

    private String estado;

    private String usCreacion;

    private String terminalCreacion;

    private Date feCreacion;

    private String usActualizacion;

    private String terminalActualizacion;

    private Date feActualizacion;

    public String getCoModulo() {
        return coModulo;
    }

    public void setCoModulo(String coModulo) {
        this.coModulo = coModulo;
    }

    public String getDeModulo() {
        return deModulo;
    }

    public void setDeModulo(String deModulo) {
        this.deModulo = deModulo;
    }

    public String getDeCorta() {
        return deCorta;
    }

    public void setDeCorta(String deCorta) {
        this.deCorta = deCorta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsCreacion() {
        return usCreacion;
    }

    public void setUsCreacion(String usCreacion) {
        this.usCreacion = usCreacion;
    }

    public String getTerminalCreacion() {
        return terminalCreacion;
    }

    public void setTerminalCreacion(String terminalCreacion) {
        this.terminalCreacion = terminalCreacion;
    }

    public Date getFeCreacion() {
        return feCreacion;
    }

    public void setFeCreacion(Date feCreacion) {
        this.feCreacion = feCreacion;
    }

    public String getUsActualizacion() {
        return usActualizacion;
    }

    public void setUsActualizacion(String usActualizacion) {
        this.usActualizacion = usActualizacion;
    }

    public String getTerminalActualizacion() {
        return terminalActualizacion;
    }

    public void setTerminalActualizacion(String terminalActualizacion) {
        this.terminalActualizacion = terminalActualizacion;
    }

    public Date getFeActualizacion() {
        return feActualizacion;
    }

    public void setFeActualizacion(Date feActualizacion) {
        this.feActualizacion = feActualizacion;
    }
}