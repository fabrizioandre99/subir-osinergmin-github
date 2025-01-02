package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmTipoEmpresa {
    private String codTipoEmpresa;

    private String dscTipoEmpresa;

    private String estado;

    private String adCodUsuario;

    private Date adFecha;

    public String getCodTipoEmpresa() {
        return codTipoEmpresa;
    }

    public void setCodTipoEmpresa(String codTipoEmpresa) {
        this.codTipoEmpresa = codTipoEmpresa;
    }

    public String getDscTipoEmpresa() {
        return dscTipoEmpresa;
    }

    public void setDscTipoEmpresa(String dscTipoEmpresa) {
        this.dscTipoEmpresa = dscTipoEmpresa;
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