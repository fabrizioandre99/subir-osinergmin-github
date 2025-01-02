package pe.gob.osinergmin.prie.admin.backend.dto.pliego;

import lombok.Data;
import pe.gob.osinergmin.prie.admin.backend.dto.pliegoSiselec.PliegoSiselecFormDTO;

import java.util.List;

@Data
public class PliegoActualizarDTO {
    private String codPliego;
    private String nomPliego;
    private List<PliegoSiselecFormDTO> sistemaElectronico;
}
