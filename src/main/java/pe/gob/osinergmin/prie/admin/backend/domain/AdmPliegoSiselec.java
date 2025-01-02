package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmPliegoSiselec {
    private String annioMes;

    private String codPliego;

    private String codSisElec;

    private String adCodUsuario;

    private Date adFecha;

    public String getAnnioMes() {
        return annioMes;
    }

    public void setAnnioMes(String annioMes) {
        this.annioMes = annioMes;
    }

    public String getCodPliego() {
        return codPliego;
    }

    public void setCodPliego(String codPliego) {
        this.codPliego = codPliego;
    }

    public String getCodSisElec() {
        return codSisElec;
    }

    public void setCodSisElec(String codSisElec) {
        this.codSisElec = codSisElec;
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