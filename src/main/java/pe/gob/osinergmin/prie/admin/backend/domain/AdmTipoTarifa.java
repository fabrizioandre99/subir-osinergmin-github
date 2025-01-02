package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmTipoTarifa {
    private String codtarifa;

    private String descripcion;

    private String estado;

    private String codusuario;

    private Date fecactualiza;

    private String adCodUsuario;

    private Date adFecha;

    public String getCodtarifa() {
        return codtarifa;
    }

    public void setCodtarifa(String codtarifa) {
        this.codtarifa = codtarifa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(String codusuario) {
        this.codusuario = codusuario;
    }

    public Date getFecactualiza() {
        return fecactualiza;
    }

    public void setFecactualiza(Date fecactualiza) {
        this.fecactualiza = fecactualiza;
    }

    public String getAdCodUsuario() {
        return adCodUsuario;
    }

    public void setAdCodUsuario(String adCodUsuario) {
        this.adCodUsuario = adCodUsuario;
    }

    public Date getAdFecha() {
        return adFecha;
    }

    public void setAdFecha(Date adFecha) {
        this.adFecha = adFecha;
    }
}