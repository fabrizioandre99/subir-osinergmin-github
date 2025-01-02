package pe.gob.osinergmin.prie.admin.backend.dto.sisElectrico;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmSistemaElectricoSearchDTO  extends PaginacionDTO {
    private String codSisElec;
    private String nomSisElec;
    private String codEmpresa;
    private String tipSisElec;
    private String codSectorTipico;

    public AdmSistemaElectricoSearchDTO() {
    }
}
