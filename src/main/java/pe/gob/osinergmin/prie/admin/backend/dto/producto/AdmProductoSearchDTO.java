package pe.gob.osinergmin.prie.admin.backend.dto.producto;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmProductoSearchDTO extends PaginacionDTO {
    private String codProducto;
    private String dscProducto;
}
