package pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion;

import lombok.Data;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionFormDTO;

import java.util.List;

@Data
public class AdmCentralGeneracionFormdDTO {
    private String codCentralGeneracion;
    private String nomCentralGeneracion;
    private String codTipoCentral;
    private String codEmpresa;
    private String codUbigeo;
    private String estado;
    private String tipo;

    private List<AdmGrupoGeneracionFormDTO> grupoGeneracion;
}
