package pe.gob.osinergmin.prie.admin.backend.dto.modoOperacion;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmModoOperacionSearchDTO extends PaginacionDTO {
    private String codModoOperacion;
    private String dscModoOperacion;
    private String codCoes;
    private String estadoRegistro;
    private String codModoOperacionAnt;

    public AdmModoOperacionSearchDTO() {}
}
