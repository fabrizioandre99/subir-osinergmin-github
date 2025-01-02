package pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa;

import java.util.Date;

public class AdmProcEmpresaFormDTO {
    private Integer idProcEmpresa;

    private String codEmpresa;

    private String codTipoEmpresa;

    private String codProcSupervision;

    private String codFuncionProcSuperv;

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

    public AdmProcEmpresaFormDTO(Integer idProcEmpresa, String codEmpresa, String codTipoEmpresa, String codProcSupervision, String codFuncionProcSuperv) {
        this.idProcEmpresa = idProcEmpresa;
        this.codEmpresa = codEmpresa;
        this.codTipoEmpresa = codTipoEmpresa;
        this.codProcSupervision = codProcSupervision;
        this.codFuncionProcSuperv = codFuncionProcSuperv;
    }

    public AdmProcEmpresaFormDTO() {}
}
