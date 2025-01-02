package pe.gob.osinergmin.prie.admin.backend.dto.sectorTipico;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmSectorTipicoSearchDTO extends PaginacionDTO {
    private String codSectorTipico;
    private String dscSectorTipico;
}
