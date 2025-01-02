package pe.gob.osinergmin.prie.admin.backend.dto.empresa;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;
import java.util.List;

public class AdmEmpresaSearchDTO extends PaginacionDTO {
    private String codEmpresa;
    private String dscEmpresa;
    private List<String> procesosAsociados;

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getDscEmpresa() {
        return dscEmpresa;
    }

    public void setDscEmpresa(String dscEmpresa) {
        this.dscEmpresa = dscEmpresa;
    }

    public List<String> getProcesosAsociados() {
        return procesosAsociados;
    }

    public void setProcesosAsociados(List<String> procesosAsociados) {
        this.procesosAsociados = procesosAsociados;
    }

    public AdmEmpresaSearchDTO(String codEmpresa, String dscEmpresa, List<String> procesosAsociados) {
        this.codEmpresa = codEmpresa;
        this.dscEmpresa = dscEmpresa;
        this.procesosAsociados = procesosAsociados;
    }

    public AdmEmpresaSearchDTO() {}
}
