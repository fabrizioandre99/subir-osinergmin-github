package pe.gob.osinergmin.prie.admin.backend.dto.procSupervision;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Setter
@Getter
public class AdmProcSupervisionSearchDTO extends PaginacionDTO {
    private String codProcSupervision;
    private String nomProcSupervision;
    private String abrevProcSupervision;
    private String coModulo;
}
