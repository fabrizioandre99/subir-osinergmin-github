package pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa;

public class ProcEmpresaPorCodDTO {
    private String codProcSupervision;
    private Integer idProcEmpresa;


    public String getCodProcSupervision() {
        return codProcSupervision;
    }

    public void setCodProcSupervision(String codProcSupervision) {
        this.codProcSupervision = codProcSupervision;
    }

    public Integer getIdProcEmpresa() {
        return idProcEmpresa;
    }

    public void setIdProcEmpresa(Integer idProcEmpresa) {
        this.idProcEmpresa = idProcEmpresa;
    }

    public ProcEmpresaPorCodDTO(String codProcSupervision, Integer idProcEmpresa) {
        this.codProcSupervision = codProcSupervision;
        this.idProcEmpresa = idProcEmpresa;
    }

    public ProcEmpresaPorCodDTO() {
    }
}
