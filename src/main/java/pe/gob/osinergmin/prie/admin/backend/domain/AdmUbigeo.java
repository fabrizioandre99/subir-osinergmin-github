package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmUbigeo {
    private String codUbigeo;

    private String nomUbigeo;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    public String getCodUbigeo() {
        return codUbigeo;
    }

    public void setCodUbigeo(String codUbigeo) {
        this.codUbigeo = codUbigeo;
    }

    public String getNomUbigeo() {
        return nomUbigeo;
    }

    public void setNomUbigeo(String nomUbigeo) {
        this.nomUbigeo = nomUbigeo;
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