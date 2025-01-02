package pe.gob.osinergmin.prie.admin.backend.dto.moduloSupervision;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmModuloSupervisionSearchDTO extends PaginacionDTO {
    private String coModulo;
    private String deModulo;
}
