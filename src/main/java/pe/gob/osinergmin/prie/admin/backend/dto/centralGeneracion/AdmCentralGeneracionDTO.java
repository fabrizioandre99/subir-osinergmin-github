package pe.gob.osinergmin.prie.admin.backend.dto.centralGeneracion;

import lombok.Data;
import pe.gob.osinergmin.prie.admin.backend.dto.grupoGeneracion.AdmGrupoGeneracionDTO;

import java.util.List;

@Data
public class AdmCentralGeneracionDTO {
    private String codCentralGeneracion;
    private String nomCentralGeneracion;
    private String codTipoCentral;
    private String nomTipoCentral;
    private String codEmpresa;
    private String nomEmpresa;
    private String codUbigeo;
    private String nomDistrito;
    private String estado;
    private String tipo;
    private String codProvincia;
    private String nomProvincia;
    private String codDepartamento;
    private String nomDepartamento;

    private List<AdmGrupoGeneracionDTO> grupoGeneracion;
}
