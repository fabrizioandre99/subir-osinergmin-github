package pe.gob.osinergmin.prie.admin.backend.dto.tipoSistTrans;

import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

public class AdmTipoSistTransSearchDTO extends PaginacionDTO {
    private String codTipoSisTrans;
    private String tipSisTrans;

    public String getTipSisTrans() {
        return tipSisTrans;
    }

    public void setTipSisTrans(String tipSisTrans) {
        this.tipSisTrans = tipSisTrans;
    }

    public String getCodTipoSisTrans() {
        return codTipoSisTrans;
    }

    public void setCodTipoSisTrans(String codTipoSisTrans) {
        this.codTipoSisTrans = codTipoSisTrans;
    }

    public AdmTipoSistTransSearchDTO(String codTipoSisTrans, String tipSisTrans) {
        this.codTipoSisTrans = codTipoSisTrans;
        this.tipSisTrans = tipSisTrans;
    }

    public AdmTipoSistTransSearchDTO() {
    }
}
