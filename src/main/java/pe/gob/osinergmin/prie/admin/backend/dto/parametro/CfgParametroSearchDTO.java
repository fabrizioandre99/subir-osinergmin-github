package pe.gob.osinergmin.prie.admin.backend.dto.parametro;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class CfgParametroSearchDTO extends PaginacionDTO {
    private String codParametro;
    private String descParametro;

    public String getCodParametro() {
        return codParametro;
    }

    public void setCodParametro(String codParametro) {
        this.codParametro = codParametro;
    }

    public String getDescParametro() {
        return descParametro;
    }

    public void setDescParametro(String descParametro) {
        this.descParametro = descParametro;
    }

}
