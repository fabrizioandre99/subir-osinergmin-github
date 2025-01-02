package pe.gob.osinergmin.prie.admin.backend.dto.tipoCombustible;

import lombok.Data;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;


public class AdmTipoCombustibleSearchDTO  extends PaginacionDTO {
    private String codTipoCombustible;
    private String nomTipoCombustible;

    public String getCodTipoCombustible() {
        return codTipoCombustible;
    }

    public void setCodTipoCombustible(String codTipoCombustible) {
        this.codTipoCombustible = codTipoCombustible;
    }

    public String getNomTipoCombustible() {
        return nomTipoCombustible;
    }

    public void setNomTipoCombustible(String nomTipoCombustible) {
        this.nomTipoCombustible = nomTipoCombustible;
    }

}
