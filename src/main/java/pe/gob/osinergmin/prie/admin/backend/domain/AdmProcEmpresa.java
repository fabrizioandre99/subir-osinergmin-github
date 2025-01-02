package pe.gob.osinergmin.prie.admin.backend.domain;

import java.util.Date;

public class AdmProcEmpresa {
    private Integer idProcEmpresa;

    private String codEmpresa;

    private String codTipoEmpresa;

    private Date fecAlta;

    private Date fecBaja;

    private String codProcSupervision;

    private String codFuncionProcSuperv;

    private String adCodUsuario;

    private Date adFecha;

    public Integer getIdProcEmpresa() {
        return idProcEmpresa;
    }

    public void setIdProcEmpresa(Integer idProcEmpresa) {
        this.idProcEmpresa = idProcEmpresa;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getCodTipoEmpresa() {
        return codTipoEmpresa;
    }

    public void setCodTipoEmpresa(String codTipoEmpresa) {
        this.codTipoEmpresa = codTipoEmpresa;
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