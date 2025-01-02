package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmBarraSiselecAlim {
    private String codSisElec;

    private String codBarra;

    private Date fecAlta;

    private Date fecBaja;

    private String adCodUsuario;

    private Date adFecha;

    private String estado;

    public String getCodSisElec() {
        return codSisElec;
    }

    public void setCodSisElec(String codSisElec) {
        this.codSisElec = codSisElec;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public Date getFecAlta() {
        return fecAlta;
    }

    public void setFecAlta(Date fecAlta) {
        this.fecAlta = fecAlta;
    }

    public Date getFecBaja() {
        return fecBaja;
    }

    public void setFecBaja(Date fecBaja) {
        this.fecBaja = fecBaja;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}