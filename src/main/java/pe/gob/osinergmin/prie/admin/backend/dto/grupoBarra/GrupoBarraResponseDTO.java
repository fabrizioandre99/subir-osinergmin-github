package pe.gob.osinergmin.prie.admin.backend.dto.grupoBarra;

import lombok.Data;


import java.util.List;

@Data
public class GrupoBarraResponseDTO {
    private String codigo;
    private String estado;
    private List<BarraDTO> barras;

}
