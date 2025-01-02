package pe.gob.osinergmin.prie.admin.backend.dto.suministroUsuario;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmSuministroUsuarioSearchDTO extends PaginacionDTO {
    private String codSuministroUsuario;
    private String nombreUsuarioLibre;
    private String codUsuarioLibre;
}
