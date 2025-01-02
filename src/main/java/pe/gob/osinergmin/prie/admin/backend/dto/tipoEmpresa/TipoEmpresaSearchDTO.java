package pe.gob.osinergmin.prie.admin.backend.dto.tipoEmpresa;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class TipoEmpresaSearchDTO extends PaginacionDTO {

    private String codTipoEmpresa;
    private String dscTipoEmpresa;

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

    public TipoEmpresaSearchDTO(String codTipoEmpresa, String dscTipoEmpresa) {
        this.codTipoEmpresa = codTipoEmpresa;
        this.dscTipoEmpresa = dscTipoEmpresa;
    }

    public TipoEmpresaSearchDTO() {
    }
}
