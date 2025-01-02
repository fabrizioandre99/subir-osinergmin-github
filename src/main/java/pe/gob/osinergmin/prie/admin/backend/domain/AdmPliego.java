package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmPliego {
    private String codPliego;

    private String nomPliego;

    private Date fecAlta;

    private Date fecBaja;

    private String adCodUsuario;

    private Date adFecha;

    public String getCodPliego() {
        return codPliego;
    }

    public void setCodPliego(String codPliego) {
        this.codPliego = codPliego;
    }

    public String getNomPliego() {
        return nomPliego;
    }

    public void setNomPliego(String nomPliego) {
        this.nomPliego = nomPliego;
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
}