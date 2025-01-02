package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmGrupoCombustible {
    private String codGrupoCombustible;

    private String nomGrupoCombustible;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    public String getCodGrupoCombustible() {
        return codGrupoCombustible;
    }

    public void setCodGrupoCombustible(String codGrupoCombustible) {
        this.codGrupoCombustible = codGrupoCombustible;
    }

    public String getNomGrupoCombustible() {
        return nomGrupoCombustible;
    }

    public void setNomGrupoCombustible(String nomGrupoCombustible) {
        this.nomGrupoCombustible = nomGrupoCombustible;
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