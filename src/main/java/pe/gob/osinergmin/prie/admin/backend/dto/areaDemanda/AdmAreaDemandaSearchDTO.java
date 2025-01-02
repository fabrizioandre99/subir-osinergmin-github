package pe.gob.osinergmin.prie.admin.backend.dto.areaDemanda;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmAreaDemandaSearchDTO extends PaginacionDTO{
    private Double areaDemanda;
    private String dscAreaDemanda;
    private String noConjunto;

    public AdmAreaDemandaSearchDTO() {
    }
}
