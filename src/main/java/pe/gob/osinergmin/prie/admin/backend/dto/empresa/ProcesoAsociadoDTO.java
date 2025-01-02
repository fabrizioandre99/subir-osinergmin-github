package pe.gob.osinergmin.prie.admin.backend.dto.empresa;

public class ProcesoAsociadoDTO {
    private String codProcSupervision;
    private String codTipoEmpresa;
    private String codFuncionProcSuperv;
    private Integer idProcEmpresa; // Nuevo campo

    public String getCodProcSupervision() {
        return codProcSupervision;
    }

    public void setCodProcSupervision(String codProcSupervision) {
        this.codProcSupervision = codProcSupervision;
    }

    public String getCodTipoEmpresa() {
        return codTipoEmpresa;
    }

    public void setCodTipoEmpresa(String codTipoEmpresa) {
        this.codTipoEmpresa = codTipoEmpresa;
    }

    public String getCodFuncionProcSuperv() {
        return codFuncionProcSuperv;
    }

    public void setCodFuncionProcSuperv(String codFuncionProcSuperv) {
        this.codFuncionProcSuperv = codFuncionProcSuperv;
    }

    public Integer getIdProcEmpresa() {
        return idProcEmpresa;
    }

    public void setIdProcEmpresa(Integer idProcEmpresa) {
        this.idProcEmpresa = idProcEmpresa;
    }
}
