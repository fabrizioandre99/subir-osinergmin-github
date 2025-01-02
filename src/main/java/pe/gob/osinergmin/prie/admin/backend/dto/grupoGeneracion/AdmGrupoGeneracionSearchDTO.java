package pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion;

import lombok.Getter;
import lombok.Setter;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Getter
@Setter
public class AdmGrupoGeneracionSearchDTO extends PaginacionDTO {

    private String codGrupoGeneracion;
    private String nomGrupoGeneracion;
    private String codCentralGeneracion;
    private String estado;

    public AdmGrupoGeneracionSearchDTO(String codGrupoGeneracion, String nomGrupoGeneracion, String codCentralGeneracion, String estado) {
        this.codGrupoGeneracion = codGrupoGeneracion;
        this.nomGrupoGeneracion = nomGrupoGeneracion;
        this.codCentralGeneracion = codCentralGeneracion;
        this.estado = estado;
    }

    public AdmGrupoGeneracionSearchDTO() {
    }
}
