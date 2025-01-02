package pe.gob.osinergmin.prie.admin.backend.dto.sunat;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmSunatSearchDTO extends PaginacionDTO {
    private String codSunat;
    private String nomSunat;
}
