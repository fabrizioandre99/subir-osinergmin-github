package pe.gob.osinergmin.prie.admin.backend.dto.indEconomico;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Setter
@Getter
public class IndEconomicoSearchDTO extends PaginacionDTO {
    private String codIndicador;
}
