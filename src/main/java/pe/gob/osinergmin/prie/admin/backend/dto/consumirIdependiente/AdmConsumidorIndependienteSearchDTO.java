package pe.gob.osinergmin.prie.admin.backend.dto.consumirIdependiente;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import pe.gob.osinergmin.prie.admin.backend.dto.PaginacionDTO;

@Setter
@Getter
public class AdmConsumidorIndependienteSearchDTO extends PaginacionDTO {
    private String codConsumidorInde;
    private String razonSocial;
}
