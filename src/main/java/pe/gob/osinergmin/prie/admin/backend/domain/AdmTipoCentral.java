package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmTipoCentral {
    private String codTipoCentral;

    private String nomTipoCentral;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    public String getCodTipoCentral() {
        return codTipoCentral;
    }

    public void setCodTipoCentral(String codTipoCentral) {
        this.codTipoCentral = codTipoCentral;
    }

    public String getNomTipoCentral() {
        return nomTipoCentral;
    }

    public void setNomTipoCentral(String nomTipoCentral) {
        this.nomTipoCentral = nomTipoCentral;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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