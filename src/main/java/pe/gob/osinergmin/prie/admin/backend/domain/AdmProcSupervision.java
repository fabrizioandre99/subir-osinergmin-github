package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmProcSupervision {
    private String codProcSupervision;

    private String nomProcSupervision;

    private String abrevProcSupervision;

    private String adCodUsuario;

    private Date adFecha;

    private String coModulo;

    private String estado;

    public String getCodProcSupervision() {
        return codProcSupervision;
    }

    public void setCodProcSupervision(String codProcSupervision) {
        this.codProcSupervision = codProcSupervision;
    }

    public String getNomProcSupervision() {
        return nomProcSupervision;
    }

    public void setNomProcSupervision(String nomProcSupervision) {
        this.nomProcSupervision = nomProcSupervision;
    }

    public String getAbrevProcSupervision() {
        return abrevProcSupervision;
    }

    public void setAbrevProcSupervision(String abrevProcSupervision) {
        this.abrevProcSupervision = abrevProcSupervision;
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

    public String getCoModulo() {
        return coModulo;
    }

    public void setCoModulo(String coModulo) {
        this.coModulo = coModulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}