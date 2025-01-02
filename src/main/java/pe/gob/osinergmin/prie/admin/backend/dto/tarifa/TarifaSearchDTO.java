package pe.gob.osinergmin.prie.admin.backend.dto.tarifa;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Setter
@Getter
public class TarifaSearchDTO extends PaginacionDTO {
    private String nomTarifa;
    private String codTarifa;

}
