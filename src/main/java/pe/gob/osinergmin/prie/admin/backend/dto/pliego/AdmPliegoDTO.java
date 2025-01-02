package pe.gob.osinergmin.prie.admin.backend.dto.pliego;

import lombok.Data;
import pe.gob.osinergmin.prie.admin.backend.dto.pliegoSiselec.PliegoSiselecDTO;

import java.util.List;

@Data
public class AdmPliegoDTO {
    private String codPliego;
    private String nomPliego;
    private Integer numSistemasElectricos;
    private List<PliegoSiselecDTO> sistemasElectricos;
}
