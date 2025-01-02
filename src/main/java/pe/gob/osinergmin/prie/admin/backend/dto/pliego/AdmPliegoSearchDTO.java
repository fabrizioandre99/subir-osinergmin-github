package pe.gob.osinergmin.prie.admin.backend.dto.pliego;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmPliegoSearchDTO extends PaginacionDTO {
    private String codPliego;
    private String nomPliego;
    private String codSisElec;
    private String sortSistemasElectricos;
    private String orderSistemasElectricos;
}
