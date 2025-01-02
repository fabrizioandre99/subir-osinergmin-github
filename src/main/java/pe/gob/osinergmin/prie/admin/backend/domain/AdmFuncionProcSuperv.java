package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmFuncionProcSuperv {
    private String codProcSupervision;

    private String codFuncionProcSuperv;

    private String dscFuncionProcSuperv;

    private String adCodUsuario;

    private Date adFecha;

    public String getCodProcSupervision() {
        return codProcSupervision;
    }

    public void setCodProcSupervision(String codProcSupervision) {
        this.codProcSupervision = codProcSupervision;
    }

    public String getCodFuncionProcSuperv() {
        return codFuncionProcSuperv;
    }

    public void setCodFuncionProcSuperv(String codFuncionProcSuperv) {
        this.codFuncionProcSuperv = codFuncionProcSuperv;
    }

    public String getDscFuncionProcSuperv() {
        return dscFuncionProcSuperv;
    }

    public void setDscFuncionProcSuperv(String dscFuncionProcSuperv) {
        this.dscFuncionProcSuperv = dscFuncionProcSuperv;
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