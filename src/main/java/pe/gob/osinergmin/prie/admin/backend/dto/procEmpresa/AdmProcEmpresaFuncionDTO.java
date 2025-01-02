package pe.gob.osinergmin.prie.admin.backend.dto.procEmpresa;

public class AdmProcEmpresaFuncionDTO {
    private String codFuncionProcSuperv;

    public String getCodFuncionProcSuperv() {
        return codFuncionProcSuperv;
    }

    public void setCodFuncionProcSuperv(String codFuncionProcSuperv) {
        this.codFuncionProcSuperv = codFuncionProcSuperv;
    }

    public AdmProcEmpresaFuncionDTO(String codFuncionProcSuperv) {
        this.codFuncionProcSuperv = codFuncionProcSuperv;
    }

    public AdmProcEmpresaFuncionDTO() {}
}
