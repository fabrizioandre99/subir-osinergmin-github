package pe.gob.osinergmin.prie.admin.backend.dto.transformador;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Setter
@Getter
public class AdmTransformadorSearchDTO extends PaginacionDTO {
    private String codEquipo;
    private String codSubestacion;
    private String codEmpresa;
    private String estadoRegistro;
}
