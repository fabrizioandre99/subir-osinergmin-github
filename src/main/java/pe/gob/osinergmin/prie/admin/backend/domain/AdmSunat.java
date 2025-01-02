package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmSunat {
    private String codSunat;

    private String nomSunat;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    public String getCodSunat() {
        return codSunat;
    }

    public void setCodSunat(String codSunat) {
        this.codSunat = codSunat;
    }

    public String getNomSunat() {
        return nomSunat;
    }

    public void setNomSunat(String nomSunat) {
        this.nomSunat = nomSunat;
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