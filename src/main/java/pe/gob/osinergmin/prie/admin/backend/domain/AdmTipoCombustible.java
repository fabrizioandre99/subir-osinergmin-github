package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmTipoCombustible {
    private String codTipoCombustible;

    private String nomTipoCombustible;

    private String codGrupoCombustible;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    public String getCodTipoCombustible() {
        return codTipoCombustible;
    }

    public void setCodTipoCombustible(String codTipoCombustible) {
        this.codTipoCombustible = codTipoCombustible;
    }

    public String getNomTipoCombustible() {
        return nomTipoCombustible;
    }

    public void setNomTipoCombustible(String nomTipoCombustible) {
        this.nomTipoCombustible = nomTipoCombustible;
    }

    public String getCodGrupoCombustible() {
        return codGrupoCombustible;
    }

    public void setCodGrupoCombustible(String codGrupoCombustible) {
        this.codGrupoCombustible = codGrupoCombustible;
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