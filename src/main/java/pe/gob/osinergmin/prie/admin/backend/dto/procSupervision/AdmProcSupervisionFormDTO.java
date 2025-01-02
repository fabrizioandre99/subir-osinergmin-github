package pe.gob.osinergmin.prie.admin.backend.dto.procSupervision;

import lombok.Data;
import pe.gob.osinergmin.prie.admin.backend.dto.funcionProcSuperv.FuncionFormdDTO;

import java.util.List;

@Data
public class AdmProcSupervisionFormDTO {
    private String codProcSupervision;
    private String nomProcSupervision;
    private String abrevProcSupervision;
    private String coModulo;
    private String estado;
    private List<FuncionFormdDTO> funciones;
}
