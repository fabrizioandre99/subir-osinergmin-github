package pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion;

import lombok.Data;

@Data
public class AdmGrupoGeneracionFormDTO {
    private String codGrupoGeneracion;
    private String nomGrupoGeneracion;
    private String codTipoCombustible;
    private String codCentralGeneracion;
    private String estado;
}
