package pe.gob.osinergmin.prie.admin.backend.dto.subestacion;

import lombok.Data;
import pe.gob.osinergmin.prie.admin.backend.dto.barra.AdmBarraListadoDTO;

import java.util.List;

@Data
public class AdmSubestacionListadoDTO {
    private String codSubestacion;
    private String nomSubestacion;
    private String nomDepartamento;
    private String nomProvincia;
    private String nomDistrito;
    private String estado;
    private Integer idSisTrans;
    private List<AdmBarraListadoDTO> barra;
}
