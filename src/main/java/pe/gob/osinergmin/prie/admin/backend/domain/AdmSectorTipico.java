package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmSectorTipico {
    private String codSectorTipico;

    private String dscSectorTipico;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    public String getCodSectorTipico() {
        return codSectorTipico;
    }

    public void setCodSectorTipico(String codSectorTipico) {
        this.codSectorTipico = codSectorTipico;
    }

    public String getDscSectorTipico() {
        return dscSectorTipico;
    }

    public void setDscSectorTipico(String dscSectorTipico) {
        this.dscSectorTipico = dscSectorTipico;
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