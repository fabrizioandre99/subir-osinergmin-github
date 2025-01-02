package pe.gob.osinergmin.prie.admin.backend.dto.usuarioLibre;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmUsuarioLibreSearchDTO extends PaginacionDTO {
    private String codUsuarioLibre;
    private String razonSocial;
}
